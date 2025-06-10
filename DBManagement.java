package controller;

import java.sql.*;
import model.Student;
import view.Gui;

public class DBManagement {

	public static void registerIntoDB(Student s) throws SQLException {
		
		String url = "jdbc:MySQL://localhost:3306/";
		String dbname = "dbjava";
		String user = "root";
		String password = "root";
		
		Connection conn = DriverManager.getConnection(url+dbname, user, password);
		
		Statement stmt = conn.createStatement();
		
		String name = s.getName();
		int age = s.getAge();
		String sex = s.getSex();
		String degree = s.getLastDegree();
		
		String sqlcomm = "INSERT INTO student2 (name, age, sex, degree) VALUES ('" + name + "', " + age + ", '" + sex +"', '" + degree + "')";
				
		System.out.println(sqlcomm);		
		stmt.executeUpdate(sqlcomm);
	}
	
	public static Student [] readFromDB() throws SQLException {
		
		System.out.println("READING FROM DB");
		
		String url = "jdbc:MySQL://localhost:3306/";
		String db = "dbjava";
		String user = "root";
		String pass = "root";
		
		Connection conn = DriverManager.getConnection(url+db, user, pass);
		Statement stmt = conn.createStatement();
		
		String sqlcomm = "select * from student2";
		
		ResultSet rs = stmt.executeQuery(sqlcomm);
		
		Student[] sarr = new Student[10];
		
		int idx = 0;
		
		while(rs.next()) {
			int Id=rs.getInt("ID");
       	 	String name=rs.getString("name");
       	 	int age=rs.getInt("age");
       	 	String sex=rs.getString("sex");
       	 	String lastdegree=rs.getString("degree");
       	 
       	 	Student s1 = new Student(name, age, sex, lastdegree);
       	 
       	 	System.out.println(" Id : " + s1.toString());
       	 
       	 	sarr[idx] = new Student(name, age, sex, lastdegree);

       	 	idx++;
		}
		
		conn.close();
		
		return sarr;
		
	}
	
	public static void main(String [] args) {
		Gui g = new Gui();
		
		/*
		Student s = new Student("ajay", 22, "male", "bachelor");
		
		try {
			registerIntoDB(s);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
	}
}
