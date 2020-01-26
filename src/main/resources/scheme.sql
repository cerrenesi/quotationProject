create table if not exists Quote
(
    id   IDENTITY NOT NULL PRIMARY KEY,
    isin varchar(12) NOT NULL,
    bid  DECIMAL(10, 2),
    ask  DECIMAL(10, 2),
    elvl DECIMAL(10, 2) NOT NULL
);
