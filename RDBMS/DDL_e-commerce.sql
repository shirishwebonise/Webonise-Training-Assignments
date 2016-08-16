-- Database: "Ebbay"
CREATE DATABASE "Ebbay";


-- Table: public."Users"
CREATE TABLE public."Users"
(
  password character(512),
  first_name character(35),
  last_name character(35),
  email character(255) NOT NULL,
  gender character(1),
  phone character(16) NOT NULL,
  CONSTRAINT constraint_pk PRIMARY KEY (email),
  CONSTRAINT unique_phone_number UNIQUE (phone),
  CONSTRAINT "check_gender_M_F_O" CHECK (gender = ANY (ARRAY['M'::bpchar, 'F'::bpchar, 'O'::bpchar])),
  CONSTRAINT password CHECK (length(password) >= 8)
);

-- Table: public."Buyers"
CREATE TABLE public."Buyers"
(
  user_id character(255) NOT NULL,
  CONSTRAINT buyer_pk PRIMARY KEY (user_id),
  CONSTRAINT buyer_fk_user FOREIGN KEY (user_id)
      REFERENCES public."Users" (email) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Table: public."Inventory_managers"
CREATE TABLE public."Inventory_managers"
(

);

-- Table: public."Discount_coupons"
CREATE TABLE public."Discount_coupons"
(
  coupon_number character(10) NOT NULL,
  usage_status boolean,
  lower_limit integer,
  discount_value_percentage integer,
  CONSTRAINT "DiscountCoupon_pk" PRIMARY KEY (coupon_number)
);

-- Table: public."Products"
CREATE TABLE public."Products"
(
  product_id character(10) NOT NULL,
  name character(32),
  price double precision,
  CONSTRAINT product_pk PRIMARY KEY (product_id)
);

-- Table: public."Orders"
CREATE TABLE public."Orders"
(
  order_id character(10) NOT NULL,
  amount double precision,
  status_id smallint,
  buyer_id character(10),
  CONSTRAINT order_pk PRIMARY KEY (order_id),
  CONSTRAINT "Order_fk_buyer" FOREIGN KEY (buyer_id)
      REFERENCES public."Buyers" (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT order_fk_status FOREIGN KEY (status_id)
      REFERENCES public."Order_status" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Table: public."Order_status"
CREATE TABLE public."Order_status"
(
  id smallint NOT NULL,
  status_text character(32),
  CONSTRAINT order_status_pk PRIMARY KEY (id)
);

-- Table: public."Order_item"
CREATE TABLE public."Order_item"
(
  product_id character(10) NOT NULL,
  order_id character(10) NOT NULL,
  quantity smallint,
  CONSTRAINT orderitem_pk PRIMARY KEY (product_id, order_id),
  CONSTRAINT orderitem_fk_orders FOREIGN KEY (order_id)
      REFERENCES public."Orders" (order_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT orderitem_fk_product FOREIGN KEY (product_id)
      REFERENCES public."Products" (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Index: public.fki_orderitem_fk_orders
CREATE INDEX fki_orderitem_fk_orders
  ON public."Order_item"
  USING btree
  (order_id COLLATE pg_catalog."default");

-- Index: public.fki_orderitem_fk_product
CREATE INDEX fki_orderitem_fk_product
  ON public."Order_item"
  USING btree
  (product_id COLLATE pg_catalog."default");

-- Index: public.orderitem_index_pk
CREATE INDEX orderitem_index_pk
  ON public."Order_item"
  USING btree
  (product_id COLLATE pg_catalog."default", order_id COLLATE pg_catalog."default");

-- Index: public."fki_Order_fk_buyer"
CREATE INDEX "fki_Order_fk_buyer"
  ON public."Orders"
  USING btree
  (buyer_id COLLATE pg_catalog."default");

-- Index: public.fki_order_fk_status
CREATE INDEX fki_order_fk_status
  ON public."Orders"
  USING btree
  (status_id);


-- Index: public."fki_Order_fk_buyer"
CREATE INDEX "fki_Order_fk_buyer"
  ON public."Orders"
  USING btree
  (buyer_id COLLATE pg_catalog."default");

-- Index: public.fki_order_fk_status
CREATE INDEX fki_order_fk_status
  ON public."Orders"
  USING btree
  (status_id);


-- Table: public."Payment_status"
CREATE TABLE public."Payment_status"
(
  id smallint NOT NULL,
  status_text character(32),
  CONSTRAINT payment_status_pk PRIMARY KEY (id)
);


-- Table: public."Payments"
CREATE TABLE public."Payments"
(
  order_id character(10) NOT NULL,
  payment_date date,
  status_id smallint,
  CONSTRAINT payment_pk PRIMARY KEY (order_id),
  CONSTRAINT payment_fk_order FOREIGN KEY (order_id)
      REFERENCES public."Orders" (order_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT payment_fk_status FOREIGN KEY (status_id)
      REFERENCES public."Payment_status" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Index: public.fki_payment_fk_order
CREATE INDEX fki_payment_fk_order
  ON public."Payments"
  USING btree
  (order_id COLLATE pg_catalog."default");

-- Index: public.fki_payment_fk_status
CREATE INDEX fki_payment_fk_status
  ON public."Payments"
  USING btree
  (status_id);

-- Table: public."Payments_discounts"
CREATE TABLE public."Payments_discounts"
(
  payment_id character(10) NOT NULL,
  coupon_number character(10) NOT NULL,
  CONSTRAINT paymentdiscounts_pk PRIMARY KEY (payment_id, coupon_number),
  CONSTRAINT paymentdiscounts_fk_discountcoupons FOREIGN KEY (coupon_number)
      REFERENCES public."Discount_coupons" (coupon_number) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT paymentdiscounts_fk_patments FOREIGN KEY (payment_id)
      REFERENCES public."Payments" (order_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Index: public.fki_paymentdiscounts_fk_discountcoupons
CREATE INDEX fki_paymentdiscounts_fk_discountcoupons
  ON public."Payments_discounts"
  USING btree
  (coupon_number COLLATE pg_catalog."default");

-- Index: public.fki_paymentdiscounts_fk_patments
CREATE INDEX fki_paymentdiscounts_fk_patments
  ON public."Payments_discounts"
  USING btree
  (payment_id COLLATE pg_catalog."default");
