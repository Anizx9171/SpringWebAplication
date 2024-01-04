-- thêm
DELIMITER //
CREATE PROCEDURE insert_customer(
    IN p_name VARCHAR(255),
    IN p_email VARCHAR(255),
    IN p_address VARCHAR(255),
    IN p_phone_number DOUBLE,
    IN p_password VARCHAR(255),
    IN p_image VARCHAR(255)
)
BEGIN
    INSERT INTO customer (name, email, address, phone_number, password, image_url)
    VALUES (p_name, p_email, p_address, p_phone_number, p_password, p_image);
END; //

-- sửa
DELIMITER //
CREATE PROCEDURE update_customer(
    IN p_id INT,
    IN p_name VARCHAR(255),
    IN p_email VARCHAR(255),
    IN p_address VARCHAR(255),
    IN p_phone_number DOUBLE,
    IN p_password VARCHAR(255),
    IN p_admin BIT(1),
    IN p_banned BIT(1)
)
BEGIN
    UPDATE customer
    SET name = p_name,
        email = p_email,
        address = p_address,
        phone_number = p_phone_number,
        password = p_password,
        admin = p_admin,
        banned = p_banned
    WHERE id = p_id;
END; //

-- xóa
DELIMITER //
CREATE PROCEDURE delete_customer(
    IN p_id INT
)
BEGIN
    DELETE FROM customer
    WHERE id = p_id;
END; //

-- tìm theo id
DELIMITER //
CREATE PROCEDURE find_customer_by_id(
    IN p_id INT
)
BEGIN
    SELECT *
    FROM customer
    WHERE id = p_id;
END; //

-- tìm theo tên
DELIMITER //

CREATE PROCEDURE find_customer_by_name(
    IN p_name VARCHAR(255)
)
BEGIN
    SELECT *
    FROM customer
    WHERE name = p_name;
END; //

-- phân trang
DELIMITER //
create procedure pagi_customer(IN _limit int, IN no_page int, OUT total int)
BEGIN
    declare _offset int;
    SET _offset = (no_page - 1) * _limit;
    SET  total = CEIL((SELECT count(*) FROM customer) / _limit);
    SELECT * FROM customer LIMIT _limit OFFSET _offset;
end; //

-- tìm theo email
DELIMITER //
CREATE PROCEDURE find_customer_by_email(
    IN p_email VARCHAR(255)
)
BEGIN
    SELECT *
    FROM customer
    WHERE email = p_email;
END; //

-- change banned
DELIMITER //
CREATE PROCEDURE change_role_customer(
    IN p_id INT
)
BEGIN
    UPDATE customer
    SET
        admin = not admin
    WHERE id = p_id;
END; //

-- lay het
DELIMITER //
CREATE PROCEDURE find_all_customer(
)
BEGIN
    SELECT *
    FROM customer;
END; //