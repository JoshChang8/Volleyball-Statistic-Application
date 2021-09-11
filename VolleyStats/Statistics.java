/*
 * Name: Joshua Chang
 * Description: Statistics allow the user to track the stats (offense and defense) for each of the players
 * 				These stats will be revealed once the user is done tracking statistics and goes to the summary frame 
 * 				Includes the image of the court that tracks the placement of the statistics (digs and attacks) 
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Statistics implements ActionListener, ChangeListener, ItemListener, MouseListener{
	
	JFrame statistics = new JFrame(); //statistic frame 
	JPanel courtPanel = new JPanel(); //panel for the court image to be displayed 
	JPanel passPanel = new JPanel(); //panel for the Pass slider to be displayed 
	JLabel passLabel = new JLabel(); //label for the Pass slider to be displayed 
	JSlider passSlider = new JSlider(); //slider to track the score of the pass
	JButton sumButton = new JButton("Summary"); //button to next frame, Summary
	JButton deselect = new JButton("Deselect Buttons"); //button to deselect all the player number and stat option buttons in the frame
	
	//allows the volleyball court image to be shown on the Statistics frame 
	ImageIcon vball = new ImageIcon("images/court.jpg");
	Image img1 = vball.getImage();
	Image newimg1 = img1.getScaledInstance(705, 435, java.awt.Image.SCALE_SMOOTH ); //set specific size for court image  
	ImageIcon vballpic = new ImageIcon (newimg1);
	JLabel vballCourt = new JLabel(vballpic); //volleyball court as label
	
	JLabel coordinates = new JLabel("COORDINATES"); //label to show coordinate of the court when mouse is clicked on court
	int x; //x-coordinate of the mouse pressed
	int y; //y-coordinate of the mouse pressed
	String score; //score of the pass 
	JToggleButton [] playerNum = new JToggleButton [7]; //array of player buttons
	JLabel offense = new JLabel("OFFENSE"); //offense label below number buttons 
	JLabel serving = new JLabel("SERVE"); //serve label
	JLabel attacking = new JLabel("ATTACK"); //attack label 
	JToggleButton [] serveResults = new JToggleButton [3]; //array of buttons for serving
	JToggleButton [] attackResults = new JToggleButton [3]; //array of buttons for attacking
	JLabel defense = new JLabel("DEFENSE"); //defense label  
	JLabel digging = new JLabel("DIG"); //dig label
	JLabel blocking = new JLabel("BLOCKING"); //block label
	JToggleButton [] digResults = new JToggleButton [2]; //array of buttons for digging
	JToggleButton [] blockResults = new JToggleButton[3]; //array of buttons for blocking
	JToggleButton pass = new JToggleButton("PASS"); //button to track score of pass
	JTextArea status = new JTextArea(); //shows the user what stat has been tracked near the bottom of the screen

	{
	//to initialize all the toggle buttons in the array
		for (int x=0; x<playerNum.length; x++)
			playerNum[x] = new JToggleButton();
		
		for (int x=0; x<serveResults.length; x++)
			serveResults[x] = new JToggleButton();
		
		for (int x=0; x<attackResults.length; x++)
			attackResults[x] = new JToggleButton();
		
		for (int x=0; x<digResults.length; x++)
			digResults[x] = new JToggleButton();
		
		for (int x=0; x<blockResults.length; x++)
			blockResults[x] = new JToggleButton();
	}
	
	//create button groups so only one button can be selected at a time for each group
	ButtonGroup playerNumGroup = new ButtonGroup(); //only one number can be selected 
	//can only select one button from serve, attack, pass, dig, and blocking because you can only have one button selected at a time
	ButtonGroup resultsGroup = new ButtonGroup(); //only one option can be selected 

	Statistics(){
	
		//placement of components 
		sumButton.setBounds(850,470,100,30);
		deselect.setBounds(540,25, 150, 30);
		courtPanel.setBounds(0,75,705,435);
		offense.setBounds(855,35,150,30);
		defense.setBounds(855,215,150,30);
		status.setBounds(10, 535, 690, 20);
		
		//action listeners allow an action to take place if the user were to press the button
		deselect.addActionListener(this);
		sumButton.addActionListener(this);
		
		courtPanel.add(vballCourt); //add image to panel
		
		//add components to frame
		statistics.add(sumButton); 
		statistics.add(deselect);
		statistics.add(courtPanel);  
		statistics.add(offense);
		statistics.add(defense);
		statistics.add(status);

		//add player number buttons to frame from the playEntries array in the PlayerEntry class 
		for (int x=0; x<playerNum.length; x++) {
			playerNumGroup.add(playerNum[x]);
			playerNum[x].setText(PlayerEntry.playerEntries[x][1]); //each player button will show the number of each player 
			playerNum[x].setBounds(710+(50*x),10,50,20); //buttons are evenly spaced out
			playerNum[x].addChangeListener(stateChangeListener); //allow action to take place if the state of the button is changed 
			statistics.add(playerNum[x]);
		}
		
		//COORDINATES OF THE COURT WHEN MOUSE IS PRESSED
		courtPanel.addMouseListener(this); //allows action to take place when the mouse enters the courtPanel
		coordinates.setBounds(310, 505, 300, 30);
		statistics.add(coordinates);		
		
		//SERVE BUTTONS
		//serving label header
		serving.setBounds(863,75,100,20);
		statistics.add(serving);	
		//add serve buttons to frame with correct placement 
		for (int x=0; x<serveResults.length; x++) {
			resultsGroup.add(serveResults[x]);
			serveResults[x].setBounds(715+(120*x),100,100,20); //buttons are evenly spaced out 
			serveResults[x].addChangeListener(stateChangeListener); //allow action to take place if the state of the button is changed 
			statistics.add(serveResults[x]);
		}
		//naming the serve buttons
		serveResults[0].setText("MISS");
		serveResults[1].setText("SUCCESS");
		serveResults[2].setText("ACE");
		
		//ATTACK BUTTONS
		//attack label header
		attacking.setBounds(860,145,100,20);
		statistics.add(attacking);
		//add attack buttons to frame with correct placement 
		for (int x=0; x<attackResults.length; x++) {
			resultsGroup.add(attackResults[x]);
			attackResults[x].setBounds(715+(120*x),170,100,20); //buttons evenly spaced 
			attackResults[x].addChangeListener(stateChangeListener); //allow action to take place if the state of the button is changed 
			statistics.add(attackResults[x]);
			}
		//naming the attack buttons
		attackResults[0].setText("ERROR");
		attackResults[1].setText("RETURN");
		attackResults[2].setText("KILL");
		
		//PASS BUTTON
		//placing and adding passing button to frame
		pass.setBounds(835,310,100,20); 
		pass.addChangeListener(stateChangeListener); //allow action to take place if the state of the button is changed 
		resultsGroup.add(pass); //add the pass button to the results buttongroup
		statistics.add(pass); 
		//slider component 
		passSlider = new JSlider(10,30,20); // (min, max, starting point of slider) slider stores values from 10-30
		passPanel.setBounds(710,240,250,70); //size of panel for slider
		passPanel.add(passSlider);
		passPanel.add(passLabel); 
		passSlider.setPreferredSize(new Dimension(200,70)); //size of the slider
		//since I want the score to display values from 1.0 - 3.0, the actual values of the slider are 10-30 but I divide the values by 10 to get the decimal numbers of 1-3 
		passLabel.setText("Score: "+(double)passSlider.getValue()/10); //to have values between 1-3 with decimals to be precise
		passLabel.setBounds(970,260,100,30);
		passSlider.addChangeListener(this); ////allow action to take place if the state of the button is changed 
		statistics.add(passPanel);
		statistics.add(passLabel);
		
		//BLOCK BUTTONS 
		//blocking header label
		blocking.setBounds(855,355,100,20);
		statistics.add(blocking);
		//add block buttons to frame with correct placement 
		for (int x=0; x<blockResults.length; x++) {
			resultsGroup.add(blockResults[x]);
			blockResults[x].setBounds(785+(75*x),385,50,20); //buttons evenly spaced 
			blockResults[x].addChangeListener(stateChangeListener); //allow action to take place if the state of the button is changed 
			statistics.add(blockResults[x]);
			}
		//naming the block buttons
		blockResults[0].setText("0");
		blockResults[1].setText("1");
		blockResults[2].setText("2");
		
		//DIG BUTTONS 
		//digging header label
		digging.setBounds(750,425,100,20);
		statistics.add(digging);
		//add dig buttons to frame with correct placement 
		for (int x=0; x<digResults.length; x++) {
			resultsGroup.add(digResults[x]);
			digResults[x].setBounds(800+(115*x),425,75,20); //buttons evenly spaced 
			digResults[x].addChangeListener(stateChangeListener); //allow action to take place if the state of the button is changed 
			statistics.add(digResults[x]);
			}
		//naming dig buttons 
		digResults[0].setText("YES");
		digResults[1].setText("NO");

		
		//statistics frame setup
		statistics.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		statistics.setTitle("Statistics");
		statistics.setSize(1080, 610);
		statistics.setLayout(null);
		statistics.setVisible(true);
	}
	
	/*
	 * Description: takes information from the specific statistic and adds the info to arrayLists
	 * pre: player number, stat type, stat result, x-coordinate of stat, y-coordinate of stat (all Strings)
	 * post: N/A
	 */
	public void enterResultsCoordinates(String num, String type, String result, String coordX, String coordY) {
    	TrackStats.numList.add(num); 
    	TrackStats.typeList.add(type);    
    	TrackStats.resultList.add(result);   
    	TrackStats.xList.add(coordX);
    	TrackStats.yList.add(coordY);
	}
	
	/*
	 * Description: takes information from the specific statistic and adds the info to arrayLists (different from the method above because 
	 * 				these statistics don't have coordinates so we set set the coordinates to -1 so we can neglect these values
	 * pre: player number, stat type, stat result (all Strings)
	 * post: N/A
	 */
	public void enterResults(String num, String type, String result) {
    	TrackStats.numList.add(num); 
    	TrackStats.typeList.add(type);    
    	TrackStats.resultList.add(result);   
    	TrackStats.xList.add("-1");  
    	TrackStats.yList.add("-1"); 
	}
	
	/*
	 * Description: stores a String of the statistic that will be showed to the user at the bottom of the statistic screen
	 * pre: player number, stat type, stat result, x-coordinate, y-coordinate (all Strings)
	 * post: String of the all the individual strings combined to show the statistic information
	 */
	public String displayStatusCoordinates(String num, String type, String result, String coordX, String coordY) {
		String status;
   	 	status = ("Stat Tracked:   Player Number: "+num+"   Type: "+type+"   Result: "+result+"   x-coordinate: "+coordX+"   y-coordinate: "+coordY+"     DESELECT BUTTONS");
   	 	return status;
	}
	
	/*
	 * Description: stores a String of the statistic that will be showed to the user at the bottom of the statistics screen
	 * 				Different from the method above because these stats don't include coordinates
	 * pre: player number, stat type, stat result (all Strings)
	 * post: String of the all the individual strings combined to show the statistic information
	 */
	public String displayStatus(String num, String type, String result) {
    	 String status;
    	 status = ("Stat Tracked:   Player Number: "+num+"   Type: "+type+"   Result: "+result+"     DESELECT BUTTONS");
    	 return status;
	}
	
	 ChangeListener stateChangeListener = new javax.swing.event.ChangeListener() {
		 
		 	/*
			 * Description: called when an state is changed
			 * pre: ChangeEvent
			 * post:N/A
			 */
	        public void stateChanged(javax.swing.event.ChangeEvent e) {
	            toggleButtonStateChanged(e);
	        }
	        
	        /*
	    	 * Description: when the state of toggleButton is changed, it is either selected or deselected, and the action will still follow through if the button is deselected 
	    	 * pre: ChangeEvent
	    	 * post:N/A
	    	 */
	        private void toggleButtonStateChanged(ChangeEvent e) {
	        	String num; //number of the player
	        	String type; //statistic type to be tracked ie) attack, pass
	        	String result; //the result of the statistic type ie) success, miss
	        	String coordX; //x-coordinate of where the play took place (only for attack and dig)
	        	String  coordY; //y-coordinate of where the play took place (only for attack and dig)
	            
	        	//statistics for player1
	        	if (playerNum[0].isSelected() && serveResults[0].isSelected()) { //player 1 misses serve
	            	num = PlayerEntry.playerEntries[0][1]; type = "serve"; result = "miss"; //values stored to be sent into array           
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));} //values will entered into the array and will also be shown on the Statistics screen to let the user know
	            
	        	if (playerNum[0].isSelected() && serveResults[1].isSelected()) { //player 1 makes serve
	        		num = PlayerEntry.playerEntries[0][1]; type = "serve"; result = "success";	;            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));}
	        	
	            if (playerNum[0].isSelected() && serveResults[2].isSelected()) { //player 1 gets an ace
	            	num = PlayerEntry.playerEntries[0][1]; type = "serve"; result = "ace";	           
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));}
	            
	            if (playerNum[0].isSelected() && attackResults[0].isSelected()) { //player 1 misses a hit
	            	num = PlayerEntry.playerEntries[0][1]; type = "attack"; result = "error";	            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));} 
	            
	        	if (playerNum[0].isSelected() && attackResults[1].isSelected()) { //player 1 hit the ball in the opposing teams side but the team digs the ball
	        		num = PlayerEntry.playerEntries[0][1]; type = "attack"; result = "return"; coordX = String.valueOf(x); coordY = String.valueOf(y);            
	            	enterResultsCoordinates(num,type,result,coordX,coordY); status.setText(displayStatusCoordinates(num, type, result,coordX,coordY));} 
	            
	        	if (playerNum[0].isSelected() && attackResults[2].isSelected()) { //player 1 hits the ball on the opposing teams side and scores the point
	            	num = PlayerEntry.playerEntries[0][1]; type = "attack"; result = "kill"; coordX = String.valueOf(x); coordY = String.valueOf(y);            
	            	enterResultsCoordinates(num,type,result,coordX,coordY); status.setText(displayStatusCoordinates(num, type, result,coordX,coordY));}		            
	            
	        	if (playerNum[0].isSelected() && blockResults[0].isSelected()) { //player 1 has an unsuccessful block
	            	num = PlayerEntry.playerEntries[0][1]; type = "block"; result = "0";	            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));} 
	            
	        	if (playerNum[0].isSelected() && blockResults[1].isSelected()) { //player 1 has a "help" block
	        		num = PlayerEntry.playerEntries[0][1]; type = "block"; result = "1";            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));} 
	          
	        	if (playerNum[0].isSelected() && blockResults[2].isSelected()) { //player 1 wins a point off the block
	            	num = PlayerEntry.playerEntries[0][1]; type = "block"; result = "2";       
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));}	
	            
	            if (playerNum[0].isSelected() && digResults[0].isSelected()) { //player 1 makes the dig
	        		num = PlayerEntry.playerEntries[0][1]; type = "dig"; result = "yes"; coordX = String.valueOf(x); coordY = String.valueOf(y);          
	            	enterResultsCoordinates(num,type,result,coordX,coordY); status.setText(displayStatusCoordinates(num, type, result,coordX,coordY));} 
	            
	            if (playerNum[0].isSelected() && digResults[1].isSelected()) { //player 1 does not make the dig
	            	num = PlayerEntry.playerEntries[0][1]; type = "dig"; result = "no"; coordX = String.valueOf(x); coordY = String.valueOf(y);
	            	enterResultsCoordinates(num,type,result,coordX,coordY); status.setText(displayStatusCoordinates(num, type, result,coordX,coordY));}	
	           
	            if (playerNum[0].isSelected() && pass.isSelected()) { //player 1 makes a pass
	            	num = PlayerEntry.playerEntries[0][1]; type = "pass"; result = String.valueOf((double)passSlider.getValue()/10); 
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));}	
	            
	            
	            //statistics for player2
	            //the understand the code, reference the statistics for player1 above as this is the same code but stored for a different player
	            if (playerNum[1].isSelected() && serveResults[0].isSelected()) {
	            	num = PlayerEntry.playerEntries[1][1]; type = "serve"; result = "miss";	            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));} 
	            
	        	if (playerNum[1].isSelected() && serveResults[1].isSelected()) {
	        		num = PlayerEntry.playerEntries[1][1]; type = "serve"; result = "success";	           
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));}
	        	
	            if (playerNum[1].isSelected() && serveResults[2].isSelected()) {
	            	num = PlayerEntry.playerEntries[1][1]; type = "serve"; result = "ace";           
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));}
	            
	            if (playerNum[1].isSelected() && attackResults[0].isSelected()) {
	            	num = PlayerEntry.playerEntries[1][1]; type = "attack"; result = "error";	            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));} 
	            
	        	if (playerNum[1].isSelected() && attackResults[1].isSelected()) {
	        		num = PlayerEntry.playerEntries[1][1]; type = "attack"; result = "return"; coordX = String.valueOf(x); coordY = String.valueOf(y);            
	            	enterResultsCoordinates(num,type,result,coordX,coordY); status.setText(displayStatusCoordinates(num, type, result,coordX,coordY));} 
	            
	        	if (playerNum[1].isSelected() && attackResults[2].isSelected()) {
	            	num = PlayerEntry.playerEntries[1][1]; type = "attack"; result = "kill"; coordX = String.valueOf(x); coordY = String.valueOf(y);            
	            	enterResultsCoordinates(num,type,result,coordX,coordY); status.setText(displayStatusCoordinates(num, type, result,coordX,coordY));}		            
	            
	        	if (playerNum[1].isSelected() && blockResults[0].isSelected()) {
	            	num = PlayerEntry.playerEntries[1][1]; type = "block"; result = "0";	            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));} 
	            
	        	if (playerNum[1].isSelected() && blockResults[1].isSelected()) {
	        		num = PlayerEntry.playerEntries[1][1]; type = "block"; result = "1";            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));} 
	            
	        	if (playerNum[1].isSelected() && blockResults[2].isSelected()) {
	            	num = PlayerEntry.playerEntries[1][1]; type = "block"; result = "2";          
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));}	
	            
	            if (playerNum[1].isSelected() && digResults[0].isSelected()) {
	        		num = PlayerEntry.playerEntries[1][1]; type = "dig"; result = "YES"; coordX = String.valueOf(x); coordY = String.valueOf(y);          
	            	enterResultsCoordinates(num,type,result,coordX,coordY); status.setText(displayStatusCoordinates(num, type, result,coordX,coordY));} 
	            
	            if (playerNum[1].isSelected() && digResults[1].isSelected()) {
	            	num = PlayerEntry.playerEntries[1][1]; type = "dig"; result = "NO"; coordX = String.valueOf(x); coordY = String.valueOf(y);
	            	enterResultsCoordinates(num,type,result,coordX,coordY); status.setText(displayStatusCoordinates(num, type, result,coordX,coordY));}	
	           
	            if (playerNum[1].isSelected() && pass.isSelected()) {
	            	num = PlayerEntry.playerEntries[1][1]; type = "pass"; result = String.valueOf((double)passSlider.getValue()/10); 
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));}		
	            
	            
	            //statistics for player3
	            //the understand the code, reference the statistics for player1 above as this is the same code but stored for a different player
	            if (playerNum[2].isSelected() && serveResults[0].isSelected()) {
	            	num = PlayerEntry.playerEntries[2][1]; type = "serve"; result = "miss";	            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));} 
	            
	        	if (playerNum[2].isSelected() && serveResults[1].isSelected()) {
	        		num = PlayerEntry.playerEntries[2][1]; type = "serve"; result = "success";	         
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));}
	        	
	            if (playerNum[2].isSelected() && serveResults[2].isSelected()) {
	            	num = PlayerEntry.playerEntries[2][1]; type = "serve"; result = "ace";            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));}
	            
	            if (playerNum[2].isSelected() && attackResults[0].isSelected()) {
	            	num = PlayerEntry.playerEntries[2][1]; type = "attack"; result = "error";	            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));} 
	            
	        	if (playerNum[2].isSelected() && attackResults[1].isSelected()) {
	        		num = PlayerEntry.playerEntries[2][1]; type = "attack"; result = "return"; coordX = String.valueOf(x); coordY = String.valueOf(y);            
	            	enterResultsCoordinates(num,type,result,coordX,coordY); status.setText(displayStatusCoordinates(num, type, result,coordX,coordY));} 
	            
	        	if (playerNum[2].isSelected() && attackResults[2].isSelected()) {
	            	num = PlayerEntry.playerEntries[2][1]; type = "attack"; result = "kill"; coordX = String.valueOf(x); coordY = String.valueOf(y);            
	            	enterResultsCoordinates(num,type,result,coordX,coordY); status.setText(displayStatusCoordinates(num, type, result,coordX,coordY));}		            
	            
	        	if (playerNum[2].isSelected() && blockResults[0].isSelected()) {
	            	num = PlayerEntry.playerEntries[2][1]; type = "block"; result = "0";	            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));} 
	            
	        	if (playerNum[2].isSelected() && blockResults[1].isSelected()) {
	        		num = PlayerEntry.playerEntries[2][1]; type = "block"; result = "1";            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));} 
	            
	        	if (playerNum[2].isSelected() && blockResults[2].isSelected()) {
	            	num = PlayerEntry.playerEntries[2][1]; type = "block"; result = "2";          
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));}	
	            
	            if (playerNum[2].isSelected() && digResults[0].isSelected()) {
	        		num = PlayerEntry.playerEntries[2][1]; type = "dig"; result = "YES"; coordX = String.valueOf(x); coordY = String.valueOf(y);          
	            	enterResultsCoordinates(num,type,result,coordX,coordY); status.setText(displayStatusCoordinates(num, type, result,coordX,coordY));} 
	            
	            if (playerNum[2].isSelected() && digResults[1].isSelected()) {
	            	num = PlayerEntry.playerEntries[2][1]; type = "dig"; result = "NO"; coordX = String.valueOf(x); coordY = String.valueOf(y);
	            	enterResultsCoordinates(num,type,result,coordX,coordY); status.setText(displayStatusCoordinates(num, type, result,coordX,coordY));}	
	           
	            if (playerNum[2].isSelected() && pass.isSelected()) {
	            	num = PlayerEntry.playerEntries[2][1]; type = "pass"; result = String.valueOf((double)passSlider.getValue()/10); 
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));}
	            
	            
	            //statistics for player4
	            //the understand the code, reference the statistics for player1 above as this is the same code but stored for a different player
	            if (playerNum[3].isSelected() && serveResults[0].isSelected()) {
	            	num = PlayerEntry.playerEntries[3][1]; type = "serve"; result = "miss";	            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));} 
	            
	        	if (playerNum[3].isSelected() && serveResults[1].isSelected()) {
	        		num = PlayerEntry.playerEntries[3][1]; type = "serve"; result = "success";            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));}
	        	
	            if (playerNum[3].isSelected() && serveResults[2].isSelected()) {
	            	num = PlayerEntry.playerEntries[3][1]; type = "serve"; result = "ace";	         
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));}
	            
	            if (playerNum[3].isSelected() && attackResults[0].isSelected()) {
	            	num = PlayerEntry.playerEntries[3][1]; type = "attack"; result = "error";	            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));} 
	            
	        	if (playerNum[3].isSelected() && attackResults[1].isSelected()) {
	        		num = PlayerEntry.playerEntries[3][1]; type = "attack"; result = "return"; coordX = String.valueOf(x); coordY = String.valueOf(y);            
	            	enterResultsCoordinates(num,type,result,coordX,coordY); status.setText(displayStatusCoordinates(num, type, result,coordX,coordY));} 
	            
	        	if (playerNum[3].isSelected() && attackResults[2].isSelected()) {
	            	num = PlayerEntry.playerEntries[3][1]; type = "attack"; result = "kill"; coordX = String.valueOf(x); coordY = String.valueOf(y);            
	            	enterResultsCoordinates(num,type,result,coordX,coordY); status.setText(displayStatusCoordinates(num, type, result,coordX,coordY));}		            
	            
	        	if (playerNum[3].isSelected() && blockResults[0].isSelected()) {
	            	num = PlayerEntry.playerEntries[3][1]; type = "block"; result = "0";	            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));} 
	            
	        	if (playerNum[3].isSelected() && blockResults[1].isSelected()) {
	        		num = PlayerEntry.playerEntries[3][1]; type = "block"; result = "1";            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));} 
	           
	        	if (playerNum[3].isSelected() && blockResults[2].isSelected()) {
	            	num = PlayerEntry.playerEntries[3][1]; type = "block"; result = "2";          
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));}	
	            
	            if (playerNum[3].isSelected() && digResults[0].isSelected()) {
	        		num = PlayerEntry.playerEntries[3][1]; type = "dig"; result = "YES"; coordX = String.valueOf(x); coordY = String.valueOf(y);          
	            	enterResultsCoordinates(num,type,result,coordX,coordY); status.setText(displayStatusCoordinates(num, type, result,coordX,coordY));} 
	            
	            if (playerNum[3].isSelected() && digResults[1].isSelected()) {
	            	num = PlayerEntry.playerEntries[3][1]; type = "dig"; result = "NO"; coordX = String.valueOf(x); coordY = String.valueOf(y);
	            	enterResultsCoordinates(num,type,result,coordX,coordY); status.setText(displayStatusCoordinates(num, type, result,coordX,coordY));}	
	           
	            if (playerNum[3].isSelected() && pass.isSelected()) {
	            	num = PlayerEntry.playerEntries[3][1]; type = "pass"; result = String.valueOf((double)passSlider.getValue()/10);  
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));}		   
	            
	            
	            //statistics for player5
	            //the understand the code, reference the statistics for player1 above as this is the same code but stored for a different player
	            if (playerNum[4].isSelected() && serveResults[0].isSelected()) {
	            	num = PlayerEntry.playerEntries[4][1]; type = "serve"; result = "miss";	            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));} 
	            
	        	if (playerNum[4].isSelected() && serveResults[1].isSelected()) {
	        		num = PlayerEntry.playerEntries[4][1]; type = "serve"; result = "success";	            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));}
	        	
	            if (playerNum[4].isSelected() && serveResults[2].isSelected()) {
	            	num = PlayerEntry.playerEntries[4][1]; type = "serve"; result = "ace";	            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));}
	            
	            if (playerNum[4].isSelected() && attackResults[0].isSelected()) {
	            	num = PlayerEntry.playerEntries[4][1]; type = "attack"; result = "error";	            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));} 
	            
	        	if (playerNum[4].isSelected() && attackResults[1].isSelected()) {
	        		num = PlayerEntry.playerEntries[4][1]; type = "attack"; result = "return"; coordX = String.valueOf(x); coordY = String.valueOf(y);            
	            	enterResultsCoordinates(num,type,result,coordX,coordY); status.setText(displayStatusCoordinates(num, type, result,coordX,coordY));} 
	            
	        	if (playerNum[4].isSelected() && attackResults[2].isSelected()) {
	            	num = PlayerEntry.playerEntries[4][1]; type = "attack"; result = "kill"; coordX = String.valueOf(x); coordY = String.valueOf(y);            
	            	enterResultsCoordinates(num,type,result,coordX,coordY); status.setText(displayStatusCoordinates(num, type, result,coordX,coordY));}		            
	            
	        	if (playerNum[4].isSelected() && blockResults[0].isSelected()) {
	            	num = PlayerEntry.playerEntries[4][1]; type = "block"; result = "0";	            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));} 
	            
	        	if (playerNum[4].isSelected() && blockResults[1].isSelected()) {
	        		num = PlayerEntry.playerEntries[4][1]; type = "block"; result = "1";            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));} 
	            if (playerNum[4].isSelected() && blockResults[2].isSelected()) {
	            	num = PlayerEntry.playerEntries[4][1]; type = "block"; result = "2";          
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));}	
	            
	            if (playerNum[4].isSelected() && digResults[0].isSelected()) {
	        		num = PlayerEntry.playerEntries[4][1]; type = "dig"; result = "YES"; coordX = String.valueOf(x); coordY = String.valueOf(y);          
	            	enterResultsCoordinates(num,type,result,coordX,coordY); status.setText(displayStatusCoordinates(num, type, result,coordX,coordY));} 
	            
	            if (playerNum[4].isSelected() && digResults[1].isSelected()) {
	            	num = PlayerEntry.playerEntries[4][1]; type = "dig"; result = "NO"; coordX = String.valueOf(x); coordY = String.valueOf(y);
	            	enterResultsCoordinates(num,type,result,coordX,coordY); status.setText(displayStatusCoordinates(num, type, result,coordX,coordY));}	
	           
	            if (playerNum[4].isSelected() && pass.isSelected()) {
	            	num = PlayerEntry.playerEntries[4][1]; type = "pass"; result = String.valueOf((double)passSlider.getValue()/10);  
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));}		   
	            
	            
	            //statistics for player 6
	            //the understand the code, reference the statistics for player1 above as this is the same code but stored for a different player
	            if (playerNum[5].isSelected() && serveResults[0].isSelected()) {
	            	num = PlayerEntry.playerEntries[5][1]; type = "serve"; result = "miss";	            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));} 
	            
	        	if (playerNum[5].isSelected() && serveResults[1].isSelected()) {
	        		num = PlayerEntry.playerEntries[5][1]; type = "serve"; result = "success";           
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));}
	        	
	            if (playerNum[5].isSelected() && serveResults[2].isSelected()) {
	            	num = PlayerEntry.playerEntries[5][1]; type = "serve"; result = "ace";	            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));}
	            
	            if (playerNum[5].isSelected() && attackResults[0].isSelected()) {
	            	num = PlayerEntry.playerEntries[5][1]; type = "attack"; result = "error";	            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));} 
	            
	        	if (playerNum[5].isSelected() && attackResults[1].isSelected()) {
	        		num = PlayerEntry.playerEntries[5][1]; type = "attack"; result = "return"; coordX = String.valueOf(x); coordY = String.valueOf(y);            
	            	enterResultsCoordinates(num,type,result,coordX,coordY); status.setText(displayStatusCoordinates(num, type, result,coordX,coordY));} 
	            
	        	if (playerNum[5].isSelected() && attackResults[2].isSelected()) {
	            	num = PlayerEntry.playerEntries[5][1]; type = "attack"; result = "kill"; coordX = String.valueOf(x); coordY = String.valueOf(y);            
	            	enterResultsCoordinates(num,type,result,coordX,coordY); status.setText(displayStatusCoordinates(num, type, result,coordX,coordY));}		            
	            
	        	if (playerNum[5].isSelected() && blockResults[0].isSelected()) {
	            	num = PlayerEntry.playerEntries[5][1]; type = "block"; result = "0";	            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));} 
	            
	        	if (playerNum[5].isSelected() && blockResults[1].isSelected()) {
	        		num = PlayerEntry.playerEntries[5][1]; type = "block"; result = "1";            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));} 
	            
	        	if (playerNum[5].isSelected() && blockResults[2].isSelected()) {
	            	num = PlayerEntry.playerEntries[5][1]; type = "block"; result = "2";          
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));}	
	            
	            if (playerNum[5].isSelected() && digResults[0].isSelected()) {
	        		num = PlayerEntry.playerEntries[5][1]; type = "dig"; result = "YES"; coordX = String.valueOf(x); coordY = String.valueOf(y);          
	            	enterResultsCoordinates(num,type,result,coordX,coordY); status.setText(displayStatusCoordinates(num, type, result,coordX,coordY));} 
	            
	            if (playerNum[5].isSelected() && digResults[1].isSelected()) {
	            	num = PlayerEntry.playerEntries[5][1]; type = "dig"; result = "NO"; 
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));}	
	           
	            if (playerNum[5].isSelected() && pass.isSelected()) {
	            	num = PlayerEntry.playerEntries[5][1]; type = "pass"; result = String.valueOf((double)passSlider.getValue()/10); 
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));}		   
	            
	            
	            //statistics for player 7
	            //the understand the code, reference the statistics for player1 above as this is the same code but stored for a different player
	            if (playerNum[6].isSelected() && serveResults[0].isSelected()) {
	            	num = PlayerEntry.playerEntries[6][1]; type = "serve"; result = "miss";	            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));} 
	            
	        	if (playerNum[6].isSelected() && serveResults[1].isSelected()) {
	        		num = PlayerEntry.playerEntries[6][1]; type = "serve"; result = "success";            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));}
	        	
	            if (playerNum[6].isSelected() && serveResults[2].isSelected()) {
	            	num = PlayerEntry.playerEntries[6][1]; type = "serve"; result = "ace";            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));}
	            
	            if (playerNum[6].isSelected() && attackResults[0].isSelected()) {
	            	num = PlayerEntry.playerEntries[6][1]; type = "attack"; result = "error";	            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));} 
	            
	        	if (playerNum[6].isSelected() && attackResults[1].isSelected()) {
	        		num = PlayerEntry.playerEntries[6][1]; type = "attack"; result = "return"; coordX = String.valueOf(x); coordY = String.valueOf(y);            
	            	enterResultsCoordinates(num,type,result,coordX,coordY); status.setText(displayStatusCoordinates(num, type, result,coordX,coordY));} 
	            
	        	if (playerNum[6].isSelected() && attackResults[2].isSelected()) {
	            	num = PlayerEntry.playerEntries[6][1]; type = "attack"; result = "kill"; coordX = String.valueOf(x); coordY = String.valueOf(y);            
	            	enterResultsCoordinates(num,type,result,coordX,coordY); status.setText(displayStatusCoordinates(num, type, result,coordX,coordY));}		            
	            
	        	if (playerNum[6].isSelected() && blockResults[0].isSelected()) {
	            	num = PlayerEntry.playerEntries[6][1]; type = "block"; result = "0";	            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));} 
	            
	        	if (playerNum[6].isSelected() && blockResults[1].isSelected()) {
	        		num = PlayerEntry.playerEntries[6][1]; type = "block"; result = "1";            
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));} 
	            
	        	if (playerNum[6].isSelected() && blockResults[2].isSelected()) {
	            	num = PlayerEntry.playerEntries[6][1]; type = "block"; result = "2";          
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));}	
	            
	            if (playerNum[6].isSelected() && digResults[0].isSelected()) {
	        		num = PlayerEntry.playerEntries[6][1]; type = "dig"; result = "YES"; coordX = String.valueOf(x); coordY = String.valueOf(y);          
	            	enterResultsCoordinates(num,type,result,coordX,coordY); status.setText(displayStatusCoordinates(num, type, result,coordX,coordY));} 
	            
	            if (playerNum[6].isSelected() && digResults[1].isSelected()) {
	            	num = PlayerEntry.playerEntries[6][1]; type = "dig"; result = "NO"; coordX = String.valueOf(x); coordY = String.valueOf(y);
	            	enterResultsCoordinates(num,type,result,coordX,coordY); status.setText(displayStatusCoordinates(num, type, result,coordX,coordY));}	
	           
	            if (playerNum[6].isSelected() && pass.isSelected()) {
	            	num = PlayerEntry.playerEntries[6][1]; type = "pass"; result = String.valueOf((double)passSlider.getValue()/10); 
	            	enterResults(num,type,result); status.setText(displayStatus(num, type, result));}		   
	        }
	    };

	    /*
		 * Description: called when an action is performed (button is pressed)
		 * pre: ActionEvent
		 * post:N/A
		 */   
	     public void actionPerformed(ActionEvent e) {
		
		 if(e.getSource() == sumButton){
			
			//converts all arrayLists to arrays 
			TrackStats.listNum = TrackStats.convertToArray(TrackStats.numList);
			TrackStats.listType = TrackStats.convertToArray(TrackStats.typeList);
			TrackStats.listResult = TrackStats.convertToArray(TrackStats.resultList);
			TrackStats.listX = TrackStats.convertToArray(TrackStats.xList);
			TrackStats.listY = TrackStats.convertToArray(TrackStats.yList);
			
			//gets rid of multiple of the same stats due to error when selecting buttons 
			//some reason whenever condition is met, 3 of the same stat is made
			TrackStats.listNum = TrackStats.removeMultiples(TrackStats.listNum);
			TrackStats.listType = TrackStats.removeMultiples(TrackStats.listType);
			TrackStats.listResult = TrackStats.removeMultiples(TrackStats.listResult);
			TrackStats.listX = TrackStats.removeMultiples(TrackStats.listX);
			TrackStats.listY = TrackStats.removeMultiples(TrackStats.listY);
			
			//converts 5 separate arrays to one 2-D array to store all the information 
			TrackStats.statisticsArray = TrackStats.convertTo2D(TrackStats.listNum,TrackStats.listType,TrackStats.listResult,TrackStats.listX,TrackStats.listY);
			
			//method is supposed to be called to create the array of labels of digging error images but does not work
			//Summary.diggingErrors = new JLabel [TrackStats.numDigErrors(TrackStats.statisticsArray)];
			
			//large data set of stats is separated into 7 different arrays for each player
			TrackStats.playerStats1 = TrackStats.specifyArray(TrackStats.statisticsArray, PlayerEntry.playerEntries[0][1]);
			TrackStats.playerStats2 = TrackStats.specifyArray(TrackStats.statisticsArray, PlayerEntry.playerEntries[1][1]);
			TrackStats.playerStats3 = TrackStats.specifyArray(TrackStats.statisticsArray, PlayerEntry.playerEntries[2][1]);
			TrackStats.playerStats4 = TrackStats.specifyArray(TrackStats.statisticsArray, PlayerEntry.playerEntries[3][1]);
			TrackStats.playerStats5 = TrackStats.specifyArray(TrackStats.statisticsArray, PlayerEntry.playerEntries[4][1]);
			TrackStats.playerStats6 = TrackStats.specifyArray(TrackStats.statisticsArray, PlayerEntry.playerEntries[5][1]);
			TrackStats.playerStats7 = TrackStats.specifyArray(TrackStats.statisticsArray, PlayerEntry.playerEntries[6][1]);
					
			//creating instance to show the Statistics frame
			Summary summary = new Summary();
			statistics.dispose(); //statistics window will disappear once the summary button is pressed
			Instructions.instructions.dispose();
		}
		
		if(e.getSource() == deselect){ //when deselect button is pressed, all buttons are deselected
			resultsGroup.clearSelection(); //no serve, attack, pass, block, or dig options are selected
        	playerNumGroup.clearSelection(); //no player number buttons are selected 
        	status.setText("ENTER ANOTHER STAT"); //in the status textArea, prompts the user to enter another stat
		}
		
	}
	
	/*
	 * Description: called when a state is changed 
	 * pre: ChangeEvent
	 * post:N/A
	 */
	public void stateChanged(ChangeEvent e) {
		//whenever state of slider changes, line of code will run and value will be updated
		passLabel.setText("Score: "+(double)passSlider.getValue()/10); //to have decimal values between 1-3 because the JSliders only hold integer values
		score = String.valueOf((double)passSlider.getValue()/10); //stores the score of the pass to be stored in the arrayList
	}
	
	/*
	 * Description: when mouse is clicked
	 * pre: MouseEvent
	 * post:N/A
	 */
	public void mouseClicked(MouseEvent e) { //when the mouse is clicked
		coordinates.setText("Coordinates: X = "+e.getX()+"   Y = "+e.getY()); //displays the coordinates of where the mouse is clicked
		x = e.getX(); //stores the x value to be stored in the arrayList
		y = e.getY(); //stores the y-coordinate to be stored in the arrayList
	}
	
	//methods are not needed, but need to be included for the MouseListener implementation
	public void mousePressed(MouseEvent e) {}
	public void itemStateChanged(ItemEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}