-- 10 seed rows for AnonymousReview delta.
INSERT INTO review_comp (reviewid, eventid, attendeeid, rating, comment, objectname, modulesequence)
SELECT
  301000 + n,
  100000 + n,
  500000 + n,
  4,
  'Seed Anonymous Review Base ' || n,
  'Event.review.core.model.ReviewComponent',
  'review_impl,review_reviewanonymous'
FROM generate_series(1, 10) AS n
ON CONFLICT (reviewid) DO NOTHING;

INSERT INTO review_impl (reviewid)
SELECT 301000 + n
FROM generate_series(1, 10) AS n
ON CONFLICT (reviewid) DO NOTHING;

INSERT INTO review_comp (reviewid, eventid, attendeeid, rating, comment, objectname, modulesequence)
SELECT
  301100 + n,
  100000 + n,
  500000 + n,
  4,
  'Seed Anonymous Review Delta ' || n,
  'Event.review.reviewanonymous.model.ReviewImpl',
  'review_reviewanonymous'
FROM generate_series(1, 10) AS n
ON CONFLICT (reviewid) DO NOTHING;

INSERT INTO review_reviewanonymous (reviewid, anonymous, record_reviewid, recordname, base_component_id)
SELECT
  301100 + n,
  n % 2 = 1,
  301000 + n,
  'Event.review.core.model.ReviewImpl',
  301000 + n
FROM generate_series(1, 10) AS n
ON CONFLICT (reviewid) DO NOTHING;
