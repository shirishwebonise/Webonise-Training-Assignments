-- Database: "Ebbay"
CREATE DATABASE "ebbay";



-- Table: public."roles"
CREATE TABLE public."user_roles"
(
  id int NOT NULL,
  role character varying(32) NOT NULL,
  CONSTRAINT "roles_pk" PRIMARY KEY (id)
);

-- Table: public."Users"
CREATE TABLE public."users"
(
  id SERIAL,
  email character varying(255) NOT NULL,
  phone character varying(16) NOT NULL,
  password character varying(512),
  first_name character varying(35),
  last_name character varying(35),
  gender char,
  role_id int NOT NULL,
  CONSTRAINT constraint_pk PRIMARY KEY (id),
  CONSTRAINT unique_phone_number UNIQUE (phone),
  CONSTRAINT unique_email UNIQUE (email),
  CONSTRAINT "check_gender_M_F_O" CHECK (gender = ANY (ARRAY['M'::bpchar, 'F'::bpchar, 'O'::bpchar])),
  CONSTRAINT password CHECK (length(password) >= 8),
  CONSTRAINT users_fk_roles FOREIGN KEY (role_id)
      REFERENCES public."user_roles" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);





-- Table: public."Discount_coupons"
CREATE TABLE public."discount_coupons"
(
  id character(10) NOT NULL,
  usage_status boolean,
  lower_limit integer,
  discount_value_percentage integer,
  CONSTRAINT "discountCoupon_pk" PRIMARY KEY (id)
);

ALTER TABLE public."discount_coupons"
ALTER COLUMN  usage_status SET default false;





CREATE TABLE public."products"
(
  id SERIAL,
  name character varying(32),
  CONSTRAINT product_pk PRIMARY KEY (id)
);

CREATE TABLE public."colored_products"
(
  id SERIAL,
  product_id integer NOT NULL,
  color character(5),
  price double precision,
  CONSTRAINT coloredproducts_pk PRIMARY KEY (id),
  CONSTRAINT "coloredproducts_fk_products" FOREIGN KEY (product_id)
    REFERENCES public."products" (id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION
);




-- Table: public."Order_status"
CREATE TABLE public."order_status"
(
  id SERIAL,
  status_text character varying(32),
  CONSTRAINT order_status_pk PRIMARY KEY (id)
);




CREATE TABLE public."orders"
(
  id SERIAL,
  status_id smallint,
  user_id integer,
  order_date date,
  amount double precision,
  CONSTRAINT order_pk PRIMARY KEY (id),
  CONSTRAINT "order_fk_user" FOREIGN KEY (user_id)
      REFERENCES public."users" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT order_fk_status FOREIGN KEY (status_id)
      REFERENCES public."order_status" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);





-- Table: public."Order_item"
CREATE TABLE public."order_items"
(
  product_id integer NOT NULL,
  order_id integer NOT NULL,
  quantity smallint,
  CONSTRAINT orderitem_pk PRIMARY KEY (product_id, order_id),
  CONSTRAINT orderitem_fk_orders FOREIGN KEY (order_id)
      REFERENCES public."orders" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT orderitem_fk_product FOREIGN KEY (product_id)
      REFERENCES public."colored_products" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "quantity_check_positive" CHECK ( quantity > 0 )
);





-- Table: public."Payment_status"
CREATE TABLE public."payment_status"
(
  id SERIAL,
  status_text character(32),
  CONSTRAINT payment_status_pk PRIMARY KEY (id)
);


-- Table: public."payments"
CREATE TABLE public."payments"
(
  order_id integer NOT NULL,
  payment_date date,
  status_id smallint,
  payment_method character(10),
  payable_amount double precision,
  CONSTRAINT payment_pk PRIMARY KEY (order_id),
  CONSTRAINT payment_fk_order FOREIGN KEY (order_id)
      REFERENCES public."orders" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT payment_fk_status FOREIGN KEY (status_id)
      REFERENCES public."payment_status" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Index: public.fki_payment_fk_status
CREATE INDEX fki_payment_fk_status
  ON public."payments"
  USING btree
  (status_id);


-- Table: public."payments_discounts"
CREATE TABLE public."payments_discounts"
(
  payment_id integer,
  coupon_number character(10) NOT NULL,
  CONSTRAINT paymentdiscounts_pk PRIMARY KEY (payment_id, coupon_number),
  CONSTRAINT paymentdiscounts_fk_discountcoupons FOREIGN KEY (coupon_number)
      REFERENCES public."discount_coupons" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT paymentdiscounts_fk_patments FOREIGN KEY (payment_id)
      REFERENCES public."payments" (order_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
-- Index: public.fki_paymentdiscounts_fk_discountcoupons
CREATE INDEX fki_paymentdiscounts_fk_discountcoupons
  ON public."payments_discounts"
  USING btree
  (coupon_number COLLATE pg_catalog."default");
  