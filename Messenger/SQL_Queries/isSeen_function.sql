DROP FUNCTION IF EXISTS is_Seen;
CREATE FUNCTION is_Seen(target_message_id integer) RETURNS BOOL
    LANGUAGE SQL
    IMMUTABLE
	RETURN	(SELECT 
				CASE 
					WHEN mt >= 	(SELECT	message_time
								 FROM 	chat_message
								 WHERE 	message_id = target_message_id
							   	 LIMIT 	1) THEN true
					ELSE false
				END	
			 FROM 
				(SELECT message_time "mt"
				 FROM	profile_connection PC 
				 		INNER JOIN chat_message CM ON CM.message_id = PC.fk_last_message_seen
				 WHERE 	PC.fk_profile_1 =	(SELECT fk_receiver
										 	 FROM 	chat_message
											 WHERE  message_id = target_message_id)
				 AND	PC.fk_profile_2 =	(SELECT fk_sender
										 	 FROM 	chat_message
											 WHERE  message_id = target_message_id)
				 LIMIT 	1) MT);