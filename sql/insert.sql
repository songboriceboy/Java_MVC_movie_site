insert into Viewers values (default,'ryan','a','ryan','Ryan','Zhang','zhangchengyi2010@gmail.com',true);
insert into Viewers values (default,'chengyi','a',null,'Ryan','Zhang','zhangchengyi2010@gmail.com',true);

insert into Owners values (default,'yiyo','a');

insert into MOVIES values (default, 'Next','posters/next.jpg','Science Fiction','Lee Tamahori','Nicolas Cage, Julianne Moore, Jessica Biel',CURRENT_TIMESTAMP,null);
insert into MOVIES values (default, 'Edge of Tomorrow','posters/edge of tomorrow.jpg','Science Fiction','Doug Liman','Tom Cruise, Emily Blunt, Bill Paxton',CURRENT_TIMESTAMP,null);
insert into MOVIES values (default, 'Inception','posters/inception_.jpg','Science Fiction','Christopher Nolan','Leonardo DiCaprio',CURRENT_TIMESTAMP,null);
insert into MOVIES values (default, 'The Matrix','posters/matrix.jpg','Science Fiction','Andy Wachowski','Keanu Reeves, Laurence Fishburne, Carrie-Anne',CURRENT_TIMESTAMP,null);
insert into MOVIES values (default, 'Lucy','posters/lucy.jpg','Science Fiction','Luc Besson','Scarlett Johansson, Morgan Freeman, Min-sik Choi',CURRENT_TIMESTAMP,null);
insert into MOVIES values (default, 'Looper','posters/looper.jpg','Science Fiction','Rian Johnson','Joseph Gordon-Levitt, Bruce Willis, Emily Blunt',CURRENT_TIMESTAMP,null);
update MOVIES set synopsis = 'A Las Vegas magician who can see into the future is pursued by FBI agents seeking to use his abilities to prevent a nuclear terrorist attack.' where id = 1;
update MOVIES set synopsis = 'A military officer is brought into an alien war against an extraterrestrial enemy who can reset the day and know the future. When this officer is enabled with the same power, he teams up with a Special Forces warrior to try and end the war.' where id = 2;
update MOVIES set synopsis = 'A thief who steals corporate secrets through use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.' where id = 3;
update MOVIES set synopsis = 'A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.' where id = 4;
update MOVIES set synopsis = 'A woman, accidentally caught in a dark deal, turns the tables on her captors and transforms into a merciless warrior evolved beyond human logic.' where id = 5;
update MOVIES set synopsis = 'In 2074, when the mob wants to get rid of someone, the target is sent into the past, where a hired gun awaits - someone like Joe - who one day learns the mob wants to ''close the loop'' by sending back Joe''s future self for assassination.' where id = 6;

insert into cinemas values (default,'Burwood',250,13);

insert into schedules values (default,1,2,'2014-10-24','1200');
insert into schedules values (default,1,2,'2014-10-25','1200');
insert into schedules values (default,1,2,'2014-10-26','1200');
insert into schedules values (default,1,2,'2014-10-27','1200');
insert into schedules values (default,1,2,'2014-10-28','1200');
insert into schedules values (default,1,2,'2014-10-29','1200');
insert into schedules values (default,1,2,'2014-10-30','1200');
insert into schedules values (default,1,2,'2014-10-31','1200');

insert into schedules values (default,1,3,'2014-10-27','0900');
insert into schedules values (default,1,3,'2014-10-28','0900');
insert into schedules values (default,1,3,'2014-10-29','0900');
insert into schedules values (default,1,3,'2014-10-30','0900');
insert into schedules values (default,1,3,'2014-10-31','0900');

insert into schedules values (default,1,4,'2014-10-27','1500');

insert into schedules values (default,1,5,'2014-10-30','1500');
insert into schedules values (default,1,5,'2014-10-31','1500');

insert into schedules values (default,1,6,'2014-10-20','1800');
insert into schedules values (default,1,1,'2014-10-30','1800');

insert into reviews (viewerid,movieid,rating,timeAdded,title,comment) values (1,2,5,CURRENT_TIMESTAMP,'Far more than I expected','Next is a much better movie than I expected to see, having read some of the reviews which called it disjointed and silly. Quite to the contrary, I found it deeply absorbing. I quickly picked up on the elements which must have caused some reviewers to accuse it of being disjointed, and began enjoying them. Of course silliness is part of any sci-fi story, we suspend our critical senses in that regard or we do not become sci-fi fans.');
insert into reviews (viewerid,movieid,rating,timeAdded,title,comment) values (2,2,4,CURRENT_TIMESTAMP,'Good. Not cheesy, Not overblown. Just pretty darn good.','Cris Johnson has the ability to see 2 minutes into his own future. He works a low-level magic act in Las Vegas with this secret ability and also makes money gambling. However, an FBI Agent sees his uncanny ability to foresee the immediate future and tries to get him to foil a terrorist plot.');
insert into reviews (viewerid,movieid,rating,timeAdded,title,comment) values (2,4,5,CURRENT_TIMESTAMP,'Insanely Brilliant ! Nolan has outdone himself !!','What is the most resilient parasite? An Idea! Yes, Nolan has created something with his unbelievably, incredibly and god- gifted mind which will blow the minds of the audience away. The world premiere of the movie, directed by Hollywoods most inventive dreamers, was shown in London and has already got top notch reviews worldwide and has scored maximum points! Now the question arises what the movie has that it deserve all this?');
------------
select * from viewers;
select * from reviews;
select * from cinemas;
select * from movies;
select * from schedules;

delete from movies where id = 1;