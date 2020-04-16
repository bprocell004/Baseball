//Brent Procell
//CIS 3300 01I
//Final Project


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseballDatabase {
	
	public static void main(String[] args) throws Exception {
	final String DB_URL = "jdbc:derby:BaseballDatabase;create=true";
		
	try {
		Connection conn = DriverManager.getConnection(DB_URL);
		// Get a Statement object.
		Statement stmt  =  conn.createStatement();
			try {
			// Drop the team table.
			stmt.execute("DROP TABLE Hitter");
			System.out.println("Player table dropped.");    
			} catch(SQLException ex) {
			// No need to report an error. The table simply did not exist.
			}
			
			
			try {
				// Drop the pitcher table.
				stmt.execute("DROP TABLE Pitcher");
				System.out.println("Pitcher table dropped.");
			} catch(SQLException ex) {
				// No need to report an error. The table simply did not exist.
			}
		

			try {
				// Drop the hitter table.
				stmt.execute("DROP TABLE Player");
				System.out.println("Hitter table dropped.");
			} catch(SQLException ex) {
				// No need to report an error. The table simply did not exist.
			}

			try {
				// Drop the team table.
				stmt.execute("DROP TABLE Team");
				System.out.println("Team table dropped.");    
			} catch(SQLException ex) {
				// No need to report an error. The table simply did not exist.
			}
		} catch(SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
			ex.printStackTrace();
		}

		
		try {
			Connection conn = DriverManager.getConnection(DB_URL);
     
			buildTeamTable(conn);  // Build the team table.
			buildPlayerTable(conn);
			buildHitterTable(conn);   //build the hitter table
			buildPitcherTable(conn);   // Build the pitcher table
			

			conn.close();	// Close the connection.
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
	}
	
	public static void buildTeamTable(Connection conn)
	{
		try {
			// Get a Statement object.
			Statement stmt = conn.createStatement();
         
			// Create the table.
			stmt.execute("CREATE TABLE Team (" +
							"ID INTEGER NOT NULL PRIMARY KEY, " +
							"TeamName CHAR(20), " +
							"TeamMascot CHAR(20), " +
							"games INTEGER, " +
							"wins INTEGER, " +
							"loses INTEGER " +
						")");
			
			// Insert some data
			stmt.execute("INSERT INTO Team VALUES(01, 'San Fransico Giants', 'Giant', 11, 7, 4)");
			stmt.execute("INSERT INTO Team VALUES(02, 'Houston Astros', 'Orbit', 11, 8, 3)");
			        
			System.out.println("Team table created.");
		} catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
	}
	
	//create player table
	public static void buildPlayerTable(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			
			stmt.execute("CREATE TABLE Player (" +
							"ID INTEGER NOT NULL PRIMARY KEY, " +
							"fName CHAR(20), " +
							"lName CHAR(20), " +
							"number INTEGER, " +
							"generalPosition CHAR(10), " +
							"position CHAR(20), " +
							"Team INTEGER REFERENCES Team(ID)" +
							")");
			
			stmt.execute("INSERT INTO PLAYER VALUES(01, 'Brent', 'Rodgers', 14, 'Hitter', 'First Baseman', 01)");
			stmt.execute("INSERT INTO PLAYER VALUES(02, 'Steven', 'Johnson', 08, 'Hitter', 'Second Baseman', 01)");
			stmt.execute("INSERT INTO PLAYER VALUES(03, 'Jack', 'Buar', 09, 'Hitter', 'Catcher', 02)");
			stmt.execute("INSERT INTO PLAYER VALUES(04, 'Savanna', 'living', 24, 'Pitcher', 'Starting Pitcher', 02)");
			stmt.execute("INSERT INTO PLAYER VALUES(05, 'procell', 'Jules', 24, 'Pitcher', 'Starting Pitcher', 01)");
			
			System.out.println("Player table created.");
		} catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
	}
	
	
	
	public static void buildHitterTable(Connection conn)
	{
		try {
			// Get a Statement object.
			Statement stmt = conn.createStatement();
         
			// Create the table.
			stmt.execute("CREATE TABLE Hitter (" +
							"ID INTEGER NOT NULL, " +
							"atBats INTEGER, " +
							"hits INTEGER, " +
							"homeruns INTEGER, " +
							"RBI INTEGER, " +
							"runs INTEGER, " +
							"stolenBase INTEGER, " +
							"PRIMARY KEY (ID), " +
							"FOREIGN KEY (ID) REFERENCES PLAYER(ID)" +
						")");
			
			// Insert some data
			stmt.execute("INSERT INTO Hitter VALUES(01, 150, 50, 12, 40, 30, 4)");
			stmt.execute("INSERT INTO Hitter VALUES(02, 134, 60, 15, 43, 10, 5)");
			stmt.execute("INSERT INTO Hitter VALUES(03, 132, 30, 15, 30, 20, 15)");
			
			System.out.println("Hitter table created.");
		} catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
	}
	
	public static void buildPitcherTable(Connection conn) {
		try {
			// Get a Statement object.
			Statement stmt = conn.createStatement();
         
			// Create the table.
			stmt.execute("CREATE TABLE Pitcher (" +
							"ID INTEGER NOT NULL, " +
							"inningsPitched INTEGER, " +
							"strikeouts INTEGER, " +
							"walks INTEGER, " +
							"earnedRuns INTEGER, " +
							"PRIMARY KEY (ID), " +
							"FOREIGN KEY (ID) REFERENCES PLAYER(ID)" +
						")");
			
			// Insert some data
			stmt.execute("INSERT INTO Pitcher VALUES(04, 60, 54, 20, 15)");
			stmt.execute("INSERT INTO Pitcher VALUES(05, 34, 23, 20, 10)");
			
			System.out.println("Pitcher table created.");
		} catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
	}
	
	}
	
	

