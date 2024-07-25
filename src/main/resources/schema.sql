CREATE TABLE Product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    desc VARCHAR(1000),
    brand VARCHAR(255),
    price DECIMAL(10, 2) NOT NULL,
    category VARCHAR(255),
    releaseDate DATE,
    available BOOLEAN,
    quantity INT
);

CREATE TABLE "User" (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);
