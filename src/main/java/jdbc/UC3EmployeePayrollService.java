package jdbc;

import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

public class UC3EmployeePayrollService {

	private IOService ioService;

	public enum IOService {
		FILE_IO, CONSOLE_IO, DB_IO, REST_IO
	}

	private List<UC3EmployeePayrollData> employeePayrollList;
	private UC3EmployeePayrollDBService employeePayrollDBService;

	public UC3EmployeePayrollService() {
		employeePayrollDBService = UC3EmployeePayrollDBService.getInstance();
	}

	public UC3EmployeePayrollService(ArrayList<UC3EmployeePayrollData> employeePayrollList2) {
		this();
		this.employeePayrollList = employeePayrollList2;
	}

	public static void main(String[] args) {
		ArrayList<UC3EmployeePayrollData> employeePayrollList = new ArrayList<>();
		UC3EmployeePayrollService employeePayrollService = new UC3EmployeePayrollService(employeePayrollList);
		Scanner consoleInputReader = new Scanner(System.in);
		employeePayrollService.readEmployeePayrollData(consoleInputReader);
		employeePayrollService.writeEmployeeData(IOService.CONSOLE_IO);
	}

	private void readEmployeePayrollData(Scanner consoleInputReader) {
		System.out.println("Enter Employee ID: ");
		int id = consoleInputReader.nextInt();
		System.out.println("Enter Employee Name: ");
		String name = consoleInputReader.next();
		System.out.println("Enter Employee Salary: ");
		double salary = consoleInputReader.nextDouble();
		employeePayrollList.add(new UC3EmployeePayrollData(id, name, salary));
	}

	public List<UC3EmployeePayrollData> readEmployeePayrollData(IOService ioService) {
		if (ioService.equals(IOService.DB_IO))
			this.employeePayrollList = employeePayrollDBService.readData();
		return this.employeePayrollList;
	}

	public boolean checkEmployeePayrollInSyncWithDB(String name) {
		List<UC3EmployeePayrollData> employeePayrollDataList = employeePayrollDBService.getInstance()
				.getEmployeePayrollData(name);
		return employeePayrollDataList.get(0).equals(getEmployeePayrollData(name));
	}

	public void updateEmployeeSalary(String name, double salary) {
		int result = employeePayrollDBService.updateEmployeeData(name, salary);
		if (result == 0)
			return;
		UC3EmployeePayrollData employeePayrollData = this.getEmployeePayrollData(name);
		if (employeePayrollData != null)
			employeePayrollData.salary = salary;
	}

	private UC3EmployeePayrollData getEmployeePayrollData(String name) {
		return this.employeePayrollList.stream()
				.filter(employeePayrollDataItem -> employeePayrollDataItem.name.equals(name)).findFirst().orElse(null);
	}

	public void writeEmployeeData(IOService ioService) {
		if (ioService.equals(IOService.CONSOLE_IO))
			System.out.println("\nWriting Employee Payroll Roaster to Console\n" + employeePayrollList);
		else if (ioService.equals(IOService.FILE_IO))
			new UC3EmployeePayrollFileIOService().writeData(employeePayrollList);
	}

	public void printData(IOService ioService) {
		if (ioService.equals(IOService.FILE_IO))
			new UC3EmployeePayrollFileIOService().printData();
	}

	public long countEntries(IOService ioService) {
		if (ioService.equals(IOService.FILE_IO))
			return new UC3EmployeePayrollFileIOService().countEntries();
		return 0;
	}

}
