CREATE VIEW vwPlayer AS
	SELECT p.PlayerID,
		per.FirstName,
        per.LastName,
        t.TeamName AS Team,
        pos.Position AS FieldPosition,
        r.PlayerRole AS Role,
        s.Status,
        p.Goals,
        p.Assists,
        p.RedCards,
        p.YellowCards		
	FROM player p NATURAL JOIN person per
	NATURAL JOIN team t
	INNER JOIN position pos ON p.FieldPosition = pos.PositionID
	INNER JOIN player_role r ON r.PlayerRoleID = p.PlayerRole
	INNER JOIN status s ON p.Status = s.StatusID;