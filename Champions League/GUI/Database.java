import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
	
	Connection conn;
	
	public Database() {
		String url = "jdbc:mysql://localhost:3306/championsleague";
		String username = "root";
		String passwd = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, passwd);
			Statement stmt = conn.createStatement();
			stmt.execute("CREATE DATABASE IF NOT EXISTS DATABASE_NAME");
		} catch (ClassNotFoundException cE) {
			System.out.println("Class Not Found Exception: " + cE.toString());      
		} catch (SQLException e) {
			System.out.println("SQL Exception: " + e.toString());
		}	
	}
	
	public Object[][] players() throws SQLException{
            // Query
	       String slctString = "SELECT * FROM vwplayer ORDER BY Team";
               Statement selectPlayers = conn.createStatement();
	       ResultSet rs = selectPlayers.executeQuery(slctString);
	       
            // Parse
	       ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
	       while(rs.next()){
	    	   ArrayList<Object> tuple = new ArrayList<Object>();
	    	   tuple.add(rs.getInt("PlayerID"));
	    	   tuple.add(rs.getString("FirstName"));
	    	   tuple.add(rs.getString("LastName"));
                   tuple.add(rs.getString("Team"));
                   tuple.add(rs.getString("FieldPosition"));
                   tuple.add(rs.getString("Role"));
                   tuple.add(rs.getString("Status"));
                   tuple.add(rs.getInt("Goals"));
                   tuple.add(rs.getInt("Assists"));
                   tuple.add(rs.getInt("RedCards"));
                   tuple.add(rs.getInt("YellowCards"));
	    	   data.add(tuple);
	       }
               
               // close stmt
               rs.close();
	       selectPlayers.close();
               
               // process data
               int colCount = 11;
	       Object[][] data2 = new Object[data.size()][colCount];
	       for(int i = 0; i < data.size(); i++){ 
	    	   for(int j = 0; j < colCount; j++)
	    		   data2[i][j] = data.get(i).get(j);
	       }
	       return data2;
	}
	
	public Object[] playersTitle() throws SQLException {
		
	       String[] columnNames = {"PlayerID", "FirstName", "LastName", "Team", "FieldPosition", "Role", "Status", "Goals", "Assists", "RedCards", "YellowCards"};
	       return columnNames;
	}

	public Object[][] matchInfo() throws SQLException{
		Statement stmt = conn.createStatement();
	       String sql = "SELECT * FROM vwmatchinfo";
	       ResultSet rs = stmt.executeQuery(sql);
	       
	       ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
	       while(rs.next()){
	    	   ArrayList<Object> tuple = new ArrayList<Object>();
	    	   tuple.add(rs.getInt("MatchNo"));
	    	   tuple.add(rs.getString("MatchType"));
	    	   tuple.add(rs.getString("Location"));
                   tuple.add(rs.getDate("Date"));
                   tuple.add(rs.getString("Referee FN"));
                   tuple.add(rs.getString("Referee LN"));
	    	   data.add(tuple);
	       }
               
	       rs.close();
	       stmt.close();
               
               int colCount = 6;
	       Object[][] data2 = new Object[data.size()][colCount];
	       for(int i = 0; i < data.size(); i++){ 
	    	   for(int j = 0; j < colCount; j++)
	    		   data2[i][j] = data.get(i).get(j);
	       }
	       return data2;
	}
	
	public Object[] matchInfoTitle() throws SQLException {
		
	       String[] columnNames = {"MatchNo", "MatchType", "Location", "Date", "Referee FN", "Referee LN"};
	       return columnNames;
	}
	
	public Object[][] groupTeams() throws SQLException{
		Statement stmt = conn.createStatement();
	       String sql = "SELECT * FROM vwgroupteams";
	       ResultSet rs = stmt.executeQuery(sql);
	       
	       ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
	       while(rs.next()){
	    	   ArrayList<Object> tuple = new ArrayList<Object>();
	    	   tuple.add(rs.getString("GroupID"));
	    	   tuple.add(rs.getString("TeamID"));
	    	   tuple.add(rs.getString("TeamName"));
                   tuple.add(rs.getInt("MatchesPlayed"));
                   tuple.add(rs.getInt("Wins"));
                   tuple.add(rs.getInt("Draws"));
                   tuple.add(rs.getInt("Losses"));
                   tuple.add(rs.getInt("GoalsFor"));
                   tuple.add(rs.getInt("GoalsAgainst"));
                   tuple.add(rs.getInt("GoalDifference"));
                   tuple.add(rs.getInt("Points"));
	    	   data.add(tuple);
	       }
               
	       rs.close();
	       stmt.close();
               
               int colCount = 11;
	       Object[][] data2 = new Object[data.size()][colCount];
	       for(int i = 0; i < data.size(); i++){ 
	    	   for(int j = 0; j < colCount; j++)
	    		   data2[i][j] = data.get(i).get(j);
	       }
	       return data2;
	}
	
	public Object[] groupTeamsTitle() throws SQLException {
		
	       String[] columnNames = {"GroupID", "TeamID", "TeamName", "MatchesPlayed", "Wins", "Draws", "Losses", "GoalsFor", "GoalsAgainst", "GoalDifference", "Points"};
	       return columnNames;
	}
	
        public Object[][] group() throws SQLException{
		Statement stmt = conn.createStatement();
	       String sql = "SELECT * FROM vwgroup";
	       ResultSet rs = stmt.executeQuery(sql);
	       
	       ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
	       while(rs.next()){
	    	   ArrayList<Object> tuple = new ArrayList<Object>();
	    	   tuple.add(rs.getString("GroupID"));
	    	   tuple.add(rs.getString("Winner"));
               tuple.add(rs.getString("Runnerup"));
	    	   data.add(tuple);
	       }
               
	       rs.close();
	       stmt.close();
               
               int colCount = 3;
	       Object[][] data2 = new Object[data.size()][colCount];
	       for(int i = 0; i < data.size(); i++){ 
	    	   for(int j = 0; j < colCount; j++)
	    		   data2[i][j] = data.get(i).get(j);
	       }
	       return data2;
	}
	
	public Object[] groupTitle() throws SQLException {
		
	       String[] columnNames = {"GroupID", "Winner", "Runnerup"};
	       return columnNames;
	}
        	
	public void insertMatch(String matchType, String location, String date, String firstname, String lastname, int age, String nationality, String team1, int team1goals, String team2, int team2goals) throws SQLException {
	    // get ids:
            Statement stmt1 = conn.createStatement();
            Statement stmt2 = conn.createStatement();
            Statement stmt3 = conn.createStatement();

               String sql1 = "SELECT count(PersonID) FROM person;";
	       String sql2 = "SELECT count(MatchID) FROM `match`;";
               String sql3 = "SELECT count(TeamMatchID) FROM team_plays_match;";
               // team 1
               String getTeam1IDString = "SELECT TeamID FROM team WHERE TeamName = ?;";
               PreparedStatement getTeamID1 = conn.prepareStatement(getTeam1IDString);
               // team 2
               String getTeam2IDString = "SELECT TeamID FROM team WHERE TeamName = ?;";
               PreparedStatement getTeamID2 = conn.prepareStatement(getTeam2IDString);
              
               getTeamID1.setString(1, team1);
               getTeamID2.setString(1, team2);
               
               ResultSet rs_1 = stmt1.executeQuery(sql1);
               ResultSet rs_2 = stmt2.executeQuery(sql2);
               ResultSet rs_3 = stmt3.executeQuery(sql3);
               ResultSet rs_4 = getTeamID1.executeQuery();
               ResultSet rs_5 = getTeamID2.executeQuery();         
               
                int PersonID = 0;
                int MatchID = 0;
                int TeamMatchID = 0;
                int team1ID = 0;
                int team2ID = 0;
                
               if(rs_1.next()) {
                   PersonID = rs_1.getInt(1) + 1;
               }
               rs_1.close();
               if (rs_2.next()) {
                   MatchID = rs_2.getInt(1) + 1;
               }
               rs_2.close();
               if (rs_3.next()) {
                   TeamMatchID = rs_3.getInt(1) + 1;
               }
               rs_3.close();
               if(rs_4.next()) {
                   team1ID = rs_4.getInt(1);
               }
               rs_4.close();
               if (rs_5.next()) {
                   team2ID = rs_5.getInt(1);
               } 
               rs_5.close();
       
               //inserts
            String person = "INSERT INTO person (PersonID, PersonType, FirstName, LastName, Age, Nationality) "
	    		+ "VALUES (?, ?, ?, ?, ?, ?);";
	    String match = "INSERT INTO `match` (MatchID, MatchType, RefereeID, Location, Date) VALUES (?, ?, ?, ?, ?);";
            String team_match1 = "INSERT INTO team_plays_match (TeamMatchID, MatchID, TeamID, GoalsScored) VALUES (?, ?, ?, ?);";
            String team_match2 = "INSERT INTO team_plays_match (TeamMatchID, MatchID, TeamID, GoalsScored) VALUES (?, ?, ?, ?);";
	    
	    PreparedStatement personStmt = conn.prepareStatement(person);
            PreparedStatement matchStmt = conn.prepareStatement(match);
            PreparedStatement team_matchStmt1 = conn.prepareStatement(team_match1);
            PreparedStatement team_matchStmt2 = conn.prepareStatement(team_match2);
            
            // person
	      personStmt.setInt (1, PersonID);
	      personStmt.setInt (2, 3);
	      personStmt.setString (3, firstname);
	      personStmt.setString (4, lastname);
	      personStmt.setInt (5, age);
	      personStmt.setString (6, nationality);
	    
            // match
	      matchStmt.setInt(1, MatchID);
              int matchtypeID;
              switch (matchType) {
                  case "Group Stage": matchtypeID = 1;
                  case "Round of 16": matchtypeID = 2;
                  case "Quarter-final": matchtypeID = 3;
                  case "Semi-final": matchtypeID = 4;
                  case "Final": matchtypeID = 5;
                  default: matchtypeID = 1;
              }
              matchStmt.setInt(2, matchtypeID);
	      matchStmt.setInt(3, PersonID);
              matchStmt.setString(4, location);
              matchStmt.setString(5, date);
            
            // team_match1
            team_matchStmt1.setInt(1, TeamMatchID);
	    team_matchStmt1.setInt (2, MatchID);
	    team_matchStmt1.setInt (3, team1ID);
	    team_matchStmt1.setInt (4, team1goals);
            TeamMatchID++;
            
            // team_match1
            team_matchStmt2.setInt(1, TeamMatchID);
	    team_matchStmt2.setInt (2, MatchID);
	    team_matchStmt2.setInt (3, team2ID);
	    team_matchStmt2.setInt (4, team2goals);
	    
            personStmt.executeUpdate();
	    matchStmt.executeUpdate();
            team_matchStmt1.executeUpdate();
            team_matchStmt2.executeUpdate();
            
            /// close
               stmt1.close();
               stmt2.close();
               stmt3.close();
               getTeamID1.close();
               getTeamID2.close();
//              rs_1.close();
//               rs_2.close();
//               rs_3.close();
//               rs_4.close();
//               rs_5.close(); 
            personStmt.close();
            matchStmt.close();
            team_matchStmt1.close();
            team_matchStmt2.close();
	}
        
        // Update database
     public void updateDatabase() throws SQLException {
         this.updateAllTeamResults(); // updates all teams' results according to the matches they played
         this.updateGroupResults(); // updates all groups with their winners and runner-ups
	 this.updateAwards();
     }
     
     // reset db
     public void resetDatabase() throws SQLException {
         this.resetTeamResults(); // sets all team results to 0 (in case any contradicting data was inserted before)
	this.resetGroupResults(); // sets winners and runnerups of each group to null
	this.resetAwards(); // sets the top scorer and most assists to null	
     }
     
     // updates wins/losses/draws/GoalsFor/GoalsAgainst of teams given a range of MatchNos (using function below this one)
	public void updateAllTeamResults() throws SQLException {
            Statement stmt2 = conn.createStatement();
            String sql2 = "SELECT count(MatchID) FROM `match`;";
            ResultSet rs2 = stmt2.executeQuery(sql2);
            rs2.next();
            int MatchID = rs2.getInt(1);
            
		for (int i = 0; i <= MatchID; i++) {
			this.updateTeamsResults(i);
		}
	}

	// function that updates 2 team's wins/losses/draws/GoalsFor/GoalsAgainst given the match results of a specific match (GoalsScored of each team)
	// (only for group stage matches)
	public void updateTeamsResults(int matchNo) throws SQLException {
		// 1) create statement to get Match results corresponding to the passed MatchNo as argument
		String sqlString = "SELECT Team1ID, Team1Goals, Team2ID, Team2Goals FROM vwgamescore where MatchNo = ?";
		PreparedStatement selectStmt = conn.prepareStatement(sqlString);
		selectStmt.setInt(1, matchNo);
		ResultSet match = selectStmt.executeQuery();

		// 2) Read results (get both team's names and their goals scored)
                int team1ID = 0;
                int team1Goals = 0;
                int team2ID = 0;
                int team2Goals = 0;
		if(match.next()) {
                    // Team1:
                    team1ID = match.getInt("Team1ID");
                    team1Goals = match.getInt("Team1Goals");
                    // Team2:
                    team2ID = match.getInt("Team2ID");
                    team2Goals = match.getInt("Team2Goals");
                }
		

		// 3) close select statements
		match.close();
		selectStmt.close();	

		// 4) Process results to determine the winner/loser/tied teams
		int result = team1Goals - team2Goals;
		String sqlTeam1; // update statement for first team
		String sqlTeam2; // update statement for second team

		String win = "UPDATE team SET Wins = Wins + 1, ";
		String loss = "UPDATE team SET Losses = Losses + 1, ";
		String draw = "UPDATE team SET Draws = Draws + 1, ";
		String goalsForAgainst = "GoalsFor = GoalsFor + ?, GoalsAgainst = GoalsAgainst + ? WHERE TeamID = ?";

		if (result > 0) { // Team1 won
			sqlTeam1 = win;
			sqlTeam2 = loss;
		}
		else if (result < 0) { // Team2 won
			sqlTeam1 = loss;
			sqlTeam2 = win;
		}
		else { // tie
			sqlTeam1 = draw;
			sqlTeam2 = draw;
		}

		sqlTeam1 += goalsForAgainst;
		sqlTeam2 += goalsForAgainst;

		// 5) Update using prepared statements
		// prepared statement for first team
		PreparedStatement updateStatement1 = conn.prepareStatement(sqlTeam1);
		updateStatement1.setInt(1, team1Goals);
		updateStatement1.setInt(2, team2Goals);
		updateStatement1.setInt(3, team1ID);

		// prepared statement for second team
		PreparedStatement updateStatement2 = conn.prepareStatement(sqlTeam2);
		updateStatement2.setInt(1, team2Goals);
		updateStatement2.setInt(2, team1Goals);
		updateStatement2.setInt(3, team2ID);

		// execute and close statements
		updateStatement1.executeUpdate();
		updateStatement2.executeUpdate();

		updateStatement1.close();
		updateStatement2.close();
	}

        
	// resets wins/losses/draws/goalsfor/goalsagainst to 0
	public void resetTeamResults() throws SQLException {
		String updateSQL = "UPDATE team SET Wins = 0, Losses = 0, Draws = 0, GoalsFor = 0, GoalsAgainst = 0";
		PreparedStatement updateStatement = conn.prepareStatement(updateSQL);
		updateStatement.executeUpdate();
	}

	// function that removes safe mode to enable modifying more easily
	public void removeSafeMode() throws SQLException {
		String removeSafeModeStr = "SET SQL_SAFE_UPDATES = 0;";
		PreparedStatement removeSafeMode = conn.prepareStatement(removeSafeModeStr);
		removeSafeMode.executeUpdate();
	}

	// resets WinnerTeams and Runnerups to NULL
	public void resetGroupResults() throws SQLException {
		// first remove safe mode to allow updates 
		this.removeSafeMode();

		// then execute update
		String updateSQL = "UPDATE `group` SET WinnerTeam = NULL, RunnerupTeam = NULL;";
		PreparedStatement updateStatement = conn.prepareStatement(updateSQL);
		updateStatement.executeUpdate();
	}

	// resets WinnerTeams and Runnerups to NULL
	public void resetAwards() throws SQLException {
		this.removeSafeMode();

		// then execute update
		String updateSQL = "UPDATE award SET PersonID = NULL Where AwardID = 1 or AwardID = 2;";
		PreparedStatement updateStatement = conn.prepareStatement(updateSQL);
		updateStatement.executeUpdate();
	}

	// updates all groups in database using function below this one
	public void updateGroupResults() throws SQLException {
		this.updateGroupResults("B");
		this.updateGroupResults("C");
		this.updateGroupResults("E");
		this.updateGroupResults("H");
	}

	// function that updates a specific group's WinnerTeam and RunnerupTeam 
	public void updateGroupResults(String groupID) throws SQLException {
		// 1) create statement to get Team scores
		String sqlString = "SELECT TeamID FROM vwgroupteams WHERE GroupID = ? ORDER BY GroupID ASC, Points DESC, GoalDifference DESC;";
		PreparedStatement selectStmt = conn.prepareStatement(sqlString);
		selectStmt.setString(1, groupID);
		ResultSet group = selectStmt.executeQuery();

		// 2) Read results (get both team's names and their goals scored)
		group.next();
		// WinnerTeam:
		int winnerTeamID = group.getInt("TeamID");

		group.next();
		// RunnerupTeam
		int runnerupTeamID = group.getInt("TeamID");

		// 3) close select statements
		group.close();
		selectStmt.close();	


		// 4) Update using prepared statements
		String updateString = "UPDATE `group` SET WinnerTeam = ?, RunnerupTeam = ? WHERE GroupID = ?";
		PreparedStatement updateStatement = conn.prepareStatement(updateString);
		updateStatement.setInt(1, winnerTeamID);
		updateStatement.setInt(2, runnerupTeamID);
		updateStatement.setString(3, groupID);

		// execute and close statements
		updateStatement.executeUpdate();
		updateStatement.close();
	}

	// function that updates the award table 
	public void updateAwards() throws SQLException {
		this.updateTopScorer();
		this.updateMostAssists();
	}

	// function that updates the top scorer
	public void updateTopScorer() throws SQLException {
		// 1) statement to get player stats
		String sqlString = "SELECT PersonID FROM Player order by Goals Desc, Assists Desc";
		PreparedStatement selectStmt = conn.prepareStatement(sqlString);
		ResultSet topScorer = selectStmt.executeQuery();

		// 2) Read results
		topScorer.next();
		// Top Scorer ID:
		int topScorerID = topScorer.getInt("PersonID");

		// 3) close select statements
		topScorer.close();
		selectStmt.close();	

		// 4) Update using prepared statements
		String updateString = "UPDATE award SET PersonID = ? WHERE AwardID = 1;";
		PreparedStatement updateStatement = conn.prepareStatement(updateString);
		updateStatement.setInt(1, topScorerID);

		// execute and close statements
		updateStatement.executeUpdate();
		updateStatement.close();
	}

	// function that updates awards with player who has the most assists
	private void updateMostAssists() throws SQLException {
		// 1) statement to get player stats
		String sqlString = "SELECT PersonID FROM Player order by Assists Desc, Goals Desc";
		PreparedStatement selectStmt = conn.prepareStatement(sqlString);
		ResultSet topAssister = selectStmt.executeQuery();

		// 2) Read results
		topAssister.next();
		// Top Scorer ID:
		int topAssisterID = topAssister.getInt("PersonID");

		// 3) close select statements
		topAssister.close();
		selectStmt.close();	

		// 4) Update using prepared statements
		String updateString = "UPDATE award SET PersonID = ? WHERE AwardID = 2;";
		PreparedStatement updateStatement = conn.prepareStatement(updateString);
		updateStatement.setInt(1, topAssisterID);

		// execute and close statements
		updateStatement.executeUpdate();
		updateStatement.close();
	}
        
        // search player
        public Object[][] searchPlayer(String firstName, String lastName) throws SQLException {
	       String sql = "SELECT * FROM vwplayer WHERE FirstName = ? and LastName = ?";
               PreparedStatement search = conn.prepareStatement(sql);
               search.setString(1, firstName);
               search.setString(2, lastName);
	       ResultSet rs = search.executeQuery();
	      
               
                              
               int colCount = 11;
               Object[][] data2 = new Object[1][colCount];
               if (rs.next()) {
                  
	       
	       data2[0][0] = rs.getInt("PlayerID");
               data2[0][1] = rs.getString("FirstName");
               data2[0][2] = rs.getString("LastName");
               data2[0][3] = rs.getString("Team");
               data2[0][4] = rs.getString("FieldPosition");
               data2[0][5] = rs.getString("Role");
               data2[0][6] = rs.getString("Status");
               data2[0][7] = rs.getInt("Goals");
               data2[0][8] = rs.getInt("Assists");
               data2[0][9] = rs.getInt("RedCards");
               data2[0][10] = rs.getInt("YellowCards");
               
               }
//	       
               
                search.close();
	       rs.close();
	       
               
               
               
	       return data2;
	}
	
	public Object[] playerTitle() throws SQLException {
		
	       String[] columnNames = {"PlayerID", "First Name", "Lasst Name", "Team", "FieldPosition", "Role", "Status", "Goals", "Assists", "RedCards", "YellowCards"};
	       return columnNames;
	}
        
        
}