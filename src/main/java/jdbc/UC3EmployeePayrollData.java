package jdbc;

import java.time.LocalDate;

public class UC3EmployeePayrollData {

	public int id;
	public String name;
	public double salary;
	public LocalDate startDate;

	public UC3EmployeePayrollData(Integer id, String name, double salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	public UC3EmployeePayrollData(int id, String name, double salary, LocalDate startDate) {
		this(id, name, salary);
		this.startDate = startDate;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name='" + name + '\'' + ", salary=" + salary;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UC3EmployeePayrollData that = (UC3EmployeePayrollData) o;
		return id == that.id && Double.compare(that.salary, salary) == 0 && name.equals(that.name);
	}
}