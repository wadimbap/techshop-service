-- Вставка данных в базовые продукты
INSERT INTO base_product (serial_number, manufacturer, price, quantity, product_type)
VALUES ('SN12345', 'HP', 799.99, 10, 'DESKTOP'),
       ('SN67890', 'Dell', 899.99, 15, 'LAPTOP'),
       ('SN11223', 'Samsung', 199.99, 20, 'MONITOR'),
       ('SN44556', 'Western Digital', 129.99, 30, 'HDD');

-- Вставка данных в настольные компьютеры
INSERT INTO desktop_computer (id, form_factor)
VALUES ((SELECT id FROM base_product WHERE serial_number = 'SN12345'), 'Desktops');

-- Вставка данных в ноутбуки
INSERT INTO laptop (id, screen_size)
VALUES ((SELECT id FROM base_product WHERE serial_number = 'SN67890'), 15);

-- Вставка данных в мониторы
INSERT INTO monitor (id, diagonal)
VALUES ((SELECT id FROM base_product WHERE serial_number = 'SN11223'), 27.0);

-- Вставка данных в жесткие диски
INSERT INTO hard_drive (id, capacity)
VALUES ((SELECT id FROM base_product WHERE serial_number = 'SN44556'), 1024);
