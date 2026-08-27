drop table if exists users;
create table if not exists users (
id bigserial primary key,
email varchar (128) unique not null,
password varchar (128) not null,
role varchar (128),
first_name varchar (128),
last_name varchar (128)
);
truncate users;
insert into users (email, password,role,first_name, last_name)
values ('ivan.petrov@example.com', '{bcrypt}$2a$12$ddik3OaHjj1IwOPikFoRZ.0hReu0iV1cYY3W/8p/4mMDrlM2WbXBS', 'user', 'Ivan', 'Petrov'),
       ('olga.sidorova@example.com', '{bcrypt}$2a$12$pXEDNBFKyH5RBDsfJrwW7O0fICbqGsAPr9xZ4YV/QnzOeK2ejdWnO','user', 'Olga', 'Sidorova'),
       ('sergey.kovalenko@example.com', '{bcrypt}$2a$12$bPy0RVrB6jHTFJwfzxr/k.533WfAkHraeyrpHeBIore7cNFdWQjKu','user', 'Sergey', 'Kovalenko'),
       ('anna.morozova@example.com', '{bcrypt}$2a$12$zfgvVy.U1A.P7JauQL1KFeoAu/cWStAP6GMti09yU5ZWXNw82DFeW','user', 'Anna', 'Morozova'),
       ('dmitry.volkov@example.com', '{bcrypt}$2a$12$ctS6i6uXgcUXpvN6j7sU6e7Gorlt9Jd.6I4lnpCBqlsd9Mf6mwC3K','user', 'Dmitry', 'Volkov'),
       ('elena.kuznetsova@example.com', '{bcrypt}$2a$12$FfO9U7fJjQi3AKvuQz1hZOnd.AOX5mZf2tgoe5HcCSPCJL5lc4rl.','user', 'Elena', 'Kuznetsova'),
       ('alexey.ivanov@example.com', '{bcrypt}/2Io70d1mD5HwW5K.lJmwcjmCYlD2DK','user', 'Alexey', 'Ivanov'),
       ('marina.fedorenko@example.com', '{bcrypt}$2a$12$hNnKU/r9FTQBgGgmVuttyOA4K3Nsn4n9YEjudjXbZ3zvmMsQkRbSK','user', 'Marina', 'Fedorenko'),
       ('viktor.orlov@example.com', '{bcrypt}$2a$12$n0vGP6RhBsQTkWwu92n07.yHFvqt71rYzYX3iQnLmYJw9NOyzCAiK','user', 'Viktor', 'Orlov'),
       ('svetlana.baranova@example.com', '{bcrypt}$2a$12$zo5ySttZynoITWyz5dl7oez5iWxacOFQ97MfVvB.11I1dF/uMy8iq','user', 'Svetlana', 'Baranova'),
       ('alex', '{bcrypt}$2a$12$bq19xQxiLggyAooFyA.JD.A00qHRC4RfPrPT7Gw0uBMUAb71wWmAu', 'admin', 'Alex', 'Mardosevich'),
       ('manager', '{bcrypt}$2a$12$y8dD9mElhEMYMQC8BYNqau64keRQ3.BG9edCeulLa2BIQriNarGNW', 'manager', 'manager', 'manager'),
       ('guest', '{bcrypt}$2a$12$vOUzQFuUMUfsxrQSCPGGjeO1JA56PJYEKz7QOoVrQzlyioqrG2PCO', 'guest', 'guest', 'guest'),
       ('user', '{bcrypt}$2a$12$goPDa.HCyXlchc9ZotgL5Oo3nhm01/9n8DtmtsuzHbsgtdOouNvRS', 'user', 'user', 'user');
select * from users;
