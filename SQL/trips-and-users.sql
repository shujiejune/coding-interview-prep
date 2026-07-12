-- LeetCode 262
-- Solution 1: Join
SELECT
	request_at AS Day,
	ROUND(
		SUM(status != 'completed') / COUNT(*),
		2
	) AS 'Cancellation Rate'
FROM
	Trips
	LEFT JOIN Users AS Clients
	ON Trips.client_id = Clients.users_id
	LEFT JOIN Users AS Drivers
	ON Trips.driver_id = Drivers.users_id
WHERE Clients.banned = 'No'
AND Drivers.banned = 'No'
AND request_at BETWEEN '2013-10-01' AND '2013-10-03'
GROUP BY Day;
-- Solution 2: Subqueries
SELECT
	request_at AS Day,
	ROUND(
		SUM(status != 'completed') / COUNT(*),
		2
	) AS 'Cancellation Rate'
FROM Trips
WHERE request_at BETWEEN '2013-10-01' AND '2013-10-03'
AND driver_id NOT IN (
	SELECT users_id
	FROM Users
	WHERE banned = 'Yes'
)
AND client_id NOT IN (
	SELECT users_id
	FROM Users
	WHERE banned = 'Yes'
)
GROUP BY Day;
-- Solution 3: CTE
WITH TripStatus AS (
	SELECT
		request_at AS Day,
		t.status != 'completed' AS cancelled
	FROM Trips t
	JOIN Users c ON client_id = c.users_id
	AND c.banned = 'No'
	JOIN Users d ON driver_id = d.users_id
	AND d.banned = 'No'
	WHERE request_at
	BETWEEN '2013-10-01' AND '2013-10-03'
)
SELECT 
	Day,
	ROUND(
		SUM(cancelled) / COUNT(cancelled),
		2
	) AS 'Cancellation Rate'
FROM TripStatus
GROUP BY Day;
