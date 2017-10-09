package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;




public class JdbcMain {
	public static void getCustomerData(){
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","1525");
			String query = "select * from customer";
			Statement stmt  = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				System.out.println(rs.getString("customerid"));
				System.out.println(rs.getString("customername"));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//prepared statement
	public static void insertCustomer(Customer customer) {
		try {
			Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","1525");
			String query = "insert into customer(customername,street,city,state,country,zipcode)values(?,?,?,?,?,?)";
			PreparedStatement pst  = conn.prepareStatement(query);
			pst.setString(1, customer.getCustomername());
			pst.setString(2, customer.getStreet());
			pst.setString(3, customer.getCity());
			pst.setString(4, customer.getState());
			pst.setString(5, customer.getCountry());
			pst.setString(6, customer.getZipcode());
			
			int noOfRowsEffected = pst.executeUpdate();
			System.out.println(noOfRowsEffected);
			pst.close();
			conn.close();
		} catch (SQLException se) {
	
			se.printStackTrace();
		}
	}
	//Statement.
	public static void insertCustomerStmt(Customer customer) {
		try {
			Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","1525");
			String query = "insert into customer(customername,street,city,state,country,zipcode)values('"+customer.getCustomername()+"','"+customer.getStreet()+"','"+customer.getCity()+"','"+customer.getState()+"','"+customer.getCountry()+"','"+customer.getZipcode()+"')";
			Statement st  = conn.prepareStatement(query);
			int noOfRowsEffected = st.executeUpdate(query);
			System.out.println(noOfRowsEffected);
			st.close();
			conn.close();
		} catch (SQLException se) {
	
			se.printStackTrace();
		}
	}
	
	
	public static  List<Customer> getCustomers(){
		List<Customer>customers = new ArrayList<>();
		try {
		
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","1525");
			String query = "select * from customer";
			Statement stmt  = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				Customer cust = new Customer();
				cust.setCity(rs.getString("city"));
				cust.setCustomername(rs.getString("customername"));
				cust.setCountry(rs.getString("country"));
				customers.add(cust);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers ;
		
	}
	
	
//	public static Customer getCustomerById(int id) {
//		
//		Customer Cust = null;
//		try {
//			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","");
//			String query ="select * from customer where customerid = ?";
//			PreparedStatement pstmt = conn.prepareStatement(query);
//			pstmt.setInt(1, id);
//			ResultSet  rs = pstmt.executeQuery(query);
//			while(rs.next()){
//				Customer cust = new Customer();
//				cust.setCity(rs.getString("city"));
//				cust.setCustomername(rs.getString("customername"));
//				cust.setCountry(rs.getString("country"));
//			}
//			rs.close();
//			pstmt.close();
//			conn.close();
//		} catch (Exception e) {
//			
//		}
//		return null;
//	}
	
	public static void main(String[] args) {
		//JdbcMain.getCustomerData();
		Customer customer = new Customer();
		customer.setCustomername("Harry");
		customer.setStreet("11th street");
		customer.setCity("florida");
		customer.setState("florida");
		customer.setCountry("usa");
		customer.setZipcode("67485");
//		insertCustomer (customer);
	
//		Customer customer1 = new Customer();
//		customer1.setCustomername("potter");
//		customer1.setStreet("12th street");
//		customer1.setCity("florida");
//		customer1.setState("florida");
//		customer1.setCountry("usa");
//		customer1.setZipcode("677345");
//		insertCustomer (customer1);
//		
//		Customer customer2 = new Customer();
//		customer2.setCustomername("dixon");
//		customer2.setStreet("13th street");
//		customer2.setCity("losvegas");
//		customer2.setState("LA");
//		customer2.setCountry("usa");
//		customer2.setZipcode("67045");
//		insertCustomerStmt (customer2);
//		
		
		List<Customer>customers=getCustomers();
		customers.forEach(cust  ->System.out.println(cust.getCustomername()));
	}
}
  