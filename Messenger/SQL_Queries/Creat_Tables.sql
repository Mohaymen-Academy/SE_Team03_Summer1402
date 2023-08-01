DROP TABLE IF EXISTS Profile_Connection;
DROP TABLE IF EXISTS Chat_Message;
DROP SEQUENCE IF EXISTS Chat_Message_id_seq;
DROP TABLE IF EXISTS Profile;
DROP SEQUENCE IF EXISTS Profile_id_seq;
DROP TABLE IF EXISTS Account;
DROP TABLE IF EXISTS Data_File;
DROP SEQUENCE IF EXISTS Data_File_id_seq;
DROP TYPE IF EXISTS Profile_Type;
CREATE TYPE Profile_Type AS ENUM ('Pv', 'Group', 'Channel');

-- Data_File TABLE:

CREATE SEQUENCE Data_File_id_seq;

CREATE TABLE Data_File
(
	file_id			INT				PRIMARY KEY		DEFAULT nextval('Data_File_id_seq'),
	file_data		BYTEA			NOT NULL
);

ALTER SEQUENCE Data_File_id_seq
OWNED BY Data_File.file_id;


-- Account TABLE:

CREATE TABLE Account
(
	username		VARCHAR(50)		PRIMARY KEY,
	password_hash 	TEXT			NOT NULL,
	phone_number 	VARCHAR(11)		NOT NULL
);	


-- Profile TABLE:

CREATE SEQUENCE Profile_id_seq;

CREATE TABLE Profile
(
	profile_id		INT				PRIMARY KEY 	DEFAULT nextval('Profile_id_seq'),
	display_name	VARCHAR(50)		NOT NULL,
	bio				VARCHAR(250)	DEFAULT '',
	fk_image_id		INT				DEFAULT 1,
	profile_type    Profile_Type 	DEFAULT 'Pv',
	fk_account_id 	VARCHAR(50),
	FOREIGN KEY(fk_account_id) REFERENCES Account(username)
		ON DELETE CASCADE,
	FOREIGN KEY(fk_image_id) REFERENCES Data_File(file_id)
);

ALTER SEQUENCE Profile_id_seq
OWNED BY Profile.profile_id;

-- CHAT_MESSAGE TABLE:

CREATE SEQUENCE Chat_Message_id_seq;

CREATE TABLE Chat_Message
(
	message_id		INT				PRIMARY KEY		DEFAULT nextval('Chat_Message_id_seq'),
	message_time	DATE			DEFAULT NOW(),
	text_message	TEXT,
	have_file 		BOOL			DEFAULT 'false',
	fk_file_id		INT,
	fk_sender		INT				NOT NULL,
	fk_receiver		INT				NOT NULL,
	FOREIGN KEY(fk_file_id) REFERENCES Data_File(file_id),
	FOREIGN KEY(fk_sender) REFERENCES Profile(profile_id)
		ON DELETE CASCADE,
	FOREIGN KEY(fk_receiver) REFERENCES Profile(profile_id)
		ON DELETE CASCADE
);

ALTER SEQUENCE Chat_Message_id_seq
OWNED BY Chat_Message.message_id;


-- Profile_Connection TABLE:

CREATE TABLE Profile_Connection
(
	fk_profile_1			INT		NOT NULL,
	fk_profile_2			INT		NOT NULL,
	is_admin				BOOL	DEFAULT 'false',
	fk_last_message_seen	INT,
	PRIMARY KEY(fk_profile_1, fk_profile_2),
	FOREIGN KEY(fk_profile_1) REFERENCES Profile(profile_id)
		ON DELETE CASCADE,
	FOREIGN KEY(fk_profile_2) REFERENCES Profile(profile_id)
		ON DELETE CASCADE,
	FOREIGN KEY(fk_last_message_seen) REFERENCES Chat_Message(message_id)
);