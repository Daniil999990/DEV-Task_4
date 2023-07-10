INSERT INTO project (CLIENT_ID, START_DATE, FINISH_DATE)
INSERT INTO worker (NAME, BIRTHDAY, LEVEL, SALARY)
VALUES ('Anton', '1990-01-01', 'Trainee', 800),
    ('Daniil', '1985-05-15', 'Junior', 1200),
    ('Johnson', '1982-09-30', 'Middle', 2500),
    ('Anastasia', '1978-07-12', 'Senior', 5000),
    ('David', '1992-03-22', 'Junior', 1500),
    ('Andrey', '1989-11-18', 'Middle', 3000),
    ('Roman', '1980-06-28', 'Senior', 4500),
    ('Slava', '1995-04-05', 'Trainee', 900),
    ('Max', '1987-08-08', 'Junior', 1400),
    ('Carina', '1991-12-10', 'Middle', 2800);


INSERT INTO client (NAME)
VALUES
('Anton'),
('Daniil'),
('Anastasia'),
('Slava'),
('Carina');


VALUES (1, '2022-01-01', '2023-02-28'),
       (2, '2022-03-15', '2023-05-31'),
       (3, '2022-06-01', '2023-08-30'),
       (4, '2022-09-10', '2023-11-15'),
       (5, '2022-12-01', '2023-12-31'),
       (3, '2022-02-15', '2023-04-30'),
       (1, '2022-05-01', '2023-07-31'),
       (4, '2022-08-10', '2023-10-15'),
       (2, '2022-11-01', '2023-12-31'),
       (5, '2023-01-15', '2023-03-31');


INSERT INTO project_worker (PROJECT_ID, WORKER_ID)
VALUES (1, 1),
(1, 2),
(1, 3),
(2, 4),
(2, 5),
(3, 6),
(4, 7),
(4, 8),
(5, 9),
(6, 3),
(6, 6),
(7, 1),
(7, 4),
(7, 5),
(8, 2),
(8, 7),
(9, 3),
(9, 6),
(9, 9),
(10, 10);

INSERT INTO project_worker (PROJECT_ID, WORKER_ID)
SELECT FLOOR(RAND() * 10) + 1, FLOOR(RAND() * 10) + 1
FROM (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION
SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10) t1,
(SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION
SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10) t2
WHERE NOT EXISTS (
SELECT 1
FROM project_worker
WHERE project_worker.PROJECT_ID = t1.ID
AND project_worker.WORKER_ID = t2.ID
);