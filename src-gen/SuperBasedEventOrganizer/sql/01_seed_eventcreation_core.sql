-- 10 seed rows for EventCreation core.
INSERT INTO eventcreation_comp (eventid, capacity, startdate, enddate, name, location, objectname, modulesequence)
SELECT
  100000 + n,
  CASE n
    WHEN 1 THEN 500
    WHEN 2 THEN 3000
    WHEN 3 THEN 150
    WHEN 4 THEN 200
    WHEN 5 THEN 80
    WHEN 6 THEN 100
    WHEN 7 THEN 400
    WHEN 8 THEN 1200
    WHEN 9 THEN 2500
    ELSE 600
  END,
  TIMESTAMP '2026-07-01 09:00:00' + (n || ' days')::interval,
  TIMESTAMP '2026-07-01 17:00:00' + (n || ' days')::interval,
  CASE n
    WHEN 1 THEN 'Tech Innovators Summit 2026'
    WHEN 2 THEN 'Jakarta Jazz Festival'
    WHEN 3 THEN 'Comic Con Indonesia'
    WHEN 4 THEN 'AI & Deep Learning Workshop'
    WHEN 5 THEN 'International Career Expo'
    WHEN 6 THEN 'Green Energy Seminar'
    WHEN 7 THEN 'National eSports Championship'
    WHEN 8 THEN 'Food & Culinary Festival'
    WHEN 9 THEN 'Corporate Startup Pitch Night'
    ELSE 'Charity Symphony Concert'
  END,
  CASE n
    WHEN 1 THEN 'JIExpo Kemayoran Hall B'
    WHEN 2 THEN 'Gelora Bung Karno Park'
    WHEN 3 THEN 'Jakarta Convention Center'
    WHEN 4 THEN 'Balai Kartini Hall'
    WHEN 5 THEN 'Fasilkom UI Auditorium'
    WHEN 6 THEN 'Grand Hyatt Ballroom'
    WHEN 7 THEN 'BritAma Arena'
    WHEN 8 THEN 'Museum Nasional Courtyard'
    WHEN 9 THEN 'Sopo Del Tower Auditorium'
    ELSE 'Taman Ismail Marzuki Concert Hall'
  END,
  'Event.eventcreation.core.model.EventCreationComponent',
  'eventcreation_impl'
FROM generate_series(1, 10) AS n
ON CONFLICT (eventid) DO NOTHING;

INSERT INTO eventcreation_impl (eventid)
SELECT 100000 + n
FROM generate_series(1, 10) AS n
ON CONFLICT (eventid) DO NOTHING;
