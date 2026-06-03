-- ====================================================
-- Product-line safe schema repair
-- ====================================================
-- Safe for enabled/disabled features.
-- This script only modifies tables/columns that exist.

-- ====================================================
-- Support columns repair
-- ====================================================

CREATE OR REPLACE FUNCTION add_support_columns_if_table_exists(
  target_table text
) RETURNS void AS $$
DECLARE
  table_exists boolean;
BEGIN
  SELECT EXISTS (
    SELECT 1
    FROM information_schema.tables
    WHERE table_schema = current_schema()
      AND table_name = target_table
  ) INTO table_exists;

  IF NOT table_exists THEN
    RAISE NOTICE 'Skipping % because the table does not exist', target_table;
    RETURN;
  END IF;

  EXECUTE format(
    'ALTER TABLE %I ADD COLUMN IF NOT EXISTS modulesequence text',
    target_table
  );

  EXECUTE format(
    'ALTER TABLE %I ADD COLUMN IF NOT EXISTS objectname text',
    target_table
  );

  RAISE NOTICE 'Ensured support columns on %', target_table;
END;
$$ LANGUAGE plpgsql;


-- Core component tables
SELECT add_support_columns_if_table_exists('eventcreation_comp');
SELECT add_support_columns_if_table_exists('report_comp');
SELECT add_support_columns_if_table_exists('review_comp');
SELECT add_support_columns_if_table_exists('checkin_comp');
SELECT add_support_columns_if_table_exists('attendeemanagement_comp');
SELECT add_support_columns_if_table_exists('notification_comp');

DROP FUNCTION IF EXISTS add_support_columns_if_table_exists(text);


-- ====================================================
-- Product-line safe FK cascade repair
-- ====================================================
-- Only adds FK if both tables and both columns exist.

CREATE OR REPLACE FUNCTION add_fk_cascade_if_columns_exist(
  target_table text,
  target_column text,
  referenced_table text,
  referenced_column text,
  constraint_name text
) RETURNS void AS $$
DECLARE
  constraint_record record;
  target_column_exists boolean;
  referenced_column_exists boolean;
BEGIN
  SELECT EXISTS (
    SELECT 1
    FROM information_schema.columns
    WHERE table_schema = current_schema()
      AND table_name = target_table
      AND column_name = target_column
  ) INTO target_column_exists;

  IF NOT target_column_exists THEN
    RAISE NOTICE 'Skipping %.% because the target column does not exist', target_table, target_column;
    RETURN;
  END IF;

  SELECT EXISTS (
    SELECT 1
    FROM information_schema.columns
    WHERE table_schema = current_schema()
      AND table_name = referenced_table
      AND column_name = referenced_column
  ) INTO referenced_column_exists;

  IF NOT referenced_column_exists THEN
    RAISE NOTICE 'Skipping %.% -> %.% because the referenced column does not exist',
      target_table,
      target_column,
      referenced_table,
      referenced_column;
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
    EXECUTE format(
      'ALTER TABLE %I DROP CONSTRAINT IF EXISTS %I',
      target_table,
      constraint_record.conname
    );
  END LOOP;

  EXECUTE format(
    'ALTER TABLE %I ADD CONSTRAINT %I FOREIGN KEY (%I) REFERENCES %I(%I) ON DELETE CASCADE',
    target_table,
    constraint_name,
    target_column,
    referenced_table,
    referenced_column
  );

  RAISE NOTICE 'Added cascade FK % on %.% -> %.%',
    constraint_name,
    target_table,
    target_column,
    referenced_table,
    referenced_column;
END;
$$ LANGUAGE plpgsql;


-- ====================================================
-- Core implementation FK repair
-- ====================================================

SELECT add_fk_cascade_if_columns_exist(
  'eventcreation_impl',
  'eventid',
  'eventcreation_comp',
  'eventid',
  'fk_eventcreation_impl_comp_cascade'
);

SELECT add_fk_cascade_if_columns_exist(
  'report_impl',
  'reportid',
  'report_comp',
  'reportid',
  'fk_report_impl_comp_cascade'
);

SELECT add_fk_cascade_if_columns_exist(
  'review_impl',
  'reviewid',
  'review_comp',
  'reviewid',
  'fk_review_impl_comp_cascade'
);

SELECT add_fk_cascade_if_columns_exist(
  'checkin_impl',
  'checkinid',
  'checkin_comp',
  'checkinid',
  'fk_checkin_impl_comp_cascade'
);

