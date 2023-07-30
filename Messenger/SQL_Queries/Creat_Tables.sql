DROP TABLE IF EXISTS Chat_Member;
DROP TABLE IF EXISTS Chat_Message;
DROP TABLE IF EXISTS Chat;
DROP TABLE IF EXISTS App_User;
DROP SEQUENCE IF EXISTS App_User_id_seq;
DROP TABLE IF EXISTS Image;

CREATE TABLE Image
(
	image_id		INT				PRIMARY KEY,
	image_data		OID				NOT NULL
);

CREATE SEQUENCE App_User_id_seq;

CREATE TABLE App_User
(
	user_id			INT				PRIMARY KEY		DEFAULT nextval('App_User_id_seq'),
	username		VARCHAR(50)	NOT NULL,
	phone_number	VARCHAR(11)		NOT NULL,
	bio				VARCHAR(250),
	fk_image_id		INT				DEFAULT 1,
	FOREIGN KEY(fk_image_id) REFERENCES Image(image_id)
);

ALTER SEQUENCE App_User_id_seq
OWNED BY App_User.user_id;

CREATE TABLE Chat
(
	chat_id 		INT				PRIMARY KEY,
	chat_type		SMALLINT		NOT NULL,
	chat_name		VARCHAR(50)
);

CREATE TABLE Chat_Message
(
	message_id		INT				PRIMARY KEY,
	seen 			BOOL			DEFAULT 'false',
	time_message	DATE			NOT NULL,
	have_text		BOOL			DEFAULT 'false',
	text_message	TEXT,
	have_image		BOOL			DEFAULT 'false',
	fk_image_id		INT				DEFAULT 1,
	have_voice		BOOL			DEFAULT 'false',
	voice_URL		TEXT,
	have_file 		BOOL			DEFAULT 'false',
	file_URL		TEXT,
	fk_chat_id		INT				NOT NULL,
	fk_user_id		INT				NOT NULL,
	FOREIGN KEY(fk_image_id) REFERENCES Image(image_id),
	FOREIGN KEY(fk_chat_id) REFERENCES Chat(chat_id),
	FOREIGN KEY(fk_user_id) REFERENCES App_User(user_id)
);

CREATE TABLE Chat_Member
(
	fk_chat_id		INT				NOT NULL,
	fk_user_id		INT				NOT NULL,
	is_admin		BOOL			DEFAULT 'false',
	PRIMARY KEY(fk_chat_id, fk_user_id),
	FOREIGN KEY(fk_chat_id) REFERENCES Chat(chat_id),
	FOREIGN KEY(fk_user_id) REFERENCES App_User(user_id)
);