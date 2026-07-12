-- LeetCode 182
SELECT email
FROM (
	SELECT email, COUNT(email) AS num
	FROM Person
	GROUP BY email
) AS stats
WHERE num > 1;
-- Or
SELECT email
FROM Person
GROUP BY email
HAVING COUNT(email) > 1;
