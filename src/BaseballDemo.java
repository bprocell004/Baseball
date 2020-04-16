//Brent Procell
//CIS 3300 01I
//Final Project

import java.sql.*;         
import java.util.Vector;

import javax.swing.*;     
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;         
import java.awt.event.*;   

public class BaseballDemo extends JFrame {
		team bballTeam;		//object for team class
		JList teamList;   	//list of team names
		JList pitcherList;   //list of pitcher names
		JList hitterList;    //list of hitter names
		pitcher leaguePitcher;    //object for pitcher class
		hitter leagueHitter; //object for hitter class
		player newPlayer;
	   JScrollPane scrollPane;     //scroll panel
	   JPanel listPanel;           //panel for team
	   JPanel listPanel2;          //panel for pitchers
	   JPanel listPanel3;          //panel for hitters
	   JPanel buttonPanel;        // A panel to hold the buttons
	   JButton newTeamButton;         // new team button
	   JButton viewTeamButton;        // view team button
	   JButton editTeamButton;        //edit team button
	   JButton newPitcherButton;         // new pitcher button
	   JButton deletePitcherButton;      // delete pitcher button
	   JButton viewPitcherButton;        // view pitcher button
	   JButton editPitcherButton;	    // edit pitcher button
	   JButton newHitterButton;         // new hitter button
	   JButton deleteHitterButton;      // delete hitter button
	   JButton viewHitterButton;        // view hitter button
	   JButton editHitterButton;	// edit hitter button
	   JButton exitButton;           //exit button
	   JButton viewStandingsButton;      //view team standing button
	   JButton hittingLeadersButton;       //view hitting leaders button
	   JButton strikeoutLeaderButton;       //view strikeout leaders button
	   JTable pitcherTable;                 //pitcher table
	   JTable teamTable;                    //team table
	   JTable hitterTable;                  //hitter table
	   JTable standingsTable;               //team standings table
	   JTable hittingTable;                 //top hitters table
	   JTable strikeoutTable;                //top strikeouts table
	   GridBagConstraints layoutConst;        //layout of GUI
	
