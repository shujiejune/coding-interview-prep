-- LeetCode 181
SELECT a.name AS 'Employee'
FROM
	Employee AS a,
	Employee AS b
WHERE a.managerId = b.Id AND a.salary > b.salary;
