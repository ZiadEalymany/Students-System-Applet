package task.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		String jdbcurl = "jdbc:mysql://localhost:3306/student_task?useSSL=false";
		String user = "hbstudent";
		String pass = "hbstudent";
		try {
			System.out.println("Connecting to database: " + jdbcurl);
			Connection myconn = DriverManager.getConnection(jdbcurl, user, pass);
			System.out.println("Connection successfull!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
