CREATE TABLE users(
    id INT PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    email VARCHAR(50) UNIQUE,
    phone VARCHAR(50) UNIQUE,
    password_hash VARCHAR(200),
    location VARCHAR(50),
    occupation VARCHAR(50),
    investment_min INT,
    investment_max INT
);

CREATE TABLE posts(
    id INT PRIMARY KEY,
    user_id INT,
    FOREIGN KEY(user_id) REFERENCES users(id),
    content VARCHAR(500),
    field VARCHAR(500),
    category VARCHAR(200),
    created_at TIMESTAMP
);

CREATE TABLE media(
    id INT PRIMARY KEY,
    post_id INT,
    FOREIGN KEY(post_id) REFERENCES posts(id),
    file_url VARCHAR(500),
    media_type VARCHAR(20),
    created_at TIMESTAMP
);

CREATE TABLE comments(
    id INT PRIMARY KEY,
    user_id INT,
    FOREIGN KEY(user_id) REFERENCES users(id),
    post_id INT,
    FOREIGN KEY(post_id) REFERENCES posts(id),
    content VARCHAR(500)
    created_at TIMESTAMP
);

CREATE TABLE likes(
    user_id INT,
    FOREIGN KEY(user_id) REFERENCES users(id),
    post_id INT,
    FOREIGN KEY(post_id) REFERENCES posts(id),
    PRIMARY KEY(user_id, post_id),
    created_id TIMESTAMP
);

CREATE TABLE search_history(
    id INT PRIMARY KEY,
    user_id INT,
    FOREIGN KEY(user_id) REFERENCES users(id),
    query VARCHAR(250),
    searched_at TIMESTAMP
);


CREATE TABLE connections(
    id INT PRIMARY KEY,
    sender_id INT,
    FOREIGN KEY(sender_id) REFERENCES users(id),
    receiver_id INT,
    FOREIGN KEY(receiver_id) REFERENCES users(id),
    status VARCHAR(10),
    created_at TIMESTAMP
);

CREATE TABLE companies(
    id INT PRIMARY KEY,
    founder_id INT,
    FOREIGN KEY(founder_id) REFERENCES users(id),
    name VARCHAR(50),
    location VARCHAR(500),
    category VARCHAR(225),
    field VARCHAR(225),
    description VARCHAR(5000),
    website VARCHAR(225),
    created_at TIMESTAMP
);

CREATE TABLE interests(
    id int PRIMARY KEY,
    field VARCHAR(50),
    category VARCHAR(50)
);

user_id
status
applied_at
reviewed_at
reviewed_by

CREATE TABLE founder_applications(
    id INT PRIMARY KEY,
    user_id INT,
    FOREIGN KEY(user_id) REFERENCES users(id),
    status VARCHAR(20),
    applied_at TIMESTAMP,
    reviewed_by INT,
    FOREIGN KEY(reviewed_by) REFERENCES users(id)
    reviewed_at TIMESTAMP
);

CREATE TABLE company_opportunities(
    id INT PRIMARY KEY,

    company_id INT,
    FOREIGN KEY(company_id) REFERENCES companies(id),

    interest_id INT,
    FOREIGN KEY(interest_id) REFERENCES interests(id),

    title VARCHAR(100),
    description VARCHAR(5000),

    minimum_amount DECIMAL(15,2),
    maximum_amount DECIMAL(15,2),

    status VARCHAR(20),

    created_at TIMESTAMP
);

CREATE TABLE notifications(
    id INT PRIMARY KEY,

    user_id INT,
    FOREIGN KEY(user_id) REFERENCES users(id),

    type VARCHAR(50),
    message VARCHAR(500),

    is_read BOOLEAN,

    created_at TIMESTAMP
);