CREATE VIEW vwGroupTeams AS 
	SELECT
		GroupID,
		TeamID,
		TeamName,
		(Wins + Losses + Draws) AS MatchesPlayed,
		Wins,
		Draws,
		Losses,
		GoalsFor,
		GoalsAgainst,
		(GoalsFor - GoalsAgainst) AS GoalDifference,
		(3 * Wins + Draws) AS Points
	FROM team
	ORDER BY GroupID ASC, Points DESC, GoalDifference DESC;