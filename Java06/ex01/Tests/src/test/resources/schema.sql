
DROP TABLE IF EXISTS product;

CREATE TABLE products(
	id		BIGINT		PRIMARY KEY,
	name	VARCHAR(30),
	price	INT			NOT NULL
);

