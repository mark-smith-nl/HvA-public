-- noinspection SqlDialectInspection

SELECT user_intresses.persoonID,
COUNT(user_intresses.persoonID) AS hits
FROM user_intresses
WHERE user_intresses.persoonID != 3 AND u
ser_intresses.interesseID in
(SELECT i.interesseID FROM user_intresses AS i
WHERE i.persoonID = 3)
AND user_intresses.persoonID NOT IN (SELECT m.matchId FROM matches AS m WHERE m.user_Id = 3)
GROUP BY user_intresses.persoonID ORDER BY COUNT(user_intresses.persoonID DESC)
