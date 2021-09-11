/*
 * Name: Joshua Chang
 * Description: Start Frame
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LaunchStart implements ActionListener{

	JFrame start = new JFrame();
	JButton startButton = new JButton("Start");
	JLabel descript = new JLabel("Welcome to the Voleyball Statistics App!");
	
	LaunchStart(){

		startButton.setBounds(500, 350, 100, 50);
		startButton.addActionListener(this);
		descript.setBounds(100,100,400,200);
		
		//add components to frame
		start.add(startButton);
		start.add(descript);
		
		//Start frame setup
		start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		start.setTitle("Volleyball Statistics Application");
		start.setSize(640, 480);
		start.setLayout(null);
		start.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == startButton){
			//creating instance to show the Instructions frame
			Instructions instructions = new Instructions();
			start.dispose(); //close frame once Instructions frame is made
		}		
	}
}