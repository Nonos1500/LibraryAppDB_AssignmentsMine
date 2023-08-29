select id from users;

select * from users;

select count(id) from users;
select count(distinct id) from users;

select count(*) from book_borrow where is_returned = 0;

select name
from book_categories;

select name from books;

select * from books where name like 'Mountain Calls';

select * from book_categories;

select BC.name, count(*) from books B
join book_categories BC on B.book_category_id = BC.id
join book_borrow BB on B.id = BB.book_id
group by BC.name
order by 2 desc ;

select U.full_name, BB.book_id, b.name, BB.is_returned from users U
join book_borrow BB on U.id = BB.user_id
join books b on BB.book_id = b.id
where BB.is_returned = 0 and U.full_name = 'Test Student 2'
;

select
