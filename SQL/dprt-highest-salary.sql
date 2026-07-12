-- LeetCode 184
SELECT 
	d.name AS 'Department',
	e.name AS 'Employee',
	salary
FROM Employee e
JOIN Department d
ON e.departmentId = d.id
WHERE (e.departmentId, salary)
IN (
	SELECT departmentId, MAX(salary)
	FROM Employee
	GROUP BY departmentId
);
