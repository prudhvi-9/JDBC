package jdbc;

import java.sql.*;

public class RetrieveTheData {
	/*
	 * This method is created to retrieve data from database.
	 */
	public static void main(String[] args) {
		/*
		 * Connections
		 */
		String url = "jdbc:mysql://localhost:3306/payroll_service";
		String username = "root";
		String password = "8897475488@Pp";

		try (Connection connection = DriverManager.getConnection(url, username, password)) {

			/*
			 * Fetching values from table
			 */
			try (Statement statement = connection.createStatement()) {
				String selectQuery = "select * from employee_payroll";
				ResultSet resultSet = statement.executeQuery(selectQuery);
				while (resultSet.next()) {
					System.out.print("ID:" + resultSet.getInt("Id"));
					System.out.print(", Name:" + resultSet.getString("Name"));
					System.out.print(", Salary:" + resultSet.getInt("Salary"));
					System.out.print(", StartDate:" + resultSet.getDate("StartDate"));
					System.out.print(", Gender:" + resultSet.getString("Gender"));
					System.out.print(", PhoneNumber:" + resultSet.getInt("PhoneNumber"));
					System.out.print(", Address:" + resultSet.getString("Address"));
					System.out.print(", Department:" + resultSet.getString("Department"));
					System.out.print(", BasicPay:" + resultSet.getInt("BasicPay"));
					System.out.print(", Deductions:" + resultSet.getInt("Deductions"));
					System.out.print(", Taxable_Pay:" + resultSet.getInt("Taxable_Pay"));
					System.out.print(", Income_Tax:" + resultSet.getInt("Income_Tax"));
					System.out.println(", Net_Pay:" + resultSet.getInt("Net_Pay"));

				}
				resultSet.close();
				System.out.println("\nValues fetched");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
