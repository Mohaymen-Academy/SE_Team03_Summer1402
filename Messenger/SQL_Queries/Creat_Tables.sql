DROP FUNCTION IF EXISTS isSeen;
DROP TABLE IF EXISTS Seen;
DROP TABLE IF EXISTS Chat_Member;
DROP TABLE IF EXISTS Chat_Message;
DROP SEQUENCE IF EXISTS Chat_Message_id_seq;
DROP TABLE IF EXISTS Chat;
DROP SEQUENCE IF EXISTS Chat_id_seq;
DROP TABLE IF EXISTS Profile;
DROP TABLE IF EXISTS Account;
DROP TABLE IF EXISTS Image;
DROP SEQUENCE IF EXISTS Image_id_seq;


-- IMAGE TABLE:

CREATE SEQUENCE Image_id_seq;

CREATE TABLE Image
(
	image_id		INT				PRIMARY KEY		DEFAULT nextval('Image_id_seq'),
	image_data		BYTEA			NOT NULL
);

ALTER SEQUENCE Image_id_seq
OWNED BY Image.image_id;


-- Account TABLE:

CREATE TABLE Account
(
	username		VARCHAR(50)		PRIMARY KEY,
	password_hash 	TEXT			NOT NULL
);	


-- Profile TABLE:

CREATE TABLE Profile
(
	fk_username		VARCHAR(50)		PRIMARY KEY,
	display_name	VARCHAR(50)		NOT NULL,
	phone_number	VARCHAR(11)		NOT NULL,
	bio				VARCHAR(250),
	fk_image_id		INT				DEFAULT 1,
	FOREIGN KEY(fk_username) REFERENCES Account(username)
		ON DELETE CASCADE,
	FOREIGN KEY(fk_image_id) REFERENCES Image(image_id)
);


-- CHAT TABLE:

CREATE SEQUENCE Chat_id_seq;

CREATE TABLE Chat
(
	chat_id 		INT				PRIMARY KEY		DEFAULT nextval('Chat_id_seq'),
	chat_type		SMALLINT		NOT NULL,
	chat_name		VARCHAR(50)
);

ALTER SEQUENCE Chat_id_seq
OWNED BY Chat.chat_id;


-- CHAT_MESSAGE TABLE:

CREATE SEQUENCE Chat_Message_id_seq;

CREATE TABLE Chat_Message
(
	message_id		INT				PRIMARY KEY		DEFAULT nextval('Chat_Message_id_seq'),
	message_time	DATE			DEFAULT NOW(),
	have_text		BOOL			DEFAULT 'false',
	text_message	TEXT,
	have_image		BOOL			DEFAULT 'false',
	fk_image_id		INT				DEFAULT 1,
	have_voice		BOOL			DEFAULT 'false',
	voice_URL		TEXT,
	have_file 		BOOL			DEFAULT 'false',
	file_URL		TEXT,
	fk_chat_id		INT				NOT NULL,
	fk_username		VARCHAR(50)		NOT NULL,
	FOREIGN KEY(fk_image_id) REFERENCES Image(image_id),
	FOREIGN KEY(fk_chat_id) REFERENCES Chat(chat_id),
	FOREIGN KEY(fk_username) REFERENCES Account(username)
		ON DELETE CASCADE
);

ALTER SEQUENCE Chat_Message_id_seq
OWNED BY Chat_Message.message_id;


-- CHAT_MEMBER TABLE:

CREATE TABLE Chat_Member
(
	fk_chat_id		INT				NOT NULL,
	fk_username		VARCHAR(50)		NOT NULL,
	is_admin		BOOL			DEFAULT 'false',
	PRIMARY KEY(fk_chat_id, fk_username),
	FOREIGN KEY(fk_chat_id) REFERENCES Chat(chat_id),
	FOREIGN KEY(fk_username) REFERENCES Account(username)
		ON DELETE CASCADE
);

-- Seen TABLE:

CREATE TABLE Seen
(
	fk_chat_id		INT				NOT NULL,
	fk_username		VARCHAR(50)		NOT NULL,
	message_count	INT				DEFAULT 0,
	PRIMARY KEY(fk_chat_id, fk_username),
	FOREIGN KEY(fk_chat_id) REFERENCES Chat(chat_id),
	FOREIGN KEY(fk_username) REFERENCES Account(username)
		ON DELETE CASCADE
);