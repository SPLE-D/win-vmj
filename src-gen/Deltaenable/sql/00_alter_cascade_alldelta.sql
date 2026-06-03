-- ====================================================
-- Alldelta cascade FK repair
-- ====================================================
-- Run after Hibernate creates/updates the schema.

CREATE OR REPLACE FUNCTION add_fk_cascade_if_column_exists(
  target_table text,
  target_column text,
  referenced_table text,
  referenced_column text,
  constraint_name text
) RETURNS void AS $$
DECLARE
  constraint_record record;
  column_exists boolean;
BEGIN
  SELECT EXISTS (
    SELECT 1
    FROM information_schema.columns
    WHERE table_schema = current_schema()
      AND table_name = target_table
      AND column_name = target_column
  ) INTO column_exists;

  IF NOT column_exists THEN
    RAISE NOTICE 'Skipping %.% because the column does not exist', target_table, target_column;
    RETURN;
  END IF;

  FOR constraint_record IN
    SELECT con.conname
    FROM pg_constraint con
    JOIN pg_class rel ON rel.oid = con.conrelid
    JOIN pg_namespace nsp ON nsp.oid = rel.relnamespace
    JOIN unnest(con.conkey) WITH ORDINALITY AS cols(attnum, ord) ON true
    JOIN pg_attribute attr ON attr.attrelid = rel.oid AND attr.attnum = cols.attnum
    WHERE con.contype = 'f'
      AND nsp.nspname = current_schema()
      AND rel.relname = target_table
      AND attr.attname = target_column
  LOOP
    EXECUTE format('ALTER TABLE %I DROP CONSTRAINT IF EXISTS %I', target_table, constraint_record.conname);
  END LOOP;

  EXECUTE format(
    'ALTER TABLE %I ADD CONSTRAINT %I FOREIGN KEY (%I) REFERENCES %I(%I) ON DELETE CASCADE',
    target_table,
    constraint_name,
    target_column,
    referenced_table,
    referenced_column
  );
END;
$$ LANGUAGE plpgsql;

SELECT add_fk_cascade_if_column_exists('eventcreation_impl', 'eventid', 'eventcreation_comp', 'eventid', 'fk_eventcreation_impl_comp_cascade');
SELECT add_fk_cascade_if_column_exists('report_impl', 'reportid', 'report_comp', 'reportid', 'fk_report_impl_comp_cascade');
SELECT add_fk_cascade_if_column_exists('review_impl', 'reviewid', 'review_comp', 'reviewid', 'fk_review_impl_comp_cascade');
SELECT add_fk_cascade_if_column_exists('checkin_impl', 'checkinid', 'checkin_comp', 'checkinid', 'fk_checkin_impl_comp_cascade');
SELECT add_fk_cascade_if_column_exists('attendeemanagement_impl', 'attendeeid', 'attendeemanagement_comp', 'attendeeid', 'fk_attendeemanagement_impl_comp_cascade');
SELECT add_fk_cascade_if_column_exists('notification_impl', 'notifiationid', 'notification_comp', 'notifiationid', 'fk_notification_impl_comp_cascade');

SELECT add_fk_cascade_if_column_exists('eventcreation_typeeventcreation', 'eventid', 'eventcreation_comp', 'eventid', 'fk_eventcreation_type_comp_cascade');
SELECT add_fk_cascade_if_column_exists('eventcreation_typeeventcreation', 'record_eventid', 'eventcreation_comp', 'eventid', 'fk_eventcreation_type_record_cascade');
SELECT add_fk_cascade_if_column_exists('eventcreation_typeeventcreation', 'base_component_id', 'eventcreation_comp', 'eventid', 'fk_eventcreation_type_base_cascade');

SELECT add_fk_cascade_if_column_exists('report_priorityreport', 'reportid', 'report_comp', 'reportid', 'fk_report_priority_comp_cascade');
SELECT add_fk_cascade_if_column_exists('report_priorityreport', 'record_reportid', 'report_comp', 'reportid', 'fk_report_priority_record_cascade');
SELECT add_fk_cascade_if_column_exists('report_priorityreport', 'base_component_id', 'report_comp', 'reportid', 'fk_report_priority_base_cascade');

SELECT add_fk_cascade_if_column_exists('review_reviewanonymous', 'reviewid', 'review_comp', 'reviewid', 'fk_review_anonymous_comp_cascade');
SELECT add_fk_cascade_if_column_exists('review_reviewanonymous', 'record_reviewid', 'review_comp', 'reviewid', 'fk_review_anonymous_record_cascade');
SELECT add_fk_cascade_if_column_exists('review_reviewanonymous', 'base_component_id', 'review_comp', 'reviewid', 'fk_review_anonymous_base_cascade');

SELECT add_fk_cascade_if_column_exists('checkin_timestampcheckin', 'checkinid', 'checkin_comp', 'checkinid', 'fk_checkin_timestamp_comp_cascade');
SELECT add_fk_cascade_if_column_exists('checkin_timestampcheckin', 'record_checkinid', 'checkin_comp', 'checkinid', 'fk_checkin_timestamp_record_cascade');
SELECT add_fk_cascade_if_column_exists('checkin_timestampcheckin', 'base_component_id', 'checkin_comp', 'checkinid', 'fk_checkin_timestamp_base_cascade');

SELECT add_fk_cascade_if_column_exists('attendeemanagement_classattendeemanagement', 'attendeeid', 'attendeemanagement_comp', 'attendeeid', 'fk_attendeemanagement_class_comp_cascade');
SELECT add_fk_cascade_if_column_exists('attendeemanagement_classattendeemanagement', 'record_attendeeid', 'attendeemanagement_comp', 'attendeeid', 'fk_attendeemanagement_class_record_cascade');
SELECT add_fk_cascade_if_column_exists('attendeemanagement_classattendeemanagement', 'base_component_id', 'attendeemanagement_comp', 'attendeeid', 'fk_attendeemanagement_class_base_cascade');

SELECT add_fk_cascade_if_column_exists('notification_targetednotification', 'notifiationid', 'notification_comp', 'notifiationid', 'fk_notification_targeted_comp_cascade');
SELECT add_fk_cascade_if_column_exists('notification_targetednotification', 'record_notifiationid', 'notification_comp', 'notifiationid', 'fk_notification_targeted_record_cascade');
SELECT add_fk_cascade_if_column_exists('notification_targetednotification', 'base_component_id', 'notification_comp', 'notifiationid', 'fk_notification_targeted_base_cascade');

DROP FUNCTION IF EXISTS add_fk_cascade_if_column_exists(text, text, text, text, text);
