set timezone = 'UTC';
set search_path to store;

/**
 * Creates 'get_book_details' function in the database.
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