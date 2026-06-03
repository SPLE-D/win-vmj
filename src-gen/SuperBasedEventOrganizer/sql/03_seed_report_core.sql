-- 10 seed rows for Report core.
INSERT INTO report_comp (reportid, eventid, totalattendee, totalrevenue, summary, objectname, modulesequence)
SELECT
  200000 + n,
  100000 + n,
  100 + n,
  1000000 + (n * 10000),
  'Seed Core Report ' || n,
  'Event.report.core.model.ReportComponent',
  'report_impl'
FROM generate_series(1, 10) AS n
ON CONFLICT (reportid) DO NOTHING;

INSERT INTO report_impl (reportid)
SELECT 200000 + n
FROM generate_series(1, 10) AS n
ON CONFLICT (reportid) DO NOTHING;
