-- password in plaintext: "password"
INSERT INTO USER (user_id, password, email, username, name, last_name, active)
VALUES
  (1, '$2a$06$OAPObzhRdRXBCbk7Hj/ot.jY3zPwR8n7/mfLtKIgTzdJa4.6TwsIm', 'user@mail.com', 'user', 'Name', 'Surname',
   1);
-- password in plaintext: "password"
INSERT INTO USER (user_id, password, email, username, name, last_name, active)
VALUES
  (2, '$2a$06$OAPObzhRdRXBCbk7Hj/ot.jY3zPwR8n7/mfLtKIgTzdJa4.6TwsIm', 'johndoe@gmail.com', 'johndoe', 'John', 'Doe', 1);
-- password in plaintext: "password"
INSERT INTO USER (user_id, password, email, username, name, last_name, active)
VALUES (3, '$2a$06$OAPObzhRdRXBCbk7Hj/ot.jY3zPwR8n7/mfLtKIgTzdJa4.6TwsIm', 'name@gmail.com', 'namesurname', 'Name',
        'Surname', 1);

INSERT INTO ROLE (role_id, role)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO ROLE (role_id, role)
VALUES (2, 'ROLE_USER');

INSERT INTO USER_ROLE (user_id, role_id)
VALUES (1, 1);
INSERT INTO USER_ROLE (user_id, role_id)
VALUES (1, 2);
INSERT INTO USER_ROLE (user_id, role_id)
VALUES (2, 2);
INSERT INTO USER_ROLE (user_id, role_id)
VALUES (3, 2);

INSERT INTO PRODUCT (name, description, quantity, price, image_url)
VALUES ('Soap', 'Pears baby soap for Kids', 1, 35.75, 'https://images.pexels.com/photos/4465124/pexels-photo-4465124.jpeg?auto=compress&cs=tinysrgb&w=900');
INSERT INTO PRODUCT (name, description, quantity, price, image_url)
VALUES ('Tooth Brush', 'Signal Tooth Brushes Size in (L, M, S)', 5, 34.50, 'https://images.pexels.com/photos/298611/pexels-photo-298611.jpeg?auto=compress&cs=tinysrgb&w=900');
INSERT INTO PRODUCT (name, description, quantity, price, image_url)
VALUES ('Shirt', 'Casual Shirt imported from France', 3, 1500.00, 'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?auto=format&fit=crop&w=900&q=80');
INSERT INTO PRODUCT (name, description, quantity, price, image_url)
VALUES ('Office Bag', 'Leather bag imported from USA', 40, 1000.00, 'https://images.unsplash.com/photo-1548036328-c9fa89d128fa?auto=format&fit=crop&w=900&q=80');
INSERT INTO PRODUCT (name, description, quantity, price, image_url)
VALUES ('Bottle', 'Hot Water Bottles', 80, 450.45, 'https://images.unsplash.com/photo-1523362628745-0c100150b504?auto=format&fit=crop&w=900&q=80');
INSERT INTO PRODUCT (name, description, quantity, price, image_url)
VALUES ('Wrist Watch', 'Imported wrist watches from swiss', 800, 2500.00, 'https://images.unsplash.com/photo-1522312346375-d1a52e2b99b3?auto=format&fit=crop&w=900&q=80');
INSERT INTO PRODUCT (name, description, quantity, price, image_url)
VALUES ('Mobile Phone', '3G/4G capability', 700, 45000.00, 'https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?auto=format&fit=crop&w=900&q=80');
INSERT INTO PRODUCT (name, description, quantity, price, image_url)
VALUES ('Shampoo', 'Head and Shoulders Shampoo', 500, 300.00, 'https://images.unsplash.com/photo-1556228578-8c89e6adf883?auto=format&fit=crop&w=900&q=80');
INSERT INTO PRODUCT (name, description, quantity, price, image_url)
VALUES ('Leather Wallets', 'Imported Leather Wallets from AUS', 1000, 500.00, 'https://images.unsplash.com/photo-1627123424574-724758594e93?auto=format&fit=crop&w=900&q=80');
INSERT INTO PRODUCT (name, description, quantity, price, image_url)
VALUES ('Camera', 'Imported Canon camera from USA', 10, 85000.00, 'https://images.unsplash.com/photo-1516035069371-29a1b244cc32?auto=format&fit=crop&w=900&q=80');