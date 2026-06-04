-- 10 seed rows for AnonymousReview delta.
INSERT INTO review_comp (reviewid, eventid, attendeeid, rating, comment, objectname, modulesequence)
SELECT
  301000 + n,
  101000 + n, -- Point to typeeventcreation base event id
  501000 + n, -- Point to classattendeemanagement base attendee id
  CASE n
    WHEN 1 THEN 5
    WHEN 2 THEN 3
    WHEN 3 THEN 4
    WHEN 4 THEN 5
    WHEN 5 THEN 4
    WHEN 6 THEN 4
    WHEN 7 THEN 3
    WHEN 8 THEN 5
    WHEN 9 THEN 5
    ELSE 4
  END,
  CASE n
    WHEN 1 THEN 'Sesi coaching eksekutif yang sangat eksklusif dan terarah.'
    WHEN 2 THEN 'Diskusi boardroom agak alot, materi presentasi kurang detail.'
    WHEN 3 THEN 'Konser musik indie sangat intim dan menyenangkan.'
    WHEN 4 THEN 'Sangat puas bisa bertemu langsung dengan mentor startup.'
    WHEN 5 THEN 'Banyak opsi kampus untuk lanjut studi, panitia ramah.'
    WHEN 6 THEN 'Pameran kerajinan sangat rapi dan produknya berkualitas.'
    WHEN 7 THEN 'Makanan lezat, tapi porsi dirasa terlalu sedikit.'
    WHEN 8 THEN 'Materi masterclass marketing sangat praktis untuk bisnis saya.'
    WHEN 9 THEN 'Diskusi Java GC sangat mendalam dan interaktif.'
    ELSE 'Karya seni yang dipamerkan di lelang sangat bernilai tinggi.'
  END,
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
  101100 + n, -- Point to typeeventcreation delta event id
  501100 + n, -- Point to classattendeemanagement delta attendee id
  CASE n
    WHEN 1 THEN 5
    WHEN 2 THEN 3
    WHEN 3 THEN 4
    WHEN 4 THEN 5
    WHEN 5 THEN 4
    WHEN 6 THEN 4
    WHEN 7 THEN 3
    WHEN 8 THEN 5
    WHEN 9 THEN 5
    ELSE 4
  END,
  CASE n
    WHEN 1 THEN 'Sesi coaching eksekutif yang sangat eksklusif dan terarah.'
    WHEN 2 THEN 'Diskusi boardroom agak alot, materi presentasi kurang detail.'
    WHEN 3 THEN 'Konser musik indie sangat intim dan menyenangkan.'
    WHEN 4 THEN 'Sangat puas bisa bertemu langsung dengan mentor startup.'
    WHEN 5 THEN 'Banyak opsi kampus untuk lanjut studi, panitia ramah.'
    WHEN 6 THEN 'Pameran kerajinan sangat rapi dan produknya berkualitas.'
    WHEN 7 THEN 'Makanan lezat, tapi porsi dirasa terlalu sedikit.'
    WHEN 8 THEN 'Materi masterclass marketing sangat praktis untuk bisnis saya.'
    WHEN 9 THEN 'Diskusi Java GC sangat mendalam dan interaktif.'
    ELSE 'Karya seni yang dipamerkan di lelang sangat bernilai tinggi.'
  END,
  'Event.review.reviewanonymous.model.ReviewImpl',
  'review_reviewanonymous'
FROM generate_series(1, 10) AS n
ON CONFLICT (reviewid) DO NOTHING;

INSERT INTO review_reviewanonymous (reviewid, anonymous, record_reviewid, recordname, base_component_id)
SELECT
  301100 + n,
  CASE WHEN n IN (2, 3, 5, 8, 9) THEN true ELSE false END,
  301000 + n,
  'Event.review.core.model.ReviewImpl',
  301000 + n
FROM generate_series(1, 10) AS n
ON CONFLICT (reviewid) DO NOTHING;
