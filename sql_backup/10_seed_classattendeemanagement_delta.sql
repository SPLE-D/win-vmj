-- 10 seed rows for ClassAttendeeManagement delta.
INSERT INTO attendeemanagement_comp (attendeeid, phonenumber, email, objectname, modulesequence)
SELECT
  501000 + n,
  CASE n
    WHEN 1 THEN '081211112222'
    WHEN 2 THEN '081322223333'
    WHEN 3 THEN '082133334444'
    WHEN 4 THEN '085644445555'
    WHEN 5 THEN '087855556666'
    WHEN 6 THEN '081166667777'
    WHEN 7 THEN '081977778888'
    WHEN 8 THEN '082288889999'
    WHEN 9 THEN '083899990000'
    ELSE '085200001111'
  END,
  CASE n
    WHEN 1 THEN 'vip.budi@corporation.com'
    WHEN 2 THEN 'ceo.siti@startup.com'
    WHEN 3 THEN 'director.andi@enterprise.com'
    WHEN 4 THEN 'dewi.vip@premium.test'
    WHEN 5 THEN 'eko.vip@business.test'
    WHEN 6 THEN 'lecturer.mega@ui.ac.id'
    WHEN 7 THEN 'rizky.guest@sponsor.com'
    WHEN 8 THEN 'lani.speaker@seminar.com'
    WHEN 9 THEN 'hendra.vip@gmail.com'
    ELSE 'anisa.member@vip.test'
  END,
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
  CASE n
    WHEN 1 THEN '081211112222'
    WHEN 2 THEN '081322223333'
    WHEN 3 THEN '082133334444'
    WHEN 4 THEN '085644445555'
    WHEN 5 THEN '087855556666'
    WHEN 6 THEN '081166667777'
    WHEN 7 THEN '081977778888'
    WHEN 8 THEN '082288889999'
    WHEN 9 THEN '083899990000'
    ELSE '085200001111'
  END,
  CASE n
    WHEN 1 THEN 'vip.budi@corporation.com'
    WHEN 2 THEN 'ceo.siti@startup.com'
    WHEN 3 THEN 'director.andi@enterprise.com'
    WHEN 4 THEN 'dewi.vip@premium.test'
    WHEN 5 THEN 'eko.vip@business.test'
    WHEN 6 THEN 'lecturer.mega@ui.ac.id'
    WHEN 7 THEN 'rizky.guest@sponsor.com'
    WHEN 8 THEN 'lani.speaker@seminar.com'
    WHEN 9 THEN 'hendra.vip@gmail.com'
    ELSE 'anisa.member@vip.test'
  END,
  'Event.attendeemanagement.classattendeemanagement.model.AttendeeManagementImpl',
  'attendeemanagement_classattendeemanagement'
FROM generate_series(1, 10) AS n
ON CONFLICT (attendeeid) DO NOTHING;

INSERT INTO attendeemanagement_classattendeemanagement (attendeeid, attendeeclass, record_attendeeid, recordname, base_component_id)
SELECT
  501100 + n,
  CASE WHEN n IN (1, 2, 3, 5, 8, 9) THEN 'VIP' ELSE 'REGULAR' END,
  501000 + n,
  'Event.attendeemanagement.core.model.AttendeeManagementImpl',
  501000 + n
FROM generate_series(1, 10) AS n
ON CONFLICT (attendeeid) DO NOTHING;
