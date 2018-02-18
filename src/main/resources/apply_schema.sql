
CREATE TABLE  Users (
	ID                   integer NOT NULL  GENERATED ALWAYS AS IDENTITY,
	NAME                 varchar(200) NOT NULL  ,
	SURNAME              varchar(200) NOT NULL  ,
	CONSTRAINT PK_USERS PRIMARY KEY ( ID )
);
CREATE TABLE  Aliases (
	ID                   integer NOT NULL  GENERATED ALWAYS AS IDENTITY,
	USER_ID              integer NOT NULL  ,
	ALIAS                varchar(200) NOT NULL  ,
	COMMITS              integer NOT NULL  ,
	CONSTRAINT PK_ALIASES PRIMARY KEY ( ID )
);

ALTER TABLE  Aliases ADD CONSTRAINT FK_ALIASES FOREIGN KEY ( USER_ID ) REFERENCES  Users( ID ) ON DELETE CASCADE ON UPDATE CASCADE;

