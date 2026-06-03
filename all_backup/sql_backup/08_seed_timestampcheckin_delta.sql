-- 10 seed rows for TimeStampCheckIn delta.
INSERT INTO checkin_comp (checkinid, attended, objectname, modulesequence)
SELECT
  401000 + n,
  n % 2 = 0,
  'Event.checkin.core.model.CheckInComponent',
  'checkin_impl,checkin_timestampcheckin'
FROM generate_series(1, 10) AS n
ON CONFLICT (checkinid) DO NOTHING;

INSERT INTO checkin_impl (checkinid)
SELECT 401000 + n
FROM generate_series(1, 10) AS n
ON CONFLICT (checkinid) DO NOTHING;

INSERT INTO checkin_comp (checkinid, attended, objectname, modulesequence)
SELECT
  401100 + n,
  n % 2 = 0,
  'Event.checkin.timestampcheckin.model.CheckInImpl',
  'checkin_timestampcheckin'
FROM generate_series(1, 10) AS n
ON CONFLICT (checkinid) DO NOTHING;

INSERT INTO checkin_timestampcheckin (checkinid, "timestamp", record_checkinid, recordname, base_component_id)
SELECT
  401100 + n,
  TIMESTAMP '2026-09-01 10:00:00' + (n || ' hours')::interval,
  401000 + n,
  'Event.checkin.core.model.CheckInImpl',
  401000 + n
FROM generate_series(1, 10) AS n
ON CONFLICT (checkinid) DO NOTHING;
