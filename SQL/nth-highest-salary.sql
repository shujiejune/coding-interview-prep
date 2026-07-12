-- LeetCode 177
SELECT (
	SELECT salary
	FROM (
		SELECT salary,
		DENSE_RANK() OVER (ORDER BY salary DESC) AS rnk
		FROM Employee
	) AS Sub
	WHERE rnk = n
	LIMIT 1
) AS NthHighestSalary;
-- Or
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
DECLARE M INT;
SET M = N-1;
  RETURN (
    SELECT DISTINCT salary
    FROM Employee
    ORDER BY salary DESC
    LIMIT M, 1
 );
END
