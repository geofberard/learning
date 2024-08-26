-- Création des boîtes
INSERT INTO boxes (id, name, interval_days)
VALUES ('b1', 'Box de révision Quotidienne', 1),
       ('b2', 'Box de révision Hebdomadaire', 7),
       ('b3', 'Box de révision Mensuelle', 30),
       ('b4', 'Box de révision Trimestrielle', 90);

-- Création des catégories
INSERT INTO categories (id, name)
VALUES ('c1', 'Mathematics'),
       ('c2', 'Science'),
       ('c3', 'History'),
       ('c4', 'Geography'),
       ('c5', 'Literature');

-- Création des cartes
INSERT INTO cards (id, question, answer, box_id, last_reviewed_date, next_review_date, category_id)
VALUES ('_1', 'What is 2 + 2?', '4', 'b1', '2024-08-01', '2024-08-02', 'c1'),
       ('_2', 'What is the capital of France?', 'Paris', 'b1', '2024-08-01', '2024-08-03', 'c2'),
       ('_3', 'Who wrote "The Odyssey"?', 'Homer', 'b1', '2024-08-01', '2024-08-04', 'c3'),
       ('_4', 'What is the chemical symbol for water?', 'H2O', 'b1', '2024-08-01', '2024-08-05', 'c2'),
       ('_5', 'In which year did the French Revolution begin?', '1789', 'b1', '2024-08-01', '2024-08-06', 'c3'),
       ('_6', 'What is the largest planet in our solar system?', 'Jupiter', 'b2', '2024-08-01', '2024-08-08', 'c2'),
       ('_7', 'Who was the first President of USA ?', 'George Washington', 'b2', '2024-08-01', '2024-08-09', 'c3'),
       ('_8', 'What is the capital of Japan?', 'Tokyo', 'b2', '2024-08-01', '2024-08-10', 'c4'),
       ('_9', 'What is the smallest unit of life?', 'Cell', 'b2', '2024-08-01', '2024-08-11', 'c2'),
       ('_10', 'Who painted the Mona Lisa?', 'Leonardo da Vinci', 'b2', '2024-08-01', '2024-08-12', 'c5'),
       ('_11', 'What is the longest river in the world?', 'Nile', 'b3', '2024-08-01', '2024-08-31', 'c4'),
       ('_12', 'What is the speed of light?', '299,792 km/s', 'b3', '2024-08-01', '2024-08-30', 'c2'),
       ('_13', 'Who was the first human in space?', 'Yuri Gagarin', 'b3', '2024-08-01', '2024-08-29', 'c2'),
       ('_14', 'What is the largest desert in the world?', 'Sahara', 'b3', '2024-08-01', '2024-08-28', 'c4'),
       ('_15', 'Which play features the character of Hamlet?', 'Hamlet', 'b3', '2024-08-01', '2024-08-27', 'c5'),
       ('_16', 'Who discovered penicillin?', 'Alexander Fleming', 'b4', '2024-08-01', '2024-11-01', 'c2'),
       ('_17', 'What is the boiling point of water in Celsius?', '100°C', 'b4', '2024-08-01', '2024-11-02', 'c2'),
       ('_18', 'Who was the ancient Greek goddess of wisdom?', 'Athena', 'b4', '2024-08-01', '2024-11-03', 'c3'),
       ('_19', 'What element has the chemical symbol Na?', 'Sodium', 'b4', '2024-08-01', '2024-11-04', 'c2'),
       ('_20', 'What is the famous clock tower in London?', 'Big Ben', 'b4', '2024-08-01', '2024-11-05', 'c4');