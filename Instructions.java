/*
 * Name: Joshua Chang
 * Description: Instructions frame that will show the user how to use the statistics portion of the game
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.BadLocationException;

public class Instructions{

	static JFrame instructions = new JFrame(); //instructions frame 
	JTextArea howToUseInfo  = new JTextArea(); //how to use description
	JTextArea serveInfo  = new JTextArea(); //serve description
	JTextArea attackInfo  = new JTextArea(); //attack description
	JTextArea passInfo = new JTextArea(); //pass description
	JTextArea blockInfo  = new JTextArea(); //block description
	JTextArea digInfo  = new JTextArea(); //dig description
	JLabel howToUse = new JLabel("How To Use"); //how to use header
	JLabel serve = new JLabel("Serve"); //serve header
	JLabel attack = new JLabel("Attack"); //attack header
	JLabel pass = new JLabel("Pass"); //pass header
	JLabel block = new JLabel("Block"); //block header
	JLabel dig = new JLabel("Dig"); //dig header
	
	//warning to user to not close information window, if so, the statistics frame will also close and you will have to start over 
	JLabel warning = new JLabel ("DO NOT CLOSE THIS WINDOW, KEEP OPEN. WINDOW WILL CLOSE ONCE YOU ARE DONE TRACKING STATISTICS");
	
	Instructions(){
		
		//placement of labels and textAreas
		warning.setBounds(100, 0, 700, 30);
		warning.setForeground(Color.RED); //to make text red
		howToUse.setBounds(380, 20, 250, 30);
		howToUseInfo.setBounds(50, 50, 800, 100);
		serve.setBounds(380, 145, 75, 30);
		serveInfo.setBounds(150, 170, 500, 60);
		attack.setBounds(380, 225, 75, 30);
		attackInfo.setBounds(130, 255, 540, 85);
		pass.setBounds(390, 340, 75, 30);
		passInfo.setBounds(170, 365, 460, 35);
		block.setBounds(385, 395, 75, 30);
		blockInfo.setBounds(180, 420, 435, 50);
		dig.setBounds(390, 465, 75, 30);
		digInfo.setBounds(250, 490, 300, 50);
		
		//set text to areaTexts
		howToUseInfo.setText("To track a statistic, you must first select the player you are tracking by selecting their number.\r\n"
				+ "Next, press the statistic option you would like to record for the player. \r\n"
				+ "For the attack and dig statistics, you can track the coordinates of where the player made the play to be later shown in the Summary frame. \r\n"
				+"You can do this by clicking the location on the volleyball court image and the coordinate should update below the image.\r\n"
				+ "It must be performed in this sequence: Player Number -> Coordinate -> Attack/Dig\r\n"
				+ "Failure to perform this sequence will result in the coordinates being incorrect (coordinates of the previous play)\r\n"
				+ "Once you successfully track a statistic, text will appear at the bottom of your screen to show you the statistic that you have just tracked.\r\n"
				+ "Lastly, once the statistic is tracked, you must press the “DESELECT” button to deselect all the buttons and then you can track another statistic.\r\n"
				+ "Once you are done with the game, press the “SUMMARY’ button to see the results. \r\n");
		
		serveInfo.setText("MISS - If the player does not make their serve\r\n"
				+ "SUCCESS - If the player makes their serve but the opposing team returns the ball\r\n"
				+ "ACE - If the player scores a point off the serve\r\n");
		
		attackInfo.setText("The MISS button does not require coordinates because the attack was not in the court\r\n"
				+ "KILL and RETURNED require coordinates\r\n"
				+ "MISS - If the attacker misses the hit\r\n"
				+ "RETURNED - If the attacker places the ball in the court but is dug by the opposing team\r\n"
				+ "KILL - If the attacker wins a point off the attack\r\n");
		
		passInfo.setText("Use the slider to score the player’s pass from 1 to 3 (3 being the best pass)\r\n"
				+ "Once you have the correct score, press the “PASS” button. \r\n");
		
		blockInfo.setText("0 - If attacker scores off the player’s block \r\n"
				+ "1 - If the block is able to touch the ball so the defense can dig the ball up\r\n"
				+ "2 - If the blocker is able to score a point off the block\r\n");
		
		digInfo.setText("Requires coordinates.\r\n"
				+ "YES - If the ball is successfully dug by the player\r\n"
				+ "NO -  If the ball is not dug by the player  \r\n");
		
		
		//call method to center JTextArea labels
		centerText(howToUseInfo);
		centerText(serveInfo);
		centerText(attackInfo);
		centerText(passInfo);
		centerText(blockInfo);
		centerText(digInfo);
		
		//add labels and textAreas to frame
		instructions.add(warning);
		instructions.add(howToUse);
		instructions.add(howToUseInfo);
		instructions.add(serve);
		instructions.add(serveInfo);
		instructions.add(attack);
		instructions.add(attackInfo);
		instructions.add(pass);
		instructions.add(passInfo);
		instructions.add(block);
		instructions.add(blockInfo);
		instructions.add(dig);
		instructions.add(digInfo);
		
	
		
		//Instructions frame setup
		instructions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		instructions.setTitle("Statistics Information");
		instructions.setSize(900, 610);
		instructions.setLayout(null);
		instructions.setVisible(true);
	}
	
	/*
	 * Description: center the text within the bounds of the array
	 * pre: textArea (the statistic descriptions)
	 * post: N/A
	 */
	public static void centerText (JTextArea ta){
	    BufferedImage fake1 = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D fake2 = fake1.createGraphics();
	    FontMetrics fm = fake2.getFontMetrics(ta.getFont());

	    int lines = ta.getLineCount();
	    ArrayList<String> list = new ArrayList<>();
	    try{
	        for (int i = 0; i < lines; i++){
	            int start = ta.getLineStartOffset(i);
	            int end = ta.getLineEndOffset(i);

	            String line = ta.getText(start, end - start).replace("\n","");
	            list.add (line.trim());
	        }
	    }
	    catch (BadLocationException e){
	        System.out.println(e);
	    }
	    alignLines (list, fm, ta);
	}

	/*
	 * Description: aligns the line of the textArea
	 * pre: arrayList, font, textArea
	 * post: N/A
	 */
	private static void alignLines (ArrayList<String> list, FontMetrics fm, JTextArea ta){
	    String leading = "      ";
	    int longest = -1;
	    for (String s : list){
	        if (fm.stringWidth(s) > longest)
	            longest = fm.stringWidth(s);
	    }
	    for (int n=0; n<list.size(); n++){
	        String s = list.get(n);
	        if (fm.stringWidth(s) >= longest)
	            continue;
	        while (fm.stringWidth(s) < longest)
	            s = ' '+s+' ';
	        list.set(n, s);
	    }
	    StringBuilder sb = new StringBuilder();
	    for (String s : list){
	        sb.append(leading).append(s).append('\n');
	    }
	    ta.setText (sb.toString());
	}
}
		
