INSERT INTO category (category_id, name, diverged)
VALUES (default, 'Root', false),
       (default, 'Смартфоны', false),
       (default, 'Apple', false),
       (default, 'Samsung', false),
       (default, 'Дополнительные продукты для смартфонов', true),
       (default, 'Наушники', false),
       (default, 'Чехлы', false),
       (default, 'Аудиооборудование', false),
       (default, 'Портативные колонки', false),
       (default, 'Ноутбуки', false),
       (default, 'Apple', false),
       (default, 'Lenovo', false);


INSERT INTO category_parent_child (parent_id, child_id)
VALUES (1, 2),
       (1, 8),
       (2, 4),
       (2, 3),
       (2, 5),
       (5, 7),
       (5, 6),
       (8, 9),
       (8, 6),
       (1, 10),
       (10, 11),
       (10, 12);

INSERT INTO product (product_id, name, price, amount, description, discount, category_id)
VALUES (default, 'iPhone 14', 100000, 14,
        'Тонкий и легкий смартфон из новой линейки Apple iPhone 14 оснащен OLED-экраном с высоким разрешением, ' ||
        'достаточным объемом памяти и увеличенным временем автономной работы в водонепроницаемом корпусе. ' ||
        'Усовершенствованные камеры позволяют снимать фотографии и видео в отличном качестве даже при слабом освещении. ' ||
        'Функция экстренной помощи со спутниковой связью станет спасательным кругом при отсутствии доступа в Интернет. ' ||
        'Мощный процессор открывает новые возможности для игр и развлечений.',
        0, 3),
       (default, 'iPhone 13', 80000, 7, 'iPhone 13', 0, 3),
       (default, 'Samsung Galaxy S8', 40000, 15, 'Samsung Galaxy S8 - это смартфон без рамки, ' ||
                                                 'который сочетает в себе все последние технические достижения бренда. ' ||
                                                 'Изогнутый с обеих сторон экран с диагональю 5,8 дюйма имеет удлиненный формат (18,5:9),' ||
                                                 'что позволяет разместить виртуальные кнопки. Это также обеспечивает больший комфорт при ' ||
                                                 'работе в многоэкранном режиме. Мощный 8-ядерный процессор Exynos 8895 обеспечивает безупречную ' ||
                                                 'производительность при снижении энергопотребления на 20%. 4 ГБ оперативной и 64 ГБ встроенной ' ||
                                                 'памяти гарантируют бесперебойную работу любых приложений, доступных на рынке.',
        0, 4),
       (default, 'Наушники Apple AirPods Pro', 20000, 30, 'Белый и хороший', 0, 6),
       (default, 'Чехол Huawei P50', 1000, 43, 'Кожаный чехол', 0, 7),
       (default, 'Huawei P50', 30000, 27,
        'Этот смартфон HUAWEI работает с предустановленной платформой App Gallery, разработанной собственными силами.' ||
        ' App Gallery - третий по популярности магазин приложений в мире, предлагающий высококачественные сервисы и приложения. ' ||
        'Откройте для себя смартфоны HUAWEI с App Gallery прямо сейчас!',
        0, 2),
       (default, 'Умная колонка Яндекс станция', 25000, 14,
        'Интеллектуальная колонка второго поколения с новым дизайном, звуком, центром управления "умным домом" и Alice. ' ||
        'Наслаждайтесь любимой музыкой с чистым и глубоким звуком, свободным от искажений. Поддерживает универсальный протокол ' ||
        'Zigbee для более быстрого реагирования "умного дома" на команды и работы даже без Интернета. Алиса, виртуальный ассистент,' ||
        ' запоминает ваши предпочтения и учится выражать эмоции. Совместное управление со станцией и интеллектуальными устройствами. ' ||
        'Гости могут познакомиться с Alice в динамиках вашего "умного дома", чтобы управлять воспроизведением музыки, ' ||
        'настраивать свои музыкальные предпочтения (с подпиской "Яндекс Плюс") и управлять устройствами "умного дома" вместе с вами, ' ||
        'например, включать и выключать свет, выбирать режимы работы приборов и просматривать показания датчиков.',
        0, 9),
       (default, 'Lenovo IdeaPad Gaming 3 15ACH6', 145500, 5, 'Ноутбук Lenovo IdeaPad Gaming 3 15ACH6 (82K2022UIN)' ||
                                                              ' — игровая модель в корпусе из ударопрочного пластика черного цвета.',
        0, 12),
       (default, 'Lenovo ThinkPad X1 Nano Gen 2', 197600, 3,
        'Ноутбук Lenovo ThinkPad X1 Nano Gen 2 — модель в корпусе из пластика, ' ||
        'оборудованная 13-дюймовым IPS-экраном (33,1 см) разрешением 2160х1350 пикселей. ' ||
        'Частота обновления составляет 60 Гц, что исключает появление подтормаживаний в' ||
        ' динамичных сценах. Матовая поверхность снижает появление бликов во время работы' ||
        ' при солнечном свете.',
        0, 12),
       (default, 'Apple MacBook Air 13', 106790, 3,
        'Ноутбук Apple MacBook Air 13, серебристый предлагает широкие возможности' ||
        ' для решения бизнес-задач, офисной работы, игр со сложной графикой. ',
        0, 11),
       (default, 'Apple MacBook Pro 14', 202980, 17,
        'Супербыстрые чипы M1 Pro и M1 Max дают феноменальную производительность и ' ||
        'обеспечивают удивительно долгое время работы без подзарядки. ' ||
        'Прибавьте к этому потрясающий дисплей Liquid Retina XDR и ещё больше ' ||
        'портов для профессиональной работы. Это тот самый ноутбук, который вы так ждали.',
        0, 11)
