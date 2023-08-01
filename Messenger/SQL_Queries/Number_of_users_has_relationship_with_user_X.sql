SELECT 	COUNT(*)
FROM 	profile_connection PC 
		INNER JOIN profile PF ON PC.fk_profile_1 = PF.profile_id
		INNER JOIN account AC ON PF.fk_account_id = AC.username
AND 	AC.username = 'Ali'