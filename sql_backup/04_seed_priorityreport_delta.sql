-- 10 seed rows for PriorityReport delta.
INSERT INTO report_comp (reportid, eventid, totalattendee, totalrevenue, summary, objectname, modulesequence)
SELECT
  201000 + n,
  101000 + n, -- Point to typeeventcreation base event id
  CASE n
    WHEN 1 THEN 90
    WHEN 2 THEN 40
    WHEN 3 THEN 2100
    WHEN 4 THEN 65
    WHEN 5 THEN 850
    WHEN 6 THEN 1300
    WHEN 7 THEN 25
    WHEN 8 THEN 180
    WHEN 9 THEN 400
    ELSE 85
  END,
  CASE n
    WHEN 1 THEN 27000000
    WHEN 2 THEN 12000000
    WHEN 3 THEN 630000000
    WHEN 4 THEN 19500000
    WHEN 5 THEN 255000000
    WHEN 6 THEN 390000000
    WHEN 7 THEN 7500000
    WHEN 8 THEN 54000000
    WHEN 9 THEN 120000000
    ELSE 25500000
  END,
  CASE n
    WHEN 1 THEN 'Laporan Keuangan Eksekutif [Base]'
    WHEN 2 THEN 'Laporan Pertemuan Direksi [Base]'
    WHEN 3 THEN 'Laporan Tiket Konser Indie [Base]'
    WHEN 4 THEN 'Laporan VIP Startup Networking [Base]'
    WHEN 5 THEN 'Laporan Evaluasi Education Fair [Base]'
    WHEN 6 THEN 'Laporan Penjualan Tenant Craft Expo [Base]'
    WHEN 7 THEN 'Laporan Keuangan Culinary Dinner [Base]'
    WHEN 8 THEN 'Laporan Kepuasan Masterclass Marketing [Base]'
    WHEN 9 THEN 'Laporan Kehadiran Developer Meetup [Base]'
    ELSE 'Laporan Donasi Art Auction [Base]'
  END,
  'Event.report.core.model.ReportComponent',
  'report_impl,report_priorityreport'
FROM generate_series(1, 10) AS n
ON CONFLICT (reportid) DO NOTHING;

INSERT INTO report_impl (reportid)
SELECT 201000 + n
FROM generate_series(1, 10) AS n
ON CONFLICT (reportid) DO NOTHING;

INSERT INTO report_comp (reportid, eventid, totalattendee, totalrevenue, summary, objectname, modulesequence)
SELECT
  201100 + n,
  101100 + n, -- Point to typeeventcreation delta event id
  CASE n
    WHEN 1 THEN 90
    WHEN 2 THEN 40
    WHEN 3 THEN 2100
    WHEN 4 THEN 65
    WHEN 5 THEN 850
    WHEN 6 THEN 1300
    WHEN 7 THEN 25
    WHEN 8 THEN 180
    WHEN 9 THEN 400
    ELSE 85
  END,
  CASE n
    WHEN 1 THEN 27000000
    WHEN 2 THEN 12000000
    WHEN 3 THEN 630000000
    WHEN 4 THEN 19500000
    WHEN 5 THEN 255000000
    WHEN 6 THEN 390000000
    WHEN 7 THEN 7500000
    WHEN 8 THEN 54000000
    WHEN 9 THEN 120000000
    ELSE 25500000
  END,
  CASE n
    WHEN 1 THEN 'Laporan Keuangan Eksekutif [Priority]'
    WHEN 2 THEN 'Laporan Pertemuan Direksi [Priority]'
    WHEN 3 THEN 'Laporan Tiket Konser Indie [Priority]'
    WHEN 4 THEN 'Laporan VIP Startup Networking [Priority]'
    WHEN 5 THEN 'Laporan Evaluasi Education Fair [Priority]'
    WHEN 6 THEN 'Laporan Penjualan Tenant Craft Expo [Priority]'
    WHEN 7 THEN 'Laporan Keuangan Culinary Dinner [Priority]'
    WHEN 8 THEN 'Laporan Kepuasan Masterclass Marketing [Priority]'
    WHEN 9 THEN 'Laporan Kehadiran Developer Meetup [Priority]'
    ELSE 'Laporan Donasi Art Auction [Priority]'
  END,
  'Event.report.priorityreport.model.ReportImpl',
  'report_priorityreport'
FROM generate_series(1, 10) AS n
ON CONFLICT (reportid) DO NOTHING;

INSERT INTO report_priorityreport (reportid, priorityreport, record_reportid, recordname, base_component_id)
SELECT
  201100 + n,
  CASE WHEN n IN (1, 2, 4, 7, 10) THEN 'CRITICAL' ELSE 'HIGH' END,
  201000 + n,
  'Event.report.core.model.ReportImpl',
  201000 + n
FROM generate_series(1, 10) AS n
ON CONFLICT (reportid) DO NOTHING;
