select * from VIEWERS;
select * from OWNERS;
select * from REVIEWS;
select * from movies;
select * from cinemas;
select * from schedules;
select * from bookings;

select MAX(id) from bookings where viewerID = 1 AND scheduleID = 53;

---- BookingConfirmInfo ---
--SELECT b.id AS bookingID, b.numTickets, m.*, s.date0, s.timeslot
SELECT m.*, b.id AS bookingID, b.numTickets, s.id AS sid, s.date0, s.timeslot, c.location, c.unitPrice 
  FROM bookings b, schedules s, movies m, cinemas c
WHERE b.scheduleID = s.id AND s.movieID = m.id AND s.cinemaID = c.id AND b.id = 1;

---- (SeatCapacity - numTicketsBooked) of a schedule ----
SELECT c.seatCapacity,  b.sumTickets FROM cinemas c, schedules s
 LEFT JOIN (SELECT scheduleID, SUM(numTickets) as sumTickets FROM bookings WHERE scheduleID = 38 GROUP BY scheduleID) b ON s.id = b.scheduleID
WHERE c.id = s.cinemaID AND s.id = 38;

-- bug: empty result if sumTickets is NULL
SELECT c.seatCapacity - b.sumTickets AS numSeatsLeft FROM cinemas c, schedules s,
 (SELECT scheduleID, SUM(numTickets) as sumTickets FROM bookings WHERE scheduleID = 53 GROUP BY scheduleID) b
WHERE c.id = s.cinemaID AND s.id = b.scheduleID;

-- MIN(c.seatCapacity) is weird but works.
SELECT MIN(c.seatCapacity) - SUM(b.numTickets) AS seatsLeft FROM cinemas c, schedules s, bookings b WHERE c.id = s.cinemaID AND s.id = b.scheduleID AND b.scheduleID = 53 GROUP BY b.scheduleID;

SELECT SUM(b.numTickets) FROM bookings b WHERE b.scheduleID = 53 GROUP BY b.scheduleID;

--- cinemas showing a particular movie ---
SELECT * FROM cinemas WHERE id IN (SELECT DISTINCT cinemaid FROM schedules WHERE movieid = 4);
SELECT DISTINCT c.* FROM cinemas c, schedules s WHERE c.id = s.cinemaid AND s.movieid = 4; 

SELECT b.scheduleid, SUM(b.numTickets) FROM bookings b GROUP BY b.scheduleid;

---- schedule + total number of tickets booked, for booking ----
SELECT * FROM schedules s 
LEFT JOIN (SELECT scheduleid, SUM(numTickets) AS sumTickets FROM bookings GROUP BY scheduleid) b ON s.id = b.scheduleid
WHERE s.date0 >= CURRENT_DATE AND s.cinemaid = 2 AND s.movieid = 4
ORDER BY s.date0, s.timeslot;

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
