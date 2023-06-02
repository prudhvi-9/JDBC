package jdbc;

import java.sql.*;
import java.util.Enumeration;

public class CreatePayroll {
	/*
	 * This method is created to check whether connection with database is established or not.
	 */
	public static void main(String[] args) {
		/*
		 * Connections
		 */
		String url = "jdbc:mysql://localhost:3306/employee_payroll";
		String username = "root";
		String password = "8897475488@Pp";
		Connection connect;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		listDrivers();
		try {
			Class.forName("Connecting to database:" + url);
			connect = DriverManager.getConnection(url, username, password);
			System.out.println("Connection is sucessful" + connect);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void listDrivers() {
		Enumeration<Driver> driverList = DriverManager.getDrivers();
		while (driverList.hasMoreElements()) {
			Driver driverClass = (Driver) driverList.nextElement();
			System.out.println(" " + driverClass.getClass().getName());
		}
	}

}
