
DROP TABLE IF EXISTS products;

CREATE TABLE products(
	id		BIGINT		PRIMARY KEY,
	name	VARCHAR(30),
	price	INT			NOT NULL
);

