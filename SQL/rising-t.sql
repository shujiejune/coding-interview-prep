-- LeetCode 197
SELECT w2.id
FROM
	Weather w1,
	Weather w2
WHERE w1.temperature < w2.temperature
AND DATEDIFF(w2.recordDate, w1.recordDate) = 1;
