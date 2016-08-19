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

INSERT INTO orders ( status_id, user_id)
VALUES ( '2', '4' );


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
VALUES ( 'approved' );

INSERT INTO payment_status ( status_text )
VALUES ( 'error' );

INSERT INTO payment_status ( status_text )
VALUES ( 'successful' );




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
VALUES ( '7', '2-25-2016', '4' );



SELECT o.id, o.status_id as order_status, p.status_id as payment_status 
FROM orders o, payments p 
WHERE o.id = p.order_id AND o.id = '1';



CREATE OR REPLACE FUNCTION makePayment( oId integer ) RETURNS text AS $$
DECLARE
	payment_status integer;
	order_status integer;
	transaction_status text;
BEGIN
	SELECT p.status_id, o.status_id INTO payment_status, order_status
	FROM orders o, payments p 
	WHERE o.id = p.order_id AND o.id = oId;

	IF payment_status = 2 AND (order_status = 2 OR order_status = 3)
	THEN
		-- make payment according to payment mode
		UPDATE payments SET status_id = 5
		WHERE order_id = oId;

		transaction_status := 'transaction successful';
	ELSE
		transaction_status := 'transaction not possible';
	END IF;

	RETURN transaction_status;
END;
$$ LANGUAGE plpgsql;


-- trigger function to update order amount when an item is added to the orders
CREATE OR REPLACE FUNCTION calcOrderTotal()
RETURNS TRIGGER
AS $$
BEGIN
	PERFORM calculateOrderTotal(NEW.order_id);

	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trig_order_items_insert 
AFTER INSERT ON order_items
FOR EACH ROW 
EXECUTE PROCEDURE calcOrderTotal();



-- function to create and set discounted amount
CREATE OR REPLACE FUNCTION calculateAndSetDiscountedAmount( orderId integer ) 
RETURNS double precision 
AS $$
DECLARE
	order_amount double precision;
	coupon_numb character(10);
	coupon_discount integer;
	coupon_discount_lower_limit integer;
	discounted_amount double precision;
	row payments_discounts%rowtype;
BEGIN
	SELECT * INTO order_amount
	FROM calculateOrderTotal(orderId);

	discounted_amount := order_amount;

	FOR row IN
        SELECT * FROM payments_discounts WHERE payment_id = orderId
    LOOP
        SELECT  lower_limit, discount_value_percentage INTO coupon_discount_lower_limit, coupon_discount
        FROM discount_coupons 
        WHERE id = row.coupon_number;

        IF order_amount > coupon_discount_lower_limit
        THEN
        	discounted_amount := ( discounted_amount - discounted_amount * ( discount_value_percentage / 100 ) );
        END IF;


    END LOOP;

    UPDATE payments SET payable_amount = discounted_amount
    WHERE payments.order_id = orderId;
    
    RETURN discounted_amount;
END;
$$ LANGUAGE plpgsql;



-- function to calculate total amount of an order
CREATE OR REPLACE FUNCTION calculateOrderTotal( orderId integer )
RETURNS double precision
AS $$
DECLARE
	order_amount double precision;
BEGIN
	SELECT SUM(oi.quantity*cp.price) INTO order_amount
	FROM order_items oi, colored_products cp 
	WHERE oi.product_id = cp.id AND oi.order_id = orderId;

	UPDATE orders SET amount = order_amount
    WHERE id = orderId;

	RETURN order_amount;
END;
$$ LANGUAGE plpgsql;


INSERT INTO payments_discounts
VALUES ('1', '642178');





-- order details
CREATE OR REPLACE VIEW orders_details
AS 
select o.id AS "order id", o.amount as "order total", o.order_date as "date", (o.amount - p.payable_amount::double precision) as "discount", p.payment_method as "payment method"
from orders o, payments p where o.id = p.order_id;




-- monthly reports generation
-- use materialized view
CREATE MATERIALIZED VIEW monthly_report
AS
	select orders.id as "order id", order_date as "order date", createCommaSeparatedProducts(orders.id) AS "product names", createCommaSeparatedProductPrices(orders.id) AS "product prices",amount as "total cost", (first_name || ' ' || last_name) as "user name", email from users, orders 
	where users.id = orders.user_id AND order_date > (CURRENT_DATE - INTERVAL '30 day');

-- refresh view 
REFRESH MATERIALIZED VIEW monthly_report




-- function to create comma separated product names
-- used in monthly report generation query
CREATE OR REPLACE FUNCTION createCommaSeparatedProducts(orderId integer)
RETURNS text
AS $$
DECLARE
	prod_names text;
BEGIN
	SELECT string_agg(products.name, ', ') INTO prod_names
	FROM order_items, products, colored_products
	WHERE order_items.product_id = colored_products.id AND products.id = colored_products.product_id AND order_items.order_id = orderId;

	RETURN prod_names;
END;
$$ LANGUAGE plpgsql;


-- function to create comma separated product prices
-- used in monthly report generation query
CREATE OR REPLACE FUNCTION createCommaSeparatedProductPrices(orderId integer)
RETURNS text
AS $$
DECLARE
	prod_names text;
BEGIN
	SELECT string_agg(CAST(colored_products.price as text), ', ') INTO prod_names
	FROM order_items, products, colored_products
	WHERE order_items.product_id = colored_products.id AND products.id = colored_products.product_id AND order_items.order_id = orderId;

	RETURN prod_names;
END;
$$ LANGUAGE plpgsql;

