-- 10 seed rows for Notification core.
INSERT INTO notification_comp (notifiationid, content, objectname, modulesequence)
SELECT
  600000 + n,
  CASE n
    WHEN 1 THEN 'Halo! Pembayaran tiket Tech Innovators Summit berhasil dikonfirmasi.'
    WHEN 2 THEN 'Jakarta Jazz Festival akan dimulai besok. Siapkan barcode tiket Anda!'
    WHEN 3 THEN 'Informasi parkir & rute alternatif menuju JCC Comic Con.'
    WHEN 4 THEN 'Terima kasih telah berpartisipasi dalam AI & Deep Learning Workshop.'
    WHEN 5 THEN 'Peluang wawancara eksklusif di Career Expo dimulai pukul 10:00.'
    WHEN 6 THEN 'Sertifikat kehadiran Seminar Green Energy sudah dapat diunduh.'
    WHEN 7 THEN 'Jadwal pertandingan National eSports Championship telah diupdate.'
    WHEN 8 THEN 'Gunakan kupon potongan 15% di tenant Culinary Festival.'
    WHEN 9 THEN 'Undangan sesi networking premium malam ini di Sopo Del.'
    ELSE 'Konser Simfoni Amal akan disiarkan secara live lewat link ini.'
  END,
  'Event.notification.core.model.NotificationComponent',
  'notification_impl'
FROM generate_series(1, 10) AS n
ON CONFLICT (notifiationid) DO NOTHING;

INSERT INTO notification_impl (notifiationid)
SELECT 600000 + n
FROM generate_series(1, 10) AS n
ON CONFLICT (notifiationid) DO NOTHING;
