select * from VIEWERS;
select * from OWNERS;
select * from REVIEWS;
select * from movies;
select * from cinemas;
select * from schedules;
select * from bookings;

select movieid, min(date0) as mindate from schedules where date0 >= CURRENT_DATE GROUP BY movieid;
select movieid, avg(rating), count(*) from reviews group by movieid;

--- movie search query ---
SELECT m.*, s.mindate, r.avgrating, r.numreviews FROM movies m
 LEFT JOIN (SELECT movieid, MIN(date0) AS mindate FROM schedules WHERE date0 >= CURRENT_DATE GROUP BY movieid) s
  ON s.movieid = m.id
 LEFT JOIN (SELECT movieid, AVG(rating) AS avgrating, COUNT(*) AS numreviews FROM reviews GROUP BY movieid) r
  ON r.movieid = m.id
WHERE (LOWER(m.title) LIKE '%sci%' or LOWER(m.genre) LIKE '%sci%') 
ORDER BY m.dateAdded DESC;

--- Now Showing / Coming Soon list query --- s.mindate = CURRENT_DATE '=' for now showing; '>' for coming soon
SELECT m.*, s.mindate, r.avgrating, r.numreviews FROM movies m
 LEFT JOIN (SELECT movieid, AVG(rating) AS avgrating, COUNT(*) AS numreviews FROM reviews GROUP BY movieid) r
  ON r.movieid = m.id,
(SELECT movieid, MIN(date0) AS mindate FROM schedules WHERE date0 >= CURRENT_DATE GROUP BY movieid) s
WHERE s.movieid = m.id AND s.mindate = CURRENT_DATE
ORDER BY m.dateAdded DESC;

select m.id, m.title, s.date0 from movies m left join schedules s on m.id = s.movieid order by m.id, s.date0;
