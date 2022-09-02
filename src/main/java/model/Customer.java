package model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

import javax.sound.midi.Soundbank;

import control.menu;

import java.util.Iterator;

public class Customer {
	private String name;
	private int point;
	private int vip;
	public Customer (String name, int point, int vip) {
		this.name = name;
		this.point = point;
		this.vip = vip;
	}
	public String getNameString () {return name;}
	public int getPoint () {return point;}
	public int getVip () {return vip;}
	public void payPoint (int point) {this.point -= point;}
	public static Customer getCustomer (String cname, String cpasswd) {
		Customer customer = null;
		String passwd = null;
		int point = 0, vip = 0;
		ResultSet hrs = null;
		DBCon dbc = new DBCon();
		dbc.connect();
		hrs = dbc.exec("SELECT * FROM customer where name=\"" + cname + "\";");
		try {
			hrs.next();
			passwd = hrs.getString("passwd");
			point = hrs.getInt("point");
			vip = hrs.getInt("vip");
		}
		catch (SQLException ex) {
			// TODO: handle exception
			System.out.println("SQLExpection: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VenderError: " + ex.getErrorCode());
		}
		System.out.println(point + " -> " + passwd);
		dbc.close();
		if (passwd != null && cpasswd.compareTo(passwd) == 0) {
			customer = new Customer(cname, point, vip);
			System.out.println("ok: " + customer);
		}
		return customer;
	}
	public String toString () {
		return name + ", " + point + ", " + vip;
	}
	public static HashMap<String, Customer> getAllCustomer () {
		HashMap<String, Customer> customers = new HashMap<>();
		Customer customer = null;
		String name= null;
		int point = 0, vip = 0;
		ResultSet hrs;
		DBCon dbc = new DBCon();
		dbc.connect();
		hrs = dbc.exec("SELECT * FROM customer");
		try {
			while (hrs.next()) {
				name = hrs.getString("name");
				point = hrs.getInt("point");
				vip = hrs.getInt("vip");
				customer = new Customer(name, point, vip);
				customers.put(name, customer);
			}
		}
		catch (SQLException ex) {
			// TODO: handle exception
			System.out.println("SQLExpection: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VenderError: " + ex.getErrorCode());
		}
		dbc.close();
		return customers;
	}
	public static void testCheckPasswd () {
		Customer customer = getCustomer("Tom", "1234");
		System.out.println(customer);
	}
	public static void testGetAllCustomer () {
		HashMap<String, Customer> customers = getAllCustomer();
		Set <String> keySet = customers.keySet();
		Iterator<String> it = keySet.iterator();
		while (it.hasNext()) {
			String name = it.next();
			Customer customer = customers.get(name);
			System.out.println(customer);
		}
	}
	public static void main (String [] argv) {
		testCheckPasswd();
		testGetAllCustomer();
	}
}
