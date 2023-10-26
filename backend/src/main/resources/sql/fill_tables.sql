INSERT INTO category (category_id, name)
VALUES (4, 'Samsung'),
       (6, 'Headphones'),
       (2, 'Smartphones'),
       (8, 'Audio-equipment'),
       (1, 'Root'),
       (7, 'Cases'),
       (9, 'Portable Speakers'),
       (5, 'Complementary products for smartphones'),
       (3, 'Apple');

INSERT INTO category_parent_child (parent_id, child_id)
VALUES (1, 2),
       (1, 8),
       (2, 4),
       (2, 3),
       (2, 5),
       (5, 7),
       (5, 6),
       (8, 9),
       (8, 6);

INSERT INTO product (product_id, name, price, amount, description, discount, category_id)
VALUES (6, 'Smartphone Huawei P50', 30000, 27,
        'This HUAWEI smartphone operates with a pre-installed AppGallery platform developed in-house. AppGallery is the third most popular app store in the world, offering high-quality services and applications. Discover HUAWEI smartphones with AppGallery now!',
        0, 2),
       (1, 'Smartphone Apple iPhone 14', 100000, 14,
        'The slim and lightweight smartphone from the new Apple iPhone 14 lineup features a high-resolution OLED screen, ample memory, and extended battery life in a waterproof casing. Advanced cameras allow you to capture photos and videos in excellent quality even in low light. The emergency assistance function with satellite communication will be a lifeline when there is no internet access. A powerful processor opens up new possibilities for gaming and entertainment.',
        0, 3),
       (2, 'Smartphone Apple iPhone 13', 80000, 7, 'iPhone 13', 0, 3),
       (3, 'Smartphone Samsung Galaxy S8', 40000, 15, 'Samsung Galaxy S8 is a completely bezel-less smartphone that combines all the latest technical achievements of the brand. The curved screen on both sides with a 5.8-inch diagonal has an elongated format (18.5:9),
        which allows virtual buttons to be accommodated. This also provides greater comfort when working in multi-screen mode. A powerful 8-core Exynos 8895 processor supports flawless performance with a 20% reduction in energy consumption. 4GB of RAM and 64GB of internal memory guarantee smooth operation of any applications available on the market.',
        0, 4),
       (4, 'Headphones Apple AirPods Pro', 20000, 30, 'White and good', 0, 6),
       (5, 'Phone case Huawei P50', 1000, 43, 'Leather case', 0, 7),
       (7, 'Smart Speaker Yandex Station', 25000, 14,
        'The second-generation smart speaker with a new design, sound, smart home control center, and Alice. Enjoy your favorite music with clear and deep sound, free from distortions. Supports the universal Zigbee protocol for faster smart home response to commands and operation even without the internet. Alice, the virtual assistant, remembers your preferences and learns to express emotions. Joint control with Station and smart devices. Guests can get to know Alice in your smart home speakers to control their music playback, customize their musical preferences (with Yandex Plus subscription), and control smart home devices with you, such as turning lights on and off, selecting appliance modes, and viewing sensor readings.',
        0, 9);


-- Insert data into the "characteristic" table
INSERT INTO characteristic (characteristic_id, name, is_range, category_id, from_, _to)
VALUES (3, 'Number of cores', false, 2, NULL, NULL),
       (1, 'Price', true, 2, 30000, 100000),
       (2, 'RAM capacity', false, 2, NULL, NULL),
       (12, 'Bluetooth version', false, 6, NULL, NULL),
       (11, 'Weight', false, 1, NULL, NULL);

INSERT INTO customer
VALUES (1, 'John', 'Wick', 'wick@gmail.com', '$2a$10$FHAGu5JX5LroDewAm6jVIugZ8gGKB9pMtRTdE6suNu8bZVmkkHoxe', 'USER');

INSERT INTO property (property_id, value, characteristic_id)
VALUES (4, '4', 3),
       (5, '6', 3),
       (6, '8', 3),
       (1, '8 GB', 2),
       (2, '16 GB', 2),
       (12, '4 GB', 2),
       (13, '0.11', 11),
       (14, 'v5.0', 12),
       (15, '0.05', 11),
       (17, '1.5', 11),
       (18, '5.0/BLE', 12),
       (16, '0.4', 11);

-- Insert data into the "product_property" table
INSERT INTO product_property (product_id, property_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (2, 2),
       (1, 5),
       (2, 5),
       (3, 4),
       (3, 6),
       (4, 13),
       (4, 14),
       (5, 15),
       (6, 16),
       (6, 1),
       (7, 17),
       (7, 18);

-- Insert data into the "comment" table
INSERT INTO comment (comment_id, rating, note, date_time, product_id, customer_id)
VALUES (1, 2, 'Smarthpone''s charging is very bad!', '2023-10-16', 1, 1),
       (5, 1, 'Broken, want to return back', '2023-10-19', 1, 1);


-- Insert data into the "photo" table
INSERT INTO photo (url, product_id, comment_id)
VALUES ('https://www.hwupgrade.it/i/n/iPhone14ProMaxDimensioni_720.jpg', 1, NULL),
       ('https://avatars.mds.yandex.net/get-mpic/4449143/img_id4805498422263347727.jpeg/orig', 6, NULL),
       ('https://avatars.mds.yandex.net/get-mpic/4465918/img_id1164587449292882755.jpeg/orig', 6, NULL),
       ('https://avatars.mds.yandex.net/get-mpic/5254781/img_id2818580988479176360.jpeg/orig', 5, NULL),
       ('https://avatars.mds.yandex.net/get-mpic/4592225/img_id6848066478882349945.jpeg/orig', 5, NULL),
       ('https://avatars.mds.yandex.net/i?id=2532c1a3a5d323bc0c29aaf56a12f07331ff4ee2-10023671-images-thumbs&n=13', 2,
        NULL),
       ('https://bgr.com/wp-content/uploads/2021/04/iphone-13-design-schematic-3d-leak-1.jpg?quality=82&strip=all', 2,
        NULL),
       ('https://apple11.ru/10727-large_default/samsung-galaxy-s8-g950-orchid-gray-.jpg', 3, NULL),
       ('https://www.cnet.com/a/img/mx3MSpyKNftH5i1cv3XMGfqy-Qg/u003d/940x0/2017/03/27/90111f8b-d4dd-4829-83a5-6310e8070ccf/gsocho-ny-02-jg.jpg',
        3, NULL),
       ('https://avatars.mds.yandex.net/i?id=78ad839d40dc413dbf83b61c6c48204b7b197f5d-9294167-images-thumbs&n=13', 4,
        NULL),
       ('https://cdn1.ozone.ru/s3/multimedia-8/6308830976.jpg', 4, NULL),
       ('https://i4.stat01.com/2/8766/187656512/afacdb/8-1-jpg.jpg', 7, NULL),
       ('https://cdn.rbt.ru/images/gen/item_image/image/9728/24/972733_r83.jpg', 1, 1);

