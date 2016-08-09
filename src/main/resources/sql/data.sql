set timezone = 'UTC';
set search_path to store;

/**
 * Add sample records to the database.
 */
insert into store.book (time_created, time_updated, title) values ('2016-08-09 20:15:40.174', '2016-08-09 20:15:40.174', 'Programming In Scala');
insert into store.author (time_created, time_updated, family_name, giver_name) values ('2016-08-09 20:15:40.174', '2016-08-09 20:15:40.174', 'Odersky', 'Martin');
insert into store.author (time_created, time_updated, family_name, giver_name) values ('2016-08-09 20:15:40.174', '2016-08-09 20:15:40.174', 'Lex', 'Spoon');