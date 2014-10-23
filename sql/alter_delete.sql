alter table Movies add column synopsis VARCHAR(1000);

--
DELETE FROM Viewers;
DELETE FROM Owners;

DROP TABLE Viewers;
DROP TABLE Owners;
