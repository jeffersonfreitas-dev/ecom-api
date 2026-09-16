CREATE TABLE orders(
    id VARCHAR(60) PRIMARY KEY,
    customer_id VARCHAR(60) NOT NULL,
    date TIMESTAMP NOT NULL,
    active BOOLEAN NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT order_customer_fk FOREIGN KEY(customer_id) REFERENCES customers(id)
);

CREATE TABLE order_items(
    id VARCHAR(60) PRIMARY KEY,
    order_Id VARCHAR(60) NOT NULL,
    product_Id VARCHAR(60) NOT NULL,
    quantity DOUBLE PRECISION NOT NULL,
    value DECIMAL(10,2) NOT NULL,
    CONSTRAINT orders_items_fk FOREIGN KEY(order_id) REFERENCES orders(id),
    CONSTRAINT items_product_fk FOREIGN KEY(product_id) REFERENCES products(id)
);