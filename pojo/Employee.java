package pojo;

public class Employee {  
	
	private String name;  
	private String id;  
	private double sal;  
	
	public Employee(String name,String id,double sal) {
		this.name = name;
		this.id = id;
		this.sal = sal;
	}
	
	public String getName() {  
	    return name;  
	}  
	
	public String getId() {  
	    return id;  
	} 
	
	public double getSal() {  
	    return sal;  
	} 	
	
	public void setName(String name) {  
	    this.name = name;  
	}  
	
	public void setId(String id) {  
	    this.id = id;  
	} 
	
	public void setSal(double sal) {  
	    this.sal = sal;  
	} 
	
}  