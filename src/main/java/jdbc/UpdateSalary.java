package jdbc;

import java.sql.*;

public class UpdateSalary {

	private static final String Update_Salary = "update employee_payroll set Salary=3000000 where Name='Terissa'; ";

	/*
	 * This method is created to update salary.
	 */
	public static void main(String[] argv) throws SQLException {
		UpdateSalary updateStatementExample = new UpdateSalary();
		updateStatementExample.updateRecord();
	}

	/*
	 * To update the salary.
	 */
	private void updateRecord() throws SQLException {
		System.out.println(Update_Salary);

		String url = "jdbc:mysql://localhost:3306/payroll_service";
		String username = "root";
		String password = "8897475488@Pp";
		try (Connection connection = DriverManager.getConnection(url, username, password)) {

			try (Statement statement = connection.createStatement()) {

				int result = statement.executeUpdate(Update_Salary);
				System.out.println("Number of records affected : " + result);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
