package food;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import javax.swing.JFrame;
 
public class Main {

	public static void main(String[] args) throws Exception{
		
		Scanner sc = new Scanner(System.in);
		
		int row = 10;
		int column = 10;
		
		char ch[][] = new char[row][column]; 
		
		ch[0][0] = '%';
		
		for(int i=0;i<row;i++) {
			for(int j=0;j<column;j++) {
			    if(i==0 && j==0 ) continue;
				else if(i==0) ch[i][j] = (char)(j+'0');
				else if(j==0) ch[i][j] = (char)(i+'0');
				else ch[i][j] = '-';
			}
		}
		System.out.println("---- Rat and Food ----");
		System.out.println();
		System.out.println("'o' - Rat, 'x' - Food, '-' - EmptyField.");
		System.out.println();
	    
	    File file = new File("notes.txt");
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(file));
	    
	    //Rat Position variables
		int xRatPos, yRatPos, xFoodPos, yFoodPos;
		xRatPos = br.read()-'0';
		yRatPos = br.read()-'0'; br.readLine();
	
		Rat rat = new Rat(xRatPos,yRatPos);
		
		//Food Position variables
		xFoodPos = br.read()-'0';
		yFoodPos = br.read()-'0'; br.readLine();
		
		Food food = new Food(xFoodPos,yFoodPos);
		
		String hindrance[] = br.readLine().split(",");
		ch[rat.xPos][rat.yPos] = 'o';
		ch[food.xPos][food.yPos] = 'x'; 
		
		for(String st:hindrance) {
			ch[st.charAt(0)-'0'][st.charAt(1)-'0'] = '|';
		}
		
		//JFrame code
		JFrame myJFrame = new JFrame();
	    myJFrame.setSize(200,200);

	    myJFrame.addKeyListener(new KeyAdapter() {
	      public void keyPressed(KeyEvent e) {
	        int keyCode = e.getKeyCode();
	        ch[rat.xPos][rat.yPos] = '-';
	        if (keyCode == KeyEvent.VK_UP) {
	        	if(rat.xPos<=1) { 
	        		System.out.println("You can't move UP.");
	        	}
	        	else{
	        		if(rat.restrict(hindrance,rat.xPos-1,rat.yPos)) System.out.println("You can't Pass the Hindrance."); 
	        		else { 
	        			ch[--rat.xPos][rat.yPos] = 'o';
			        	rat.won(ch, food);
			        	printField(ch,row,column);
	        		}
	        	} 
	        }
	        else if (keyCode == KeyEvent.VK_DOWN) {
	        	if(rat.xPos>=9) { 
	        		System.out.println("You can't move DOWN."); 
	        	}
	        	else {
		        	if(rat.restrict(hindrance,rat.xPos+1,rat.yPos)) System.out.println("You can't Pass the Hindrance."); 
		        	else {
		        		ch[++rat.xPos][rat.yPos] = 'o';
		        		rat.won(ch, food);
			        	printField(ch,row,column);
		        	}
	        	}
	        }
	        else if (keyCode == KeyEvent.VK_LEFT) {
	        	if(rat.yPos<=1) { 
	        		System.out.println("You can't move LEFT."); 
	        	}
	        	else {
		        	if(rat.restrict(hindrance,rat.xPos,rat.yPos-1)) System.out.println("You can't Pass the Hindrance."); 
		        	else{
			        	ch[rat.xPos][--rat.yPos] = 'o';
			        	rat.won(ch, food);
			        	printField(ch,row,column);
		        	}
	        	}
	        }
	        else if (keyCode == KeyEvent.VK_RIGHT) {
	        	if(rat.yPos>=9) { 
	        		System.out.println("You can't move RIGHT."); 
	        	}
	        	else {
		        	if(rat.restrict(hindrance,rat.xPos,rat.yPos+1)) System.out.println("You can't Pass the Hindrance."); 
		        	else {
			        	ch[rat.xPos][++rat.yPos] = 'o';
			        	rat.won(ch, food);
			        	printField(ch,row,column);
		        	}
	        	}
	        }
	      }
	    });
	    myJFrame.setVisible(true);
	    //JFrame code
	    
	    
	
		printField(ch,row,column);
		
		sc.close();
	}
	
	public static void printField(char[][] ch,int row,int column) {
		for(int i=0;i<row;i++) {
			for(int j=0;j<column;j++) {
			   System.out.print(ch[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
