-- 10 seed rows for TypeEventCreation delta.
INSERT INTO eventcreation_comp (eventid, capacity, startdate, enddate, name, location, objectname, modulesequence)
SELECT
  101000 + n,
  CASE n
    WHEN 1 THEN 120
    WHEN 2 THEN 50
    WHEN 3 THEN 2500
    WHEN 4 THEN 80
    WHEN 5 THEN 1000
    WHEN 6 THEN 1500
    WHEN 7 THEN 30
    WHEN 8 THEN 200
    WHEN 9 THEN 450
    ELSE 100
  END,
  TIMESTAMP '2026-08-01 09:00:00' + (n || ' days')::interval,
  TIMESTAMP '2026-08-01 17:00:00' + (n || ' days')::interval,
  CASE n
    WHEN 1 THEN 'Exclusive Executive Coaching'
    WHEN 2 THEN 'Private Board Meeting Seminar'
    WHEN 3 THEN 'Annual Indie Music Showcase'
    WHEN 4 THEN 'VIP Startup Networking Session'
    WHEN 5 THEN 'National Education Fair'
    WHEN 6 THEN 'International Craft Expo'
    WHEN 7 THEN 'High-Table Culinary Dinner'
    WHEN 8 THEN 'Digital Marketing Masterclass'
    WHEN 9 THEN 'Java Developer Meetup'
    ELSE 'Charity Art Auction'
  END,
  CASE n
    WHEN 1 THEN 'Shangri-La Boardroom'
    WHEN 2 THEN 'Pullman Hotel Meeting Room'
    WHEN 3 THEN 'Senayan Park Amphitheater'
    WHEN 4 THEN 'WeWork Auditorium'
    WHEN 5 THEN 'Smesco Convention Hall'
    WHEN 6 THEN 'ICE BSD Hall 5'
    WHEN 7 THEN 'Plataran Menteng'
    WHEN 8 THEN 'GoWork Pacific Place'
    WHEN 9 THEN 'Fasilkom UI Lab 3'
    ELSE 'The Ritz-Carlton Gallery'
  END,
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
  CASE n
    WHEN 1 THEN 120
    WHEN 2 THEN 50
    WHEN 3 THEN 2500
    WHEN 4 THEN 80
    WHEN 5 THEN 1000
    WHEN 6 THEN 1500
    WHEN 7 THEN 30
    WHEN 8 THEN 200
    WHEN 9 THEN 450
    ELSE 100
  END,
  TIMESTAMP '2026-08-01 09:00:00' + (n || ' days')::interval,
  TIMESTAMP '2026-08-01 17:00:00' + (n || ' days')::interval,
  CASE n
    WHEN 1 THEN 'Exclusive Executive Coaching [Private]'
    WHEN 2 THEN 'Private Board Meeting Seminar [Private]'
    WHEN 3 THEN 'Annual Indie Music Showcase [Public]'
    WHEN 4 THEN 'VIP Startup Networking Session [Private]'
    WHEN 5 THEN 'National Education Fair [Public]'
    WHEN 6 THEN 'International Craft Expo [Public]'
    WHEN 7 THEN 'High-Table Culinary Dinner [Private]'
    WHEN 8 THEN 'Digital Marketing Masterclass [Public]'
    WHEN 9 THEN 'Java Developer Meetup [Public]'
    ELSE 'Charity Art Auction [Private]'
  END,
  CASE n
    WHEN 1 THEN 'Shangri-La Boardroom'
    WHEN 2 THEN 'Pullman Hotel Meeting Room'
    WHEN 3 THEN 'Senayan Park Amphitheater'
    WHEN 4 THEN 'WeWork Auditorium'
    WHEN 5 THEN 'Smesco Convention Hall'
    WHEN 6 THEN 'ICE BSD Hall 5'
    WHEN 7 THEN 'Plataran Menteng'
    WHEN 8 THEN 'GoWork Pacific Place'
    WHEN 9 THEN 'Fasilkom UI Lab 3'
    ELSE 'The Ritz-Carlton Gallery'
  END,
  'Event.eventcreation.typeeventcreation.model.EventCreationImpl',
  'eventcreation_typeeventcreation'
FROM generate_series(1, 10) AS n
ON CONFLICT (eventid) DO NOTHING;

INSERT INTO eventcreation_typeeventcreation (eventid, eventtype, record_eventid, recordname, base_component_id)
SELECT
  101100 + n,
  CASE WHEN n IN (1, 2, 4, 7, 10) THEN 'PRIVATE' ELSE 'PUBLIC' END,
  101000 + n,
  'Event.eventcreation.core.model.EventCreationImpl',
  101000 + n
FROM generate_series(1, 10) AS n
ON CONFLICT (eventid) DO NOTHING;
