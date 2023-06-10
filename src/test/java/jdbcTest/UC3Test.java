package jdbcTest;

import static jdbc.UC3EmployeePayrollService.IOService.DB_IO;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

import jdbc.UC3EmployeePayrollService;
import jdbc.UC3EmployeePayrollData;

public class UC3Test {
	@Test
	public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDB() {
		UC3EmployeePayrollService employeePayrollService = new UC3EmployeePayrollService();
		List<UC3EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(DB_IO);
		employeePayrollService.updateEmployeeSalary("Terisa", 3000000.00);
		boolean result;
		result = employeePayrollService.checkEmployeePayrollInSyncWithDB("Terisa");
		Assert.assertTrue(result);

	}

}
