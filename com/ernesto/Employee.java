public class Employee{
	private int id;
	private String firstName;
	private String lastName;
	private int salary;

	public Employee() {		
	}

	public Employee(String fName, String lName, int salary) {		
		this.firstName = fName;
		this.lastName = lName;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public String getFirstName(){
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getSalary(){
		return salary;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFirstName(String firstname){
		this.firstName = firstname;
	}

	public void setLastName(String lastname) {
		this.lastName = lastname;
	}

	public void setSalary(int salary){
		this.salary = salary;
	}

}