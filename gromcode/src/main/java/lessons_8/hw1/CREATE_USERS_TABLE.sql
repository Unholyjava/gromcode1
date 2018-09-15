CREATE TABLE USERS
(
	ID NUMBER NOT NULL ENABLE,
	CONSTRAINT USERS_PK PRIMARY KEY (ID),
	USER_NAME NVARCHAR2(100) NOT NULL,
	PASSWORD NVARCHAR2(20) NOT NULL,
	COUNTRY NVARCHAR2(40),
	USER_TYPE NVARCHAR2(10)
);