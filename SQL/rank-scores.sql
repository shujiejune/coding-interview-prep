-- LeetCode 178
SELECT score,
DENSE_RANK() OVER (ORDER BY score DESC) AS 'rank'
FROM Scores;
