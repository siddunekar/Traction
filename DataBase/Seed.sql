INSERT INTO users
(id, username, email, phone, password_hash, location, occupation, investment_min, investment_max)
VALUES
(1, 'siddu_dev', 'siddu@test.com', '9000000001', 'test_hash_1', 'Ballari', 'Software Engineer', 500000, 1000000),
(2, 'arjun_invest', 'arjun@test.com', '9000000002', 'test_hash_2', 'Bengaluru', 'Investor', 1000000, 2500000),
(3, 'ravi_agri', 'ravi@test.com', '9000000003', 'test_hash_3', 'Gulbarga', 'Farmer', NULL, NULL),
(4, 'priya_founder', 'priya@test.com', '9000000004', 'test_hash_4', 'Hyderabad', 'Entrepreneur', 200000, 500000),
(5, 'neha_student', 'neha@test.com', '9000000005', 'test_hash_5', 'Mysuru', 'Student', NULL, NULL);

INSERT INTO interests (id, field, category)
VALUES
(1, 'Artificial Intelligence', 'Technology'),
(2, 'FinTech', 'Finance'),
(3, 'Healthcare', 'Health'),
(4, 'Agriculture', 'Business'),
(5, 'EdTech', 'Education');

INSERT INTO companies
(id, founder_id, name, location, category, field, description, website, created_at)
VALUES
(1, 4, 'HealthNova', 'Hyderabad', 'Healthcare', 'Healthcare',
 'Digital healthcare platform for accessible medical services.',
 'https://healthnova.example.com', CURRENT_TIMESTAMP),

(2, 4, 'AgriVision', 'Gulbarga', 'Agriculture', 'Agriculture',
 'Technology platform helping farmers improve crop decisions.',
 'https://agrivision.example.com', CURRENT_TIMESTAMP),

(3, 2, 'FinEdge', 'Bengaluru', 'Finance', 'FinTech',
 'Financial technology platform for small businesses.',
 'https://finedge.example.com', CURRENT_TIMESTAMP);