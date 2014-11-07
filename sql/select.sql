select * from VIEWERS;
select * from OWNERS;
select * from REVIEWS;
select * from movies;
select * from cinemas;
select * from schedules;
select * from bookings;

---- for displaying schedule table of a cinema ----
SELECT MAX(date0) FROM schedules WHERE cinemaid = 1 GROUP BY cinemaid;

SELECT * FROM schedules s 
LEFT JOIN movies m ON m.id = s.movieid 
WHERE s.cinemaid = 1 AND s.timeslot = '1500' AND s.date0 >= CURRENT_DATE ORDER BY s.date0;

----
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

--- movie details query ---
SELECT * FROM movies m
 LEFT JOIN (SELECT movieid, MIN(date0) AS mindate FROM schedules WHERE date0 >= CURRENT_DATE GROUP BY movieid) s
  ON s.movieid = m.id
 LEFT JOIN (SELECT movieid, AVG(rating) AS avgrating, COUNT(*) AS numreviews FROM reviews GROUP BY movieid) r
  ON r.movieid = m.id
WHERE m.id = 5; 

--- reviews of a movie ----
SELECT v.username, r.* FROM reviews r, viewers v WHERE r.viewerid = v.id AND r.movieid = 2 ORDER BY r.timeAdded desc;

select m.id, m.title, s.date0 from movies m left join schedules s on m.id = s.movieid order by m.id, s.date0;
