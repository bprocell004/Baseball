//Brent Procell
//CIS 3300 01I
//Final Project

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//class pitcher extends abstract class player
public class pitcher extends player {
	//variable declarations
	private int inningsPitched;
	private int strikeouts;
	private int walks;
	private int earnedRuns;
	// Constant for database URL.
		public final String DB_URL ="jdbc:derby:BaseballDatabase";
		// Field for the database connection
		private Connection conn;
	
		//default constructor
	public pitcher() {
	}
	//constructor to set up new pitcher to the database
	public pitcher(int playerID, int inningsPitched, int strikeouts, int walks, int earnedRuns) throws SQLException {
		this.playerID = playerID;
		this.inningsPitched = inningsPitched;
		this.strikeouts = strikeouts;
		this.walks = walks;
		this.earnedRuns = earnedRuns;
		
		//create database connection
		 conn = DriverManager.getConnection(DB_URL);
		 Statement stmt = conn.createStatement();
		 
		 stmt.execute("INSERT INTO Pitcher VALUES (" +
				  this.playerID + ", " +
	              this.inningsPitched + ", " +
	              this.strikeouts + ", " +
	              this.walks + ", " +
	              this.earnedRuns + ")");
		 
		 //close connections
		 conn.close();
	      stmt.close();
	}
	
	
	//sets innnings pitched in the database
	public void setInningsPitched(String pitcherName, int ip)
	           throws SQLException
	   		{
		  // Create a connection to the database.
		   conn = DriverManager.getConnection(DB_URL);

		// Create a Statement object for the query.
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		String name[] = pitcherName.split(" ", 2);
		
		ResultSet result = stmt.executeQuery("SELECT ID FROM Player WHERE fName = '" + name[0] + "'");
		
		int ID = 0;
		
		while (result.next()) {
			ID = result.getInt(1);
		}
		
		System.out.println(ID);
		
		// Execute the update.
		stmt.executeUpdate("UPDATE Pitcher " +
	        "SET inningsPitched = " +
	         ip +
	         " WHERE ID = " + ID + "");

		// Close the Connection and Statement objects.
		conn.close();
		stmt.close();
		}
	
	//method to set strikeouts in the database
	public void setStrikeouts(String pitcherName, int so)
	           throws SQLException
	   		{
		  // Create a connection to the database.
		   conn = DriverManager.getConnection(DB_URL);

		// Create a Statement object for the query.
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

		String name[] = pitcherName.split(" ", 2);
		
		ResultSet result = stmt.executeQuery("SELECT ID FROM Player WHERE fName = '" + name[0] + "'");
		
		int ID = 0;
	
		while (result.next()) {
			ID = result.getInt(1);
		}
	
		// Execute the update.
		stmt.executeUpdate("UPDATE Pitcher " +
	        "SET inningsPitched = " +
	         so +
	         " WHERE ID = " + ID + "");
		
		// Close the Connection and Statement objects.
		conn.close();
		stmt.close();
		}
	
	//method to set earned runs in the database
	public void setEarnedRuns(String pitcherName, int ip)
	           throws SQLException
	   		{
		  // Create a connection to the database.
		   conn = DriverManager.getConnection(DB_URL);

		// Create a Statement object for the query.
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

		String name[] = pitcherName.split(" ", 2);
		
		ResultSet result = stmt.executeQuery("SELECT ID FROM Player WHERE fName = '" + name[0] + "'");
		
		int ID = 0;
	
		while (result.next()) {
			ID = result.getInt(1);
		}
	
		// Execute the update.
		stmt.executeUpdate("UPDATE Pitcher " +
	        "SET inningsPitched=" +
	         ip +
	         " WHERE ID = " + ID + "");

		// Close the Connection and Statement objects.
		conn.close();
		stmt.close();
		}
	
	//method to set walks in the database
	public void setWalks(String pitcherName, int walks)
	           throws SQLException
	   		{
		  // Create a connection to the database.
		   conn = DriverManager.getConnection(DB_URL);

		// Create a Statement object for the query.
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

		String name[] = pitcherName.split(" ", 2);
		
		ResultSet result = stmt.executeQuery("SELECT ID FROM Player WHERE fName = '" + name[0] + "'");
		
		int ID = 0;

		while (result.next()) {
			ID = result.getInt(1);
		}
	
		// Execute the update.
		stmt.executeUpdate("UPDATE Pitcher " +
	        "SET inningsPitched = " +
	         walks +
	         " WHERE ID = " + ID + "");

		// Close the Connection and Statement objects.
		conn.close();
		stmt.close();
		}
	
	//method to get pitchers name based off of the team that they are on.
	public String[] getPitcherNames(String teamsname) throws SQLException
	{
		// Create a connection to the database.
		   conn = DriverManager.getConnection(DB_URL);
		// Create a Statement object for the query.
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		// Execute the query.
		ResultSet resultSet = stmt.executeQuery("SELECT fName, lName FROM Player INNER JOIN Team ON Player.Team = TEAM.ID WHERE teamName = '" + teamsname + "' AND generalPosition = 'Pitcher'");
	     
		// Get the number of rows
		resultSet.last();           		// Move to the last row
		int numRows = resultSet.getRow(); 	// Get the current row number
		resultSet.first();                	// Move back to the first row
	
		// Create an array for the pitcher names.
		String[] names = new String[numRows];
		
	  
		// Populate the array with pitcher names.
		for (int index = 0; index < numRows; index++){
	
			// Store the pitchers name in the array.
			names[index] = resultSet.getString(1).trim() + " " + resultSet.getString(2).trim();
		
			//System.out.println(names[index]);
			// Go to the next row in the result set.
			resultSet.next();
		}
	  
		// Close the statement object.
		stmt.close();
	  
		return names;
	
	}
	
	//return the result set of the player's information based on what team thar are on.
	public ResultSet getPlayerFull(String teamsname) throws SQLException
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
		
		ResultSet resultSet = stmt.executeQuery("SELECT fName, lName, number, position, inningsPitched, strikeouts, walks, earnedRuns FROM Pitcher RIGHT JOIN Player ON Pitcher.ID = Player.ID WHERE Team = " + teamID + " AND generalPosition = 'Pitcher'");
		
		//ResultSet resultSet = stmt.executeQuery("SELECT fName, LName, position, number FROM Player WHERE Team = " + ID + " AND generalPosition = 'Pitcher'");
		return resultSet;

	}
	
	//method to return pitchers and display them in order by their strikeouts.
	public ResultSet getTopStrikeout() throws SQLException
	{
		// Create a connection to the database.
		   conn = DriverManager.getConnection(DB_URL);
		// Create a Statement object for the query.
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		// Execute the query.
		ResultSet resultSet = stmt.executeQuery("SELECT fName, lName, TeamName, position, strikeouts FROM Pitcher INNER JOIN Player ON Pitcher.ID = Player.ID INNER JOIN Team ON Player.Team = Team.ID ORDER BY strikeouts DESC");
		
		return resultSet;
	}
	
	//method to delete pitcher
	public void deletePitcher(String name)throws SQLException
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
	      stmt.executeUpdate("DELETE FROM Pitcher WHERE ID = " + ID + "");
	      stmt.executeUpdate("DELETE FROM Player WHERE ID = " + ID + "");
	      // Close the connection and statement objects.
	      conn.close();
	      stmt.close();
	   }  
	
}
