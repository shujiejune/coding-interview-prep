WITH RankedSalary AS (
	SELECT 
		id,
		company,
		salary,
		ROW_NUMBER() OVER (
			PARTITION BY company
			ORDER BY salary
		) AS rnk
	FROM Employee
), CompanyEmployees AS (
	SELECT
		company,
		COUNT(DISTINCT id) AS cnt
	FROM Employee
	GROUP BY company
)
SELECT 
	rs.id,
	rs.company,
	rs.salary
FROM RankedSalary rs
JOIN CompanyEmployees ce
ON rs.company = ce.company
AND rs.rnk BETWEEN ce.cnt / 2 AND ce.cnt / 2 + 1;
