//Brent Procell
//CIS 3300 01I
//Final Project

import java.sql.*;

public class team {
	//declairing variables
	private int teamID;
	private String teamName;
	private String mascot;
	private int games;
	private int wins;
	private int loses;
	// Constant for database URL.
	public final String DB_URL ="jdbc:derby:BaseballDatabase";
	// Field for the database connection
	private Connection conn;

	//team constructor with arguments passed in to add a new team to the database
	public team(int teamID, String teamName, String mascot, int games, int wins, int loses) throws SQLException {
		this.teamID = teamID;
		this.teamName = teamName;
		this.mascot = mascot;
		this.games = games;
		this.wins = wins;
		this.loses = loses;
		conn = DriverManager.getConnection(DB_URL);
		Statement stmt = conn.createStatement();
		 
		 stmt.executeUpdate("INSERT INTO Team VALUES (" +
	              this.teamID + ", '" +
	              this.teamName + "', '" + 
	              this.mascot + "', " +
	              this.games + ", " +
	              this.wins + ", " +
	              this.loses +
	               ")");
		 
		 conn.close();
	      stmt.close();
	}
	
	public team() throws SQLException {
		conn = DriverManager.getConnection(DB_URL);
	}
	
	//method to return all team names from the database.
	public String[] getTeamNames() throws SQLException
	{
		// Create a connection to the database.
		   conn = DriverManager.getConnection(DB_URL);
		// Create a Statement object for the query.
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		// Execute the query.
		ResultSet resultSet = stmt.executeQuery("SELECT teamName FROM Team");
	     
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
			names[index] = resultSet.getString(1);
			System.out.println(names[index]);
			// Go to the next row in the result set.
			resultSet.next();
		}
	  
		// Close the statement object.
		stmt.close();
	  
		return names;
	}
	
	//method to set amount of games played.
	public void setGamesPlayed(String teamName, int games)
	           throws SQLException
	   		{
		  // Create a connection to the database.
		   conn = DriverManager.getConnection(DB_URL);

		// Create a Statement object for the query.
		Statement stmt = conn.createStatement();

		// Execute the update.
		stmt.executeUpdate("UPDATE Team " +
	        "SET games = " +
	         games +
	         " WHERE teamName = '" +
	         teamName + "'");

		// Close the Connection and Statement objects.
		conn.close();
		stmt.close();
		}
	
	//method to set the amount of wins
	public void setWins(String teamName, int wins)
	           throws SQLException
	   		{
		  // Create a connection to the database.
		   conn = DriverManager.getConnection(DB_URL);

		// Create a Statement object for the query.
		Statement stmt = conn.createStatement();

		// Execute the update.
		stmt.executeUpdate("UPDATE Team " +
	        "SET wins = " +
	         wins +
	         " WHERE teamName = '" +
	         teamName + "'");

		// Close the Connection and Statement objects.
		conn.close();
		stmt.close();
		}
	
	//method to set the amount of loses to the database
	public void setLoses(String teamName, int loses)
	           throws SQLException
	   		{
		  // Create a connection to the database.
		   conn = DriverManager.getConnection(DB_URL);

		// Create a Statement object for the query.
		Statement stmt = conn.createStatement();

		// Execute the update.
		stmt.executeUpdate("UPDATE Team " +
	        "SET loses = " +
	         loses +
	         " WHERE teamName = '" +
	         teamName + "'");

		// Close the Connection and Statement objects.
		conn.close();
		stmt.close();
		}
	
	//method to return the result set of teamnames that equal the arguement passed through
	public ResultSet getTeamInfo(String teamsname) throws SQLException
	{
		// Create a connection to the database.
		   conn = DriverManager.getConnection(DB_URL);
		// Create a Statement object for the query.
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		// Execute the query.
		ResultSet resultSet = stmt.executeQuery("SELECT * FROM Team WHERE teamName = '" + teamsname + "'");
		
		return resultSet;
	
	}
	
	//method to retrieve the teams name, wins and loses from the database
	public ResultSet getStandings() throws SQLException
	{
		// Create a connection to the database.
		   conn = DriverManager.getConnection(DB_URL);
		// Create a Statement object for the query.
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		// Execute the query.
		ResultSet resultSet = stmt.executeQuery("SELECT teamName, wins, loses FROM Team ORDER BY wins DESC");
		
		return resultSet;
	}
}	

