-- 10 seed rows for AttendeeManagement core.
INSERT INTO attendeemanagement_comp (attendeeid, phonenumber, email, objectname, modulesequence)
SELECT
  500000 + n,
  '08120000' || lpad(n::text, 2, '0'),
  'seed-attendee-' || n || '@example.test',
  'Event.attendeemanagement.core.model.AttendeeManagementComponent',
  'attendeemanagement_impl'
FROM generate_series(1, 10) AS n
ON CONFLICT (attendeeid) DO NOTHING;

INSERT INTO attendeemanagement_impl (attendeeid)
SELECT 500000 + n
FROM generate_series(1, 10) AS n
ON CONFLICT (attendeeid) DO NOTHING;
