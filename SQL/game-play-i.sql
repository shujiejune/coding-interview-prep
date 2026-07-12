SELECT
	player_id,
	MIN(event_date) AS 'first_login'
FROM Activity
GROUP BY player_id;

-- window function
SELECT DISTINCT 
	player_id,
	FIRST_VALUE(event_date)
	OVER (
		PARTITION BY player_id
		ORDER BY event_date
	) AS 'first_login'
FROM Activity;
