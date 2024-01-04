-- phân trang
DELIMITER //
create procedure pagi_order(IN _limit int, IN no_page int, OUT total int)
BEGIN
    declare _offset int;
    SET _offset = (no_page - 1) * _limit;
    SET  total = CEIL((SELECT count(*) FROM orders) / _limit);
    SELECT * FROM orders LIMIT _limit OFFSET _offset;
end; //

-- sua trang thai
DELIMITER //
CREATE PROCEDURE change_status_order(
    IN p_id INT,
    IN p_status INT
)
BEGIN
    UPDATE orders
    SET status = p_status
    WHERE id = p_id;
END; //

-- lay order detail
DELIMITER //
CREATE PROCEDURE get_order_detail_by_order_id(IN p_id int)
BEGIN
    SELECT * FROM order_detail WHERE order_id = p_id;
end; //

-- lay order theo nguoi dang nhap
DELIMITER //
CREATE PROCEDURE get_order_by_customer_id(
    IN p_id int
)
BEGIN
    SELECT *
    FROM orders
    WHERE customer_id = p_id;
END; //

--
DELIMITER //
CREATE PROCEDURE get_order_by_id(
    IN p_id int
)
BEGIN
    SELECT *
    FROM orders
    WHERE id = p_id;
END; //