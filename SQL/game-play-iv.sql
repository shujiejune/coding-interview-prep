WITH first_logins AS (
	SELECT 
		a.player_id,
		MIN(a.event_date) AS first_login
	FROM Activity a
	GROUP BY player_id
), consec_logins AS (
	SELECT
		COUNT(a.player_id) AS num_logins
	FROM first_logins f
	INNER JOIN Activity a
	ON f.player_id = a.player_id
	AND f.first_login = DATE_SUB(a.event_date, INTERVAL 1 DAY)
)
SELECT
	ROUND(
		(SELECT c.num_logins FROM consec_logins c) /
		(SELECT COUNT(f.player_id) FROM first_logins f)
	, 2) AS fraction;
