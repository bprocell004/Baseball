import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Brent Procell
//CIS 3300 01I
//Final Project

//abstract class to be used for hitter or pitcher classes
public class player {
	//variable declarations
	protected int playerID;
	protected String fName;
	protected String lName;
	protected int number;
	protected String generalPosition;
	protected String position;
	protected int teamID;
	
	// Constant for database URL.
	public final String DB_URL ="jdbc:derby:BaseballDatabase";
	// Field for the database connection
	private Connection conn;
	
	//constructor
	public player () {
	}
	
	public player(int playerID, String fName, String lName, int number, String generalPosition, String position, int teamID) throws SQLException {
		this.playerID = playerID;
		this.fName = fName;
		this.lName = lName;
		this.number = number;
		this.generalPosition = generalPosition;
		this.position = position;
		this.teamID = teamID;
		
		//create database connection
		 conn = DriverManager.getConnection(DB_URL);
		 Statement stmt = conn.createStatement();
		 
		 stmt.execute("INSERT INTO Player VALUES (" +
	              this.playerID + ", '" +
	              this.fName + "', '" +
	              this.lName + "', " +
	              this.number + ", '" +
	              this.generalPosition + "', '" +
	              this.position + "', " +
	              this.teamID + ")");
		 
		 //close connections
		 conn.close();
	      stmt.close();
	}
	
	public String[] getPlayerNames(String teamsname) throws SQLException
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
	

}
