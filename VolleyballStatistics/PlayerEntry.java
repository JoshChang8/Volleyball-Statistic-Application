import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PlayerEntry implements ActionListener{

	JFrame playerEntry = new JFrame();
	JLabel players = new JLabel("Player Name");
	JLabel numbers = new JLabel("#");
	JLabel positions = new JLabel("Position");
	JButton statButton = new JButton("Start Statistics");
	JTextArea [] playerNames = new JTextArea[7];
	JTextArea [] playerNumbers = new JTextArea[7];
	JTextArea [] playerPositions = new JTextArea[7];
	private String playerEntries [][] = new String [7][3];
	JButton submitEntryButton = new JButton("Submit Entries");
	JLabel confirm = new JLabel("The data has been submitted");
	
	{
	for (int x=0; x<playerNames.length; x++) 
		playerNames[x] = new JTextArea();
	
	for (int x=0; x<playerNumbers.length; x++) 
		playerNumbers[x] = new JTextArea();
	
	for (int x=0; x<playerPositions.length; x++) 
		playerPositions[x] = new JTextArea();
	}
	
	public String [][] getplayerEntries() {
		return playerEntries;
	}
	
	PlayerEntry(){
		
		for (int x=0; x<playerNames.length; x++) {
			playerNames[x].setBounds(50, 85+x+(40*x), 150, 15);
			playerEntry.add(playerNames[x]);
		}
		
		for (int x=0; x<playerNumbers.length; x++) {
			playerNumbers[x].setBounds(235, 85+x+(40*x), 50, 15);
			playerEntry.add(playerNumbers[x]);
		}
		
		for (int x=0; x<playerPositions.length; x++) {
			playerPositions[x].setBounds(325, 85+x+(40*x), 50, 15);
			playerEntry.add(playerPositions[x]);
		}
		
		//adding placements of components and action listeners to buttons
		statButton.setBounds(450,380,150,50);
		statButton.addActionListener(this);
		submitEntryButton.setBounds(160,380,125,30);
		submitEntryButton.addActionListener(this);
		players.setBounds(90, 50, 150, 15);
		numbers.setBounds(255, 50, 150, 15);
		positions.setBounds(325, 50, 150, 15);
		
		//add components to frame
		playerEntry.add(statButton);
		playerEntry.add(submitEntryButton);
		playerEntry.add(players);
		playerEntry.add(numbers);
		playerEntry.add(positions);
		
		//playerEntry frame setup
		playerEntry.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		playerEntry.setTitle("Player Entry");
		playerEntry.setSize(640, 480);
		playerEntry.setLayout(null);
		playerEntry.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == statButton){
			//creating instance to show the Statistics frame
			Statistics statistics = new Statistics();
			playerEntry.dispose(); //close frame once Instructions frame is made
		
		}		
		
		if(e.getSource() == submitEntryButton){
			
			for(int x=0; x<playerNames.length; x++) 
				playerEntries[x][0] = playerNames[x].getText();
			
			for(int x=0; x<playerNumbers.length; x++) 
				playerEntries[x][1] = playerNumbers[x].getText();
			
			for(int x=0; x<playerPositions.length; x++) 
				playerEntries[x][2] = playerPositions[x].getText();
			
			
			for(int row=0; row<playerEntries.length; row++) {
				for(int col=0; col<playerEntries[0].length; col++) {
					System.out.print(playerEntries[row][col]+ " ");
				}
				System.out.println();
			}
			
			confirm.setBounds(560,365,100,30);
			playerEntry.add(confirm);
		}	
	}
	
}