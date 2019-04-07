insert into authors (id, `name`) values (1, 'Толстой Лев Николаевич');
insert into authors (id, `name`) values (2, 'Достоевский Фёдор Михайлович');

insert into genres (id, `name`) values (1, 'Комедия');
insert into genres (id, `name`) values (2, 'Трагедия');

insert into  books (id, `name`, author_id, genre_id) values ( 1, 'Преступление и наказание', 2, 2);