	   public BaseballDemo() throws SQLException
	   {
		  
	      // Set the window title.
	     setTitle("Baseball Database");
	     
	      // Specify an action for the close button.
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	      // Build the list panel.
	      buildListPanel();
	      buildListPanelPitcher();
	      buildListPanelHitter();
	      buildButtonPanel();
	     
	      setLayout(new GridBagLayout());
	      
	      //design the layouts for each listpanel and button panel
		   layoutConst = new GridBagConstraints();
		   layoutConst.gridx = 1;
		   layoutConst.gridy = 0;
		   layoutConst.insets = new Insets(5, 5, 5, 5);
		   add(listPanel, layoutConst);
		   
		   layoutConst = new GridBagConstraints();
		   layoutConst.gridx = 2;
		   layoutConst.gridy = 0;
		   layoutConst.insets = new Insets(5, 5, 5, 5);
		   add(listPanel2, layoutConst);
		   
		   layoutConst = new GridBagConstraints();
		   layoutConst.gridx = 3;
		   layoutConst.gridy = 0;
		   layoutConst.insets = new Insets(5, 5, 5, 5);
		   add(listPanel3, layoutConst);
		   
		   //layoutConst = new GridBagConstraints();
		   layoutConst.gridx = 2;
		   layoutConst.gridy = 2;
		   layoutConst.insets = new Insets(5, 5, 5, 5);
		   add(buttonPanel, layoutConst);
		  
		  //set the sizes of the panels 
		   listPanel.setPreferredSize(new Dimension(300, 225));
		   listPanel2.setPreferredSize(new Dimension(300, 225));
		   listPanel3.setPreferredSize(new Dimension(300, 225));
		   buttonPanel.setPreferredSize(new Dimension(400, 90));
		   
		   //set the size of the jlist
		   hitterList.setFixedCellWidth(250);
	       hitterList.setFixedCellHeight(20);
	       pitcherList.setFixedCellWidth(250);
	       pitcherList.setFixedCellHeight(20);
	       teamList.setFixedCellWidth(250);
	       teamList.setFixedCellHeight(20);
	      
	      //method to show pitchers and hitters for a team when the use selects a team name
	      teamList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String selectedName = (String) teamList.getSelectedValue();
				if (selectedName != null) {
					leaguePitcher = new pitcher();
					leagueHitter = new hitter();
					try {
					pitcherList.setListData(leaguePitcher.getPitcherNames(selectedName));
					hitterList.setListData(leagueHitter.getHitterNames(selectedName));
					} catch (SQLException e1) {
					e1.printStackTrace();
			}	
			}	
			}
	      });
	      
	     // Pack and display.
	     pack();
	     setVisible(true);   
	   }
	   
	   //build the team list planel
	   private void buildListPanel()
	   {
	      try
	      {
	         // Create a panel.
	        listPanel = new JPanel();
	      
	         // Add a titled border to the panel.
	         listPanel.setBorder(BorderFactory.
	         createTitledBorder("Select a Team"));
	        
	         // Create an PhoneBookManager object.
	         bballTeam = new team();
	      
	         teamList = new JList(bballTeam.getTeamNames());
	         
	         // Set the number of visible rows.
	         teamList.setVisibleRowCount(6);
	      	         
	         // Put the JList object in a scroll pane.	     
	         scrollPane = new JScrollPane(teamList);
	         
	         // Add the scroll pane to the panel.
	         listPanel.add(scrollPane);
	         
	      	} catch(SQLException ex)
		      {
		         
		         //display message if something is wrong.
		         JOptionPane.showMessageDialog(null, ex.toString());
		      }
	      }
	      
	      //build the pitcher list panel
		   private void buildListPanelPitcher()
		   {		     
		         // Create a panel.		         
		         listPanel2 = new JPanel();
		        		         
		         // Add a titled border to the panel.		         
		         listPanel2.setBorder(BorderFactory.
		         createTitledBorder("Team Pitchers")); 
		         
		         // Create a JList object to hold the names.
		         pitcherList = new JList();
		        		         
		         // Set the number of visible rows.		      
		         pitcherList.setVisibleRowCount(6);
		        	         
		         // Put the JList object in a scroll pane.
		         scrollPane = new JScrollPane(pitcherList);
		       
		         // Add the scroll pane to the panel.
		         listPanel2.add(scrollPane);		         
		      }
		      
		   //build list for the hitters
		      private void buildListPanelHitter()
			   {
			      listPanel3 = new JPanel();
								 
				 // Add a titled border to the panel.				 
				 listPanel3.setBorder(BorderFactory.
				 createTitledBorder("Team Hitters")); 
  				 
				 // Create a JList object to hold the names.
				 hitterList = new JList();
								 
				 // Set the number of visible rows.   
				 hitterList.setVisibleRowCount(6);
								 
				 // Put the JList object in a scroll pane.
				 scrollPane = new JScrollPane(hitterList);
      
				 // Add the scroll pane to the panel.
				 listPanel3.add(scrollPane);
	   }
	   
	//create the buttons for the GUI
	   private void buildButtonPanel()
	   {
	      // Create a panel.
	      buttonPanel = new JPanel();

	      // Add a titled border to the panel.
	      buttonPanel.setBorder(BorderFactory.
	      createTitledBorder("Select an Action"));
	      
	      //buttons for the button panel
	      exitButton = new JButton("Quit");
	      viewStandingsButton = new JButton("View Standings");
	      hittingLeadersButton = new JButton("Hit Leaders");
	      strikeoutLeaderButton = new JButton("Strikeout Leaders");
	         
	      //buttons for the team panel
	      newTeamButton = new JButton("New");
	      viewTeamButton = new JButton("View");
	      editTeamButton = new JButton("Edit wins/loses");
	      
	      //buttons for the pitcher panel
	      newPitcherButton = new JButton("New");    
		  deletePitcherButton = new JButton("Delete");     
		  viewPitcherButton = new JButton("View All");      
		  editPitcherButton = new JButton("Edit Stats");
		  
		  //buttons for the hitter panel
		   newHitterButton = new JButton("New");        
		   deleteHitterButton = new JButton("Delete");      
		   viewHitterButton = new JButton("View All");       
		   editHitterButton = new JButton("Edit Stats");
	      
	      // Register the action listeners for the team buttons
	      newTeamButton.addActionListener(new NewTeamButtonListener());
	      viewTeamButton.addActionListener(new ViewTeamButtonListener());
	      editTeamButton.addActionListener(new EditTeamButtonListener());
	      
	   // Register the action listeners for the pitcher buttons
	      viewPitcherButton.addActionListener(new ViewPlayerButtonListener());
	      newPitcherButton.addActionListener(new NewPitcherButtonListener());
	      deletePitcherButton.addActionListener(new DeletePitcherButtonListener());
	      editPitcherButton.addActionListener(new EditPitcherButtonListener());
	      
	   // Register the action listeners for the hitter buttons
	      viewHitterButton.addActionListener(new ViewHitterButtonListener());
	      newHitterButton.addActionListener(new NewHitterButtonListener());
	      deleteHitterButton.addActionListener(new DeleteHitterButtonListener());
	      editHitterButton.addActionListener(new EditHitterButtonListener());
	      
	   // Register the action listeners for the panel buttons
	      exitButton.addActionListener(new ExitButtonListener());
	      viewStandingsButton.addActionListener(new ViewStandingsButtonListener());
	      hittingLeadersButton.addActionListener(new ViewHittingLeadersButtonListener());
	      strikeoutLeaderButton.addActionListener(new ViewStrikeoutLeaderButtonListener());

	      // Add the buttons to the button panel
	      buttonPanel.add(viewStandingsButton);
	      buttonPanel.add(hittingLeadersButton);
	      buttonPanel.add(strikeoutLeaderButton);
	      buttonPanel.add(exitButton);
	      
	   // Add the buttons to the team panel
	      listPanel.add(newTeamButton);
	      listPanel.add(viewTeamButton);
	      listPanel.add(editTeamButton);
	      
	   // Add the buttons to the pitcher panel
	      listPanel2.add(newPitcherButton);
	      listPanel2.add(viewPitcherButton);
	      listPanel2.add(deletePitcherButton);
	      listPanel2.add(editPitcherButton);
	      
	   // Add the buttons to the hitter panel
	      listPanel3.add(newHitterButton);
	      listPanel3.add(viewHitterButton);
	      listPanel3.add(deleteHitterButton);
	      listPanel3.add(editHitterButton);
	   }
	   
	   //method to view team in a table format
	   private class ViewTeamButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  String selectedName = (String) teamList.getSelectedValue(); //string to hold selected name
	    	  
	    if(selectedName != null) 
	           {
	    	  try {
	    		  bballTeam = new team();
	    		  //call buildtable method to create the team table
	    		  teamTable = new JTable(buildTableModel(bballTeam.getTeamInfo(selectedName)));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	    	  //set the view and size of the table
	    	  teamTable.setPreferredScrollableViewportSize(teamTable.getPreferredSize());
	    	  teamTable.setFillsViewportHeight(true);
	    	 
	    	  //scroll pane for table
	    	  UIManager.put("OptionPane.minimumSize",new Dimension(1000,0)); 
	    	  JOptionPane.showMessageDialog(null, new JScrollPane(teamTable));
	      }
	    else {
	    	//display message if team is not selected
            JOptionPane.showMessageDialog(null, 
            "Select a team from the list.");
      }
	   }
	   }
	   
	   //method to view team standings when button is clicked
	   private class ViewStandingsButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {		
	    	  try {
	    		  bballTeam = new team();
	    		  //call buildtable method to creat table for team standings
	    		  standingsTable = new JTable(buildTableModel(bballTeam.getStandings()));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	    	  //set size and view of table
	    	  standingsTable.setPreferredScrollableViewportSize(standingsTable.getPreferredSize());
	    	  standingsTable.setFillsViewportHeight(true);
	    	 
	    	  //create scroll pnae
	    	  UIManager.put("OptionPane.minimumSize",new Dimension(1000,0)); 
	    	  JOptionPane.showMessageDialog(null, new JScrollPane(standingsTable));
	      }
	   }
	   
	   //method to display list of hitting leaders once button is clicked
	   private class ViewHittingLeadersButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  try {
	    		  leagueHitter = new hitter();
	    		  //call build table method to create table of best hitters in order
	    		  hitterTable = new JTable(buildTableModel(leagueHitter.getTopHitters()));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	    	  //view and size of hitter table
	    	  hitterTable.setPreferredScrollableViewportSize(hitterTable.getPreferredSize());
	    	  hitterTable.setFillsViewportHeight(true);
	    	 
	    	  //size and scroll for hitter table
	    	  UIManager.put("OptionPane.minimumSize",new Dimension(1000,0)); 
	    	  JOptionPane.showMessageDialog(null, new JScrollPane(hitterTable));
	      }
	   }
	   
	   //method to display a table of strikeout leaders in order.
	   private class ViewStrikeoutLeaderButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e) {  
	    	  try {
	    		  leaguePitcher = new pitcher();
	    		  //call build table method to make a table of strikeout leaders
	    		  strikeoutTable = new JTable(buildTableModel(leaguePitcher.getTopStrikeout()));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	    	  //tablet view and size
	    	  strikeoutTable.setPreferredScrollableViewportSize(strikeoutTable.getPreferredSize());
	    	  strikeoutTable.setFillsViewportHeight(true);
	    	 
	    	  //table size and scroll pan
	    	  UIManager.put("OptionPane.minimumSize",new Dimension(1000,0)); 
	    	  JOptionPane.showMessageDialog(null, new JScrollPane(strikeoutTable));
	      }
	   }
	   
	   //method to show pitchers in a table format based off their team
	   private class ViewPlayerButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  //get selected name
	    	  String selectedName = (String) teamList.getSelectedValue();
	    	  leaguePitcher = new pitcher();
	    	
	     if(selectedName != null) 
	            {
	    	  try {
	    		  //call build table method to make the pitcher table based off of team
				pitcherTable = new JTable(buildTableModel(leaguePitcher.getPlayerFull(selectedName)));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	    	  //pitcher table size and view
	    	  pitcherTable.setPreferredScrollableViewportSize(pitcherTable.getPreferredSize());
	    	  pitcherTable.setFillsViewportHeight(true);
	    	 
	    	  //pitcher size and scroll pane
	    	  UIManager.put("OptionPane.minimumSize",new Dimension(1000,0)); 
	    	  JOptionPane.showMessageDialog(null, new JScrollPane(pitcherTable));
	      }
	     else {
	    	 //show message if team is not selected
             JOptionPane.showMessageDialog(null, 
             "Select a team from the list.");
       }
	   }
	   }
	   //method to view hitter list in table format base off thier team
	   private class ViewHitterButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  //get selected team name
	    	  String selectedName = (String) teamList.getSelectedValue();
	    	  leagueHitter = new hitter();
			
	     if(selectedName != null) 
	            {
	    	  try {
	    		  //call build table method to make the hitter tabler
				hitterTable = new JTable(buildTableModel(leagueHitter.getHitterFull(selectedName)));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	    	  //table size and view
	    	  hitterTable.setPreferredScrollableViewportSize(hitterTable.getPreferredSize());
	    	  hitterTable.setFillsViewportHeight(true);
	    	 
	    	  //scroll size and scroll pane.
	    	  UIManager.put("OptionPane.minimumSize",new Dimension(1200,0)); 
	    	  JOptionPane.showMessageDialog(null, new JScrollPane(hitterTable));
	      }
	     else {
	    	 //show message if team is not selected.
             JOptionPane.showMessageDialog(null, 
             "Select a team from the list.");
       }
	   }
	   }
	   
	   //method to make a new team for the database
	   private class NewTeamButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	         try
	         {
	        	 //get a new ID
	        	String newID = 
	        			JOptionPane.showInputDialog(null,
	        			"Enter a team ID.");
	        	int result = Integer.parseInt(newID);
	        	
	        	//determine that ID has value
	        	if (newID != null)
	        	{
	            // Get a new name from the user.
	            String newName = 
	                     JOptionPane.showInputDialog(null, 
	                     "Enter a team name");      
	            
	            // Determine that the name has a value.
	            if(newName != null)
	            {
	               // Get a mascot from the user.
	               String newMascot = 
	                     JOptionPane.showInputDialog(null, 
	                     "Enter team mascot.");
	                     
	               // Determine that the mascot has a value.
	               if(newMascot != null)
	               {
	            	   //get new games
	            	   String newGames = 
	                           JOptionPane.showInputDialog(null, 
	                           "Enter amount of games played.");
	            	   int gamesPlayed = Integer.parseInt(newGames);
	            	   
	                     // Determine that the games has a value.
	                     if(newGames != null)
	                     {
	                    	 //get new wins
	                    	  String newWins = 
	                                  JOptionPane.showInputDialog(null, 
	                                  "Enter total wins");
	                    	  int wins = Integer.parseInt(newWins);
	                    	  
	                            // Determine that wins has a value.
	                            if(newWins != null)
	                            {
	                            	//get new loses
	                            	String newLoses =
	                            			JOptionPane.showInputDialog(null,
	                            					"Enter total loses");
	                            	int loses = Integer.parseInt(newLoses);
	                            	
	                            	//determine that loses has a value
	                            	if(newLoses != null)
	                            	{
	                            		//set new team
	                            		bballTeam = new team(result, newName, newMascot, gamesPlayed, wins, loses);

	                  //show the user it was complete
	                  JOptionPane.showMessageDialog(null, 
	                  "Added to Team");
	            
	                  //update team list
	                  bballTeam = new team();
	                  teamList.setListData(bballTeam.getTeamNames());                    
	               }           
	            }
	        	}
	            }
	            }
	        	}
	         }
	               
	         catch(SQLException ex)
	         {  
	        	 //display message if anything goes wrong
	            JOptionPane.showMessageDialog(null, ex.toString());
	         }           
	      }
	         }
	   
	   private class NewHitterButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	         //get a new ID
			String newID = 
					JOptionPane.showInputDialog(null,
					"Enter player ID.");
			int id = Integer.parseInt(newID);
			
			//determine that ID has value
			if (newID != null)
			{
			// Get a new name from the user.
			String fName = 
			         JOptionPane.showInputDialog(null, 
			         "Enter first name of the hitter");      
			
			// Determine that the name has a value.
				if(fName != null)
				{
				
			    	String lName = 
			             JOptionPane.showInputDialog(null, 
			             "Enter last name of the hitter");      
			    
			    // Determine that the name has a value.
					if(lName != null)
					{
			   // Get a new number from the user.
			   String newNumber = 
			         JOptionPane.showInputDialog(null, 
			         "Enter jersey number.");
			   int number = Integer.parseInt(newNumber);
			   
			   // Determine that the number has a value.
			   		if(newNumber != null)
			   		{
				   //get new position
				   String newPosition = 
			               JOptionPane.showInputDialog(null, 
			               "Enter position.");
				   
			         // Determine that the position has a vallue
			         	if(newPosition != null)
			         	{
			                //get team id
			         		String teamID =
			                JOptionPane.showInputDialog(null, "Enter team ID");
			                int team = Integer.parseInt(teamID);
			                
			                if(teamID != null) {
				         		String atBat =
						        JOptionPane.showInputDialog(null, "Enter hitter's at bats");
						        int atBats = Integer.parseInt(atBat);
						        
				                if(atBat != null) {
					         		String hit =
							        JOptionPane.showInputDialog(null, "Enter amount of hits");
							        int hits = Integer.parseInt(hit);
							        
					                if(hit != null) {
						         		String hrs =
								        JOptionPane.showInputDialog(null, "Enter amount of homeruns");
								        int hr = Integer.parseInt(hrs);
								        
						                if(hrs != null) {
							         		String rbis =
									        JOptionPane.showInputDialog(null, "Enter amount of RBIs");
									        int rbi = Integer.parseInt(rbis);
									        
							                if(rbis != null) {
								         		String run =
										        JOptionPane.showInputDialog(null, "Enter amount of runs");
										        int runs = Integer.parseInt(run);
										        
								                if(run != null) {
									         		String sbs =
											        JOptionPane.showInputDialog(null, "Enter amount of stolen bases");
											        int sb = Integer.parseInt(sbs);
			                
			                
			                String generalPosition = "Hitter";
			                   //determine if team id has a value
			                   if(sbs != null)
			                   {
			     
			                	   //add new pitcher
			                	   try {
									newPlayer = new player(id, fName, lName, number, generalPosition, newPosition, team);
									leagueHitter = new hitter(id, atBats, hits, hr, rbi, runs, sb);
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

			      // send message it was complete
			      JOptionPane.showMessageDialog(null, 
			      "New Pitcher added");               
			   }           
			}
			}
			}
			}	
       }          
	         }
	   }
			   		}
					}
				}
			}
	      }
	   }
	   
	   //method to add a new pitcher
	   private class NewPitcherButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	         //get a new ID
			String newID = 
					JOptionPane.showInputDialog(null,
					"Enter player ID.");
			int id = Integer.parseInt(newID);
			
			//determine that ID has value
			if (newID != null)
			{
			// Get a new name from the user.
			String fName = 
			         JOptionPane.showInputDialog(null, 
			         "Enter first name of the pitcher");      
			
			// Determine that the name has a value.
				if(fName != null)
				{
				
			    	String lName = 
			             JOptionPane.showInputDialog(null, 
			             "Enter last name of the pitcher");      
			    
			    // Determine that the name has a value.
					if(lName != null)
					{
			   // Get a new number from the user.
			   String newNumber = 
			         JOptionPane.showInputDialog(null, 
			         "Enter jersey number.");
			   int number = Integer.parseInt(newNumber);
			   
			   // Determine that the number has a value.
			   		if(newNumber != null)
			   		{
				   //get new position
				   String newPosition = 
			               JOptionPane.showInputDialog(null, 
			               "Enter position.");
				   
			         // Determine that the position has a vallue
			         	if(newPosition != null)
			         	{
			                //get team id
			         		String teamID =
			                JOptionPane.showInputDialog(null, "Enter team ID");
			                int team = Integer.parseInt(teamID);
			                
			                	if(teamID != null) {
			                		String ips =
			                		JOptionPane.showInputDialog(null, "Enter Innings Pitched");
					                int ip = Integer.parseInt(ips);
					                
					                if(ips != null) {
					                	String sos =
					                	JOptionPane.showInputDialog(null, "Enter amount of Strikeouts");
						                int so = Integer.parseInt(sos);
						                
						                if(sos != null) {
						                String walks =
						                JOptionPane.showInputDialog(null, "Enter amount of walks");
						                int walk = Integer.parseInt(walks);
						                
						                	if(walks != null) {
						                		String ers =
						                		JOptionPane.showInputDialog(null, "Enter amount of Earned Runs");
								                int er = Integer.parseInt(ers);
								             
			                String generalPosition = "Pitcher";
			                   //determine if team id has a value
			                   if(ers != null)
			                   {
			     
			                	   //add new pitcher
			                	   try {
									newPlayer = new player(id, fName, lName, number, generalPosition, newPosition, team);
									leaguePitcher = new pitcher(id, ip, so, walk, er);
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

			      // send message it was complete
			      JOptionPane.showMessageDialog(null, 
			      "New Pitcher added");               
			   }           
			}
			}
			}
			}	
       }          
	         }
	   }
	      }
	   }
	      }
	   }

	   
	   //method to delete the hitter based of selected name when button is clicked
	   private class DeleteHitterButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	         try
	         {
	            // Get the selected value from the name list.
	            String selectedName = (String) hitterList.getSelectedValue();
	            
	            // Determine that the name has a value.
	            if(selectedName != null)
	            {
	               // call delete method
	               leagueHitter = new hitter();
	               leagueHitter.deleteHitter(selectedName);
	               
	               //show that the hitter was removed
	               JOptionPane.showMessageDialog(null, 
	               "Hitter removed from database");
	            }
	            else
	               //notify user to select a name if not already
	               JOptionPane.showMessageDialog(null, 
	               "Select a name from the list.");
	         }
	         catch(SQLException ex)
	         {
	        	 //show message if something goes wrong
	            JOptionPane.showMessageDialog(null, ex.toString());
	         }
	      }
	   }
	   
	   //method to delete pitcher from database based off of name when button is clicked
	   private class DeletePitcherButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	         try
	         {
	            // Get the selected value from the name list.
	            String selectedName = (String) pitcherList.getSelectedValue();
	            
	            System.out.println(selectedName);
	            
	            // make a function to retrieve playerID from the selectedName
	            
	            // Determine that the name has a value.
	            if(selectedName != null)
	            {
	               //call delete pitcher method
	               leaguePitcher = new pitcher();
	               leaguePitcher.deletePitcher(selectedName);
	               
	               //display that the pitcher was removed
	               JOptionPane.showMessageDialog(null, 
	               "Pitcher removed from database");
	            }
	            else
	              //show message if name is not selected
	               JOptionPane.showMessageDialog(null, 
	               "Select a name from the list.");
	         }
	         catch(SQLException ex)
	         {
	            //message if something goes wrong
	            JOptionPane.showMessageDialog(null, ex.toString());
	         }
	      }
	   }
	   
	   //method to edit teams wins/loses when button is pressed
	   private class EditTeamButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	         try
	         {
	            // Get the selected value from the name list.
	            String selectedName = (String) teamList.getSelectedValue();
	            
	            bballTeam = new team();
	            
	            // Determine that the name has a value.
	            if(selectedName != null)
	            {
	               //get new amount of games played
	               String newGamesPlayed = JOptionPane.showInputDialog(null,
	                      selectedName + "\nEnter amount of games played.");
	               int games = Integer.parseInt(newGamesPlayed);
	               //get new wins for the team
	               String newWin =
	            		   JOptionPane.showInputDialog(null, 
	            		   selectedName + "\nEnter total wins.");
	               		int wins = Integer.parseInt(newWin);
	            // get new loses for the team
	               String newLose =
	            		   JOptionPane.showInputDialog(null, 
	            		   selectedName + "\nEnter total loses");
	               			int loses = Integer.parseInt(newLose);
	               // Determine that the games played has a value.
	               if(newGamesPlayed != null)
	               {
	                  //call method to complete
	                  bballTeam.setGamesPlayed(selectedName, games);
	               
	                  //show that it updated
	                  JOptionPane.showMessageDialog(null, 
	                  "Games played updated");
	               }
	               //determine if wins has value
	               if(newWin != null)
	               {
	                  //call method to complete
	                  bballTeam.setWins(selectedName, wins);
	               
	                  //show the user it was complete
	                  JOptionPane.showMessageDialog(null, 
	                  "Wins updated");
	               } 
	               //determine if loses has value
	               if(newLose != null)
	               {
	                  //call method to complete
	                  bballTeam.setLoses(selectedName, loses);
	               
	                  //show the user it was complete
	                  JOptionPane.showMessageDialog(null, 
	                  "loses updated");
	               }   
	            }
	            else
	               //show message if name is not selected
	               JOptionPane.showMessageDialog(null, 
	               "Select a name from the list.");
	         }
	         catch(SQLException ex)
	         {
	            //message if something goes wrong
	            JOptionPane.showMessageDialog(null, ex.toString());
	         }
	      }
	   }
	   
	   //method to edit pitcher stats if button is clicked
	   private class EditPitcherButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	         try
	         {
	            // Get the selected value from the name list.
	            String selectedName = (String) pitcherList.getSelectedValue();
	            
	            leaguePitcher = new pitcher();
	            
	            // Determine that the name has a value.
	            if(selectedName != null)
	            {
	               //get new innings pitched
	               String newInningsPitched = JOptionPane.showInputDialog(null,
	                      selectedName + "\nEnter total number of innings pitched.");
	               int ip = Integer.parseInt(newInningsPitched);
	           //get new strikeouts
	               String newStrikeOuts =
	            		   JOptionPane.showInputDialog(null, 
	            		   selectedName + "\nEnter total strikeouts.");
	               			int so = Integer.parseInt(newStrikeOuts);
	            //get new walks
	               String newWalks =
	            		   JOptionPane.showInputDialog(null, 
	            		   selectedName + "\nEnter total walks");
	               			int walks = Integer.parseInt(newWalks);
	             //get new earned runs
	               String newEarnedRuns =
	     	            	JOptionPane.showInputDialog(null, 
	     	            	selectedName + "\nEnter total earned runs");
	     	               	int er = Integer.parseInt(newEarnedRuns);
	               // Determine innings pitched has value
	               if(newInningsPitched != null)
	               {
	                  //call method to complete
	                  leaguePitcher.setInningsPitched(selectedName, ip);
	               
	                  //display that update was successful
	                  JOptionPane.showMessageDialog(null, 
	                  "Innings pitched updated");
	               }
	               //determine that strikeouts has a value
	               if(newStrikeOuts != null)
	               {
	                  //call method to complete
	                  leaguePitcher.setStrikeouts(selectedName, so);
	               
	                  //display that update was successful
	                  JOptionPane.showMessageDialog(null, 
	                  "strikeouts updated");
	               } 
	               //determine if walks has a value
	               if(newWalks != null)
	               {
	                  //call method to complete
	                  leaguePitcher.setWalks(selectedName, walks);
	               
	                  //display that update was successful
	                  JOptionPane.showMessageDialog(null, 
	                  "walks updated");
	               }
	               //determine earned runs has a value
	               if(newEarnedRuns != null)
	               {
	                  //call method to complete
	                  leaguePitcher.setEarnedRuns(selectedName, er);
	               
	                  //display that the updated was successful
	                  JOptionPane.showMessageDialog(null, 
	                  "Earned runs updated");
	               }   
	            }
	            else
	               //show message if name is not selected
	               JOptionPane.showMessageDialog(null, 
	               "Select a name from the list.");
	         }
	         catch(SQLException ex)
	         {
	            //show message if something goes wrong
	            JOptionPane.showMessageDialog(null, ex.toString());
	         }
	      }
	   }
	   
	   //method to edit the hitter stats when the button is clicked
	   private class EditHitterButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	         try
	         {
	            // Get the selected value from the name list.
	            String selectedName = (String) hitterList.getSelectedValue();
	            
	            leagueHitter = new hitter();
	            
	            // Determine that the name has a value.
	            if(selectedName != null)
	            {
	               //get new at Bats
	               String newAtBats = JOptionPane.showInputDialog(null,
	                      selectedName + "\nEnter total number of at bats.");
	               int atBats = Integer.parseInt(newAtBats);
	            //get new hits
	               String newHits =
	            		   JOptionPane.showInputDialog(null, 
	            		   selectedName + "\nEnter total hits.");
	               			int hits = Integer.parseInt(newHits);
	            // get new home runs
	               String newHomeruns =
	            		   JOptionPane.showInputDialog(null, 
	            		   selectedName + "\nEnter total Homeruns");
	               			int hrs = Integer.parseInt(newHomeruns);
	               //get new RBIs
	               String newRBI =
	     	            	JOptionPane.showInputDialog(null, 
	     	            	selectedName + "\nEnter total RBIs");
	     	               	int rbi = Integer.parseInt(newRBI);
	     	       //get new runs
	     	       String newRuns =
	   	     	            JOptionPane.showInputDialog(null, 
	   	     	            selectedName + "\nEnter total runs");
	   	     	            int runs = Integer.parseInt(newRuns);
	   	     	    //get new stolen bases
	   	     	   String newStolenBases =
		     	            JOptionPane.showInputDialog(null, 
		     	            selectedName + "\nEnter total stolen bases");
		     	            int sb = Integer.parseInt(newStolenBases);
	               // Determine that at bats has a value
	               if(newAtBats != null)
	               {
	                  //call method to complete
	                  leagueHitter.setAtBats(selectedName, atBats);
	               
	                  //show user update was completed
	                  JOptionPane.showMessageDialog(null, 
	                  "at bats updated");
	               }
	               //determine that hits has a value
	               if(newHits != null)
	               {
	                  //call method to complete
	                  leagueHitter.setHits(selectedName, hits);
	               
	                  //show user update was completed
	                  JOptionPane.showMessageDialog(null, 
	                  "hits updated");
	               } 
	               //determine that home runs has a value
	               if(newHomeruns != null)
	               {
	                  //call method to complete
	                  leagueHitter.setHomeRuns(selectedName, hrs);
	               
	                  //show user update was completed
	                  JOptionPane.showMessageDialog(null, 
	                  "homeruns updated");
	               }
	               //determine that RBIs has a value
	               if(newRBI != null)
	               {
	                  //call method to complete
	                  leagueHitter.setRBI(selectedName, rbi);
	               
	                  //show user update was completed
	                  JOptionPane.showMessageDialog(null, 
	                  "RBIs updated");
	               }
	               //determine that runs has a value
	               if(newRuns != null)
	               {
	                  //call method to complete
	                  leagueHitter.setRuns(selectedName, runs);
	               
	                  //show user update was completed
	                  JOptionPane.showMessageDialog(null, 
	                  "runs updated");
	               }
	               //determine that stolen bases has a value
	               if(newStolenBases != null)
	               {
	                  //call method to complete
	                  leagueHitter.setStolenBase(selectedName, sb);
	               
	                  //show user update was complete
	                  JOptionPane.showMessageDialog(null, 
	                  "stolen bases updated");
	               }
	            }
	            else
	               //show message if name is not selected
	               JOptionPane.showMessageDialog(null, 
	               "Select a name from the list.");
	         }
	         catch(SQLException ex)
	         {
	            //show message is something goes wrong
	            JOptionPane.showMessageDialog(null, ex.toString());
	         }
	      }
	   }
	   
	   //build table for viewing teams, players and standings
	   public static DefaultTableModel buildTableModel(ResultSet rs)
		        throws SQLException {
		   
		    ResultSetMetaData metaData = rs.getMetaData();

		    // names of columns
		    Vector<String> columnNames = new Vector<String>();
		    int columnCount = metaData.getColumnCount();
		    for (int column = 1; column <= columnCount; column++) {
		        columnNames.add(metaData.getColumnName(column));
		    }

		    // data of the table
		    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		    while (rs.next()) {
		        Vector<Object> vector = new Vector<Object>();
		        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		            vector.add(rs.getObject(columnIndex));
		        }
		        data.add(vector);
		    }

		    return new DefaultTableModel(data, columnNames);
		}
	   
	   //Method to exit the application if you click the quit button
	   private class ExitButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	         // Exit the application.
	         System.exit(0);
	      }
	   }
	   
	   
public static void main(String[] args) throws SQLException
{
   // Instantiate baseball demo class
   new BaseballDemo();

}
}



