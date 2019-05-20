CREATE VIEW vwGameScore AS
	SELECT
		m.MatchID AS MatchNo,
		IF(m.MatchType = 1, t1.GroupID, NULL) AS 'Group',
		t1.TeamID AS 'Team1ID',
		t1.TeamName AS 'Team1Name',
		tpm1.GoalsScored AS 'Team1Goals',
		tpm2.GoalsScored AS 'Team2Goals',
		t2.TeamName AS 'Team2Name',
		t2.TeamID AS 'Team2ID'
	FROM
		team t1 INNER JOIN team_plays_match tpm1 ON t1.TeamID = tpm1.TeamID
		INNER JOIN `match` m ON tpm1.MatchID = m.MatchID
		INNER JOIN team_plays_match tpm2 ON m.MatchID = tpm2.MatchID
		INNER JOIN team t2 ON tpm2.TeamID = t2.TeamID
	WHERE
		tpm1.MatchID = tpm2.MatchID
		and t1.TeamID <> t2.TeamID
	GROUP BY m.MatchID;