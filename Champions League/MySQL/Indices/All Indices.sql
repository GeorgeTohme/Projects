CREATE INDEX GoalsAssists_idx ON Player
(Goals DESC, Assists DESC);
--------------------------------------
CREATE INDEX AssistsGoals_idx ON Player
(Assists DESC, Goals DESC);