CREATE FUNCTION isSeen(target_message_id integer) RETURNS BOOL
    LANGUAGE SQL
    IMMUTABLE
	RETURN	(SELECT 
				CASE 
					WHEN cnt >= (SELECT	MAX(message_count)
								 FROM 	Seen
								 WHERE 	fk_username != 	(SELECT	fk_username
														 FROM 	Chat_Message
														 WHERE 	message_id = target_message_id)
								 AND  	fk_chat_id 	=	(SELECT	fk_chat_id
														 FROM 	Chat_Message
														 WHERE 	message_id = target_message_id)) THEN false
					ELSE true
				END	
			 FROM 
				(SELECT COUNT(*) "cnt"
				 FROM	chat_message
				 WHERE 	fk_chat_id =  	(SELECT fk_chat_id
										 FROM 	chat_message
										 WHERE  message_id = target_message_id)
				 AND 	message_time <	(SELECT message_time
										 FROM 	chat_message
										 WHERE  message_id = target_message_id)) GG);