/*
 * Name: Joshua Chang
 * Description: Start Frame, what user will first see when using the game
 *              Title, Short description of application and start button
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LaunchStart implements ActionListener{

	JFrame start = new JFrame(); //start frame 
	JButton startButton = new JButton("Start"); //start button
	JLabel title = new JLabel("VolleyStats"); //title
	JLabel descript = new JLabel("A Program to Track the Perfromance of Athletes!"); //short description of application
	LaunchStart(){
		
		//image of the background
		ImageIcon bg = new ImageIcon("images/titlepage.png");
		Image img1 = bg.getImage();
		Image newimg1 = img1.getScaledInstance(680, 480, java.awt.Image.SCALE_SMOOTH ); //set specific size for court image  
		ImageIcon backg = new ImageIcon (newimg1);
		JLabel background = new JLabel(backg); //set background image as label

		//placement and style of components
		startButton.setBounds(540, 400, 75, 30);
		startButton.addActionListener(this); //needed in order to for an action to happen once a user presses the button
		title.setBounds(275,50,400,200);
		descript.setBounds(250,200,400,50);
		title.setFont(new Font("ComicSans", Font.BOLD, 60));
		descript.setFont(new Font("ComicSans", Font.BOLD, 15));
		background.setBounds(0, 0, 640, 480);
		
		//add components to frame
		start.add(startButton);
		start.add(title);
		start.add(descript);
		start.add(background);
		
		//Start frame setup
		start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		start.setTitle("Volleyball Statistics Application");
		start.setSize(640, 480);
		start.setVisible(true);
	}

	/*
	 * Description: called when an action is performed (button is pressed)
	 * pre: ActionEvent
	 * post:N/A
	 */
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == startButton){ //if the "Start" button is pressed
			//creating instance to show the Player Entry frame
			PlayerEntry playerEntry = new PlayerEntry();
			start.dispose(); //close Start frame once Player Entry frame is made
			//dialog box to show user how to navigate the next frame and what to do
			JOptionPane.showMessageDialog(null,"      Please input the names, numbers, and positions of the players. \n"
					+ "       All numbers must be entered. Enter the position abbreviations.\n "
					+ "LS (Leftside), RS (Rightside), MB (Middleblocker), S (Setter), LIB (Libero)", "Player Entry Instructions",JOptionPane.INFORMATION_MESSAGE);
		}		
	}
	
	public static void main(String[] args) {
		
		//creating instance to start the Start frame
		LaunchStart launchStart = new LaunchStart();
	}
}