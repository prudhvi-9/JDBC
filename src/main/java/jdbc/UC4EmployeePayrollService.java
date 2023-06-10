package jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UC4EmployeePayrollService {
	private IOService ioService;

	public enum IOService {
		FILE_IO, CONSOLE_IO, DB_IO, REST_IO
	}

	private List<UC4EmployeePayrollData> employeePayrollList;
	private UC4EmployeePayrollDBService employeePayrollDBService;

	public UC4EmployeePayrollService() {
		employeePayrollDBService = UC4EmployeePayrollDBService.getInstance();

	}

	public UC4EmployeePayrollService(List<UC4EmployeePayrollData> employeePayrollList) {
		this();
		this.employeePayrollList = employeePayrollList;
	}

	public static void main(String[] args) {
		ArrayList<UC4EmployeePayrollData> employeePayrollList = new ArrayList<>();
		UC4EmployeePayrollService employeePayrollService = new UC4EmployeePayrollService(employeePayrollList);
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
		employeePayrollList.add(new UC4EmployeePayrollData(id, name, salary));
	}

	public List<UC4EmployeePayrollData> readEmployeePayrollData(IOService ioService) {
		if (ioService.equals(IOService.DB_IO))
			this.employeePayrollList = employeePayrollDBService.readData();
		return this.employeePayrollList;
	}

	public List<UC4EmployeePayrollData> readEmployeePayrollDataRange(IOService ioService, LocalDate startDate,
			LocalDate endDate) {
		if (ioService.equals(IOService.DB_IO))
			return employeePayrollDBService.getEmployeePayrollForDataRange(startDate, endDate);
		return null;
	}

	public boolean checkEmployeePayrollInSyncWithDB(String name) {
		List<UC4EmployeePayrollData> employeePayrollDataList = employeePayrollDBService.getInstance()
				.getEmployeePayrollData(name);
		return employeePayrollDataList.get(0).equals(getEmployeePayrollData(name));
	}

	public void updateEmployeeSalary(String name, double salary) {
		int result = employeePayrollDBService.updateEmployeeData(name, salary);
		if (result == 0)
			return;
		UC4EmployeePayrollData employeePayrollData = this.getEmployeePayrollData(name);
		if (employeePayrollData != null)
			employeePayrollData.salary = salary;
	}

	private UC4EmployeePayrollData getEmployeePayrollData(String name) {
		return this.employeePayrollList.stream()
				.filter(employeePayrollDataItem -> employeePayrollDataItem.name.equals(name)).findFirst().orElse(null);
	}

	void writeEmployeeData(IOService ioService) {
		if (ioService.equals(IOService.CONSOLE_IO))
			System.out.println("\nWriting Employee Payroll Roaster to Console\n" + employeePayrollList);
		else if (ioService.equals(IOService.FILE_IO))
			new UC4EmployeePayrollFileIOService().writeData(employeePayrollList);
	}

	public void printData(IOService ioService) {
		if (ioService.equals(IOService.FILE_IO))
			new UC4EmployeePayrollFileIOService().printData();
	}

	public long countEntries(IOService ioService) {
		if (ioService.equals(IOService.FILE_IO))
			return new UC4EmployeePayrollFileIOService().countEntries();
		return 0;
	}

	public void readDataFromFile(IOService ioService) {
		if (ioService.equals(IOService.CONSOLE_IO)) {
			new UC4EmployeePayrollFileIOService().readDataFromFile();
		}
	}
}
