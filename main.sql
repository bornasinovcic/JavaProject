DROP TABLE IF EXISTS food, gadget;
CREATE TABLE food
(
    food_id            VARCHAR(22)   NOT NULL,
    food_name          VARCHAR(33)   NOT NULL,
    food_price         DECIMAL(7, 2) NOT NULL,
    food_quantity      INT           NOT NULL,
    food_proteins      INT           NOT NULL,
    food_carbohydrates INT           NOT NULL,
    food_fats          INT           NOT NULL,
    PRIMARY KEY (food_id),
    UNIQUE (food_name)
);
CREATE TABLE gadget
(
    gadget_id       VARCHAR(22)   NOT NULL,
    gadget_name     VARCHAR(33)   NOT NULL,
    gadget_price    DECIMAL(7, 2) NOT NULL,
    gadget_quantity INT           NOT NULL,
    gadget_warranty INT           NOT NULL,
    PRIMARY KEY (gadget_id),
    UNIQUE (gadget_name)
);
TRUNCATE TABLE food;
TRUNCATE TABLE gadget;
INSERT INTO food (food_id, food_name, food_price, food_quantity, food_proteins, food_carbohydrates, food_fats)
VALUES ('T7xnke6YI955Idfx', 'Hamburger peciva 300 g', 1.33, 5, 8, 53, 5),
       ('fPBkRxk4LBsWtdYd', 'Donut okus čokolade 56 g', 0.62, 5, 6, 45, 28),
       ('PymKjFRpfLWMbMCc', 'Toblerone Torta 400 g', 5.97, 1, 10, 38, 28),
       ('LIabkLXeW8AXMcDB', 'Mlinar Čupavci 520 g', 5.97, 3, 6, 43, 27),
       ('UdKuuKqnchLtPWdh', 'Ocean Plodovi mora 300 g', 2.91, 5, 12, 7, 2),
       ('hnewqudVVHuawezP', 'Ocean Filet oslić 500 g', 5.31, 9, 17, 1, 2);
INSERT INTO gadget (gadget_id, gadget_name, gadget_price, gadget_quantity, gadget_warranty)
VALUES ('fkVjhvCBJHvV6Zwn', 'Laptop (Predator Triton)', 4559.05, 1, 36),
       ('Y3VG7wwPHbzbq5qb', 'Laptop (ACER ConceptD 7 Ezel)', 4019.00, 1, 36),
       ('sP7dRqeZeE37Y3vw', 'Laptop (GIGABYTE AERO 17)', 3052.49, 1, 60),
       ('gxYmMSKdbKSBXnm5', 'Laptop (APPLE MacBook Pro 16)', 4277.52, 1, 36),
       ('wT4v5S2aVAvBaSra', 'Laptop (ASUS ROG Zephyrus)', 4738.07, 2, 12),
       ('z9cu2sjBhSX7fWUn', 'Monitor 24" DELL', 149.00, 3, 36);
SELECT SUM(food_quantity * food_price) FROM food;
SELECT SUM(food_quantity * food_proteins) FROM food;
SELECT SUM(food_quantity * food_proteins * 4) FROM food;
SELECT SUM(food_quantity * food_carbohydrates) FROM food;
SELECT SUM(food_quantity * food_carbohydrates * 4) FROM food;
SELECT SUM(food_quantity * food_fats) FROM food;
SELECT SUM(food_quantity * food_fats * 9) FROM food;
SELECT SUM((food_quantity * food_proteins * 4) +
           (food_quantity * food_carbohydrates * 4) +
           (food_quantity * food_fats * 9))
FROM food;