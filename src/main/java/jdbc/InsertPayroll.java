package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertPayroll {
	/*
	 * This method is created to create and insert the values.
	 */
	public static void main(String[] argv) throws SQLException {

		String url = "jdbc:mysql://localhost:3306/payroll_service";
		String username = "root";
		String password = "8897475488@Pp";

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			/*
			 * To create table .
			 */
			try (Statement statement = connection.createStatement()) {

				String sql = "CREATE TABLE employee_payroll (Id int Auto increment PrimaryKey ,Name varchar(20),Salary decimal(10,3),StartDate date,Gender varchar(20) ,PhoneNumber int,Address varchar(50),Department varchar(50),	BasicPay int ,Deductions int ,Taxable_Pay int,Income_Tax int ,Net_Pay int);";
				statement.executeUpdate(sql);
			}
			/*
			 * To inseret values .
			 */
			try (Statement statement = connection.createStatement()) {

				String sql = "INSERT INTO  employee_payroll VALUES('Ravi',30000,'2020-03-10','Male',897654567,'Hari colony','IT',25000, 3000,1000,1000,24000);";
				statement.executeUpdate(sql);
				sql = "INSERT INTO  employee_payroll VALUES(3,'Tiny',50000,'2020-01-10','Male',897654567,'Hari colony','IT',25000, 3000,1000,1000,24000);";
				statement.executeUpdate(sql);

			}
			/*
			 * To delete values .
			 */
			try (Statement statement = connection.createStatement()) {
				String sql = "DELETE FROM  employee_payroll where ID=3;";
				statement.executeUpdate(sql);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
