-- hàm category
-- thêm
DELIMITER //
CREATE PROCEDURE insert_category(
    IN p_name VARCHAR(255),
    IN p_description VARCHAR(255)
)
BEGIN
    INSERT INTO category (name, description)
    VALUES (p_name, p_description);
END; //

-- sửa
DELIMITER //
CREATE PROCEDURE update_category(
    IN p_id INT,
    IN p_name VARCHAR(255),
    IN p_description VARCHAR(255)
)
BEGIN
    UPDATE category
    SET name = p_name,
        description = p_description
    WHERE id = p_id;
END; //

-- xóa
DELIMITER //
CREATE PROCEDURE delete_category(
    IN p_id INT
)
BEGIN
    DELETE FROM category
    WHERE id = p_id;
END; //

-- tìm theo id
DELIMITER //
CREATE PROCEDURE find_category_by_id(
    IN p_id INT
)
BEGIN
    SELECT *
    FROM category
    WHERE id = p_id;
END; //

-- tìm theo tên
DELIMITER //
CREATE PROCEDURE find_category_by_name(
    IN p_name VARCHAR(255)
)
BEGIN
    SELECT *
    FROM category
    WHERE name LIKE p_name;
END; //

-- phân trang
DELIMITER //
create procedure pagi_category(IN _limit int, IN no_page int, OUT total int)
BEGIN
    declare _offset int;
    SET _offset = (no_page - 1) * _limit;
    SET  total = CEIL((SELECT count(*) FROM category) / _limit);
    SELECT * FROM category LIMIT _limit OFFSET _offset;
end; //

-- chuyển trạng thái (xóa mềm)
DELIMITER //
CREATE PROCEDURE change_category_status(
    IN p_id INT
)
BEGIN
    UPDATE category
    SET status = NOT status
    WHERE id = p_id;
END; //

-- lay tat
DELIMITER //
CREATE PROCEDURE find_all_category(
)
BEGIN
    SELECT *
    FROM category;
END; //