package pojo;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
    	
    	ArrayList<Employee> employee = new ArrayList<Employee>();
    	
    	String value1, value2; double value3;
    	
    	File file = new File("/Users/prasath-pt6576/Downloads/EmployeeDetails.csv");
        CSVParser csvParser = CSVFormat.DEFAULT.parse(new FileReader(file));
     
        Scanner sc = new Scanner(System.in);
        
        List<CSVRecord> records = csvParser.getRecords();
        
        Connection connect = null;
    	
    	try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			connect = DriverManager.getConnection(  
			"jdbc:mysql://127.0.0.1:3306/demo","root","!Prasath27"); 
			
			Statement stmt=connect.createStatement();  
	
			for(CSVRecord x : records) {
				
	        	 value1 = valueOrDefault(x.get(0), "none");
	        	 value2 = valueOrDefault(x.get(1), "none");
	        	 value3 = Double.parseDouble(valueOrDefault(x.get(2), "0.0"));
	        	 
	        	 String sql = "insert into Employee (Name, Id, Salary)"+"values ('"+value1+"','"+value2+"','"+value3+"')"; 
	        	 stmt.executeUpdate(sql);	        	
//	        	  
	        }
			
			ResultSet rs=stmt.executeQuery("select * from Employee");
			
			while(rs.next()) {
				employee.add(new Employee(rs.getString(1),rs.getString(2),rs.getDouble(3)));
			}
			
			rs=stmt.executeQuery("select * from Employee");
			printTable(rs);
			
			boolean check;
			do{
				System.out.println("Enter to alter the table 1.Update 2.Delete 3.Print_Employee_Details 4.Exit ");
				int choice  = sc.nextInt();
				
				if(choice==1) {
					
					String value,primaryKey; 
			    	
			    	System.out.println("Since Id is the Primary key, Enter any Id to Identify record: (format: 'PT-1234')");
			    	sc.nextLine();
					primaryKey = sc.nextLine();
					
					System.out.println("Enter any column name for updation: (Name, Id, Salary)");
			    	String columnName = sc.nextLine();
			    	
					if(columnName.equals("Name")) System.out.println("Enter the value you want to update: (format: 'Prasath')");
					else if(columnName.equals("Id")) System.out.println("Enter the value you want to update: (format: 'PT-6544')");
					else System.out.println("Enter the value you want to update: (format: '24400.0')");
					
					value = sc.nextLine();
			    			    		
			    	update(value,primaryKey,columnName,stmt,sc);
			    	updateEmployee(value,primaryKey,employee,columnName);
			    	
			    	System.out.println("Updated records.\n");
			    	printEmployee(employee);
			    
				}else if(choice==2) {
					
					System.out.println("Since Id is the Primary key, Enter the Id of the record for Deletion:");
					sc.nextLine();
					String Id = sc.nextLine();
					delete(Id,stmt);
					deleteEmployee(employee,Id);
					
					System.out.println("Updated records after deletion.\n");
					rs=stmt.executeQuery("select * from Employee");
					printEmployee(employee);
					
				}else if(choice==3) {
					
					printEmployee(employee);
					
				}
				
				check = (choice==4) ? false : true;
				
			}while(check);
			
			String truncate = "TRUNCATE TABLE Employee";
			stmt.executeUpdate(truncate);
			 
		}
		catch(Exception e){ 
			System.out.println(e);
		}
    	finally {
    		
    		connect.close(); 
    		System.out.println("DB Disconnected.");
    		
    	}
         
    	printEmployee(employee);

    }


	public static String valueOrDefault(String value, String defaultValue) {
        return (value.length() == 0) ? defaultValue : value; 
    }
	
	
    
    public static void update(String value,String primaryKey, String columnName,Statement stmt,Scanner sc) throws SQLException {
    	
    	String updateSql;
		
		if(columnName.equals("Salary")) {
			
			updateSql = "UPDATE Employee SET Salary='"+Double.parseDouble(value)+"' WHERE Id='"+primaryKey+"'";
			
		}else if(columnName.equals("Id")){
			
			updateSql = "UPDATE Employee SET Id='"+value+"' WHERE Id='"+primaryKey+"'"; 
			
		}else {
			
			updateSql = "UPDATE Employee SET Name='"+value+"' WHERE Id='"+primaryKey+"'"; 
			
		}
		
		stmt.executeUpdate(updateSql);
    	
    }
    
    
    public static void delete(String Id,Statement stmt) throws SQLException {
    	
    	String deleteSql = "DELETE FROM Employee where Id='"+Id+"'";
    	stmt.executeUpdate(deleteSql);
    	
    }
    
    
    public static void printTable(ResultSet rs) throws SQLException {
    	while(rs.next()) {
			System.out.println(rs.getString(1)+", "+rs.getString(2)+", "+rs.getDouble(3));
		}
    }
    
    
    
    public static void updateEmployee(String value,String primaryKey,ArrayList<Employee> employee,String columnName) throws SQLException{	
    	for(Employee e:employee) {
			if(e.getId().equals(primaryKey)) {
				if(columnName.equals("Name")) {e.setName(value);}
				else if(columnName.equals("Id")) e.setId(value);
				else e.setSal(Double.parseDouble(value));
			}
        }
    }
    
    
    public static void deleteEmployee(ArrayList<Employee> employee,String primaryKey) {
    	
    	for(Employee e:employee) {
			if(e.getId().equals(primaryKey)) {
				employee.remove(e);
				break;
			}
        }
    }
    
    
    public static void printEmployee(ArrayList<Employee> employee) {
    	for(Employee e:employee) {
			System.out.println(e.getName()+", "+e.getId()+", "+e.getSal());
        }
    	System.out.println();
    }
}
