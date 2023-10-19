INSERT INTO category (category_id, name)
VALUES (3, 'Samsung'),
       (5, 'Headphones'),
       (1, 'Smartphones'),
       (7, 'Audio-equipment'),
       (0, 'Root'),
       (6, 'Cases'),
       (8, 'Portable Speakers'),
       (4, 'Complementary products for smartphones'),
       (2, 'Apple');

INSERT INTO category_parent_child (parent_id, child_id)
VALUES (0, 1),
       (0, 7),
       (1, 3),
       (1, 2),
       (1, 4),
       (4, 6),
       (4, 5),
       (7, 8),
       (7, 5);

INSERT INTO product (product_id, name, price, amount, description, discount, category_id)
VALUES (6, 'Smartphone Huawei P50', 30000, 27,
        'This HUAWEI smartphone operates with a pre-installed AppGallery platform developed in-house. AppGallery is the third most popular app store in the world, offering high-quality services and applications. Discover HUAWEI smartphones with AppGallery now!',
        0, 1),
       (1, 'Smartphone Apple iPhone 14', 100000, 14,
        'The slim and lightweight smartphone from the new Apple iPhone 14 lineup features a high-resolution OLED screen, ample memory, and extended battery life in a waterproof casing. Advanced cameras allow you to capture photos and videos in excellent quality even in low light. The emergency assistance function with satellite communication will be a lifeline when there is no internet access. A powerful processor opens up new possibilities for gaming and entertainment.', 0, 2),
(2, 'Smartphone Apple iPhone 13', 80000, 7, 'iPhone 13', 0, 2),
(3, 'Smartphone Samsung Galaxy S8', 40000, 15, 'Samsung Galaxy S8 is a completely bezel-less smartphone that combines all the latest technical achievements of the brand. The curved screen on both sides with a 5.8-inch diagonal has an elongated format (18.5:9),
        which allows virtual buttons to be accommodated. This also provides greater comfort when working in multi-screen mode. A powerful 8-core Exynos 8895 processor supports flawless performance with a 20% reduction in energy consumption. 4GB of RAM and 64GB of internal memory guarantee smooth operation of any applications available on the market.', 0, 3),
(4, 'Headphones Apple AirPods Pro', 20000, 30, 'White and good', 0, 5),
(5, 'Phone case Huawei P50', 1000, 43, 'Leather case', 0, 6),
(7, 'Smart Speaker Yandex Station', 25000, 14, 'The second-generation smart speaker with a new design, sound, smart home control center, and Alice. Enjoy your favorite music with clear and deep sound, free from distortions. Supports the universal Zigbee protocol for faster smart home response to commands and operation even without the internet. Alice, the virtual assistant, remembers your preferences and learns to express emotions. Joint control with Station and smart devices. Guests can get to know Alice in your smart home speakers to control their music playback, customize their musical preferences (with Yandex Plus subscription), and control smart home devices with you, such as turning lights on and off, selecting appliance modes, and viewing sensor readings.', 0, 8);
