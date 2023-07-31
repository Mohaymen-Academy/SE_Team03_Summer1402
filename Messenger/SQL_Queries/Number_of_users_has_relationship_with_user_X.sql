SELECT 	COUNT(*)
FROM 	chat_member CM INNER JOIN chat CH ON CM.fk_chat_id = CH.chat_id
WHERE 	CH.chat_type = '1'
AND 	CM.fk_username = 'Hossein'