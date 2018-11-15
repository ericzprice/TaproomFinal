package understanding;

import java.time.LocalDate;
//Person super class.  super class of Person is Object


public class Person extends Object{
	
	String name;
	String email;
	String mobileNo;
	public Person(String name, String email, String mobileNo) {
		super();
		this.name = name;
		this.email = email;
		this.mobileNo = mobileNo;
	}
	
	

}
//sub class
class Employee extends Person
{
	
	
	int salary;
	LocalDate dateOfJoining;
	String designation;
	public Employee(String name, String email, String mobileNo,int salary,LocalDate dateOfJoining,String designation) {
		super(name, email, mobileNo);
		this.salary=salary;
		this.dateOfJoining=dateOfJoining;
		this.designation=designation;
	}
	
	
	
}
