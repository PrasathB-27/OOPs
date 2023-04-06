package theatre;

public class Theatre {
	
	int row ;
	int column ;
	char seats[][];
	
	
//	public Theatre(int row,int column,char[][] seats) {
//		this.row = row;
//		this.column = column;
//		this.seats = seats;
//	}
	
	public void printAvailability() {
		
		System.out.println("---------- Seats Availablity ----------");
		System.out.println();
		
		for(int i=0;i<row;i++) {
			for(int j=0;j<column;j++) {
				System.out.print(seats[i][j]+" ");
			}
			System.out.println();
		}
		
	}
	
}
