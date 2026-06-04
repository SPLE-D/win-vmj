-- 10 seed rows for Review core.
INSERT INTO review_comp (reviewid, eventid, attendeeid, rating, comment, objectname, modulesequence)
SELECT
  300000 + n,
  100000 + n,
  500000 + n,
  CASE n
    WHEN 1 THEN 5
    WHEN 2 THEN 4
    WHEN 3 THEN 5
    WHEN 4 THEN 3
    WHEN 5 THEN 4
    WHEN 6 THEN 3
    WHEN 7 THEN 5
    WHEN 8 THEN 4
    WHEN 9 THEN 4
    ELSE 5
  END,
  CASE n
    WHEN 1 THEN 'Materi tech summit sangat berbobot dan futuristik!'
    WHEN 2 THEN 'Musisi luar biasa, tapi antrean masuk GBK terlalu panjang.'
    WHEN 3 THEN 'Luar biasa seru! Cosplay-nya keren dan booth rapi.'
    WHEN 4 THEN 'Materi AI-nya bagus, tapi sesi hands-on terlalu cepat.'
    WHEN 5 THEN 'Banyak perusahaan besar yang hadir, sangat membantu karir saya.'
    WHEN 6 THEN 'Topik Green Energy menarik, sayang AC di ballroom kurang dingin.'
    WHEN 7 THEN 'Turnamen eSports tergokil! Panggungnya spektakuler.'
    WHEN 8 THEN 'Makanan expo enak-enak, sayangnya kapasitas meja makan kurang.'
    WHEN 9 THEN 'Networking session dengan venture capital sangat bermanfaat.'
    ELSE 'Konser orkestra yang sangat megah dan menyentuh hati.'
  END,
  'Event.review.core.model.ReviewComponent',
  'review_impl'
FROM generate_series(1, 10) AS n
ON CONFLICT (reviewid) DO NOTHING;

INSERT INTO review_impl (reviewid)
SELECT 300000 + n
FROM generate_series(1, 10) AS n
ON CONFLICT (reviewid) DO NOTHING;