;


-- not isRange characteristics
INSERT INTO characteristic (characteristic_id, name, is_range, category_id, from_, _to)
VALUES (default, 'Объем оперативной памяти', false, 2, NULL, NULL),
       (default, 'Число ядер', false, 2, NULL, NULL),
       (default, 'Версия Bluetooth', false, 8, NULL, NULL),
       (default, 'Операционная система', false, 10, NULL, NULL),
       (default, 'Цвет', false, 7, NULL, NULL);

-- isRange characteristics
INSERT INTO characteristic (characteristic_id, name, is_range, category_id, from_, _to)
VALUES (default, 'Диагональ экрана', true, 10, 0, 20),
       (default, 'Вес', true, 1, 0, 100000);

INSERT INTO customer
VALUES (default, 'John', 'Wick', 'wick@gmail.com', '$2a$10$FHAGu5JX5LroDewAm6jVIugZ8gGKB9pMtRTdE6suNu8bZVmkkHoxe',
        'ROLE_ADMIN');

INSERT INTO property (property_id, value, characteristic_id)
VALUES (default, '8 GB', 1),   --1
       (default, '16 GB', 1),--2
       (default, '4 GB', 1),--3
       (default, '4', 2),--4
       (default, '6', 2),--5
       (default, '8', 2),--6
       (default, 'v5.0', 3),--7
       (default, '5.0/BLE', 3),--8
       (default, 'Windows', 4),--9
       (default, 'Mac OS', 4),--10
       (default, 'Чёрный', 5),--11
       (default, 'Серый', 5),--12
       (default, 'Белый', 5);--13

INSERT INTO property (property_id, value, characteristic_id)
VALUES (default, '0.206', 7),--14 1
       (default, '0.173', 7),--15 2
       (default, '0.185', 7),--16 3
       (default, '0.3', 7),--17 4
       (default, '0.123', 7),--18 5
       (default, '0.194', 7),--19 6
       (default, '1.3', 7),--20 7
       (default, '3.2', 7),--21 8
       (default, '2.2', 7),--22 9
       (default, '1.2', 7),--23 10
       (default, '1.4', 7);--24 11


INSERT INTO property (property_id, value, characteristic_id)
VALUES (default, '13', 6),   --25
       (default, '14', 6),   --26
       (default, '15.6', 6), --27
       (default, '16', 6);
--28


