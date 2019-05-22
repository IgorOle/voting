DROP TABLE VOTES;
DROP TABLE MENU;
DROP TABLE DISHES;
DROP TABLE RESTAURANTS;
DROP TABLE USER_ROLES;
DROP TABLE USERS;

DROP SEQUENCE PUBLIC.GLOBAL_SEQ;

CREATE TABLE USERS
(
  ID INTEGER PRIMARY KEY NOT NULL,
  EMAIL VARCHAR(200) NOT NULL,
  "NAME" VARCHAR(200) NOT NULL,
  ENABLED BOOLEAN DEFAULT TRUE NOT NULL,
  PASSWORD VARCHAR(200) NOT NULL,
  CONSTRAINT USERS_EMAIL_IDX UNIQUE (EMAIL)
);
CREATE TABLE USER_ROLES
(
  USER_ID INTEGER NOT NULL,
  ROLE VARCHAR(30),
  CONSTRAINT USER_ROLES_IDX UNIQUE (USER_ID, ROLE),
  FOREIGN KEY (USER_ID) REFERENCES USERS (ID) ON DELETE CASCADE
);
CREATE TABLE RESTAURANTS
(
	ID INTEGER PRIMARY KEY NOT NULL,
	"NAME" VARCHAR(300) NOT NULL,
	CONSTRAINT RESTAURANT_NAME_IDX UNIQUE (NAME)
)
;
CREATE TABLE DISHES
(
	ID INTEGER PRIMARY KEY NOT NULL,
	"NAME" VARCHAR(300) NOT NULL,
	"DESCRIBE" VARCHAR(300),
	CONSTRAINT DISHES_NAME_IDX UNIQUE (NAME)
)
;
CREATE TABLE MENU
(
	ID INTEGER NOT NULL PRIMARY KEY,
	DATE_IS DATE,
	DISH_ID INTEGER NOT NULL
		constraint MENU_DISHES_ID_FK
			references DISHES
				on delete cascade,
	RESTAURANT_ID INTEGER NOT NULL
		constraint MENU_RESTAURANTS_ID_FK
			references RESTAURANTS
				on delete cascade,
    CONSTRAINT RESTAURANT_DATE_DISH_RESTAURANT_IDX UNIQUE (DATE_IS, DISH_ID, RESTAURANT_ID)
)
;


CREATE TABLE VOTES
(
	ID INTEGER not null
		primary key,
	USER_ID INTEGER not null
		constraint VOTES_USERS_ID_FK
			references USERS
				on delete cascade,
	RESTAURANT_ID INTEGER not null
		constraint VOTES_RESTAURANTS_ID_FK
			references RESTAURANTS
				on delete cascade,
	DATE_IS TIMESTAMP not null
)
;




CREATE UNIQUE INDEX users_email_uindex ON users (email);
CREATE SEQUENCE PUBLIC.global_seq START WITH 100000 INCREMENT BY 1;

