package jdbc;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UC3EmployeePayrollDBService {

	private PreparedStatement employeePayrollDataStatement;
	private static UC3EmployeePayrollDBService employeePayrollDBService;

	private UC3EmployeePayrollDBService() {
	}

	public static UC3EmployeePayrollDBService getInstance() {
		if (employeePayrollDBService == null)
			employeePayrollDBService = new UC3EmployeePayrollDBService();
		return employeePayrollDBService;
	}

	private Connection getConnection() throws SQLException {
		String jdbcURL = "jdbc:mysql://localhost:3306/emp_payroll_jdbc";
		String username = "root";
		String password = "8897475488@Pp";
		Connection connection;
		System.out.println("Connecting to database:" + jdbcURL);
		connection = DriverManager.getConnection(jdbcURL, username, password);
		System.out.println("Connection is successful!!!!!!" + connection);
		return connection;
	}

	public List<UC3EmployeePayrollData> readData() {
		String sql = "SELECT * FROM employee_payroll; ";
		List<UC3EmployeePayrollData> employeePayrollList = new ArrayList<>();
		try (Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			employeePayrollList = this.getEmployeePayrollData(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeePayrollList;
	}

	public List<UC3EmployeePayrollData> getEmployeePayrollData(String name) {
		List<UC3EmployeePayrollData> employeePayrollList = null;
		if (this.employeePayrollDataStatement == null)
			this.prepareStatementForEmployeeData();
		try {
			employeePayrollDataStatement.setString(1, name);
			ResultSet resultSet = employeePayrollDataStatement.executeQuery();
			employeePayrollList = this.getEmployeePayrollData(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeePayrollList;

	}

	private List<UC3EmployeePayrollData> getEmployeePayrollData(ResultSet resultSet) {
		List<UC3EmployeePayrollData> employeePayrollList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				double salary = resultSet.getDouble("salary");
				LocalDate startDate = resultSet.getDate("start").toLocalDate();
				employeePayrollList.add(new UC3EmployeePayrollData(id, name, salary, startDate));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeePayrollList;
	}

	private void prepareStatementForEmployeeData() {
		try {
			Connection connection = this.getConnection();
			String sql = "SELECT * FROM employee_payroll WHERE name = ?";
			employeePayrollDataStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int updateEmployeeData(String name, double salary) {
		return this.updateEmployeeDataUsingStatement(name, salary);
	}

	private int updateEmployeeDataUsingStatement(String name, double salary) {
		String sql = String.format("update employee_payroll set salary = %.2f where name = '%s';", salary, name);
		try (Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			return statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
