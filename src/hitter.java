//Brent Procell
//CIS 3300 01I
//Final Project

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class hitter extends player {
			//declare variables
			private int atBats;
			private int hits;
			private int homeruns;
			private int rbi;
			private int runs;
			private int stolenBases;
		
	// Constant for database URL.
			public final String DB_URL ="jdbc:derby:BaseballDatabase";
			// Field for the database connection
			private Connection conn;
			
			//default constructor
			public hitter() {
			}
			//constructor to add a new hitter to the database
			public hitter(int playerID, int atBats, int hits, int homeruns, int rbi, int runs, int stolenBases) throws SQLException {
				this.playerID = playerID;
				this.atBats = atBats;
				this.hits = hits;
				this.homeruns = homeruns;
				this.rbi = rbi;
				this.runs = runs;
				this.stolenBases = stolenBases;
				
				//database connection
				conn = DriverManager.getConnection(DB_URL);
				 Statement stmt = conn.createStatement();
				 
				 
				stmt.execute("INSERT INTO Hitter VALUES (" +
				          this.playerID + ", " +					          
				          this.atBats + ", " +
				          this.hits + ", " +
				          this.homeruns + ", " +
				          this.rbi + ", " +
				          this.runs + ", " +
				          this.stolenBases + ")");
				      
			
				 //close connection
				 conn.close();
			     stmt.close();
			
			}
	
	//method to return hitter names based off their team.
	public String[] getHitterNames(String teamsname) throws SQLException
	{
		// Create a connection to the database.
		   conn = DriverManager.getConnection(DB_URL);
		// Create a Statement object for the query.
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		// Execute the query.
		ResultSet resultSet = stmt.executeQuery("SELECT fName, lName FROM Player INNER JOIN Team ON Player.Team = TEAM.ID WHERE teamName = '" + teamsname + "' AND generalPosition = 'Hitter'");
	     
		// Get the number of rows
		resultSet.last();           		// Move to the last row
		int numRows = resultSet.getRow(); 	// Get the current row number
		resultSet.first();                	// Move back to the first row
	
		// Create an array for the team names.
		String[] names = new String[numRows];
		
	  
		// Populate the array with team names.
		for (int index = 0; index < numRows; index++)
		{
			// Store the team name in the array.
			names[index] = resultSet.getString(1).trim() + " " + resultSet.getString(2).trim();
		
			// Go to the next row in the result set.
			resultSet.next();
		}
	  
		// Close the statement object.
		stmt.close();
	  
		return names;
	}
	
	//method to set number of at bats in the database
	public void setAtBats(String hitterName, int atBats)
	           throws SQLException
	   		{
		  // Create a connection to the database.
		   conn = DriverManager.getConnection(DB_URL);

		// Create a Statement object for the query.
		   Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

			String name[] = hitterName.split(" ", 2);
			
			ResultSet result = stmt.executeQuery("SELECT ID FROM Player WHERE fName = '" + name[0] + "'");
			
			int ID = 0;
		
			while (result.next()) {
				ID = result.getInt(1);
			}
		
			// Execute the update.
			stmt.executeUpdate("UPDATE Hitter " +
		        "SET atBats = " +
		         atBats +
		         " WHERE ID = " + ID + "");

		// Close the Connection and Statement objects.
		conn.close();
		stmt.close();
		}
	
	//method to set number of hits for a player in the database
	public void setHits(String hitterName, int hits)
	           throws SQLException
	   		{
		  // Create a connection to the database.
		   conn = DriverManager.getConnection(DB_URL);

		// Create a Statement object for the query.
		   Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

			String name[] = hitterName.split(" ", 2);
			
			ResultSet result = stmt.executeQuery("SELECT ID FROM Player WHERE fName = '" + name[0] + "'");
			
			int ID = 0;
		
			while (result.next()) {
				ID = result.getInt(1);
			}
		
			// Execute the update.
			stmt.executeUpdate("UPDATE Hitter " +
		        "SET hits = " +
		         hits +
		         " WHERE ID = " + ID + "");

		// Close the Connection and Statement objects.
		conn.close();
		stmt.close();
		}
	
	//method to set how many homeruns a player has hit in the database
	public void setHomeRuns(String hitterName, int hrs)
	           throws SQLException
	   		{
		  // Create a connection to the database.
		   conn = DriverManager.getConnection(DB_URL);

		// Create a Statement object for the query.
		   Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

			String name[] = hitterName.split(" ", 2);
			
			ResultSet result = stmt.executeQuery("SELECT ID FROM Player WHERE fName = '" + name[0] + "'");
			
			int ID = 0;
		
			while (result.next()) {
				ID = result.getInt(1);
			}
		
			// Execute the update.
			stmt.executeUpdate("UPDATE Hitter " +
		        "SET homeruns = " +
		         hrs +
		         " WHERE ID = " + ID + "");

		// Close the Connection and Statement objects.
		conn.close();
		stmt.close();
		}
	
	//method to set RBIs for the player in the database
	public void setRBI(String hitterName, int rbi)
	           throws SQLException
	   		{
		  // Create a connection to the database.
		   conn = DriverManager.getConnection(DB_URL);

		// Create a Statement object for the query.
		   Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

			String name[] = hitterName.split(" ", 2);
			
			ResultSet result = stmt.executeQuery("SELECT ID FROM Player WHERE fName = '" + name[0] + "'");
			
			int ID = 0;
		
			while (result.next()) {
				ID = result.getInt(1);
			}
		
			// Execute the update.
			stmt.executeUpdate("UPDATE Hitter " +
		        "SET RBI = " +
		         rbi +
		         " WHERE ID = " + ID + "");

		// Close the Connection and Statement objects.
		conn.close();
		stmt.close();
		}
	
	//method to set the amount of runs for a player in the database
	public void setRuns(String hitterName, int runs)
	           throws SQLException
	   		{
		  // Create a connection to the database.
		   conn = DriverManager.getConnection(DB_URL);

		// Create a Statement object for the query.
		   Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

			String name[] = hitterName.split(" ", 2);
			
			ResultSet result = stmt.executeQuery("SELECT ID FROM Player WHERE fName = '" + name[0] + "'");
			
			int ID = 0;
		
			while (result.next()) {
				ID = result.getInt(1);
			}
		
			// Execute the update.
			stmt.executeUpdate("UPDATE Hitter " +
		        "SET runs = " +
		         runs +
		         " WHERE ID = " + ID + "");

		// Close the Connection and Statement objects.
		conn.close();
		stmt.close();
		}
	
	//method to set the amount of stolen bases for a player in the database
	public void setStolenBase(String hitterName, int sb)
	           throws SQLException
	   		{
		
		 // Create a connection to the database.
		   conn = DriverManager.getConnection(DB_URL);
		   
		  // Create a connection to the database.
		   Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

			String name[] = hitterName.split(" ", 2);
			
			ResultSet result = stmt.executeQuery("SELECT ID FROM Player WHERE fName = '" + name[0] + "'");
			
			int ID = 0;
		
			while (result.next()) {
				ID = result.getInt(1);
			}
		
			// Execute the update.
			stmt.executeUpdate("UPDATE Hitter " +
		        "SET stolenBase = " +
		         sb +
		         " WHERE ID = " + ID + "");

		// Close the Connection and Statement objects.
		conn.close();
		stmt.close();
		}
	
	//method to return result set of the query for a table in the database of hitters
	public ResultSet getHitterFull(String teamsname) throws SQLException
	{
		// Create a connection to the database.
		   conn = DriverManager.getConnection(DB_URL);
		// Create a Statement object for the query.
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		// Execute the query.
		ResultSet result = stmt.executeQuery("SELECT ID FROM Team WHERE teamName = '" + teamsname + "'");
		
		int teamID = 0;

		while (result.next()) {
			teamID = result.getInt(1);
		}
		
		System.out.println(teamID);
		
		ResultSet resultSet = stmt.executeQuery("SELECT fName, lName, number, position, hits, homeruns, RBI, runs, stolenBase FROM Hitter RIGHT JOIN Player ON Hitter.ID = Player.ID WHERE Team = " + teamID + " AND generalPosition = 'Hitter'");
		
		return resultSet;
	
	}
	
	//method to return the hitters based on the order of who has the most hits
	public ResultSet getTopHitters() throws SQLException
	{
		// Create a connection to the database.
		   conn = DriverManager.getConnection(DB_URL);
		// Create a Statement object for the query.
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		// Execute the query.
		ResultSet resultSet = stmt.executeQuery("SELECT fName, lName, TeamName, position, hits FROM Hitter INNER JOIN Player ON Hitter.ID = Player.ID INNER JOIN Team ON Player.Team = Team.ID ORDER BY hits DESC");
		
		return resultSet;
	
	}
	
	//delete hitter from database
	public void deleteHitter(String name)throws SQLException
	   {
	      // Create a connection to the database.
	      conn = DriverManager.getConnection(DB_URL);

	      // Create a Statement object for the query.
	      Statement stmt = conn.createStatement();

	      String name2[] = name.split(" ", 2);
			
			ResultSet result = stmt.executeQuery("SELECT ID FROM Player WHERE fName = '" + name2[0] + "' AND lName = '" + name2[1] + "'");
			
			int ID = 0;

			while (result.next()) {
				ID = result.getInt(1);
			}

	      // Execute the query.
	      stmt.executeUpdate("DELETE FROM Hitter WHERE ID = " + ID + "");
	      stmt.executeUpdate("DELETE FROM Player WHERE ID = " + ID + "");

	      // Close the connection and statement objects.
	      conn.close();
	      stmt.close();
	   }  
}
