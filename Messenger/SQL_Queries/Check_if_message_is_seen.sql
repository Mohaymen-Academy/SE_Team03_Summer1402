SELECT 	COUNT(*)
FROM	chat_message
WHERE 	fk_chat_id = '1'
AND 	message_time < 	(SELECT 	message_time
						 FROM 	chat_message
					     WHERE   message_id = '20') S;