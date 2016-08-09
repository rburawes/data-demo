set timezone = 'UTC';
set search_path to store;

/**
 * Creates or replaces 'get_book_details' function in the database.
 * Simply returns the details of the book using the id.
 */
drop function if exists get_book_details(bigint);
create or replace function get_book_details(id bigint) returns refcursor as $$
  declare details refcursor := 'book_details';
  begin
    open details for select * from store.book as b where b.id = $1;
    return details;
  end;
$$ language plpgsql;

/**
 * Add sample records to the database.
 */
insert into store.book (time_created, time_updated, title) values ('2016-08-09 20:15:40.174', '2016-08-09 20:15:40.174', 'Programming In Scala');
insert into store.author (time_created, time_updated, family_name, giver_name) values ('2016-08-09 20:15:40.174', '2016-08-09 20:15:40.174', 'Odersky', 'Martin');
insert into store.author (time_created, time_updated, family_name, giver_name) values ('2016-08-09 20:15:40.174', '2016-08-09 20:15:40.174', 'Lex', 'Spoon');