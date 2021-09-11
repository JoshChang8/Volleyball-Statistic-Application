import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Statistics implements ActionListener{
	
	JFrame statistics = new JFrame();
	JPanel court = new JPanel();
	JButton sumButton = new JButton("Summary");

	
	Statistics(){
		
		court.setBounds(10,10,640,480);
		court.setBackground(Color.BLACK);
		sumButton.setBounds(900,500,150,50);
		sumButton.addActionListener(this);
		
		//add components to frame
		statistics.add(sumButton);
		statistics.add(court);
		
		//statistics frame setup
		statistics.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		statistics.setTitle("Statistics");
		statistics.setSize(1080, 610);
		statistics.setLayout(null);
		statistics.setVisible(true);
		
	}

	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == sumButton){
			//creating instance to show the Statistics frame
			Summary summary = new Summary();
			statistics.dispose();
		}		
		
	}

}