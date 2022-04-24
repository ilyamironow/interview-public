CREATE TABLE transfers
(
    ID            integer primary key,
    SOURCE_ID     integer NOT NULL,
    TARGET_ID     integer NOT NULL,
    AMOUNT        NUMERIC(20, 5) NOT NULL,
    TRANSFER_TIME timestamp NOT NULL,
    FOREIGN KEY (SOURCE_ID) REFERENCES accounts(ID),
    FOREIGN KEY (TARGET_ID) REFERENCES accounts(ID)
);