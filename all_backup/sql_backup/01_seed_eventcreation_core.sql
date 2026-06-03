-- 10 seed rows for EventCreation core.
INSERT INTO eventcreation_comp (eventid, capacity, startdate, enddate, name, location, objectname, modulesequence)
SELECT
  100000 + n,
  50 + n,
  TIMESTAMP '2026-07-01 09:00:00' + (n || ' days')::interval,
  TIMESTAMP '2026-07-01 17:00:00' + (n || ' days')::interval,
  'Seed Core Event ' || n,
  'Core Venue ' || n,
  'Event.eventcreation.core.model.EventCreationComponent',
  'eventcreation_impl'
FROM generate_series(1, 10) AS n
ON CONFLICT (eventid) DO NOTHING;

INSERT INTO eventcreation_impl (eventid)
SELECT 100000 + n
FROM generate_series(1, 10) AS n
ON CONFLICT (eventid) DO NOTHING;
