-- Seed users and roles for the Fneo event organizer product.

INSERT INTO auth_user_comp (id) VALUES ('11111111-1111-4111-8111-111111111111') ON CONFLICT DO NOTHING;
INSERT INTO auth_user_comp (id) VALUES ('22222222-2222-4222-8222-222222222222') ON CONFLICT DO NOTHING;
INSERT INTO auth_user_comp (id) VALUES ('33333333-3333-4333-8333-333333333333') ON CONFLICT DO NOTHING;
INSERT INTO auth_user_comp (id) VALUES ('44444444-4444-4444-8444-444444444444') ON CONFLICT DO NOTHING;

INSERT INTO auth_user_impl (id, allowedPermissions, name, email)
VALUES ('11111111-1111-4111-8111-111111111111', '', 'Fneo Admin', 'admin@fneo.event.test')
ON CONFLICT DO NOTHING;

INSERT INTO auth_user_impl (id, allowedPermissions, name, email)
VALUES ('22222222-2222-4222-8222-222222222222', '', 'Event Organizer', 'organizer@fneo.event.test')
ON CONFLICT DO NOTHING;

INSERT INTO auth_user_impl (id, allowedPermissions, name, email)
VALUES ('33333333-3333-4333-8333-333333333333', '', 'Check-in Staff', 'checkin.staff@fneo.event.test')
ON CONFLICT DO NOTHING;

INSERT INTO auth_user_impl (id, allowedPermissions, name, email)
VALUES ('44444444-4444-4444-8444-444444444444', '', 'Event Attendee', 'attendee@fneo.event.test')
ON CONFLICT DO NOTHING;

INSERT INTO auth_user_passworded (id, password, record_id)
VALUES (
  '11111111-1111-4111-8111-111111111111',
  '349cbccafc082902f6d88098da92b998129d98c079996b96f305705ffddc67baa935e07353a00b6068e6b0f8e1245ee8d499c80ece5232ad938825cb292bce3b',
  '11111111-1111-4111-8111-111111111111'
) ON CONFLICT DO NOTHING;

INSERT INTO auth_user_passworded (id, password, record_id)
VALUES (
  '22222222-2222-4222-8222-222222222222',
  '349cbccafc082902f6d88098da92b998129d98c079996b96f305705ffddc67baa935e07353a00b6068e6b0f8e1245ee8d499c80ece5232ad938825cb292bce3b',
  '22222222-2222-4222-8222-222222222222'
) ON CONFLICT DO NOTHING;

INSERT INTO auth_user_passworded (id, password, record_id)
VALUES (
  '33333333-3333-4333-8333-333333333333',
  '349cbccafc082902f6d88098da92b998129d98c079996b96f305705ffddc67baa935e07353a00b6068e6b0f8e1245ee8d499c80ece5232ad938825cb292bce3b',
  '33333333-3333-4333-8333-333333333333'
) ON CONFLICT DO NOTHING;

INSERT INTO auth_user_passworded (id, password, record_id)
VALUES (
  '44444444-4444-4444-8444-444444444444',
  '349cbccafc082902f6d88098da92b998129d98c079996b96f305705ffddc67baa935e07353a00b6068e6b0f8e1245ee8d499c80ece5232ad938825cb292bce3b',
  '44444444-4444-4444-8444-444444444444'
) ON CONFLICT DO NOTHING;

INSERT INTO auth_role_comp (id) VALUES ('aaaaaaaa-aaaa-4aaa-8aaa-aaaaaaaaaaaa') ON CONFLICT DO NOTHING;
INSERT INTO auth_role_comp (id) VALUES ('bbbbbbbb-bbbb-4bbb-8bbb-bbbbbbbbbbbb') ON CONFLICT DO NOTHING;
INSERT INTO auth_role_comp (id) VALUES ('cccccccc-cccc-4ccc-8ccc-cccccccccccc') ON CONFLICT DO NOTHING;
INSERT INTO auth_role_comp (id) VALUES ('dddddddd-dddd-4ddd-8ddd-dddddddddddd') ON CONFLICT DO NOTHING;

INSERT INTO auth_role_impl (id, name, allowedPermissions)
VALUES (
  'aaaaaaaa-aaaa-4aaa-8aaa-aaaaaaaaaaaa',
  'Platform Administrator',
  'administrator'
) ON CONFLICT DO NOTHING;

INSERT INTO auth_role_impl (id, name, allowedPermissions)
VALUES (
  'bbbbbbbb-bbbb-4bbb-8bbb-bbbbbbbbbbbb',
  'Event Organizer',
  'home,CreateEventCreation,UpdateEventCreation,DeleteEventCreation,CreateTypeEventCreation,UpdateTypeEventCreation,DeleteTypeEventCreation,CreateReport,UpdateReport,DeleteReport,CreateNotification,UpdateNotification,DeleteNotification'
) ON CONFLICT DO NOTHING;

INSERT INTO auth_role_impl (id, name, allowedPermissions)
VALUES (
  'cccccccc-cccc-4ccc-8ccc-cccccccccccc',
  'Check-in Staff',
  'home,CreateCheckIn,UpdateCheckIn,DeleteCheckIn,CreateAttendeeManagement,UpdateAttendeeManagement,DeleteAttendeeManagement'
) ON CONFLICT DO NOTHING;

INSERT INTO auth_role_impl (id, name, allowedPermissions)
VALUES (
  'dddddddd-dddd-4ddd-8ddd-dddddddddddd',
  'Event Attendee',
  'home,CreateReview,UpdateReview,DeleteReview,CreateAnonymousReview,UpdateAnonymousReview,DeleteAnonymousReview'
) ON CONFLICT DO NOTHING;

INSERT INTO auth_user_role_comp (id) VALUES ('10000000-0000-4000-8000-000000000001') ON CONFLICT DO NOTHING;
INSERT INTO auth_user_role_comp (id) VALUES ('10000000-0000-4000-8000-000000000002') ON CONFLICT DO NOTHING;
INSERT INTO auth_user_role_comp (id) VALUES ('10000000-0000-4000-8000-000000000003') ON CONFLICT DO NOTHING;
INSERT INTO auth_user_role_comp (id) VALUES ('10000000-0000-4000-8000-000000000004') ON CONFLICT DO NOTHING;

INSERT INTO auth_user_role_impl (id, authRole, authUser)
VALUES (
  '10000000-0000-4000-8000-000000000001',
  'aaaaaaaa-aaaa-4aaa-8aaa-aaaaaaaaaaaa',
  '11111111-1111-4111-8111-111111111111'
) ON CONFLICT DO NOTHING;

INSERT INTO auth_user_role_impl (id, authRole, authUser)
VALUES (
  '10000000-0000-4000-8000-000000000002',
  'bbbbbbbb-bbbb-4bbb-8bbb-bbbbbbbbbbbb',
  '22222222-2222-4222-8222-222222222222'
) ON CONFLICT DO NOTHING;

INSERT INTO auth_user_role_impl (id, authRole, authUser)
VALUES (
  '10000000-0000-4000-8000-000000000003',
  'cccccccc-cccc-4ccc-8ccc-cccccccccccc',
  '33333333-3333-4333-8333-333333333333'
) ON CONFLICT DO NOTHING;

INSERT INTO auth_user_role_impl (id, authRole, authUser)
VALUES (
  '10000000-0000-4000-8000-000000000004',
  'dddddddd-dddd-4ddd-8ddd-dddddddddddd',
  '44444444-4444-4444-8444-444444444444'
) ON CONFLICT DO NOTHING;