-- Insert data into the "product_property" table
INSERT INTO product_property (product_id, property_id)
VALUES (1, 2),
       (1, 14),
       (1, 6),
       (2, 1),
       (2, 15),
       (2, 5),
       (3, 3),
       (3, 16),
       (3, 4),
       (4, 8),
       (4, 17),
       (5, 12),
       (5, 18),
       (6, 3),
       (6, 19),
       (6, 6),
       (7, 7),
       (7, 20),
       (8, 9),
       (8, 21),
       (8, 11),
       (8, 28),
       (9, 22),
       (9, 9),
       (9, 11),
       (9, 27),
       (10, 10),
       (10, 23),
       (10, 12),
       (10, 25),
       (11, 10),
       (11, 24),
       (11, 26),
       (11, 13);


-- Insert data into the "comment" table
INSERT INTO comment (comment_id, rating, note, date_time, product_id, customer_id)
VALUES (default, 2, 'Зарядка смартфона очень плохая!', '2023-10-16', 1, 1),
       (default, 1, 'Сломанный, хочу вернуться обратно', '2023-10-19', 1, 1);


-- Insert data into the "photo" table
INSERT INTO photo (url, product_id, comment_id)
VALUES ('https://storage.yandexcloud.net/e-shop/11pro.png', 1, NULL),
       ('https://storage.yandexcloud.net/e-shop/12iphone.png', 1, NULL),
       ('https://storage.yandexcloud.net/e-shop/iphone11.jpg', 1, NULL),

       ('https://storage.yandexcloud.net/e-shop/apple-iphone-12-violet-back.png', 2, NULL),
       ('https://storage.yandexcloud.net/e-shop/14plus.jpg', 2, NULL),
       ('https://storage.yandexcloud.net/e-shop/phone.jpg', 2, NULL),

       ('https://storage.yandexcloud.net/e-shop/sum1S8.jpg', 3, NULL),
       ('https://storage.yandexcloud.net/e-shop/sumS8.png', 3, NULL),
       ('https://storage.yandexcloud.net/e-shop/sum3S8.png', 3, NULL),

       ('https://storage.yandexcloud.net/e-shop/airpods1.png', 4, NULL),
       ('https://storage.yandexcloud.net/e-shop/airpods2.png', 4, NULL),
       ('https://storage.yandexcloud.net/e-shop/airpods3.png', 4, NULL),

       ('https://storage.yandexcloud.net/e-shop/chehol1.png', 5, NULL),
       ('https://storage.yandexcloud.net/e-shop/chehol2.png', 5, NULL),
       ('https://storage.yandexcloud.net/e-shop/chehol3.png', 5, NULL),

       ('https://storage.yandexcloud.net/e-shop/p50_1.png', 6, NULL),
       ('https://storage.yandexcloud.net/e-shop/p50_2.png', 6, NULL),
       ('https://storage.yandexcloud.net/e-shop/p50_3.png', 6, NULL),

       ('https://storage.yandexcloud.net/e-shop/ya1.jpg', 7, NULL),
       ('https://storage.yandexcloud.net/e-shop/ya2.jpg', 7, NULL),
       ('https://storage.yandexcloud.net/e-shop/ya3.jpg', 7, NULL),

       ('https://storage.yandexcloud.net/e-shop/Lenovo1.png', 8, NULL),
       ('https://storage.yandexcloud.net/e-shop/Lenovo2.jpg', 8, NULL),
       ('https://storage.yandexcloud.net/e-shop/Lenovo3.jpg', 8, NULL),

       ('https://storage.yandexcloud.net/e-shop/thinkpad1.png', 9, NULL),
       ('https://storage.yandexcloud.net/e-shop/thinkpad2.jpg', 9, NULL),
       ('https://storage.yandexcloud.net/e-shop/thinkpad3.png', 9, NULL),

       ('https://storage.yandexcloud.net/e-shop/air13.png', 10, NULL),
       ('https://storage.yandexcloud.net/e-shop/air13_2.jpg', 10, NULL),
       ('https://storage.yandexcloud.net/e-shop/air13_3.jpg', 10, NULL),

       ('https://storage.yandexcloud.net/e-shop/14pro.jpg', 11, NULL),
       ('https://storage.yandexcloud.net/e-shop/14pro_2.jpg', 11, NULL),
       ('https://storage.yandexcloud.net/e-shop/14pro_3.jpg', 11, NULL),

       ('https://cdn.rbt.ru/images/gen/item_image/image/9728/24/972733_r83.jpg', 1, 1);

INSERT INTO purchase(purchase_id)
values (-1);