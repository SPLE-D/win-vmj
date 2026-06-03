-- 10 seed rows for TargetedNotification delta.
INSERT INTO notification_comp (notifiationid, content, objectname, modulesequence)
SELECT
  601000 + n,
  'Seed Targeted Notification Base ' || n,
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
  'Seed Targeted Notification Delta ' || n,
  'Event.notification.targetednotification.model.NotificationImpl',
  'notification_targetednotification'
FROM generate_series(1, 10) AS n
ON CONFLICT (notifiationid) DO NOTHING;

INSERT INTO notification_targetednotification (notifiationid, target, record_notifiationid, recordname, base_component_id)
SELECT
  601100 + n,
  700000 + n,
  601000 + n,
  'Event.notification.core.model.NotificationImpl',
  601000 + n
FROM generate_series(1, 10) AS n
ON CONFLICT (notifiationid) DO NOTHING;
