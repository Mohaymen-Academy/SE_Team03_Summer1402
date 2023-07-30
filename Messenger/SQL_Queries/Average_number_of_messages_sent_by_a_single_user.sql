SELECT 	AVG(cnt)
FROM	(SELECT	fk_user_id, COUNT(*) "cnt"
		 FROM 	chat_message
		 GROUP 	BY fk_user_id) CT