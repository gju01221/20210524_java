package ex07_jdbc;

public class EmployeesDTO {
	private String employee_id;
	private String first_name;
	private int salary;
	
	public EmployeesDTO() {
		super();
	}
	public EmployeesDTO(String employee_id, String first_name, int salary) {
		super();
		this.employee_id = employee_id;
		this.first_name = first_name;
		this.salary = salary;
	}
	//게터,세터
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		return "EmployeesDTO [employee_id=" + employee_id + ", first_name=" + first_name + ", salary=" + salary + "]";
	}
	
	
	
	
	
}
