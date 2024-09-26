INSERT INTO role (id, name) VALUES (2, 'ROLE_ADMIN');
INSERT INTO role (id, name) VALUES (1, 'ROLE_USER');

INSERT INTO user (id, username, password) VALUES (1, 'admin', '$2a$10$yprrA0y1LRfs8Osc3ALeg.iq9lnMdzFeVS/uVRKkokmBdYBBi.LFu');
INSERT INTO user (id, username, password) VALUES (2, 'user', '$2a$10$Ir59qO8MtbPJL0u333JMm.JbTG8d37h0WX5/fpqxM1REfoLII7lHC');

--INSERT INTO user_roles(user_id, role_id) VALUES (1,1);
INSERT INTO user_roles(user_id, role_id) VALUES (1,2);
INSERT INTO user_roles(user_id, role_id) VALUES (2,1);

INSERT INTO registration (id, name, birthdate, gender, country) VALUES (511, 'bamban', '2020-09-12 00:00:00.000000','Male','Indonesia');
INSERT INTO registration (id, name, birthdate, gender, country) VALUES (521, 'bambag', '2020-09-12 00:00:00.000000','Male','Malaysia');
INSERT INTO registration (id, name, birthdate, gender, country) VALUES (531, 'bambad', '2020-09-12 00:00:00.000000','Male','Thailand');
INSERT INTO registration (id, name, birthdate, gender, country) VALUES (541, 'bambat', '2020-09-12 00:00:00.000000','Male','Singapore');