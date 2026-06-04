-- 10 seed rows for CheckIn core.
INSERT INTO checkin_comp (checkinid, attended, objectname, modulesequence)
SELECT
  400000 + n,
  n % 3 != 0,
  'Event.checkin.core.model.CheckInComponent',
  'checkin_impl'
FROM generate_series(1, 10) AS n
ON CONFLICT (checkinid) DO NOTHING;

INSERT INTO checkin_impl (checkinid)
SELECT 400000 + n
FROM generate_series(1, 10) AS n
ON CONFLICT (checkinid) DO NOTHING;
