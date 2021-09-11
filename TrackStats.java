/*
 * Name: Joshua Chang
 * Class Description: Class of methods that take all of the stats and break it down to make it easier to be analyzed 
 */
import java.util.ArrayList;

public class TrackStats {

	public static ArrayList <String> numList = new ArrayList<String>(); //arrayList to store the player numbers for statistics
	public static String [] listNum; //exact values of numList but in the form of an array
	public static ArrayList <String> typeList = new ArrayList<String>(); //arrayList to store the types for statistics
	public static String [] listType; //exact values of typeList but in the form of an array
	public static ArrayList <String> resultList = new ArrayList<String>(); //arrayList to store the results for statistics
	public static String [] listResult; //exact values of resultList but in the form of an array
	public static ArrayList <String> xList = new ArrayList<String>(); //arrayList to store the player x-coordinates for statistics
	public static String [] listX; //exact values of xList but in the form of an array
	public static ArrayList <String> yList = new ArrayList<String>(); //arrayList to store the player y-coordinates for statistics
	public static String [] listY; //exact values of yList but in the form of an array
	public static String statisticsArray [][]; //2D array that combines the 5 arrays of the statistics (num, type, result, xCoord, yCoord)
	
	public static String playerStats1 [][]; //the statistics for player1
	public static String playerStats2 [][]; //the statistics for player2
	public static String playerStats3 [][]; //the statistics for player3
	public static String playerStats4 [][]; //the statistics for player4
	public static String playerStats5 [][]; //the statistics for player5
	public static String playerStats6 [][]; //the statistics for player6
	public static String playerStats7 [][]; //the statistics for player7
	
	//arrayList of the arrayLists of numList, typeList, resultList, xList, yList
	public static ArrayList <ArrayList<String>> playerStats = new ArrayList <ArrayList<String>>();
	
	/*
	 * Description: converts an arrayList to an array
	 * pre: arrayList
	 * post: returns a String array
	 */
	public static String[] convertToArray (ArrayList arrlist) {
		String [] array = (String[]) arrlist.toArray(new String[0]);
		return array;
	}
	
	/*
	 * Description: trims the array to removes the tracked stats that were in excess
	 * 				whenever a statistic is tracked, 2 more of the same are added (do not know why, something to do with toggle buttons)
	 * pre: String array
	 * post: returns a String array 
	 */
	public static String [] removeMultiples(String arr[]) {
		
		ArrayList <String> newArr = new ArrayList<String>(); //use an arrayList so you can add indexes (you cannot do this for arrays)
		
		//by the end of this forLoop, the arrayList should be a third of the original array since you are eliminating the excess indexes
		for (int x = 0; x < arr.length; x += 3) { //for every 3 indexes in the array (which hold the same elements), the arrayList will store one 
			newArr.add(arr[x]); 
		}
		
		String [] array = convertToArray(newArr); //method is called to convert arrayList to array
		return array;
	}
	
	/*
	 * Description: combines all the individual 1D arrays to combine into a 2D array 
	 * pre: 1D string arrays of player numbers, stat types, stat results, x-coordinates, and y-coordinates
	 * post: 2D array of all the stats combined 
	 */
	public static String [][] convertTo2D (String[]num, String[]type, String[]result, String[]x, String[]y) {
		String [][] array = new String [num.length][5];
		
		for(int i=0; i<num.length; i++) //1st col of 2D array is player numbers
			array[i][0] = num[i];
		
		for(int i=0; i<type.length; i++) //2nd col is stat types 
			array[i][1] = type[i];
		
		for(int i=0; i<result.length; i++) //3rd col is result types
			array[i][2] = result[i];
		
		for(int i=0; i<x.length; i++) //4th col is x-coordinates
			array[i][3] = x[i];
		
		for(int i=0; i<y.length; i++) //5th col is y-coordinates
			array[i][4] = y[i];
		
		//for the programmer, you can see the elements of the 2D array which carries all the statistic info
		for(int row=0; row<array.length; row++) {
			for(int col=0; col<array[0].length; col++) {
				System.out.print(array[row][col]+"  ");
			}
			System.out.println();
		}
		return array;
	}
	
