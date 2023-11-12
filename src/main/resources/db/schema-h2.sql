DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    id       BIGINT      NOT NULL,
    username VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR,
    PRIMARY KEY (id)
);