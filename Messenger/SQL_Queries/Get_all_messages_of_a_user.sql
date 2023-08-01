SELECT 	text_message
FROM 	chat_message CM 
		INNER JOIN profile PF ON CM.fk_sender = PF.profile_id
		INNER JOIN account AC ON PF.fk_account_id = AC.username
WHERE 	AC.username = 'Ali'