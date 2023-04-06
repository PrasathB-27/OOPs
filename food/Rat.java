//$Id$
package food;

public class Rat {
	
//	String ratName;
	int xPos;
	int yPos;
	Food food;
	public Rat(int xPos,int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public void won(char ch[][],Object obj) {
		food = (Food)obj;
		if(ch[this.xPos][this.yPos]==ch[food.xPos][food.yPos]) {
			ch[this.xPos][this.yPos]='-'; 
			ch[food.xPos][food.yPos]='o';
			System.out.println("--- Game Over ---");
			System.out.println();
			Main.printField(ch, 10, 10);
	    	System.exit(0);
		}
	}
	
	public boolean restrict(String[] hindrance,int a,int b) {
		String st = a+""+b;
		for(String s: hindrance) {
			if(st.equals(s)) return true;
		}
		return false;
	}
}
