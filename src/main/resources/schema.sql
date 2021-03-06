DROP TABLE IF EXISTS AUTHORS;
CREATE TABLE AUTHORS(ID LONG PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(255) not null);

DROP TABLE IF EXISTS GENRES;
CREATE TABLE GENRES(ID LONG PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(255) not null unique);

DROP TABLE IF EXISTS BOOKS;
CREATE TABLE BOOKS(ID LONG PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(255) not null, AUTHOR_ID LONG, GENRE_ID LONG);

DROP TABLE IF EXISTS BOOK_COMMENTS;
CREATE TABLE BOOK_COMMENTS(ID LONG PRIMARY KEY AUTO_INCREMENT, BOOK_ID LONG, COMMENT_TEXT VARCHAR(4000));

ALTER TABLE BOOKS ADD FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHORS(ID);
ALTER TABLE BOOKS ADD FOREIGN KEY (GENRE_ID) REFERENCES GENRES(ID);
ALTER TABLE BOOK_COMMENTS ADD FOREIGN KEY (BOOK_ID) REFERENCES BOOKS(ID);
