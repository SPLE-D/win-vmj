-- 10 seed rows for AttendeeManagement core.
INSERT INTO attendeemanagement_comp (attendeeid, phonenumber, email, objectname, modulesequence)
SELECT
  500000 + n,
  CASE n
    WHEN 1 THEN '081234567890'
    WHEN 2 THEN '081398765432'
    WHEN 3 THEN '082122334455'
    WHEN 4 THEN '085611223344'
    WHEN 5 THEN '087855667788'
    WHEN 6 THEN '081199887766'
    WHEN 7 THEN '081944332211'
    WHEN 8 THEN '082255667711'
    WHEN 9 THEN '083811224499'
    ELSE '085299881122'
  END,
  CASE n
    WHEN 1 THEN 'budi.santoso@gmail.com'
    WHEN 2 THEN 'siti.aminah@yahoo.com'
    WHEN 3 THEN 'andi.wijaya@outlook.com'
    WHEN 4 THEN 'dewi.lestari@event.test'
    WHEN 5 THEN 'eko.prasetyo@gmail.com'
    WHEN 6 THEN 'mega.purnama@ui.ac.id'
    WHEN 7 THEN 'rizky.hidayat@event.test'
    WHEN 8 THEN 'lani.surya@gmail.com'
    WHEN 9 THEN 'hendra.kusuma@gmail.com'
    ELSE 'anisa.fitria@event.test'
  END,
  'Event.attendeemanagement.core.model.AttendeeManagementComponent',
  'attendeemanagement_impl'
FROM generate_series(1, 10) AS n
ON CONFLICT (attendeeid) DO NOTHING;

INSERT INTO attendeemanagement_impl (attendeeid)
SELECT 500000 + n
FROM generate_series(1, 10) AS n
ON CONFLICT (attendeeid) DO NOTHING;
