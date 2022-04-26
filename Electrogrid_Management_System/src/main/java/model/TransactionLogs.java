package model;
import java.sql.*;
public class TransactionLogs {

	public Connection connect() 
	{ 
	 Connection con = null; 
	 
	 try 
	 { 
		 Class.forName("com.mysql.jdbc.Driver"); 
		 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_ed","root", ""); //For testing
		 System.out.print("Successfully connected"); 
	 } 
	 catch(Exception e) 
	 { 
		 System.out.print(" disconnected");  
	 } 

	 return con; 
	}
	
	
	public String readBill()
	{ 
	 String output = ""; 
	try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 { 
		 return "Error while connecting to the database for reading."; 
	 } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Power Usage</th>" 
	 +"<th>Amount</th>"
	 + "<th>Start Date</th><th>End Date</th>" 
	 + "<th>Update</th><th>Remove</th></tr>"; 
	 String query = "select * from bill"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String billID = Integer.toString(rs.getInt("billID")); 
	 String powerUsage = Integer.toString(rs.getInt("powerUsage"));
	 String amount = Double.toString(rs.getDouble("amount"));
	 String startDate = rs.getString("startDate");
	 String endDate = rs.getString("endDate");
	 // Add a row into the html table
	 output += "<td>" + powerUsage + "</td>"; 
	 output += "<td>" + amount + "</td>";
	 output += "<td>" + startDate + "</td>"; 
	 output += "<td>" + endDate + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' " 
	 + " type='button' value='Update'></td>"
	 + "<td><form method='post' action='bill.jsp'>"
	 + "<input name='btnRemove' " 
	 + " type='submit' value='Remove'>"
	 + "<input name='billID' type='hidden' " 
	 + " value='" + billID + "'>" + "</form></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	catch (Exception e) 
	 { 
	 output = "Error while reading the bill details."; 
	 System.err.println(e.getMessage()); 
	 } 
		return output; 
	}
	
	
	public String readInvoice()
	{ 
	 String output = ""; 
	try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 { 
	 return "Error while connecting to the database for reading."; 
	 } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Plant ID</th>" 
	 +"<th>Branch Name</th>"
	 + "<th>Genarated Units</th><th>Start Date</th><th>End Date</th>" 
	 + "<th>Update</th><th>Remove</th></tr>"; 
	 String query = "select * from invoice"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String invoiceID = Integer.toString(rs.getInt("invoiceID")); 
	 String plantID = rs.getString("plantID");
	 String branchName = rs.getString("branchName");
	 String genUnits = Integer.toString(rs.getInt("genUnits"));
	 String startDate = rs.getString("startDate");
	 String endDate = rs.getString("endDate");
	 // Add a row into the html table
	 output += "<td>" + plantID + "</td>"; 
	 output += "<td>" + branchName + "</td>";
	 output += "<td>" + genUnits + "</td>";
	 output += "<td>" + startDate + "</td>"; 
	 output += "<td>" + endDate + "</td>";

	 // buttons
	 output += "<td><input name='btnUpdate' " 
	 + " type='button' value='Update'></td>"
	 + "<td><form method='post' action='bill.jsp'>"
	 + "<input name='btnRemove' " 
	 + " type='submit' value='Remove'>"
	 + "<input name='invoiceID' type='hidden' " 
	 + " value='" + invoiceID + "'>" + "</form></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	catch (Exception e) 
	 { 
		 output = "Error while reading the invoice details."; 
		 System.err.println(e.getMessage()); 
	 } 
	return output; 
	}
	

	
}
