-- thêm
DELIMITER //
CREATE PROCEDURE insert_product(
    IN p_category_id INT,
    IN p_name VARCHAR(255),
    IN p_description VARCHAR(255),
    IN p_price DOUBLE,
    IN p_quantity INT,
    In p_image VARCHAR(255)
)
BEGIN
    INSERT INTO product (category_id, name, description, price, quantity, image_url)
    VALUES (p_category_id, p_name, p_description, p_price, p_quantity, p_image);
END; //

-- sửa
DELIMITER //
CREATE PROCEDURE update_product(
    IN p_id INT,
    IN p_category_id INT,
    IN p_name VARCHAR(255),
    IN p_description VARCHAR(255),
    IN p_price DOUBLE,
    IN p_quantity INT,
    IN p_image VARCHAR(255)
)
BEGIN
    UPDATE product
    SET category_id = p_category_id,
        name = p_name,
        description = p_description,
        price = p_price,
        quantity = p_quantity,
        image_url = p_image
    WHERE id = p_id;
END; //

-- xóa
DELIMITER //
CREATE PROCEDURE delete_product(
    IN p_id INT
)
BEGIN
    DELETE FROM product
    WHERE id = p_id;
END; //

-- tìm theo id
DELIMITER //
CREATE PROCEDURE find_product_by_id(
    IN p_id INT
)
BEGIN
    SELECT *
    FROM product
    WHERE id = p_id;
END; //

-- tìm theo tên
DELIMITER //
CREATE PROCEDURE find_product_by_name(
    IN p_name VARCHAR(255)
)
BEGIN
    SELECT *
    FROM product
    WHERE name LIKE p_name;
END; //

-- phân trang
DELIMITER //
create procedure pagi_product(IN _limit int, IN no_page int, OUT total int)
BEGIN
    declare _offset int;
    SET _offset = (no_page - 1) * _limit;
    SET  total = CEIL((SELECT count(*) FROM product) / _limit);
    SELECT * FROM product LIMIT _limit OFFSET _offset;
end; //

DELIMITER //
CREATE PROCEDURE change_status_product(
    IN p_id INT
)
BEGIN
    UPDATE product
    SET status = not status
    WHERE id = p_id;
END; //

-- find by cateId
DELIMITER //
CREATE PROCEDURE find_product_by_cat_id(
    IN p_cat_id INT
)
BEGIN
    SELECT *
    FROM product
    WHERE category_id = p_cat_id;
END; //

-- get number of products
DELIMITER //
CREATE PROCEDURE get_number_of_product_and_order(OUT count_product int, OUT count_order int)
BEGIN
    SET  count_product = (SELECT COUNT(*) FROM product);
    SET count_order =  (SELECT COUNT(*) FROM orders);
end //