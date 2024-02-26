-- Create the "users" schema
CREATE SCHEMA IF NOT EXISTS users;

-- Create the "User" table within the "users" schema
CREATE TABLE IF NOT EXISTS users."User" (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);