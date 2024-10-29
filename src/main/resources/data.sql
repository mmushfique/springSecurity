INSERT INTO user (username, password, active)
    values ('user','user',true);

INSERT INTO user (username, password, active)
    values ('admin','admin',true);


INSERT INTO authorities (username,authority)
    values ('user','ROLE_USER');

INSERT INTO authorities (username,authority)
    values ('admin','ROLE_ADMIN');