CREATE TABLE cart (
  id VARCHAR(255) PRIMARY KEY,
  supermarket_id INT,
  name VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE cart_items (
    cart_id VARCHAR(255) NOT NULL,
    item_key VARCHAR(255) NOT NULL,
    item_value DOUBLE NOT NULL,
    PRIMARY KEY (cart_id, item_key));

ALTER TABLE cart_items ADD CONSTRAINT FK_cart_id FOREIGN KEY (cart_id) REFERENCES cart (id);

INSERT INTO cart (id, supermarket_id, name) VALUES ('82aff49e-3d9a-42e1-8427-1b084e748d2b', '1', 'Carrefour');
INSERT INTO cart (id, supermarket_id, name) VALUES ('bd3da8a1-a08d-4f0a-9bc6-e45e84b42943', '2', 'Makro');

INSERT INTO cart_items (cart_id, item_key, item_value) VALUES ('82aff49e-3d9a-42e1-8427-1b084e748d2b', 'Tomato', 1.50);
INSERT INTO cart_items (cart_id, item_key, item_value) VALUES ('bd3da8a1-a08d-4f0a-9bc6-e45e84b42943', 'Cake', 51.00);