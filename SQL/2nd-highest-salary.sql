-- LeetCode 176
SELECT
	(
		SELECT DISTINCT salary
		FROM Employee
		ORDER BY salary DESC
		LIMIT 1 OFFSET 1
	)
AS SecondHighestSalary;

-- cannot use the following solution
SELECT salary,
	DENSE_RANK() OVER(ORDER BY salary DESC) AS rnk
FROM Employee
AS SalaryRank
WHERE rnk = 2;
-- because if there is no rank 2
-- WHERE clause returns nothing (an empty set)
-- and cannot use a window function alias directly in a WHERE clause
-- correct window function solution
SELECT (
	SELECT salary
	FROM (
		SELECT salary, DENSE_RANK() OVER (ORDER BY salary DESC) AS rnk
		FROM Employee
	) AS Sub
	WHERE rnk = 2
	LIMIT 1
) AS SecondHighestSalary;