SELECT add_fk_cascade_if_columns_exist(
  'attendeemanagement_impl',
  'attendeeid',
  'attendeemanagement_comp',
  'attendeeid',
  'fk_attendeemanagement_impl_comp_cascade'
);

SELECT add_fk_cascade_if_columns_exist(
  'notification_impl',
  'notifiationid',
  'notification_comp',
  'notifiationid',
  'fk_notification_impl_comp_cascade'
);


-- ====================================================
-- Delta/decorator FK repair
-- ====================================================

SELECT add_fk_cascade_if_columns_exist(
  'eventcreation_typeeventcreation',
  'eventid',
  'eventcreation_comp',
  'eventid',
  'fk_eventcreation_type_comp_cascade'
);

SELECT add_fk_cascade_if_columns_exist(
  'eventcreation_typeeventcreation',
  'record_eventid',
  'eventcreation_comp',
  'eventid',
  'fk_eventcreation_type_record_cascade'
);

SELECT add_fk_cascade_if_columns_exist(
  'eventcreation_typeeventcreation',
  'base_component_id',
  'eventcreation_comp',
  'eventid',
  'fk_eventcreation_type_base_cascade'
);


SELECT add_fk_cascade_if_columns_exist(
  'report_priorityreport',
  'reportid',
  'report_comp',
  'reportid',
  'fk_report_priority_comp_cascade'
);

SELECT add_fk_cascade_if_columns_exist(
  'report_priorityreport',
  'record_reportid',
  'report_comp',
  'reportid',
  'fk_report_priority_record_cascade'
);

SELECT add_fk_cascade_if_columns_exist(
  'report_priorityreport',
  'base_component_id',
  'report_comp',
  'reportid',
  'fk_report_priority_base_cascade'
);


SELECT add_fk_cascade_if_columns_exist(
  'review_reviewanonymous',
  'reviewid',
  'review_comp',
  'reviewid',
  'fk_review_anonymous_comp_cascade'
);

SELECT add_fk_cascade_if_columns_exist(
  'review_reviewanonymous',
  'record_reviewid',
  'review_comp',
  'reviewid',
  'fk_review_anonymous_record_cascade'
);

SELECT add_fk_cascade_if_columns_exist(
  'review_reviewanonymous',
  'base_component_id',
  'review_comp',
  'reviewid',
  'fk_review_anonymous_base_cascade'
);


SELECT add_fk_cascade_if_columns_exist(
  'checkin_timestampcheckin',
  'checkinid',
  'checkin_comp',
  'checkinid',
  'fk_checkin_timestamp_comp_cascade'
);

SELECT add_fk_cascade_if_columns_exist(
  'checkin_timestampcheckin',
  'record_checkinid',
  'checkin_comp',
  'checkinid',
  'fk_checkin_timestamp_record_cascade'
);

SELECT add_fk_cascade_if_columns_exist(
  'checkin_timestampcheckin',
  'base_component_id',
  'checkin_comp',
  'checkinid',
  'fk_checkin_timestamp_base_cascade'
);


SELECT add_fk_cascade_if_columns_exist(
  'attendeemanagement_classattendeemanagement',
  'attendeeid',
  'attendeemanagement_comp',
  'attendeeid',
  'fk_attendeemanagement_class_comp_cascade'
);

SELECT add_fk_cascade_if_columns_exist(
  'attendeemanagement_classattendeemanagement',
  'record_attendeeid',
  'attendeemanagement_comp',
  'attendeeid',
  'fk_attendeemanagement_class_record_cascade'
);

SELECT add_fk_cascade_if_columns_exist(
  'attendeemanagement_classattendeemanagement',
  'base_component_id',
  'attendeemanagement_comp',
  'attendeeid',
  'fk_attendeemanagement_class_base_cascade'
);


SELECT add_fk_cascade_if_columns_exist(
  'notification_targetednotification',
  'notifiationid',
  'notification_comp',
  'notifiationid',
  'fk_notification_targeted_comp_cascade'
);

SELECT add_fk_cascade_if_columns_exist(
  'notification_targetednotification',
  'record_notifiationid',
  'notification_comp',
  'notifiationid',
  'fk_notification_targeted_record_cascade'
);

SELECT add_fk_cascade_if_columns_exist(
  'notification_targetednotification',
  'base_component_id',
  'notification_comp',
  'notifiationid',
  'fk_notification_targeted_base_cascade'
);

DROP FUNCTION IF EXISTS add_fk_cascade_if_columns_exist(text, text, text, text, text);