package model;
import java.sql.*;

public class CardDetails {
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

	
	
	
	
	public String readCardDetails()
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
	 output = "<table border='1'><tr><th>Card No</th>" 
	 +"<th>Expire Date</th><th>Expire Month</th>"
	 + "<th>Expire Year</th><th>Security Code</th>" 
	 + "<th>Update</th><th>Remove</th></tr>"; 
	 String query = "select * from carddetails"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String cardDetID = Integer.toString(rs.getInt("cardDetID")); 
	 String cardNo = rs.getString("cardNo"); 
	 String expDate = Integer.toString(rs.getInt("expDate"));
	 String expMonth = Integer.toString(rs.getInt("expMonth"));
	 String expYear = Integer.toString(rs.getInt("expYear"));
	 String securityCode = Integer.toString(rs.getInt("securityCode"));
	 // Add a row into the html table
	 output += "<tr><td>" + cardNo + "</td>"; 
	 output += "<td>" + expDate + "</td>"; 
	 output += "<td>" + expMonth + "</td>";
	 output += "<td>" + expYear + "</td>"; 
	 output += "<td>" + securityCode + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update'></td>"
	 + "<td>"
	 + "<form method='post' action='carddetails.jsp'> "
	 + "<input name='btnRemove' type='submit' value='Remove'>"
	 + "<input name='cardDetID' type='hidden'  value='" + cardDetID + "'>" + "</form></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	catch (Exception e) 
 { 
	 output = "Error while reading the carddetails."; 
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
	}
	
	
	
	
	public String insertCardDetails(String cNo, String eDate, String eMonth, String eYear,String secCode) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 // create a prepared statement
	 String query = " insert into carddetails (`cardDetID`,`cardNo`,`expDate`,`expMonth`,`expYear`,`securityCode`) values (?, ?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, cNo); 
	 preparedStmt.setInt(3, Integer.parseInt(eDate)); 
	 preparedStmt.setInt(4, Integer.parseInt(eMonth)); 
	 preparedStmt.setInt(5, Integer.parseInt(eYear)); 
	 preparedStmt.setInt(6, Integer.parseInt(secCode));   
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while inserting the item."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	
	
	public String updateCardDetails(String ID, String cNo, String eDate, String eMonth, String eYear,String secCode)
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "update carddetails set cardNo=?,expDate=?,expMonth=?,expYear=?,securityCode=? where cardDetID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values  
		 
		 preparedStmt.setString(1, cNo); 
		 preparedStmt.setInt(2, Integer.parseInt(eDate)); 
		 preparedStmt.setInt(3, Integer.parseInt(eMonth)); 
		 preparedStmt.setInt(4, Integer.parseInt(eYear)); 
		 preparedStmt.setInt(5, Integer.parseInt(secCode)); 
		 preparedStmt.setInt(6, Integer.parseInt(ID));
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while updating the details."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
	
	
	
	public String deleteCardDetails(String cardDetID) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from carddetails where cardDetID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(cardDetID)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while deleting the details."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
}
