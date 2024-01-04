-- thêm
DELIMITER //
CREATE PROCEDURE insert_orders(
    IN p_customer_id int,
    IN p_total int,
    OUT p_id int
)
BEGIN
    INSERT INTO orders (customer_id, total)
    VALUES (p_customer_id, p_total);
    SELECT LAST_INSERT_ID() INTO p_id;
END; //

-- thêm
DELIMITER //
CREATE PROCEDURE insert_order_detail(
    IN p_order_id int,
    IN p_product_id int,
    IN p_quantity int,
    IN p_price DOUBLE
)
BEGIN
    INSERT INTO order_detail (order_id, product_id, quantity, price)
    VALUES (p_order_id, p_product_id, p_quantity, p_price);
END; //
