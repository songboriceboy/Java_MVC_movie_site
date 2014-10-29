insert into Viewers values (default,'ryan','a','ryan','Ryan','Zhang','zhangchengyi2010@gmail.com',true);
insert into Viewers values (default,'chengyi','a',null,'Ryan','Zhang','zhangchengyi2010@gmail.com',true);

insert into Owners values (default,'yiyo','a');

insert into REVIEWS values (default, 5, 'review title');

insert into MOVIES values (default, 'Next','posters/next.jpg','Science Fiction',null,null,CURRENT_TIMESTAMP);
insert into MOVIES values (default, 'Edge of Tomorrow','posters/edge of tomorrow.jpg','Science Fiction',null,null,CURRENT_TIMESTAMP);
insert into MOVIES values (default, 'Inception','posters/inception_.jpg','Science Fiction',null,null,CURRENT_TIMESTAMP);
insert into MOVIES values (default, 'The Matrix','posters/matrix.jpg','Science Fiction',null,null,CURRENT_TIMESTAMP);
insert into MOVIES values (default, 'Lucy','posters/lucy.jpg','Science Fiction',null,null,CURRENT_TIMESTAMP);
insert into MOVIES values (default, 'Looper','posters/looper.jpg','Science Fiction',null,null,CURRENT_TIMESTAMP);

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
insert into schedules values (default,1,7,'2014-10-30','1800');

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