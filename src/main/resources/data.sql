-- Insert Authors
INSERT INTO author (id, name, bio) VALUES (1, 'J.K. Rowling', 'British author, best known for the Harry Potter series.');
INSERT INTO author (id, name, bio) VALUES (2, 'George R.R. Martin', 'American novelist, known for A Song of Ice and Fire.');
INSERT INTO author (id, name, bio) VALUES (3, 'J.R.R. Tolkien', 'English writer, known for The Lord of the Rings.');
INSERT INTO author (id, name, bio) VALUES (4, 'Agatha Christie', 'English writer, known for her detective novels.');
INSERT INTO author (id, name, bio) VALUES (5, 'Stephen King', 'American author of horror, supernatural fiction.');
INSERT INTO author (id, name, bio) VALUES (6, 'Isaac Asimov', 'American writer and professor of biochemistry.');
INSERT INTO author (id, name, bio) VALUES (7, 'Arthur C. Clarke', 'English science-fiction writer and inventor.');
INSERT INTO author (id, name, bio) VALUES (8, 'Jane Austen', 'English novelist known primarily for her six major novels.');
INSERT INTO author (id, name, bio) VALUES (9, 'Mark Twain', 'American writer, humorist, entrepreneur, publisher.');
INSERT INTO author (id, name, bio) VALUES (10, 'Charles Dickens', 'English writer and social critic.');

-- Insert Books
INSERT INTO book (id, title, isbn, author_id) VALUES (1, 'Harry Potter and the Sorcerers Stone', '978-0747532699', 1);
INSERT INTO book (id, title, isbn, author_id) VALUES (2, 'Harry Potter and the Chamber of Secrets', '978-0747538493', 1);
INSERT INTO book (id, title, isbn, author_id) VALUES (3, 'A Game of Thrones', '978-0553103540', 2);
INSERT INTO book (id, title, isbn, author_id) VALUES (4, 'The Fellowship of the Ring', '978-0618260263', 3);
INSERT INTO book (id, title, isbn, author_id) VALUES (5, 'And Then There Were None', '978-0312330873', 4);
INSERT INTO book (id, title, isbn, author_id) VALUES (6, 'The Shining', '978-0385121675', 5);
INSERT INTO book (id, title, isbn, author_id) VALUES (7, 'Foundation', '978-0553293357', 6);
INSERT INTO book (id, title, isbn, author_id) VALUES (8, '2001: A Space Odyssey', '978-0451452733', 7);
INSERT INTO book (id, title, isbn, author_id) VALUES (9, 'Pride and Prejudice', '978-0141439518', 8);
INSERT INTO book (id, title, isbn, author_id) VALUES (10, 'A Tale of Two Cities', '978-0141439600', 10);
