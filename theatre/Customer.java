package theatre;
import java.util.*;
public class Customer {
	
	int numberOfSeats;
	String row;
	String seats;
	Scanner sc= new Scanner(System.in);
	public Customer(int numberOfSeats,String row) {
		this.numberOfSeats = numberOfSeats;
		this.row = row;
	}
	 
	public void book(char seats[][],int column,Object obj) {
		
		Theatre t = (Theatre)obj;
		
		int a;
		int num;
		a = this.row.charAt(0)-64; //A = 1
		char ch = this.row.charAt(0); // eg: A3  (i.e) A
		num = (this.row.charAt(1)-'0');//// eg: A3  (i.e) 3
		int num2 = a;
		
		int i = 1,f=0,f1=0,f2=0;
		while(i<=numberOfSeats){
				while(a>=10 || a<=0 || seats[num][a] == 'x') { 
					if(f2==1){a--; a--; f1=1; f2=0; continue;} 
					if(f==1) {a++; a++; f1 = 1; f=0; continue;} 
					t.printAvailability();
					System.out.println(i-1+" seat(s) have filled, there is no seat for the rest count, Enter some other seat to proceed.");
					String seatRow = sc.nextLine();
					a = seatRow.charAt(0)-64;
					ch = seatRow.charAt(0);
					num = seatRow.charAt(1)-'0';
					num2 = num;
					f=1;
				}
				if((f==1 && a<column && f1==1) || (num2 <(column/2) && numberOfSeats > num2) || (num2 >=(column/2) && numberOfSeats <= Math.abs(num2-column))) {
					seats[num][a++] = 'x';
					Seat s = new Seat(ch+""+a);
					s.status = "Booked";
					ch++;
					f2=1;
					if(a>=10 || a<=0) { f=0; }
				}
				else if((f2==1 && f==0 && a<column-1 && f1==1) || (num2 <(column/2) && numberOfSeats <= num2) || (num2 >=(column/2) && numberOfSeats > Math.abs(num2-column))) {
					seats[num][a--] = 'x';
					Seat s = new Seat(ch+""+a);
					s.status = "Booked";
					ch--;
					f=1;
				}
				i++;
		}
		
	}
	
}


//	num = (this.row.charAt(1)-'0')*10 + this.row.charAt(2)-'0';

//else {
//	seats[num][a--] = 'x';
//	Seat s = new Seat(ch+""+a);
//	s.status = "Booked";
//	ch--;
//}