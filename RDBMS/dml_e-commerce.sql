INSERT INTO user_roles
VALUES ('1', 'buyer');

INSERT INTO user_roles
VALUES ('2', 'inventory manager');




INSERT INTO users (email, password, first_name, last_name, gender, phone, role_id)
VALUES ('parsekarshirish@gmail.com', 'jDFkjdfSD', 'shirish', 'parsekar', 'M', '9545327373', '1');

INSERT INTO users (email, password, first_name, last_name, gender, phone, role_id)
VALUES ('rahulsss@gmail.com', 'jDFkjdfSD', 'rahul', 'dcosta', 'M', '4545454545', '1');

INSERT INTO users (email, password, first_name, last_name, gender, phone, role_id)
VALUES ('rohitjain@gmail.com', 'jDFkjdfSD', 'rohit', 'jain', 'M', '7897896544', '1');

INSERT INTO users (email, password, first_name, last_name, gender, phone, role_id)
VALUES ('sonianaik@yahoo.com', 'jDFkjdfSD', 'sonia', 'naik', 'F', '4444444444', '2');

INSERT INTO users (email, password, first_name, last_name, gender, phone, role_id)
VALUES ('riyasen@gmail.com', 'jDFkjdfSD', 'riya', 'sen', 'F', '1145327373', '1');




INSERT INTO discount_coupons ( id, lower_limit, discount_value_percentage )
VALUES ('642178', '1000', '10');

INSERT INTO discount_coupons ( id, lower_limit, discount_value_percentage )
VALUES ('841108', '500', '20');

INSERT INTO discount_coupons ( id, lower_limit, discount_value_percentage )
VALUES ('689678', '1200', '15');

INSERT INTO discount_coupons ( id, lower_limit, discount_value_percentage )
VALUES ('024172', '1500', '10');

INSERT INTO discount_coupons ( id, lower_limit, discount_value_percentage )
VALUES ('542174', '1000', '10');




INSERT INTO products ( name )
VALUES ( 'AAA');

INSERT INTO products ( name)
VALUES ( 'BBB');

INSERT INTO products ( name)
VALUES ( 'CCC');

INSERT INTO products ( name)
VALUES ( 'DDD');

INSERT INTO products ( name)
VALUES ( 'EEE');



INSERT INTO colored_products ( product_id, color, price )
VALUES ( '1', 'red', '500' );

INSERT INTO colored_products ( product_id, color, price )
VALUES ( '1', 'green', '600' );

INSERT INTO colored_products ( product_id, color, price )
VALUES ( '2', 'yellow', '1500' );

INSERT INTO colored_products ( product_id, color, price )
VALUES ( '2', 'grey', '1600' );

INSERT INTO colored_products ( product_id, color, price )
VALUES ( '3', 'grey', '1300' );

INSERT INTO colored_products ( product_id, color, price )
VALUES ( '3', 'red', '1000' );




INSERT INTO order_status
VALUES ( '1', 'waiting apporval');

INSERT INTO order_status
VALUES ( '2', 'approved');

INSERT INTO order_status
VALUES ( '3', 'shipped');

INSERT INTO order_status
VALUES ( '4', 'delivered');

INSERT INTO order_status
VALUES ( '5', 'cancelled');




INSERT INTO orders ( status_id, user_id)
VALUES ( '1', '1' );

INSERT INTO orders ( status_id, user_id)
VALUES ( '1', '1' );

INSERT INTO orders ( status_id, user_id)
VALUES ( '2', '2' );

INSERT INTO orders ( status_id, user_id)
VALUES ( '3', '2' );

INSERT INTO orders ( status_id, user_id)
VALUES ( '5', '4' );




INSERT INTO order_items 
VALUES ( '1', '1', '3');

INSERT INTO order_items 
VALUES ( '1', '2', '2');

INSERT INTO order_items 
VALUES ( '2', '2', '1');

INSERT INTO order_items 
VALUES ( '2', '1', '2');

INSERT INTO order_items 
VALUES ( '1', '3', '3');

INSERT INTO order_items 
VALUES ( '3', '1', '2');

INSERT INTO order_items 
VALUES ( '3', '2', '1');

INSERT INTO order_items 
VALUES ( '4', '5', '4');




INSERT INTO payment_status ( status_text )
VALUES ( 'init' );
INSERT INTO payments 
VALUES ( '6', '2-25-2016', '1' );
INSERT INTO payment_status ( status_text )
VALUES ( 'approved' );

INSERT INTO payment_status ( status_text )
VALUES ( 'error' );

INSERT INTO payment_status ( status_text )
VALUES ( 'cancelled' );



INSERT INTO payments 
VALUES ( '1', '12-2-2016', '1' );

INSERT INTO payments 
VALUES ( '2', '1-3-2016', '1' );

INSERT INTO payments 
VALUES ( '3', '2-25-2016', '1' );

INSERT INTO payments 
VALUES ( '4', '10-12-2016', '1' );

INSERT INTO payments 
VALUES ( '5', '12-2-2016', '1' );

INSERT INTO payments 
VALUES ( '6', '2-25-2016', '1' );

