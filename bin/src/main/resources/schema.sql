CREATE TABLE product (
    id INTEGER NOT NULL AUTO_INCREMENT,
    product_code VARCHAR(128) NOT NULL,
    product_category VARCHAR(128) NOT NULL,
    brand VARCHAR(128) NOT NULL,
    seller_name VARCHAR(128) NOT NULL,
    color VARCHAR(128) NOT NULL,
    size INTEGER NOT NULL,
    price DECIMAL NOT NULL,
    
    PRIMARY KEY (id)
);