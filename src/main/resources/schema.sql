-- Создание таблицы базовых продуктов
CREATE TABLE base_product
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    serial_number VARCHAR(255),
    manufacturer  VARCHAR(255),
    price         DOUBLE,
    quantity      INT,
    product_type  VARCHAR(255)
);

-- Создание таблицы настольных компьютеров
CREATE TABLE desktop_computer
(
    id          BIGINT PRIMARY KEY,
    form_factor VARCHAR(255),
    FOREIGN KEY (id) REFERENCES base_product (id)
);

-- Создание таблицы ноутбуков
CREATE TABLE laptop
(
    id          BIGINT PRIMARY KEY,
    screen_size INT,
    FOREIGN KEY (id) REFERENCES base_product (id)
);

-- Создание таблицы мониторов
CREATE TABLE monitor
(
    id       BIGINT PRIMARY KEY,
    diagonal DOUBLE,
    FOREIGN KEY (id) REFERENCES base_product (id)
);

-- Создание таблицы жестких дисков
CREATE TABLE hard_drive
(
    id       BIGINT PRIMARY KEY,
    capacity INT,
    FOREIGN KEY (id) REFERENCES base_product (id)
);