create table users
(
	id serial primary key,
	name varchar(30) not null,
	password varchar(50) not null,
	created_date timestamp not null
);

create table categories
(
	id serial primary key,
	name varchar(50) not null,
	user_id integer references users(id) not null,
	--parent_category_id ?,
	is_active boolean not null,
	created_date timestamp not null
);

create table wallets
(
	id serial primary key,
	name varchar(30) not null,
	user_id integer references users(id) unique not null,
	is_active boolean not null,
	created_date timestamp not null
);

create table orders
(
	id serial primary key,
	category_id integer references categories(id) not null,
	amount integer not null,
	description varchar(200) not null,
	wallet_id integer references wallets(id) not null,
	is_expense boolean not null,
	created_date timestamp not null
);




