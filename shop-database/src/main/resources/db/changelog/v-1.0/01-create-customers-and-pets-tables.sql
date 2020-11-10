CREATE TABLE customers
(
    id    bigserial PRIMARY KEY,
    login varchar(30) NOT NULL UNIQUE
);
GO

CREATE TABLE pets
(
    id          bigserial PRIMARY KEY,
    name        varchar(50) NOT NULL,
    customer_id bigint      NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers (id)
);
GO

