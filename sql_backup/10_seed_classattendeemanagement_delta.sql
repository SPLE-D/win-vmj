-- 10 seed rows for ClassAttendeeManagement delta.
INSERT INTO attendeemanagement_comp (attendeeid, phonenumber, email, objectname, modulesequence)
SELECT
  501000 + n,
  '08220000' || lpad(n::text, 2, '0'),
  'seed-class-attendee-base-' || n || '@example.test',
  'Event.attendeemanagement.core.model.AttendeeManagementComponent',
  'attendeemanagement_impl,attendeemanagement_classattendeemanagement'
FROM generate_series(1, 10) AS n
ON CONFLICT (attendeeid) DO NOTHING;

INSERT INTO attendeemanagement_impl (attendeeid)
SELECT 501000 + n
FROM generate_series(1, 10) AS n
ON CONFLICT (attendeeid) DO NOTHING;

INSERT INTO attendeemanagement_comp (attendeeid, phonenumber, email, objectname, modulesequence)
SELECT
  501100 + n,
  '08330000' || lpad(n::text, 2, '0'),
  'seed-class-attendee-delta-' || n || '@example.test',
  'Event.attendeemanagement.classattendeemanagement.model.AttendeeManagementImpl',
  'attendeemanagement_classattendeemanagement'
FROM generate_series(1, 10) AS n
ON CONFLICT (attendeeid) DO NOTHING;

INSERT INTO attendeemanagement_classattendeemanagement (attendeeid, attendeeclass, record_attendeeid, recordname, base_component_id)
SELECT
  501100 + n,
  CASE WHEN n % 2 = 0 THEN 'VIP' ELSE 'REGULAR' END,
  501000 + n,
  'Event.attendeemanagement.core.model.AttendeeManagementImpl',
  501000 + n
FROM generate_series(1, 10) AS n
ON CONFLICT (attendeeid) DO NOTHING;
