package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ParticularDateRange {
	/*
	 * This method is created to find record from a particular date range.
	 */

	public static void main(String[] args) {
		/*
		 * Connections
		 */
		String url = "jdbc:mysql://localhost:3306/payroll_service";
		String username = "root";
		String password = "8897475488@Pp";

		try (Connection connection = DriverManager.getConnection(url, username, password)) {

			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM employee_payroll where StartDate BETWEEN ? AND ? ; ");
			/*
			 * 1 specifies the first parameter in the query.
			 */
			statement.setString(1, "2020-05-01");
			statement.setString(2, "2020-06-30");
			ResultSet resultSet = statement.executeQuery();

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

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}