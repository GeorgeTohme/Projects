-- ---------------------------------------------------------------------------
-- ------------------------------- vwGameScore -------------------------------
-- ---------------------------------------------------------------------------
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

	
-- ---------------------------------------------------------------------------
-- ------------------------------ vwGroupTeams -------------------------------
-- ---------------------------------------------------------------------------
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
	
	
-- ---------------------------------------------------------------------------	
-- ------------------------------ vwMatchInfo --------------------------------
-- ---------------------------------------------------------------------------
CREATE VIEW vwMatchInfo AS
    SELECT 
        m.MatchID AS MatchNo,
        mt.MatchType AS MatchType,
        m.Location AS Location,
        m.Date AS Date,
        p.FirstName AS 'Referee FN',
        p.LastName AS 'Referee LN'
    FROM
        `match` m INNER JOIN match_type mt ON m.MatchType = mt.MatchTypeID
        INNER JOIN person p ON m.RefereeID = p.PersonID;


-- ---------------------------------------------------------------------------
-- -------------------------------- vwGroup ----------------------------------
-- ---------------------------------------------------------------------------		
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


-- ---------------------------------------------------------------------------
-- ------------------------------- vwPlayer ----------------------------------
-- ---------------------------------------------------------------------------		
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
	

-- ---------------------------------------------------------------------------
-- ------------------------------- vwAward -----------------------------------
-- ---------------------------------------------------------------------------
CREATE VIEW vwAward AS
	SELECT a.AwardID,
		a.AwardName,
		p.FirstName,
        p.LastName
	FROM award a NATURAL JOIN person p;