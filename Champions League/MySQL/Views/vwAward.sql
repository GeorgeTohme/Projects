CREATE VIEW vwAward AS
	SELECT a.AwardID,
		a.AwardName,
		p.FirstName,
        p.LastName
	FROM award a NATURAL JOIN person p;