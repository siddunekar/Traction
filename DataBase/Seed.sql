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


 INSERT INTO posts
(id, user_id, content, field, category, created_at)
VALUES
(1, 1, 'Building an AI based platform for smarter agriculture.', 'Artificial Intelligence', 'Technology', CURRENT_TIMESTAMP),
(2, 2, 'Looking for promising FinTech startups to invest in.', 'FinTech', 'Finance', CURRENT_TIMESTAMP),
(3, 3, 'Exploring technology that can improve farming productivity.', 'Agriculture', 'Business', CURRENT_TIMESTAMP),
(4, 4, 'Healthcare innovation needs better access to technology.', 'Healthcare', 'Health', CURRENT_TIMESTAMP),
(5, 5, 'Learning about AI and modern software development.', 'Artificial Intelligence', 'Technology', CURRENT_TIMESTAMP);

INSERT INTO media
(id, post_id, file_url, media_type, created_at)
VALUES
(1, 1, 'https://example.com/images/agri-ai.jpg', 'IMAGE', CURRENT_TIMESTAMP),
(2, 1, 'https://example.com/videos/agri-demo.mp4', 'VIDEO', CURRENT_TIMESTAMP),
(3, 2, 'https://example.com/images/fintech.jpg', 'IMAGE', CURRENT_TIMESTAMP),
(4, 4, 'https://example.com/images/healthcare.jpg', 'IMAGE', CURRENT_TIMESTAMP);

INSERT INTO comments
(id, user_id, post_id, content, created_at)
VALUES
(1, 2, 1, 'This sounds interesting. What problem are you solving first?', CURRENT_TIMESTAMP),
(2, 3, 1, 'This could be useful for farmers.', CURRENT_TIMESTAMP),
(3, 1, 2, 'I am interested in learning more about this.', CURRENT_TIMESTAMP),
(4, 5, 4, 'Technology can definitely improve healthcare access.', CURRENT_TIMESTAMP);


INSERT INTO likes
(user_id, post_id, created_at)
VALUES
(2, 1, CURRENT_TIMESTAMP),
(3, 1, CURRENT_TIMESTAMP),
(1, 2, CURRENT_TIMESTAMP),
(4, 2, CURRENT_TIMESTAMP),
(5, 4, CURRENT_TIMESTAMP),
(1, 5, CURRENT_TIMESTAMP);


INSERT INTO search_history
(id, user_id, query, searched_at)
VALUES
(1, 1, 'AI startups', CURRENT_TIMESTAMP),
(2, 1, 'agriculture technology', CURRENT_TIMESTAMP),
(3, 2, 'FinTech startups', CURRENT_TIMESTAMP),
(4, 3, 'agriculture investment', CURRENT_TIMESTAMP),
(5, 4, 'healthcare startups', CURRENT_TIMESTAMP),
(6, 5, 'AI companies', CURRENT_TIMESTAMP);


INSERT INTO connections
(id, sender_id, receiver_id, status, created_at)
VALUES
(1, 1, 2, 'ACCEPTED', CURRENT_TIMESTAMP),
(2, 1, 4, 'PENDING', CURRENT_TIMESTAMP),
(3, 3, 4, 'REJECTED', CURRENT_TIMESTAMP),
(4, 5, 1, 'WITHDRAWN', CURRENT_TIMESTAMP);


INSERT INTO founder_applications
(id, user_id, status, applied_at, reviewed_by, reviewed_at)
VALUES
(1, 4, 'APPROVED', CURRENT_TIMESTAMP, 2, CURRENT_TIMESTAMP),
(2, 1, 'PENDING', CURRENT_TIMESTAMP, NULL, NULL),
(3, 5, 'REJECTED', CURRENT_TIMESTAMP, 2, CURRENT_TIMESTAMP);


INSERT INTO company_opportunities
(id, company_id, interest_id, title, description, minimum_amount, maximum_amount, status, created_at)
VALUES
(1, 1, 3,
 'Healthcare Investment Opportunity',
 'HealthNova is looking for investors interested in healthcare technology.',
 500000.00, 1000000.00,
 'OPEN',
 CURRENT_TIMESTAMP),

(2, 2, 4,
 'Agriculture Technology Investment',
 'AgriVision is looking for investment to expand its agricultural technology platform.',
 300000.00, 800000.00,
 'OPEN',
 CURRENT_TIMESTAMP),

(3, 3, 2,
 'FinTech Startup Investment',
 'FinEdge is seeking investors to expand its financial technology platform.',
 1000000.00, 2500000.00,
 'OPEN',
 CURRENT_TIMESTAMP),

(4, 1, 1,
 'AI Healthcare Partnership',
 'HealthNova is looking for partners interested in artificial intelligence and healthcare.',
 200000.00, 500000.00,
 'OPEN',
 CURRENT_TIMESTAMP);

 INSERT INTO notifications
(id, user_id, type, message, is_read, created_at)
VALUES
(1, 2, 'CONNECTION_ACCEPTED', 'Your connection request was accepted.', TRUE, CURRENT_TIMESTAMP),
(2, 4, 'CONNECTION_REQUEST', 'You received a new connection request.', FALSE, CURRENT_TIMESTAMP),
(3, 1, 'POST_LIKE', 'Someone liked your post.', FALSE, CURRENT_TIMESTAMP),
(4, 1, 'POST_COMMENT', 'Someone commented on your post.', TRUE, CURRENT_TIMESTAMP),
(5, 4, 'FOUNDER_APPROVED', 'Your founder application has been approved.', FALSE, CURRENT_TIMESTAMP),
(6, 2, 'NEW_OPPORTUNITY', 'A new investment opportunity is available.', FALSE, CURRENT_TIMESTAMP);

