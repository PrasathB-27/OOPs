package theatre;
import java.util.*;
public class Main {
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int row = 10;
		int column = 10;
		char seats[][] = new char[row][column];
		
		
		Theatre t = new Theatre();
		
		seats[0][0] = '-';
		for(int i=0;i<row;i++) {
			for(int j=0;j<column;j++) {
			    if(i==0 && j==0 ) continue;
				else if(i==0) seats[i][j] = (char)(j+64);
				else if(j==0) seats[i][j] = (char)(i+'0');
				else seats[i][j] = 'o';
			}
		}
		
		t.column = column;
		t.row = row;
		t.seats = seats;
		t.printAvailability();
		
		boolean chk ;
		
		do {
			
			System.out.println("Enter number of seats you want to book: ");
			int numberOfSeats = sc.nextInt();
			sc.nextLine();
			System.out.println("Enter the seat you want to Book:");
			String seatsRow = sc.nextLine();
			while(restriction(seats,seatsRow)) {
				System.out.println("The seat you trying to book is not available");
				System.out.println("Enter some other seat to proceed:");
				seatsRow = sc.nextLine();
			}
			Customer c = new Customer(numberOfSeats,seatsRow);
			c.book(t.seats,column,t);
			t.printAvailability();
			System.out.println("Do you want to continue(true/false):");
			chk = sc.nextBoolean();
			
		} while(chk);
		sc.close();
	}
	
	public static boolean restriction(char[][] seats,String seatsRow) {
		
		int a;
		int num;
		a = seatsRow.charAt(0)-64;   //A = 1
		num = (seatsRow.charAt(1)-'0'); //// eg: A3  (i.e) 3
		
		if(seats[num][a] == 'x') {
			return true;
		}
		else {
			return false;
		}
	}
	

}
