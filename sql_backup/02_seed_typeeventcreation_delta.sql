-- 10 seed rows for TypeEventCreation delta.
INSERT INTO eventcreation_comp (eventid, capacity, startdate, enddate, name, location, objectname, modulesequence)
SELECT
  101000 + n,
  70 + n,
  TIMESTAMP '2026-08-01 09:00:00' + (n || ' days')::interval,
  TIMESTAMP '2026-08-01 17:00:00' + (n || ' days')::interval,
  'Seed Type Event Base ' || n,
  'Type Base Venue ' || n,
  'Event.eventcreation.core.model.EventCreationComponent',
  'eventcreation_impl,eventcreation_typeeventcreation'
FROM generate_series(1, 10) AS n
ON CONFLICT (eventid) DO NOTHING;

INSERT INTO eventcreation_impl (eventid)
SELECT 101000 + n
FROM generate_series(1, 10) AS n
ON CONFLICT (eventid) DO NOTHING;

INSERT INTO eventcreation_comp (eventid, capacity, startdate, enddate, name, location, objectname, modulesequence)
SELECT
  101100 + n,
  70 + n,
  TIMESTAMP '2026-08-01 09:00:00' + (n || ' days')::interval,
  TIMESTAMP '2026-08-01 17:00:00' + (n || ' days')::interval,
  'Seed Type Event Delta ' || n,
  'Type Delta Venue ' || n,
  'Event.eventcreation.typeeventcreation.model.EventCreationImpl',
  'eventcreation_typeeventcreation'
FROM generate_series(1, 10) AS n
ON CONFLICT (eventid) DO NOTHING;

INSERT INTO eventcreation_typeeventcreation (eventid, eventtype, record_eventid, recordname, base_component_id)
SELECT
  101100 + n,
  CASE WHEN n % 2 = 0 THEN 'PRIVATE' ELSE 'PUBLIC' END,
  101000 + n,
  'Event.eventcreation.core.model.EventCreationImpl',
  101000 + n
FROM generate_series(1, 10) AS n
ON CONFLICT (eventid) DO NOTHING;
