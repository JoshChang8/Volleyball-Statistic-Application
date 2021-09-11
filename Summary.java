/*
 * Name: Joshua Chang 
 * Description: Summary shows the user the statistics of each player 
 * 				Includes the image of the court that tracks the placement of the statistics ex) passing, attacks, digs
 */
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Summary implements ActionListener{
	
	JFrame summary = new JFrame(); //summary frame 
	JPanel panel = new JPanel(); //panel for court image to be displayed 
	
	//allows volleyball court image to be on the Summary frame 
	public static ImageIcon vball = new ImageIcon("images/court.jpg");
	public static Image img1 = vball.getImage();
	public static Image newimg1 = img1.getScaledInstance(705, 435, java.awt.Image.SCALE_SMOOTH ); //set specific size for court image  
	public static ImageIcon vballpic = new ImageIcon (newimg1);
	public static JLabel vballCourt = new JLabel(vballpic);
	
	//image of the a red x
	public static ImageIcon red = new ImageIcon("images/redx.png");
	public static Image img2 = red.getImage();
	public static Image newimg2 = img2.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH ); //set specific size for court image  
	public static ImageIcon redpic = new ImageIcon (newimg2);
	
	//label array of red x images, does not work
	//public static JLabel [] diggingErrors = new JLabel [TrackStats.numDigErrors(TrackStats.statisticsArray)];
		
	JLabel statSum = new JLabel("STAT SUMMARIES");
	JLabel playerName = new JLabel(); //players name
	JLabel playerPosition = new JLabel(); //players position ie) LIB, S, LS
	
	JToggleButton teamSum = new JToggleButton ("Team");
	JToggleButton [] playerSum = new JToggleButton [7]; //array of buttons for statistic summaries for each player
	
	JTextArea [] serving = new JTextArea [3]; //array textArea for the serving statistics
	JTextArea [] attacking = new JTextArea [3]; //array textArea for the attacking statistics
	JTextArea [] passing = new JTextArea [2]; //array textArea for the passing statistics
	JTextArea [] blocking = new JTextArea [2]; //array textArea for the blocking statistics
	JTextArea [] digging = new JTextArea [2]; //array textArea for the digging statistics
	
	{
		//to initialize the textareas in the array
			for (int x=0; x<playerSum.length; x++)
				playerSum[x] = new JToggleButton();
			
			for (int x=0; x<serving.length; x++)
				serving[x] = new JTextArea();
			
			for (int x=0; x<attacking.length; x++)
				attacking[x] = new JTextArea();
			
			for (int x=0; x<passing.length; x++)
				passing[x] = new JTextArea();
			
			for (int x=0; x<blocking.length; x++)
				blocking[x] = new JTextArea();
			
			for (int x=0; x<digging.length; x++)
				digging[x] = new JTextArea();
			
			//Does not work
			//for(int x=0; x<diggingErrors.length; x++) 
			//diggingErrors[x] = new JLabel("X");
		
	}
	
	//create button groups so only one button can be selected at a time for each group
	ButtonGroup sumGroup = new ButtonGroup(); //only one button can be pressed
	
	Summary(){
		
		sumGroup.add(teamSum); //add the team summary to the summary button group as well 
		
		//placement of components 
		panel.setBounds(0,0,705,435);
		statSum.setBounds(845, 5, 150, 20);
		teamSum.setBounds(855, 30, 75, 20);
		teamSum.addActionListener(this);
		playerName.setBounds(730, 90, 300, 30);
		playerPosition.setBounds(950,90,200,30);
		panel.add(vballCourt); //add image to panel
		
		//adding components to Summary frame 
		summary.add(panel);
		summary.add(statSum);
		summary.add(teamSum);
		summary.add(playerName);
		summary.add(playerPosition);
		
		//add player number buttons to frame from the playEntries array in the PlayerEntry class 
		for (int x=0; x<playerSum.length; x++) {
			sumGroup.add(playerSum[x]);
			playerSum[x].setText(PlayerEntry.playerEntries[x][1]); //each player button will show the number of each player
			playerSum[x].setBounds(710+(50*x),60,50,20); //buttons are evenly spaced out
			playerSum[x].addActionListener(this);
			summary.add(playerSum[x]);
		}
		
		//SERVING STATISTICS
		//serving header label
		JLabel serve = new JLabel ("SERVING STATISTICS");
		serve.setBounds(730, 130, 300, 20);
		summary.add(serve);
		//add and place serving text areas to frame 
		for (int x=0; x<serving.length; x++) {
			serving[x].setBounds(720,170+(25*x),150,20); //textAreas are evenly spaced out
			summary.add(serving[x]);
		}
		
		//ATTACKING STATISTICS
		//attacking header label
		JLabel attack = new JLabel ("ATTACKING STATISTICS");
		attack.setBounds(900, 130, 300, 20);
		summary.add(attack);
		//add and place attacking text areas to frame 		
		for (int x=0; x<attacking.length; x++) {
			attacking[x].setBounds(900,170+(25*x),150,20); //textAreas are evenly spaced out
			summary.add(attacking[x]);
		}
		
		//PASSING STATISTICS
		//passing header label
		JLabel pass = new JLabel ("PASSING STATISTICS");
		pass.setBounds(730, 270, 300, 20);
		summary.add(pass);
		//add and place passing text areas to frame 				
		for (int x=0; x<passing.length; x++) {
			passing[x].setBounds(720,300+(25*x),150,20); //textAreas are evenly spaced out
			summary.add(passing[x]);
		}
		
		//DIGGING STATISTICS
		//digging header label
		JLabel dig = new JLabel ("DIGGING STATISTICS");
		dig.setBounds(910, 270, 300, 20);
		summary.add(dig);
		//add and place digging text areas to frame 				
		for (int x=0; x<digging.length; x++) {
			digging[x].setBounds(900,300+(25*x),150,20); //textAreas are evenly spaced out
			summary.add(digging[x]);
		}
		
		//BLOCKING STATISTICS
		//blocking header label
		JLabel block = new JLabel ("BLOCKING STATISTICS");
		block.setBounds(805, 370, 300, 20);
		summary.add(block);
		//add and place blocking text areas to frame 						
		for (int x=0; x<blocking.length; x++) {
			blocking[x].setBounds(795,400+(25*x),150,20); //textAreas are evenly spaced out
			summary.add(blocking[x]);
		}		
										
		//statistics frame setup
		summary.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		summary.setTitle("Summary");
		summary.setSize(1080, 510);
		summary.setLayout(null);
		summary.setVisible(true);
	}
	
	/* Method was used for the intention of displaying red "X"s on the court using the given coordinates but code was not working 
	 * Description: converts an arrayList of integer to an array 
	 * pre: arrayList of integers wanted to be converted
	 * post: array of integers 
	 */
	public static int[] convertIntegers(ArrayList<Integer> integers){
	    int[] ret = new int[integers.size()]; //array the size of the arrayList
	    for (int i=0; i < ret.length; i++) 
	        ret[i] = integers.get(i).intValue(); //index of the array gets the same element of the arrayList 
	    
	    return ret;
	}
	
	/* Method was used for the intention of displaying red "X"s on the court using the given coordinates but code was not working 
	 * Description: converts the 2D statistics array into a 2D array with coordinates of the digging errors
	 * pre: 2D statistics array 
	 * post:2D array of the coordinates of the digging errors
	 */
	public static int [][] coordinateArray (String arr [][]){ 
		int x;
		int y;
		
		ArrayList <Integer> newArrx = new ArrayList <Integer>(); //arrayList of integers to store x-coordinates
		ArrayList <Integer> newArry = new ArrayList <Integer>(); //arrayList of integers to store y-coordinates
		
		for(int i=0; i<arr.length; i++) {
			if(arr[i][2].equalsIgnoreCase("no")) { //if the statistic is was an unsuccessful dig
				x = Integer.parseInt(arr[i][3]); //x-coordinate of unsuccessful dig 
				y = Integer.parseInt(arr[i][4]); //y-coordinate of unsuccessful dig
				//adding x and y coordinates to corresponding arrayLists
				newArrx.add(x); 
				newArry.add(y);
			}
		}
		//convert the arrayList to an array using the convertIntegers method made above 
		int [] arrayx = convertIntegers(newArrx);
		int [] arrayy = convertIntegers(newArry);
		
		int [][] finalArray = new int [arrayy.length][2]; //2D array is made with the x and y coordinates of the unsuccessful digs 
		for(int j=0; j<arrayy.length; j++) {
			finalArray[j][0] = arrayx[j]; //x coordinates are transferred over
			finalArray[j][1] = arrayy[j]; //y coordinates are transfered over
		}
		return finalArray;
	}
	
	/* Method was used for the intention of displaying red "X"s on the court using the given coordinates but code was not working 
	 * Description: to display the dig errors on the volleyball court image panel
	 * pre: Array of statistics and array of buttons 
	 * post:N/A
	 */
	public static void displayDigError (String arr [][], JLabel label []) {
		int [][] array;
		array = coordinateArray(arr); //2D array with the x and y coordinates of the unsuccessful digs
		for(int i=0; i<arr.length; i++) {
		label[i].setBounds(array[i][0], array[i][1], 20, 20); //determines the placement of where the image of the red "X" is going to go
		vballCourt.add(label[i]); //the image label of the red "X" is (supposed to be) added to the volleyball image label
		}
	}
	
	//when an action is performed (in this case, pressing a button), method is called to perform an action
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub1
		
		//show stat results for the entire team
		if(e.getSource() == teamSum){ //if the team button is pressed
			
			playerName.setText("Name: "); //name of player (since this is team button, player not shown)
			playerPosition.setText("Position: "); //position of player 
			//displays serving attempts which is calculated from servingAttempts method in TrackStats class
			serving[0].setText("Serving Attempts: "+TrackStats.servingAttempts(TrackStats.statisticsArray)); 
			//displays serve percentage which is calculated from the servePercent method in the TrackStats class
			serving[1].setText("Serve Percentage: "+TrackStats.servePercent(TrackStats.statisticsArray)); 
			//displays the ace percentage which is calculated from the acePercent method in the TrackStats class
			serving[2].setText("Ace Percentage: "+TrackStats.acePercent(TrackStats.statisticsArray)); 
			
			//displays the attack attempts which is calculated from the attackAttempts method in the TrackStats class
			attacking[0].setText("Attack Attempts: "+TrackStats.attackAttempts(TrackStats.statisticsArray)); 
			//displays the kill percentage which is calculated from the killPercent method in the TrackStats class
			attacking[1].setText("Kill Percentage: "+TrackStats.killPercent(TrackStats.statisticsArray)); 
			//displays the error percentage which is calculated from the errorPercent method in the TrackStats class
			attacking[2].setText("Error Percentage: "+TrackStats.errorPercent(TrackStats.statisticsArray));
			
			//displays the passing attempts which is calculated from the passingAttempts method in the TrackStats class
			passing[0].setText("Pass Attempts: "+TrackStats.passingAttempts(TrackStats.statisticsArray)); 
			//displays the passing score which is calculated from the passingScore method in the TrackStats class
			passing[1].setText("Passing Score: "+TrackStats.passingScore(TrackStats.statisticsArray)); 
			
			//displays the digging attempts which is calculated from the diggingAttempts method in the TrackStats class
			digging[0].setText("Digging Attempts: "+TrackStats.diggingAttempts(TrackStats.statisticsArray)); 
			//displays the digging percentage which is calculated from the diggingPercent method in the TrackStats class
			digging[1].setText("Digging Percentage: "+TrackStats.diggingPercent(TrackStats.statisticsArray)); 
			
			//displays the blocking score which is calculated from the blockingScore method in the TrackStats class
			blocking[0].setText("Blocking Score: "+TrackStats.blockingScore(TrackStats.statisticsArray)); 
			//displays the blocking percentage which is calculated from the blockingPercent method in the TrackStats class
			blocking[1].setText("Blocking Percentage: "+TrackStats.blockingPercent(TrackStats.statisticsArray)); 
			
			//calls the method to display the dig errors on the court but does not work
			//displayDigError(TrackStats.statisticsArray, diggingErrors); 
			
		}
		
		//show stat results for player 1
		//to the understand the code, reference the summary for teamSum above as this is the same code but stored for a different player
		if(e.getSource() == playerSum[0]){
			
			playerName.setText("Name: "+PlayerEntry.playerEntries[0][0]);
			playerPosition.setText("Position: "+PlayerEntry.playerEntries[0][2]);
			
			serving[0].setText("Serving Attempts: "+TrackStats.servingAttempts(TrackStats.playerStats1));
			serving[1].setText("Serve Percentage: "+TrackStats.servePercent(TrackStats.playerStats1));
			serving[2].setText("Ace Percentage: "+TrackStats.acePercent(TrackStats.playerStats1));
			
			attacking[0].setText("Attack Attempts: "+TrackStats.attackAttempts(TrackStats.playerStats1));
			attacking[1].setText("Kill Percentage: "+TrackStats.killPercent(TrackStats.playerStats1));
			attacking[2].setText("Error Percentage: "+TrackStats.errorPercent(TrackStats.playerStats1));
			
			passing[0].setText("Pass Attempts: "+TrackStats.passingAttempts(TrackStats.playerStats1));
			passing[1].setText("Passing Score: "+TrackStats.passingScore(TrackStats.playerStats1));
			
			digging[0].setText("Digging Attempts: "+TrackStats.diggingAttempts(TrackStats.playerStats1));
			digging[1].setText("Digging Percentage: "+TrackStats.diggingPercent(TrackStats. playerStats1));
			
			blocking[0].setText("Blocking Score: "+TrackStats.blockingScore(TrackStats.playerStats1));
			blocking[1].setText("Blocking Percentage: "+TrackStats.blockingPercent(TrackStats.playerStats1));
		}
		
		//show stat results for player 2
		//to the understand the code, reference the summary for teamSum above as this is the same code but stored for a different player
		if(e.getSource() == playerSum[1]){
			
			playerName.setText("Name: "+PlayerEntry.playerEntries[1][0]);
			playerPosition.setText("Position: "+PlayerEntry.playerEntries[1][2]);
			
			serving[0].setText("Serving Attempts: "+TrackStats.servingAttempts(TrackStats.playerStats2));
			serving[1].setText("Serve Percentage: "+TrackStats.servePercent(TrackStats.playerStats2));
			serving[2].setText("Ace Percentage: "+TrackStats.acePercent(TrackStats.playerStats2));
			
			attacking[0].setText("Attack Attempts: "+TrackStats.attackAttempts(TrackStats.playerStats2));
			attacking[1].setText("Kill Percentage: "+TrackStats.killPercent(TrackStats.playerStats2));
			attacking[2].setText("Error Percentage: "+TrackStats.errorPercent(TrackStats.playerStats2));
			
			passing[0].setText("Pass Attempts: "+TrackStats.passingAttempts(TrackStats.playerStats2));
			passing[1].setText("Passing Score: "+TrackStats.passingScore(TrackStats.playerStats2));
			
			digging[0].setText("Digging Attempts: "+TrackStats.diggingAttempts(TrackStats.playerStats2));
			digging[1].setText("Digging Percentage: "+TrackStats.diggingPercent(TrackStats. playerStats2));
			
			blocking[0].setText("Blocking Score: "+TrackStats.blockingScore(TrackStats.playerStats2));
			blocking[1].setText("Blocking Percentage: "+TrackStats.blockingPercent(TrackStats.playerStats2));
		}
		
		//show stat results for player 3
		//to the understand the code, reference the summary for teamSum above as this is the same code but stored for a different player
		if(e.getSource() == playerSum[2]){
			
			playerName.setText("Name: "+PlayerEntry.playerEntries[2][0]);
			playerPosition.setText("Position: "+PlayerEntry.playerEntries[2][2]);
			
			serving[0].setText("Serving Attempts: "+TrackStats.servingAttempts(TrackStats.playerStats3));
			serving[1].setText("Serve Percentage: "+TrackStats.servePercent(TrackStats.playerStats3));
			serving[2].setText("Ace Percentage: "+TrackStats.acePercent(TrackStats.playerStats3));
			
			attacking[0].setText("Attack Attempts: "+TrackStats.attackAttempts(TrackStats.playerStats3));
			attacking[1].setText("Kill Percentage: "+TrackStats.killPercent(TrackStats.playerStats3));
			attacking[2].setText("Error Percentage: "+TrackStats.errorPercent(TrackStats.playerStats3));
			
			passing[0].setText("Pass Attempts: "+TrackStats.passingAttempts(TrackStats.playerStats3));
			passing[1].setText("Passing Score: "+TrackStats.passingScore(TrackStats.playerStats3));
			
			digging[0].setText("Digging Attempts: "+TrackStats.diggingAttempts(TrackStats.playerStats3));
			digging[1].setText("Digging Percentage: "+TrackStats.diggingPercent(TrackStats. playerStats3));
			
			blocking[0].setText("Blocking Score: "+TrackStats.blockingScore(TrackStats.playerStats3));
			blocking[1].setText("Blocking Percentage: "+TrackStats.blockingPercent(TrackStats.playerStats3));
		}
		
		//show stat results for player 4
		//to the understand the code, reference the summary for teamSum above as this is the same code but stored for a different player
		if(e.getSource() == playerSum[3]){
			
			playerName.setText("Name: "+PlayerEntry.playerEntries[3][0]);
			playerPosition.setText("Position: "+PlayerEntry.playerEntries[3][2]);
			
			serving[0].setText("Serving Attempts: "+TrackStats.servingAttempts(TrackStats.playerStats4));
			serving[1].setText("Serve Percentage: "+TrackStats.servePercent(TrackStats.playerStats4));
			serving[2].setText("Ace Percentage: "+TrackStats.acePercent(TrackStats.playerStats4));
			
			attacking[0].setText("Attack Attempts: "+TrackStats.attackAttempts(TrackStats.playerStats4));
			attacking[1].setText("Kill Percentage: "+TrackStats.killPercent(TrackStats.playerStats4));
			attacking[2].setText("Error Percentage: "+TrackStats.errorPercent(TrackStats.playerStats4));
			
			passing[0].setText("Pass Attempts: "+TrackStats.passingAttempts(TrackStats.playerStats4));
			passing[1].setText("Passing Score: "+TrackStats.passingScore(TrackStats.playerStats4));
			
			digging[0].setText("Digging Attempts: "+TrackStats.diggingAttempts(TrackStats.playerStats4));
			digging[1].setText("Digging Percentage: "+TrackStats.diggingPercent(TrackStats. playerStats4));
			
			blocking[0].setText("Blocking Score: "+TrackStats.blockingScore(TrackStats.playerStats4));
			blocking[1].setText("Blocking Percentage: "+TrackStats.blockingPercent(TrackStats.playerStats4));
		}
		
		//show stat results for player 5
		//to the understand the code, reference the summary for teamSum above as this is the same code but stored for a different player
		if(e.getSource() == playerSum[4]){
			
			playerName.setText("Name: "+PlayerEntry.playerEntries[4][0]);
			playerPosition.setText("Position: "+PlayerEntry.playerEntries[4][2]);
			
			serving[0].setText("Serving Attempts: "+TrackStats.servingAttempts(TrackStats.playerStats5));
			serving[1].setText("Serve Percentage: "+TrackStats.servePercent(TrackStats.playerStats5));
			serving[2].setText("Ace Percentage: "+TrackStats.acePercent(TrackStats.playerStats5));
			
			attacking[0].setText("Attack Attempts: "+TrackStats.attackAttempts(TrackStats.playerStats5));
			attacking[1].setText("Kill Percentage: "+TrackStats.killPercent(TrackStats.playerStats5));
			attacking[2].setText("Error Percentage: "+TrackStats.errorPercent(TrackStats.playerStats5));
			
			passing[0].setText("Pass Attempts: "+TrackStats.passingAttempts(TrackStats.playerStats5));
			passing[1].setText("Passing Score: "+TrackStats.passingScore(TrackStats.playerStats5));
			
			digging[0].setText("Digging Attempts: "+TrackStats.diggingAttempts(TrackStats.playerStats5));
			digging[1].setText("Digging Percentage: "+TrackStats.diggingPercent(TrackStats. playerStats5));
			
			blocking[0].setText("Blocking Score: "+TrackStats.blockingScore(TrackStats.playerStats5));
			blocking[1].setText("Blocking Percentage: "+TrackStats.blockingPercent(TrackStats.playerStats5));
		}
		
		//show stat results for player 6
		//to the understand the code, reference the summary for teamSum above as this is the same code but stored for a different player
		if(e.getSource() == playerSum[5]){
			
			playerName.setText("Name: "+PlayerEntry.playerEntries[5][0]);
			playerPosition.setText("Position: "+PlayerEntry.playerEntries[5][2]);
			
			serving[0].setText("Serving Attempts: "+TrackStats.servingAttempts(TrackStats.playerStats6));
			serving[1].setText("Serve Percentage: "+TrackStats.servePercent(TrackStats.playerStats6));
			serving[2].setText("Ace Percentage: "+TrackStats.acePercent(TrackStats.playerStats6));
			
			attacking[0].setText("Attack Attempts: "+TrackStats.attackAttempts(TrackStats.playerStats6));
			attacking[1].setText("Kill Percentage: "+TrackStats.killPercent(TrackStats.playerStats6));
			attacking[2].setText("Error Percentage: "+TrackStats.errorPercent(TrackStats.playerStats6));
			
			passing[0].setText("Pass Attempts: "+TrackStats.passingAttempts(TrackStats.playerStats6));
			passing[1].setText("Passing Score: "+TrackStats.passingScore(TrackStats.playerStats6));
			
			digging[0].setText("Digging Attempts: "+TrackStats.diggingAttempts(TrackStats.playerStats6));
			digging[1].setText("Digging Percentage: "+TrackStats.diggingPercent(TrackStats. playerStats6));
			
			blocking[0].setText("Blocking Score: "+TrackStats.blockingScore(TrackStats.playerStats6));
			blocking[1].setText("Blocking Percentage: "+TrackStats.blockingPercent(TrackStats.playerStats6));
		}
		
		//show stat results for player 7
		//to the understand the code, reference the summary for teamSum above as this is the same code but stored for a different player
		if(e.getSource() == playerSum[6]){
			
			playerName.setText("Name: "+PlayerEntry.playerEntries[6][0]);
			playerPosition.setText("Position: "+PlayerEntry.playerEntries[6][2]);
			
			serving[0].setText("Serving Attempts: "+TrackStats.servingAttempts(TrackStats.playerStats7));
			serving[1].setText("Serve Percentage: "+TrackStats.servePercent(TrackStats.playerStats7));
			serving[2].setText("Ace Percentage: "+TrackStats.acePercent(TrackStats.playerStats7));
			
			attacking[0].setText("Attack Attempts: "+TrackStats.attackAttempts(TrackStats.playerStats7));
			attacking[1].setText("Kill Percentage: "+TrackStats.killPercent(TrackStats.playerStats7));
			attacking[2].setText("Error Percentage: "+TrackStats.errorPercent(TrackStats.playerStats7));
			
			passing[0].setText("Pass Attempts: "+TrackStats.passingAttempts(TrackStats.playerStats7));
			passing[1].setText("Passing Score: "+TrackStats.passingScore(TrackStats.playerStats7));
			
			digging[0].setText("Digging Attempts: "+TrackStats.diggingAttempts(TrackStats.playerStats7));
			digging[1].setText("Digging Percentage: "+TrackStats.diggingPercent(TrackStats. playerStats7));
			
			blocking[0].setText("Blocking Score: "+TrackStats.blockingScore(TrackStats.playerStats7));
			blocking[1].setText("Blocking Percentage: "+TrackStats.blockingPercent(TrackStats.playerStats7));
		}
	}
}
