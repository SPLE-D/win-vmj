-- 10 seed rows for Notification core.
INSERT INTO notification_comp (notifiationid, content, objectname, modulesequence)
SELECT
  600000 + n,
  'Seed Core Notification ' || n,
  'Event.notification.core.model.NotificationComponent',
  'notification_impl'
FROM generate_series(1, 10) AS n
ON CONFLICT (notifiationid) DO NOTHING;

INSERT INTO notification_impl (notifiationid)
SELECT 600000 + n
FROM generate_series(1, 10) AS n
ON CONFLICT (notifiationid) DO NOTHING;
