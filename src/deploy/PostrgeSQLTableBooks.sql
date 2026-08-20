drop table if exists books;
create table if not exists books (
id bigserial primary key,
name varchar (128) not null,
author varchar (128),
price numeric(19, 2) not null);

truncate Books;
INSERT INTO books (name, author, price) VALUES
('1984', 'George Orwell', 159.00),
('Crime and Punishment', 'Fyodor Dostoevsky', 125.00),
('The Lord of the Rings', 'J.R.R. Tolkien', 250.00),
('Harry Potter and the Philosopher''s Stone', 'J.K. Rowling', 189.00),
('To Kill a Mockingbird', 'Harper Lee', 142.00),
('The Great Gatsby', 'F. Scott Fitzgerald', 119.00),
('The Master and Margarita', 'Mikhail Bulgakov', 135.00),
('War and Peace', 'Leo Tolstoy', 220.00),
('Anna Karenina', 'Leo Tolstoy', 199.00),
('The Brothers Karamazov', 'Fyodor Dostoevsky', 185.00),
('The Catcher in the Rye', 'J.D. Salinger', 129.00),
('The Picture of Dorian Gray', 'Oscar Wilde', 109.00),
('Dracula', 'Bram Stoker', 149.00),
('Frankenstein', 'Mary Shelley', 115.00),
('Jane Eyre', 'Charlotte Bronte', 139.00),
('Pride and Prejudice', 'Jane Austen', 125.00),
('Moby Dick', 'Herman Melville', 169.00),
('Don Quixote', 'Miguel de Cervantes', 200.00),
('One Hundred Years of Solitude', 'Gabriel Garcia Marquez', 175.00),
('Dreamcatcher', 'Stephen King', 199.00);
select * from books;