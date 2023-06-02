package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FindSumMinMaxByGender {

	public static void main(String[] args) {
		/*
		 * Connections
		 */
		String url = "jdbc:mysql://localhost:3306/payroll_service";
		String username = "root";
		String password = "8897475488@Pp";
		/*
		 * Fetching values from table
		 */
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			/*
			 * Fetching sum of salaries from table where gender is male.
			 */

			try (Statement statement1 = connection.createStatement()) {
				String strQuery = "SELECT SUM(Salary) FROM  employee_payroll where Gender='Male' GROUP BY Gender;";
				ResultSet resultset = statement1.executeQuery(strQuery);

				while (resultset.next()) {
					String SumOfSalaries = resultset.getString(1);
					System.out.println("Sum of Male salaries :" + SumOfSalaries);
				}
			}
			/*
			 * Fetching sum of salaries from table where gender is female.
			 */
			try (Statement statement1 = connection.createStatement()) {
				String strQuery = "SELECT SUM(Salary) FROM  employee_payroll where Gender='Female' GROUP BY Gender;";
				ResultSet resultset = statement1.executeQuery(strQuery);

				while (resultset.next()) {
					String SumOfSalaries = resultset.getString(1);
					System.out.println("Sum of Female salaries :" + SumOfSalaries);
				}
			}
			/*
			 * Fetching average of salaries from table where gender is male.
			 */
			try (Statement statement1 = connection.createStatement()) {
				String strQuery = "SELECT AVG(Salary) FROM  employee_payroll where Gender='Male' GROUP BY Gender;";
				ResultSet resultset = statement1.executeQuery(strQuery);

				while (resultset.next()) {
					String AverageOfSalaries = resultset.getString(1);
					System.out.println("Average of Male salaries :" + AverageOfSalaries);
				}
			}
			/*
			 * Fetching average of salaries from table where gender is female.
			 */
			try (Statement statement1 = connection.createStatement()) {
				String strQuery = "SELECT AVG(Salary) FROM  employee_payroll where Gender='Female' GROUP BY Gender;";
				ResultSet resultset = statement1.executeQuery(strQuery);

				while (resultset.next()) {
					String AverageOfSalaries = resultset.getString(1);
					System.out.println("Average of Female salaries :" + AverageOfSalaries);
				}
			}
			/*
			 * Fetching maximum of salaries from table where gender is male.
			 */
			try (Statement statement1 = connection.createStatement()) {
				String strQuery = "SELECT MAX(Salary) FROM  employee_payroll where Gender='Male' GROUP BY Gender;";
				ResultSet resultset = statement1.executeQuery(strQuery);

				while (resultset.next()) {
					String MaximumOfSalaries = resultset.getString(1);
					System.out.println("Maximum of Male salaries :" + MaximumOfSalaries);
				}
			}
			/*
			 * Fetching maximum of salaries from table where gender is female.
			 */
			try (Statement statement1 = connection.createStatement()) {
				String strQuery = "SELECT MAX(Salary) FROM  employee_payroll where Gender='Female' GROUP BY Gender;";
				ResultSet resultset = statement1.executeQuery(strQuery);

				while (resultset.next()) {
					String MaximumOfSalaries = resultset.getString(1);
					System.out.println("Maximum of Female salaries :" + MaximumOfSalaries);
				}
			}
			/*
			 * Fetching minimum of salaries from table where gender is male.
			 */
			try (Statement statement1 = connection.createStatement()) {
				String strQuery = "SELECT MIN(Salary) FROM  employee_payroll where Gender='Male' GROUP BY Gender;";
				ResultSet resultset = statement1.executeQuery(strQuery);

				while (resultset.next()) {
					String MinimumOfSalaries = resultset.getString(1);
					System.out.println("Minimum of Male salaries :" + MinimumOfSalaries);
				}
			}
			/*
			 * Fetching minimum of salaries from table where gender is female.
			 */
			try (Statement statement1 = connection.createStatement()) {
				String strQuery = "SELECT MIN(Salary) FROM  employee_payroll where Gender='Female' GROUP BY Gender;";
				ResultSet resultset = statement1.executeQuery(strQuery);

				while (resultset.next()) {
					String MinimumOfSalaries = resultset.getString(1);
					System.out.println("Minimum of Female salaries :" + MinimumOfSalaries);
				}
			}
			/*
			 * Fetching count of salaries from table where gender is male.
			 */
			try (Statement statement1 = connection.createStatement()) {
				String strQuery = "SELECT COUNT(Gender) FROM  employee_payroll where Gender='Male' GROUP BY Gender;";
				ResultSet resultset = statement1.executeQuery(strQuery);

				while (resultset.next()) {
					String CountOfSalaries = resultset.getString(1);
					System.out.println("Count of Male employees :" + CountOfSalaries);
				}
			}
			/*
			 * Fetching count of salaries from table where gender is female.
			 */
			try (Statement statement1 = connection.createStatement()) {
				String strQuery = "SELECT COUNT(Gender) FROM  employee_payroll where Gender='Female' GROUP BY Gender;";
				ResultSet resultset = statement1.executeQuery(strQuery);

				while (resultset.next()) {
					String CountOfSalaries = resultset.getString(1);
					System.out.println("Count of Female employees :" + CountOfSalaries);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
