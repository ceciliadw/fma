CREATE TABLE fma_user (
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	version INT NOT NULL,
	userid VARCHAR(100) NOT NULL,
	username VARCHAR(200) NOT NULL,
	email VARCHAR(200),
	deleted BIT(1) NOT NULL, 
	created_date TIMESTAMP NOT NULL, 
	last_updated_date TIMESTAMP NOT NULL, 
	last_updated_by INT
); 

CREATE TABLE role (
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	version INT NOT NULL,
	role_name VARCHAR(100) NOT NULL,
	role_description VARCHAR(400) NOT NULL,
	email VARCHAR(200),
	deleted BIT(1) NOT NULL, 
	last_updated_date TIMESTAMP NOT NULL, 
	last_updated_by INT
); 

CREATE TABLE user_role (
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	version INT NOT NULL,
	fma_user_id INT NOT NULL, 
	role_id INT NOT NULL,
	deleted BIT(1) NOT NULL, 
	last_updated_date TIMESTAMP NOT NULL, 
	last_updated_by INT
); 

CREATE TABLE offering(
	id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	version INT NOT NULL,
	fma_user_id INT NOT NULL,
	description VARCHAR(2000) NOT NULL, 
	pickup_flag BIT(1),
	post_flag BIT(1),
	pickup_location VARCHAR(100), 
	postage_price VARCHAR(100),
	status INT NOT NULL,
	deleted BIT(1) NOT NULL, 
	created_date TIMESTAMP NOT NULL, 
	last_updated_date TIMESTAMP NOT NULL, 
	last_updated_by INT
);

