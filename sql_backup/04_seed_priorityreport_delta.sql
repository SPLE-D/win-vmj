-- 10 seed rows for PriorityReport delta.
INSERT INTO report_comp (reportid, eventid, totalattendee, totalrevenue, summary, objectname, modulesequence)
SELECT
  201000 + n,
  100000 + n,
  200 + n,
  2000000 + (n * 10000),
  'Seed Priority Report Base ' || n,
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
  100000 + n,
  200 + n,
  2000000 + (n * 10000),
  'Seed Priority Report Delta ' || n,
  'Event.report.priorityreport.model.ReportImpl',
  'report_priorityreport'
FROM generate_series(1, 10) AS n
ON CONFLICT (reportid) DO NOTHING;

INSERT INTO report_priorityreport (reportid, priorityreport, record_reportid, recordname, base_component_id)
SELECT
  201100 + n,
  CASE WHEN n % 2 = 0 THEN 'CRITICAL' ELSE 'HIGH' END,
  201000 + n,
  'Event.report.core.model.ReportImpl',
  201000 + n
FROM generate_series(1, 10) AS n
ON CONFLICT (reportid) DO NOTHING;
