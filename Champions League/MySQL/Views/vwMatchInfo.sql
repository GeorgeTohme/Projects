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