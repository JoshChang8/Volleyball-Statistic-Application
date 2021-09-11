/*
 * Name: Joshua Chang
 * Description: PlayerEntry allows user to input the name, number, and position of each player
 *              The player's number will be shown in the statistics panel so each play can be sorted under each player
 *              The information collected will be shown in the Statistics and Summary
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PlayerEntry implements ActionListener{
	
	public static String playerEntries [][] = new String [7][3]; //array of player's name, number, and position
	JFrame playerEntry = new JFrame(); //player entry frame 
	JLabel players = new JLabel("Player Name"); //header for player name 
	JLabel numbers = new JLabel("#"); //header for player number 
	JLabel positions = new JLabel("Position"); //header for player position
	JButton statButton = new JButton("Start Statistics"); //button to next frame, Statistics
	JTextArea [] playerNames = new JTextArea[7]; //array of text areas for user to input the player's names
	JTextArea [] playerNumbers = new JTextArea[7]; //array of text areas for user to input the player's numbers
	JTextArea [] playerPositions = new JTextArea[7]; //array of player positions for user to input the players positions 
	JButton submitEntryButton = new JButton("Submit Entries"); //button to collect information in textAreas to playerEntries array
	JLabel confirm = new JLabel("The data has been submitted"); //confirmation message that user has submitted the information 
	
	{
	//initializing all the textAreas in the array
	for (int x=0; x<playerNames.length; x++) 
		playerNames[x] = new JTextArea();
	
	for (int x=0; x<playerNumbers.length; x++) 
		playerNumbers[x] = new JTextArea();
	
	for (int x=0; x<playerPositions.length; x++) 
		playerPositions[x] = new JTextArea();
	}
	
	PlayerEntry(){
		
		//adding playerNames textArea to frame and with specific placement 
		for (int x=0; x<playerNames.length; x++) {
			playerNames[x].setBounds(50, 85+x+(40*x), 150, 15); //to make sure vertical spacing between each name in entry textArea is even
			playerEntry.add(playerNames[x]);
		}
		
		//adding playerNumbers textArea to frame and with specific placement
		for (int x=0; x<playerNumbers.length; x++) {
			playerNumbers[x].setBounds(235, 85+x+(40*x), 50, 15); //to make sure vertical spacing between each number in entry textArea is even
			playerEntry.add(playerNumbers[x]);
		}
		
		//adding playerPositions textArea to frame and with specific placement
		for (int x=0; x<playerPositions.length; x++) {
			playerPositions[x].setBounds(325, 85+x+(40*x), 50, 15); //to make sure vertical spacing between each position in entry textArea is even
			playerEntry.add(playerPositions[x]);
		}
		
		//placement of components
		statButton.setBounds(220,400,125,30);
		submitEntryButton.setBounds(50,400,125,30);
		players.setBounds(90, 50, 150, 15);
		numbers.setBounds(255, 50, 150, 15);
		positions.setBounds(325, 50, 150, 15);
		confirm.setBounds(140,360,250,30);
		
		//action listeners in buttons allows an action to take place if the user were to press the button
		statButton.addActionListener(this);
		submitEntryButton.addActionListener(this);
		
		//add components to frame
		playerEntry.add(statButton);
		playerEntry.add(submitEntryButton);
		playerEntry.add(players);
		playerEntry.add(numbers);
		playerEntry.add(positions);
		playerEntry.add(confirm);
		confirm.setVisible(false); //will only appear if "Submit Entries" button is pressed 
		
		//playerEntry frame setup
		playerEntry.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		playerEntry.setTitle("Player Entry");
		playerEntry.setSize(460, 480);
		playerEntry.setLayout(null);
		playerEntry.setVisible(true);
	}
	
	/*
	 * Description: called when an action is performed (button is pressed)
	 * pre: ActionEvent
	 * post:N/A
	 */
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == statButton){ //if the "Start Statistics" button is pressed
			Statistics statistics = new Statistics(); //creating instance to show the Statistics frame
			Instructions instructions = new Instructions(); //creating instance to show the Instructions frame
			playerEntry.dispose(); //close playerEntry frame once Statistics frame is made
		
		}		
		
		if(e.getSource() == submitEntryButton){ //if the "Submit Entries" button is pressed 
			
			confirm.setVisible(true); //label will appear to let the user know that the information has been stored
			
			//each for loop will transfer data from the textAreas to the playerEntries array 
			for(int x=0; x<playerNames.length; x++) 
				//names user entered in textAreas will be transferred to playerEntries array 
				playerEntries[x][0] = playerNames[x].getText();
			
			for(int x=0; x<playerNumbers.length; x++) 
				//numbers user entered in textAreas will be transferred to playerEntries array
				playerEntries[x][1] = playerNumbers[x].getText();
			
			for(int x=0; x<playerPositions.length; x++) 
				//positions user entered in textAreas will be transferred to playerEntries array
				playerEntries[x][2] = playerPositions[x].getText();
		}	
	}
	
}