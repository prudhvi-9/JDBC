package jdbcTest;

import java.util.List;
import static jdbc.UC4EmployeePayrollService.IOService.DB_IO;

import org.junit.Assert;
import org.junit.Test;

import jdbc.UC4EmployeePayrollData;
import jdbc.UC4EmployeePayrollService;

public class UC4Test {
	@Test
	public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDB() {
		UC4EmployeePayrollService employeePayrollService = new UC4EmployeePayrollService();
		List<UC4EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(DB_IO);
		employeePayrollService.updateEmployeeSalary("Charlie", 3000000.00);
		boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("Charlie");
		Assert.assertTrue(result);

	}
}
