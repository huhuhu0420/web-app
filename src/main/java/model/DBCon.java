package model;
import java.sql.*;
import java.util.*;

public class DBCon {
	private Connection con;
	private String jdbcName;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pstmt;
	public DBCon () {
		con = null;
		jdbcName = "org.mariadb.jdbc.Driver";
		stmt = null;
		pstmt = null;
		rs = null;
	}
	public void connect() {
		try {
			Class.forName(jdbcName);
			con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/ec2?"+"user=test&password=test");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ResultSet exec (String sql) {
		try {
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			rs = pstmt.executeQuery();
		}
		catch (SQLException ex) {
			// TODO: handle exception
			System.out.println("SQLExeption: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return rs;
	}
	public boolean check (String cname, String cpasswd) {
		Map<String, String> map = new HashMap<>();
		String name = null, passwd = null;
		ResultSet hrs;
		DBCon dbc = new DBCon();
		dbc.connect();
		hrs = dbc.exec("SELECT * FROM customer;");
		try {
			while (hrs.next()) {
				name = hrs.getString("name");
				passwd = hrs.getString("passwd");
				map.put(name, passwd);
			}
		}
		catch (SQLException ex) {
			// TODO: handle exception
			System.out.println("SQLExeption: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());	
		}
		System.out.println(passwd);
		dbc.close();
		System.out.println(map);
		passwd = map.get(cname);
		if (passwd != null && cpasswd.compareTo(passwd) == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	public void select (String sql) {
		try {
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("name"));
				System.out.println("," + rs.getString("point"));
			}
		}
		catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	public void close () {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	public static void test (String name, String passwd) {
		DBCon dbc = new DBCon();
		Boolean b = dbc.check(name, passwd);
		System.out.println(name + "/" + passwd + "->" + b);
	}
	public static void main (String [] argv) {
		test("Tom", "1234");
	}
}
