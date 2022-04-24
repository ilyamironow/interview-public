CREATE TABLE accounts
(
    ID         integer primary key,
    FIRST_NAME VARCHAR(50),
    LAST_NAME  VARCHAR(50),
    BALANCE    NUMERIC(20, 5) NOT NULL
);