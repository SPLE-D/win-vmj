-- 10 seed rows for Review core.
INSERT INTO review_comp (reviewid, eventid, attendeeid, rating, comment, objectname, modulesequence)
SELECT
  300000 + n,
  100000 + n,
  500000 + n,
  3 + (n % 3),
  'Seed Core Review ' || n,
  'Event.review.core.model.ReviewComponent',
  'review_impl'
FROM generate_series(1, 10) AS n
ON CONFLICT (reviewid) DO NOTHING;

INSERT INTO review_impl (reviewid)
SELECT 300000 + n
FROM generate_series(1, 10) AS n
ON CONFLICT (reviewid) DO NOTHING;