	/*
	 * Description: given a 2D array with the stats of all players, a 2D array will be returned specific to a player 
	 * pre: 2D String array and the player number 
	 * post: a 2D array that only has that players stats 
	 */
	public static String [][] specifyArray (String [][] arr, String playerNum) {
		int counter = 0;
		
		//forLoop will determine the number of stats of all the statistics belong to the specific player
		for(int x=0; x<arr.length; x++) {
			if(arr[x][0].equals(playerNum))
				counter++;
		}
		
		int [] tracker = new int [counter]; //array will hold the number of indexes needed to the array for the specific player
		String [][] playerArray = new String [counter][5]; //new array for specific player 
		int tally = 0; //acts as the index for the tracker array
		
		for(int x=0; x<arr.length; x++) {
			
			if(arr[x][0].equals(playerNum)) { //if the element of the index of the array matches the player number
				tracker[tally] = x; //tracker array will store the desired row number 
				tally++; //so the tracker array can store the desired row number in the next index
			}
		}
			
		for(int x=0; x<tracker.length; x++) {
			for(int i=0; i<playerArray[0].length; i++) { //the player will copy the entire row of the statistics array
				playerArray [x][i] = arr[tracker[x]][i]; //will fill out the entire row of the players statistic
			}
		}
		
		return playerArray;
	}
	
	/*
	 * Description: determines the average blocking score of the player or team
	 * pre: 2D array of statistics 
	 * post: String of the score with one decimal place ex) 1.2
	 */
	public static String blockingScore (String [][] arr) {
		double attempts = 0.0;
		double one = 0.0;
		double two = 0.0;
		String score;
		
		for(int x=0; x<arr.length; x++) {
			if(arr[x][1].equals("block")) //if the statistic type is a block
				attempts++;
			
			if(arr[x][2].equals("1")) //if the result is 1
				one++;
			
			if(arr[x][2].equals("2")) //if the result is 2
				two++;
		}
		
		//it is important that if the number of attempts is zero, blocking score would be N/A because no attempts were tracked
		//also, you cannot divide 0 by 0, so an error would also occur 
		if(attempts==0) 
			return "N/A";
		
		score = String.valueOf(((one+(2*two))/attempts));
		return score.substring(0,3); //returns score with one decimal place ex) 1.6
	}
	
	/*
	 * Description: determines the blocking percent of the player or team
	 * pre: 2D array of statstics
	 * post: String of the percentage of blocks
	 */
	public static String blockingPercent (String [][] arr) {
		int attempts = 0;
		int blocks = 0;
		
		for(int x=0; x<arr.length; x++) { 
			if(arr[x][1].equals("block")) //if stat type is block
				attempts++;
	
			if(arr[x][2].equals("2")) //if stat result is 2
				blocks++;
		}
		
		//it is important that if the number of attempts is zero, blocking percent would be N/A because no attempts were tracked
		//also, you cannot divide 0 by 0, so an error would also occur 
		if(attempts==0)
			return "N/A";
		
		return String.valueOf((int)((blocks/attempts)*100))+"%";
	}
	
	/*
	 * Description: returns the amount of digs attempted
	 * pre: 2D array of statistics
	 * post: integer number of attempts
	 */
	public static int diggingAttempts (String [][] arr) {
		int attempts = 0;
		
		for(int x=0; x<arr.length; x++) {
			if(arr[x][1].equals("dig")) //if the stat type is a dig
				attempts++;
		}
		
		return attempts;
	}
	
	/*
	 * Description: calculates the digging percentage
	 * pre: 2D statistics array
	 * post: String of the digging percentage
	 */
	public static String diggingPercent (String [][] arr) {
		int attempts = 0;
		int digs = 0;
		
		for(int x=0; x<arr.length; x++) {
			if(arr[x][1].equals("dig")) { //if the stat type is a dig
				attempts++;
				if(arr[x][2].equals("yes")) //if the stat result is a successful dig
					digs++;
			}
		}
		
		//it is important that if the number of attempts is zero, digging percent would be N/A because no attempts were tracked
		//also, you cannot divide 0 by 0, so an error would also occur 
		if(attempts==0)
			return "N/A";
		
		return String.valueOf((int)((digs/attempts)*100))+"%";
	}
	
	/*
	 * Description: calculates the number of passing attempts 
	 * pre: 2D array of the statistics
	 * post: integer of the number of passing attempts
	 */
	public static int passingAttempts (String [][] arr) {
		int attempts = 0;
		
		for(int x=0; x<arr.length; x++) {
			if(arr[x][1].equals("pass")) //if stat type is a pass
				attempts++;
		}
		
		return attempts;
	}
	
