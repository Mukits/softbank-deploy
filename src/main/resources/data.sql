-- Consider the use of Hibernate/JPA for the database
-- 
-- This is the SHA128 for pwd='smithsmith'
INSERT INTO users (id, lastlogin, email, password) VALUES (1, now(), 'john@smith.com', '$2a$10$33isIWf1f98utCwWLNThc.9dvy9/7qpE80faF59YG6Ia1g/5a2jdS');
--INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN')
--INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
