-- 10 seed rows for Report core.
INSERT INTO report_comp (reportid, eventid, totalattendee, totalrevenue, summary, objectname, modulesequence)
SELECT
  200000 + n,
  100000 + n, -- Link to core event
  CASE n
    WHEN 1 THEN 450
    WHEN 2 THEN 2800
    WHEN 3 THEN 130
    WHEN 4 THEN 180
    WHEN 5 THEN 75
    WHEN 6 THEN 95
    WHEN 7 THEN 380
    WHEN 8 THEN 1100
    WHEN 9 THEN 2400
    ELSE 550
  END,
  CASE n
    WHEN 1 THEN 135000000
    WHEN 2 THEN 840000000
    WHEN 3 THEN 39000000
    WHEN 4 THEN 54000000
    WHEN 5 THEN 22500000
    WHEN 6 THEN 28500000
    WHEN 7 THEN 114000000
    WHEN 8 THEN 330000000
    WHEN 9 THEN 720000000
    ELSE 165000000
  END,
  CASE n
    WHEN 1 THEN 'Laporan Keuangan & Evaluasi Tech Innovators Summit 2026'
    WHEN 2 THEN 'Laporan Rekap Pengunjung & Pendapatan Jakarta Jazz Festival'
    WHEN 3 THEN 'Rangkuman Penjualan Tiket & Feedback Comic Con Indonesia'
    WHEN 4 THEN 'Laporan Kehadiran & Kepuasan Peserta AI Workshop'
    WHEN 5 THEN 'Evaluasi Perusahaan & Pengunjung International Career Expo'
    WHEN 6 THEN 'Rangkuman Sponsor & Registrasi Green Energy Seminar'
    WHEN 7 THEN 'Laporan Distribusi Hadiah & Views National eSports Championship'
    WHEN 8 THEN 'Laporan Penjualan Tenant Food & Culinary Festival'
    WHEN 9 THEN 'Rangkuman Pitching & Pendanaan Corporate Startup Pitch Night'
    ELSE 'Laporan Donasi & Keuangan Charity Symphony Concert'
  END,
  'Event.report.core.model.ReportComponent',
  'report_impl'
FROM generate_series(1, 10) AS n
ON CONFLICT (reportid) DO NOTHING;

INSERT INTO report_impl (reportid)
SELECT 200000 + n
FROM generate_series(1, 10) AS n
ON CONFLICT (reportid) DO NOTHING;