	/*
	 * Description: calculates the average passing score
	 * pre: 2D array of the statistics
	 * post: String of the average passing score
	 */
	public static String passingScore (String [][] arr) {
		double attempts = 0.0;
		double totalScore = 0.0;
		String score;
		
		for(int x=0; x<arr.length; x++) {
			if(arr[x][1].equals("pass")) { //if stat type is a pass
				attempts++;
				totalScore = totalScore + Double.parseDouble(arr[x][2]); //add the score
			}
		}
		
		//it is important that if the number of attempts is zero, passing score would be N/A because no attempts were tracked
		//also, you cannot divide 0 by 0, so an error would also occur 
		if(attempts==0)
			return "N/A";
		
		score = String.valueOf(totalScore/attempts);
		return score.substring(0,3); //returns score with one decimal place ex) 2.6
	}
	
	/*
	 * Description: calculates the number of serving attempts 
	 * pre: 2D array of statistics 
	 * post: integer of the number of serving attempts
	 */
	public static int servingAttempts (String [][] arr) {
		int attempts = 0;
		
		for(int x=0; x<arr.length; x++) {
			if(arr[x][1].equals("serve")) //if the stat type is a serve
				attempts++;
		}
		
		return attempts;
	}
	
	/*
	 * Description: calculates the percentage of the made serves
	 * pre: 2D array of statistics 
	 * post: String of the serving percentage 
	 */
	public static String servePercent (String [][] arr) {
		double attempts = 0.0;
		double success = 0.0;
		
		for(int x=0; x<arr.length; x++) {
			if(arr[x][1].equals("serve")) { //if the stat type is a serve
				attempts++;
				if(arr[x][2].equals("success")|| arr[x][2].equals("ace")) //if the result is a success or ace
					success++;
			}
		}
		
		//it is important that if the number of attempts is zero, serving percent would be N/A because no attempts were tracked
		//also, you cannot divide 0 by 0, so an error would also occur 
		if(attempts==0)
			return "N/A";
		
		return String.valueOf((int)((success/attempts)*100))+"%";
	}
	
	/*
	 * Description: calculates the percentage of service aces made
	 * pre: 2D statistics array
	 * post: String of the ace percentage
	 */
	public static String acePercent (String [][] arr) {
		double attempts = 0.0;
		double aces = 0.0;
		
		for(int x=0; x<arr.length; x++) {
			if(arr[x][1].equals("serve")) { //if the stat type is a serve
				attempts++;
				if(arr[x][2].equals("ace")) //if the stat result is an ace
					aces++;
			}
		}
		
		//it is important that if the number of attempts is zero, ace percentage would be N/A because no attempts were tracked
		//also, you cannot divide 0 by 0, so an error would also occur 
		if(attempts==0)
			return "N/A";
		
		return String.valueOf((int)((aces/attempts)*100))+"%";
	}
	
	/*
	 * Description: calculates the number of attack attempts
	 * pre: 2D array of statistics
	 * post: integer value of attack attempts 
	 */
	public static int attackAttempts (String [][] arr) {
		int attempts = 0;
		
		for(int x=0; x<arr.length; x++) {
			if(arr[x][1].equals("attack")) //if the stat type is an attack
				attempts++;
		}
		
		return attempts;
	}
	
	/*
	 * Description: calculates the kill percentage of attacks 
	 * pre: 2D statistics array
	 * post: String value of the kill percentage of attacks
	 */
	public static String killPercent (String [][] arr) {
		double attempts = 0.0;
		double kills = 0.0;
		
		for(int x=0; x<arr.length; x++) {
			if(arr[x][1].equals("attack")) { //if the stat type is an attack
				attempts++;
				if(arr[x][2].equals("kill")) //if the stat result is a kill
					kills++;
			}
		}
		
		//it is important that if the number of attempts is zero, blocking score would be N/A because no attempts were tracked
		//also, you cannot divide 0 by 0, so an error would also occur 
		if(attempts==0)
			return "N/A";
		
		return String.valueOf((int)((kills/attempts)*100))+"%";	
	}
	
	/*
	 * Description: calculate the error percentage of attacks
	 * pre: 2D array of statistics
	 * post: String value of the error percentage
	 */
	public static String errorPercent (String [][] arr) {
		double attempts = 0.0;
		double errors = 0.0;
		
		for(int x=0; x<arr.length; x++) {
			if(arr[x][1].equals("attack")) { //if the stat type is an attack
				attempts++;
				if(arr[x][2].equals("error")) //if the stat result is an error
					errors++;
			}
		}
		
		//it is important that if the number of attempts is zero, error percentage would be N/A because no attempts were tracked
		//also, you cannot divide 0 by 0, so an error would also occur 
		if(attempts==0)
			return "N/A";
		
		return String.valueOf((int)((errors/attempts)*100))+"%";	
	}	
}