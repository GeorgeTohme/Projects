CREATE VIEW vwGroup AS 
	SELECT
		g.GroupID,
		WinnerTeam AS WinnerTeamID,
		w.TeamName AS Winner,
		RunnerUpTeam AS RunnerupTeamID,
		r.TeamName AS Runnerup
	FROM
		`group` g LEFT OUTER JOIN team w ON g.WinnerTeam = w.TeamID 
		LEFT OUTER JOIN team r ON g.RunnerupTeam = r.TeamID;