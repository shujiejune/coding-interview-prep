WITH RankedActivity AS (
	SELECT 
		player_id,
		device_id,
		RANK() OVER (
			PARTITION BY player_id
			ORDER BY event_date ASC
		) AS rnk
	FROM Activity
)
SELECT player_id, device_id
FROM RankedActivity
WHERE rnk = 1;
