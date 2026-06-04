-- 10 seed rows for TargetedNotification delta.
INSERT INTO notification_comp (notifiationid, content, objectname, modulesequence)
SELECT
  601000 + n,
  CASE n
    WHEN 1 THEN 'Undangan VIP Khusus: Makan malam bersama pembicara eksekutif.'
    WHEN 2 THEN 'Pemberitahuan Khusus: Akses lounge eksekutif di Pullman Hotel.'
    WHEN 3 THEN 'Informasi VIP: Penukaran merchandise gratis di Senayan Park.'
    WHEN 4 THEN 'Undangan Khusus: Sesi tanya jawab tertutup dengan founder startup.'
    WHEN 5 THEN 'Info VIP: Jalur masuk cepat (fast-track) di Smesco Hall.'
    WHEN 6 THEN 'Notifikasi Peserta: Update katalog produk Craft Expo.'
    WHEN 7 THEN 'Konfirmasi Reservasi: Meja makan khusus VIP di Plataran.'
    WHEN 8 THEN 'Pengumuman Peserta: Link unduh handout masterclass marketing.'
    WHEN 9 THEN 'Pemberitahuan Developer: Sesi diskusi panel Java GC di Lab 3.'
    ELSE 'Notifikasi Donatur: Laporan dampak donasi lelang seni.'
  END,
  'Event.notification.core.model.NotificationComponent',
  'notification_impl,notification_targetednotification'
FROM generate_series(1, 10) AS n
ON CONFLICT (notifiationid) DO NOTHING;

INSERT INTO notification_impl (notifiationid)
SELECT 601000 + n
FROM generate_series(1, 10) AS n
ON CONFLICT (notifiationid) DO NOTHING;

INSERT INTO notification_comp (notifiationid, content, objectname, modulesequence)
SELECT
  601100 + n,
  CASE n
    WHEN 1 THEN 'Undangan VIP Khusus: Makan malam bersama pembicara eksekutif.'
    WHEN 2 THEN 'Pemberitahuan Khusus: Akses lounge eksekutif di Pullman Hotel.'
    WHEN 3 THEN 'Informasi VIP: Penukaran merchandise gratis di Senayan Park.'
    WHEN 4 THEN 'Undangan Khusus: Sesi tanya jawab tertutup dengan founder startup.'
    WHEN 5 THEN 'Info VIP: Jalur masuk cepat (fast-track) di Smesco Hall.'
    WHEN 6 THEN 'Notifikasi Peserta: Update katalog produk Craft Expo.'
    WHEN 7 THEN 'Konfirmasi Reservasi: Meja makan khusus VIP di Plataran.'
    WHEN 8 THEN 'Pengumuman Peserta: Link unduh handout masterclass marketing.'
    WHEN 9 THEN 'Pemberitahuan Developer: Sesi diskusi panel Java GC di Lab 3.'
    ELSE 'Notifikasi Donatur: Laporan dampak donasi lelang seni.'
  END,
  'Event.notification.targetednotification.model.NotificationImpl',
  'notification_targetednotification'
FROM generate_series(1, 10) AS n
ON CONFLICT (notifiationid) DO NOTHING;

INSERT INTO notification_targetednotification (notifiationid, target, record_notifiationid, recordname, base_component_id)
SELECT
  601100 + n,
  501100 + n, -- Point to classattendeemanagement delta attendee id!
  601000 + n,
  'Event.notification.core.model.NotificationImpl',
  601000 + n
FROM generate_series(1, 10) AS n
ON CONFLICT (notifiationid) DO NOTHING;
