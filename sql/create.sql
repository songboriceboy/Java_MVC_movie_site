CREATE TABLE Viewers (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    username VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    nickname VARCHAR(20),
    firstname VARCHAR(20),
    lastname VARCHAR(20),
    email VARCHAR(50),
    activated BOOLEAN,
    CONSTRAINT unique_username UNIQUE (username),
    CONSTRAINT pk_viewers PRIMARY KEY (id)
);

CREATE TABLE Owners (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    username VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    CONSTRAINT pk_onwers PRIMARY KEY (id)
);

CREATE TABLE Movies (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    title VARCHAR(40) NOT NULL,
    poster VARCHAR(40) NOT NULL,
    genre VARCHAR(20),
    director VARCHAR(30),
    actors VARCHAR(100),
    dateAdded TIMESTAMP,
--    CONSTRAINT ck_genre CHECK (genre IN ('SciFi','Comedy','Drama')),
    CONSTRAINT pk_movies PRIMARY KEY (id)
);
DROP TABLE reviews;
CREATE TABLE Reviews (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    viewerId INTEGER NOT NULL,
    movieId INTEGER NOT NULL,
    title VARCHAR(100) NOT NULL,
    comment VARCHAR(1000),
    rating REAL NOT NULL,
    timeAdded TIMESTAMP,
    CONSTRAINT pk_reviews PRIMARY KEY (id),
    CONSTRAINT fk_review_viewer_id FOREIGN KEY (viewerId) REFERENCES Viewers(id),
    CONSTRAINT fk_review_movie_id FOREIGN KEY (movieId) REFERENCES Movies(id)
);

CREATE TABLE Cinemas (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    location VARCHAR(40) NOT NULL,
    seatCapacity SMALLINT NOT NULL,
    unitPrice SMALLINT NOT NULL,
    CONSTRAINT unique_location UNIQUE (location),
    CONSTRAINT pk_cinemas PRIMARY KEY (id)
);

CREATE TABLE Schedules (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    cinemaId INTEGER NOT NULL,
    movieId INTEGER NOT NULL,
    date0 DATE NOT NULL, -- using 'date' as column name is fine
    timeslot VARCHAR(10) NOT NULL,
    CONSTRAINT ck_timeslot CHECK (timeslot in ('0900','1200','1500','1800')),
    CONSTRAINT unique_movie_per_cinema_date_time UNIQUE (cinemaId, date0, timeslot),
    CONSTRAINT pk_schedules PRIMARY KEY (id),
    CONSTRAINT fk_cinema_id FOREIGN KEY (cinemaId) REFERENCES Cinemas(id),
    CONSTRAINT fk_movie_id FOREIGN KEY (movieId) REFERENCES Movies(id)
);

CREATE TABLE Bookings (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    viewerId INTEGER NOT NULL,
    scheduleId INTEGER NOT NULL,
    numTickets SMALLINT NOT NULL,
    CONSTRAINT pk_bookings PRIMARY KEY (id),
    CONSTRAINT fk_booking_viewer_id FOREIGN KEY (viewerId) REFERENCES Viewers(id),
    CONSTRAINT fk_schedule_id FOREIGN KEY (scheduleId) REFERENCES Schedules(id)
);
