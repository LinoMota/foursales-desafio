CREATE TABLE users (
   id BIGINT NOT NULL AUTO_INCREMENT,
   username VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   role ENUM('USER', 'ADMIN') DEFAULT 'USER' NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (username)
);

CREATE TABLE products (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  category VARCHAR(255) NOT NULL,
  price DECIMAL(10, 2) NOT NULL,
  stock INT NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE orders (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    status ENUM('PENDING', 'PAID', 'CANCELLED') NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE order_items (
     id BIGINT NOT NULL AUTO_INCREMENT,
     order_id BIGINT NOT NULL,
     product_id BIGINT NOT NULL,
     quantity INT NOT NULL,
     price DECIMAL(10, 2) NOT NULL,
     PRIMARY KEY (id),
     FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
     FOREIGN KEY (product_id) REFERENCES products(id)
);

# cria um usuario default e um admin
# senha normaluser; senhauser
# senha admin: senhaadmin
INSERT INTO users (username, password, role) VALUES
                                                 ('normaluser', '$2a$10$uYmcOinJOofKcuPCJI4/oueFwDRrsqRXldQQuxQfhRVcCdGp4vQTG', 'USER'),
                                                 ('admin', '$2a$10$zJmCDmCV/xfFKevUQfkA4uLY1kaq/QmBsIHYe4zYxs6NHAoYvMISO', 'ADMIN');