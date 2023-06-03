package jdbcTest;

import org.junit.Assert;
import org.junit.Test;

import jdbc.EmployeePayrollService;
import jdbc.EmployeePayrollData;

import java.util.Arrays;
import static jdbc.EmployeePayrollService.IOService.DB_IO;
import static jdbc.EmployeePayrollService.IOService.FILE_IO;

public class EmployeePayrollServiceTest {

	@Test
	public void givenEmployees_WhenWrittenToFile_ShouldMatchEmployeeEntries() {
		EmployeePayrollData[] arrayOfEmps = { new EmployeePayrollData(1, "Sai Kiran", 100000.0),
				new EmployeePayrollData(2, "Sai Prasad", 200000.0),
				new EmployeePayrollData(3, "Shiva Charan", 300000.0), };
		
		EmployeePayrollService employeePayrollService;
		employeePayrollService = new EmployeePayrollService(Arrays.asList(arrayOfEmps));
		
		employeePayrollService.writeEmployeeData(FILE_IO);
		employeePayrollService.printData(FILE_IO);
		long entries = employeePayrollService.countEntries(FILE_IO);
		Assert.assertEquals(3, entries);
	}
	
}
