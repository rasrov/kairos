DROP TABLE IF EXISTS BRAND;
DROP TABLE IF EXISTS PRICES;
DROP TABLE IF EXISTS PRICE_LIST;
DROP TABLE IF EXISTS PRODUCT;

CREATE TABLE BRAND (
	id integer NOT NULL,
	name varchar(15) NOT NULL,
    CONSTRAINT brand_pkey PRIMARY KEY (id)
);

CREATE TABLE PRICE_LIST (
    id integer NOT NULL,
    fee integer NOT NULL,
    CONSTRAINT price_list_pkey PRIMARY KEY (id)
);

CREATE TABLE PRODUCT (
    id integer NOT NULL,
    name varchar(50) NOT NULL,
    CONSTRAINT product_pkey PRIMARY KEY (id)
);

CREATE TABLE PRICES (
    id integer NOT NULL,
	brand_id integer NOT NULL,
	start_date timestamp NOT NULL,
	end_date timestamp NOT NULL,
	price_id integer NOT NULL,
	product_id integer NOT NULL,
	priority integer NOT NULL,
	price numeric(10, 2) NOT NULL,
	curr varchar(5) NOT NULL,
	FOREIGN KEY (brand_id) REFERENCES brand(id)
);

INSERT INTO BRAND (id, name) VALUES (1, 'ZARA');
INSERT INTO BRAND (id, name) VALUES (2, 'PULL&BEAR');
INSERT INTO BRAND (id, name) VALUES (3, 'BERSHKA');

INSERT INTO PRICE_LIST (id, fee) VALUES (1, 5);
INSERT INTO PRICE_LIST (id, fee) VALUES (2, 10);
INSERT INTO PRICE_LIST (id, fee) VALUES (3, 15);
INSERT INTO PRICE_LIST (id, fee) VALUES (4, 21);

INSERT INTO PRODUCT (id, name) VALUES (35455, 'SUETER');
INSERT INTO PRODUCT (id, name) VALUES (35456, 'ABRIGO');
INSERT INTO PRODUCT (id, name) VALUES (35457, 'SUDADERA');

INSERT INTO PRICES (id, brand_id, start_date, end_date, price_id, product_id, priority, price, curr) VALUES (1, 1, '2020-06-14T00:00:00', '2020-12-31T23:59:59', 1, 35455, 0, 35.50, 'EUR');
INSERT INTO PRICES (id, brand_id, start_date, end_date, price_id, product_id, priority, price, curr) VALUES (2, 1, '2020-06-14T15:00:00', '2020-06-14T18:30:00', 2, 35455, 1, 25.45, 'EUR');
INSERT INTO PRICES (id, brand_id, start_date, end_date, price_id, product_id, priority, price, curr) VALUES (3, 1, '2020-06-15T00:00:00', '2020-06-15T11:00:00', 3, 35455, 1, 30.50, 'EUR');
INSERT INTO PRICES (id, brand_id, start_date, end_date, price_id, product_id, priority, price, curr) VALUES (4, 1, '2020-06-15T16:00:00', '2020-12-31T23:59:59', 4, 35455, 1, 38.95, 'EUR');