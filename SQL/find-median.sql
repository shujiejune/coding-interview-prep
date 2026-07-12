-- LeetCode 571
SELECT avg(n.Number) median
FROM Numbers n
WHERE n.Frequency >= abs(
	(SELECT sum(Frequency) FROM Numbers WHERE Number <= n.Number) -
	(SELECT sum(Frequency) FROM Numbers WHERE Number >= n.Number)
)
