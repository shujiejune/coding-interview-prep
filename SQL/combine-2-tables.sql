-- LeetCode 175
SELECT firstName, lastName, city, state
FROM Person p LEFT JOIN Address a
ON p.personId = a.personId;
-- using WHERE clause to filter records will fail
-- if there is no address info for a person
-- because it will not display the name info
