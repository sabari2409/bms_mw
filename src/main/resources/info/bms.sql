select * from public.city;
SELECT * FROM public.theatre ORDER BY id ASC;
select * from public.auditorium;
select * from public.seat;
select * from public.movies;
select * from public.shows;
select * from public.show_seat;
select * from public.payment;
select * from public.ticket;

-- truncate table public.city;
-- truncate table public.theatre;
-- truncate table public.auditorium;
-- truncate table public.seat;
-- truncate table public.movies;
-- truncate table public.show;
-- truncate table public.show_seat;

-- get all the theatres and show times for the movie name jailer (id = 8)

select * from shows;
select * from movies where id = 8;
select 
	audi.id, 
	audi.name, 
	audi.theatre_id, 
	sh.show_date, 
	sh.start_time, 
	sh.end_time,
	th.name as theatre_name
	from auditorium audi, shows sh, theatre th
	where 
	sh.auditorium_id = audi.id and 
	audi.theatre_id = th.id and sh.movie_id = 8;




-- insert city data
CREATE OR REPLACE PROCEDURE insert_city_proc()
LANGUAGE plpgsql
AS $$
BEGIN
insert into public.city(city_type, id, created_at,updated_at, name) values(1, 1, now(), now(), 'Chennai');
insert into public.city(city_type, id, created_at,updated_at, name) values(1, 2, now(), now(), 'Bangalore');
insert into public.city(city_type, id, created_at,updated_at, name) values(1, 3, now(), now(), 'Hyderabad');
insert into public.city(city_type, id, created_at,updated_at, name) values(1, 4, now(), now(), 'Maharastra');
END;
$$;

-- insert theatre data
create or replace procedure insert_theatre_proc()
	language plpgsql
	as $$
	begin
		insert into public.theatre(city_id,created_at,updated_at,address,name)
	    values
		(1, now(), now(), 'Velacherry', 'PVR' ),
		(1, now(), now(), 'Thousand Light', 'PVR - Sathyam Cinemas' ),
		(1, now(), now(), 'OMR', 'PVR-INOX' );
	end;
	$$;

-- insert auditorium data
create or replace procedure insert_auditorium_proc()
	language plpgsql
	as $$
	begin
	insert into auditorium(theatre_id, created_at, updated_at,name)
	values
	(7, now(), now(), 'Audi 1'),
	(7, now(), now(), 'Audi 2'),
	(7, now(), now(), 'Audi 3'),
	(7, now(), now(), 'Audi 4'),
	(7, now(), now(), 'Audi 5');
	end;
$$;

create or replace procedure insert_movies_proc()
	language plpgsql
	as $$
	begin
		insert into movies(created_at, updated_at, language, name) values
	    (now(), now(), 'Tamil', 'Jailer'),
		(now(), now(), 'Tamil', 'U Turn'),
		(now(), now(), 'Tamil', 'Va Vathiyar'),
		(now(), now(), 'Tamil', 'Kanguva'),
		(now(), now(), 'Tamil', 'Leo'),
		(now(), now(), 'Tamil', 'Mankatha'),
		(now(), now(), 'Tamil', 'Thunivu');
	end;
$$;

create or replace procedure insert_seat_proc()
	language plpgsql
	as $$
	begin
		insert into seat(auditorium_id, seat_no, seat_status,seat_type,created_at, updated_at)
	    values
	    (16,'A1',0,0, now(), now()),
		(16,'A2',0,1, now(), now()),
		(16,'A3',0,2, now(), now()),
		(16,'A4',0,0, now(), now()),
		(16,'A5',0,1, now(), now()),
		(16,'A6',0,2, now(), now()),
		(16,'A7',0,0, now(), now()),
		(16,'A8',0,1, now(), now());
	end;
    $$;

create or replace procedure insert_show_proc()
	language plpgsql
	as $$
	begin
		insert into shows(auditorium_id, movie_id, show_date, created_at, end_time, start_time, updated_at) values
	    (16,8, now(), now(), CURRENT_TIMESTAMP + INTERVAL '3 hours', current_timestamp, now()),
		(16,8, now(), now(), CURRENT_TIMESTAMP + INTERVAL '7 hours', CURRENT_TIMESTAMP + INTERVAL '4 hours', now());
	end;
 $$;

create or replace procedure insert_show_seats_proc()
	language plpgsql
	as $$
	begin
	  insert into show_seat(seat_id, seat_status,show_id, created_at, updated_at) values
	  	(9,0,1, current_timestamp, current_timestamp),
		(10,0,1, current_timestamp, current_timestamp),
		(11,0,1, current_timestamp, current_timestamp),
		(12,0,1, current_timestamp, current_timestamp),
		(13,0,1, current_timestamp, current_timestamp);

	end;
$$;

-- initialize the master data in all the required table on server startup
CREATE or replace PROCEDURE init_master_data()
LANGUAGE plpgsql
AS $$
BEGIN

    CALL insert_city_proc();
    CALL insert_theatre_proc();
    CALL insert_auditorium_proc();
    CALL insert_seat_proc();
    CALL insert_movies_proc();
    CALL insert_show_proc();
    CALL insert_show_seats_proc();

END;
$$;

call init_master_data();
call insert_city_proc();
call insert_theatre_proc();
call insert_auditorium_proc();
call insert_seat_proac();
call insert_movies_proc();
call insert_show_proc();


SELECT routine_name, routine_type FROM information_schema.routines WHERE routine_name = 'init_master_data';

SELECT version();

SELECT n.nspname AS schema,
       p.proname AS procedure_name
FROM pg_proc p
JOIN pg_namespace n ON p.pronamespace = n.oid
WHERE proname = 'init_master_data';

commit;

select * from public.show_seat order by updated_at desc;
select * from movies;
select * from show_seat;
select * from public.ticket;
select * from public.shows;
select * from public.seat;
select * from public.show_seat_lock;
select * from public.payment;
select * from public.transaction;

alter table public.seat add price double;

update public.seat set price = 180 where seat_type = 2;

select * from public.users;
insert into public.users(created_at,updated_at,email,is_active,name,password)
values (now(), now(), 'pepdistech@gmail.com', true, 'pepdis', 'pepdis');
commit;