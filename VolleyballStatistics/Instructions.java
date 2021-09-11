import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Instructions implements ActionListener{

	JFrame instructions = new JFrame();
	JButton playerButton = new JButton("Player Entry");
	JLabel label = new JLabel("Hello");
	
	Instructions(){
		
		label.setBounds(0,0,250,150);
		playerButton.setBounds(900,500,150,50);
		playerButton.addActionListener(this);
		
		//add components to frame
		instructions.add(label);
		instructions.add(playerButton);
		
		//Instructions frame setup
		instructions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		instructions.setTitle("How To Use The Statistics Application");
		instructions.setSize(1080, 610);
		instructions.setLayout(null);
		instructions.setVisible(true);
	}


	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == playerButton){
			//creating instance to show the Player Entry frame
			PlayerEntry playerEntry = new PlayerEntry();
			instructions.dispose(); //close frame once Instructions frame is made
		}		
		
	}
}