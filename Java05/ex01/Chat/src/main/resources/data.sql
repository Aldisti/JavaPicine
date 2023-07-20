
INSERT INTO chat.user(login, pass) VALUES ('adi-stef', '123');
INSERT INTO chat.user(login, pass) VALUES ('gpanico', '456');
INSERT INTO chat.user(login, pass) VALUES ('mpaterno', '789');
INSERT INTO chat.user(login, pass) VALUES ('lde-mich', '321');
INSERT INTO chat.user(login, pass) VALUES ('dcastagn', '654');

INSERT INTO chat.room(name, owner) VALUES ('aaa', (SELECT id FROM chat.user WHERE id = 1));
INSERT INTO chat.room(name, owner) VALUES ('bbb', (SELECT id FROM chat.user WHERE id = 2));
INSERT INTO chat.room(name, owner) VALUES ('ccc', (SELECT id FROM chat.user WHERE id = 3));
INSERT INTO chat.room(name, owner) VALUES ('ddd', (SELECT id FROM chat.user WHERE id = 4));
INSERT INTO chat.room(name, owner) VALUES ('eee', (SELECT id FROM chat.user WHERE id = 5));

INSERT INTO chat.message(author, room, text, time) VALUES (1, 1, 'ciao', '2023-07-18 12:42:42');
INSERT INTO chat.message(author, room, text, time) VALUES (2, 2, 'come', '2023-07-18 12:42:42');
INSERT INTO chat.message(author, room, text, time) VALUES (3, 3, 'stai', '2023-07-18 12:42:42');
INSERT INTO chat.message(author, room, text, time) VALUES (4, 4, 'sono', '2023-07-18 12:42:42');
INSERT INTO chat.message(author, room, text, time) VALUES (5, 5, 'nero', '2023-07-18 12:42:42');
