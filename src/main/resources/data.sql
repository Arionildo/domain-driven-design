CREATE TABLE IF NOT EXISTS customer (
    cpf VARCHAR(255) PRIMARY KEY,
    order_limit DECIMAL(19, 2)
);

CREATE TABLE IF NOT EXISTS products (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    price DECIMAL(19, 2)
);

CREATE TABLE IF NOT EXISTS orders (
    id BIGINT PRIMARY KEY,
    order_date TIMESTAMP,
    total_value DECIMAL(19, 2)
);

CREATE TABLE IF NOT EXISTS order_items (
    id BIGINT PRIMARY KEY,
    order_id BIGINT,
    product_id BIGINT,
    quantity INT,
    price DECIMAL(19, 2),
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE SET NULL
);


INSERT INTO products (price, id, name) VALUES (5.00, 1, 'coke');
INSERT INTO products (price, id, name) VALUES (9.50, 2, 'heineken');
INSERT INTO products (price, id, name) VALUES (35.90, 3, 'hamburger california');
INSERT INTO products (price, id, name) VALUES (37.80, 4, 'hamburger texas');
INSERT INTO products (price, id, name) VALUES (39.50, 5, 'hamburger florida');
INSERT INTO products (price, id, name) VALUES (6.00, 6, 'rustic potatoes');
INSERT INTO products (price, id, name) VALUES (12.90, 7, 'petit gâteau');

INSERT INTO customer (cpf, order_limit) VALUES ('999.888.777-66', 600.00);
INSERT INTO customer (cpf, order_limit) VALUES ('000.111.222-33', 50.00);
