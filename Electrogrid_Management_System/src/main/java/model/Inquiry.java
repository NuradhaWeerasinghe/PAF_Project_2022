package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Inquiry {
	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
			
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/service_management_db", "root", "");
		}
			
		catch (Exception e)
		{
			e.printStackTrace();
		}
			
		return con;
	}
	
	//Insert inquiry to DB table
	public String insertInquiries(String inquiry_Type, String customer_Type, String desc, String branch_Code, String branch_Name, String Address)
	{
		String output = "";
		try 
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for inserting."; 
			}
			
			// create a prepared statement
			String query = " insert into inquiry (`inquiry_id`,`inquiry_type`,`customer_type`,`description`,`branch_code`, `branch_name`, `address`)"
							+ " values (?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, inquiry_Type);
			preparedStmt.setString(3, customer_Type);
			preparedStmt.setString(4, desc);
			preparedStmt.setString(5, branch_Code);
			preparedStmt.setString(6, branch_Name);
			preparedStmt.setString(7, Address);
			
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Inserted successfully";
		}
		
		catch (Exception e)
		{
			output = "Error while inserting the inquiry.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	//Read inquiry details
		public String readInquiries()
		{
			JsonObject inquiries = new JsonObject();
			
			try
			{
				Connection con = connect();
				
				if (con == null)
				{
					return "Error while connecting to the database for reading."; 
				}
				
				String query = "select * from inquiry";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				// Prepare the json to be displayed
				JsonArray array = new JsonArray();
				
				// iterate through the rows in the result set
				while (rs.next()) {
					JsonObject innerInquiries= new JsonObject();
					
					innerInquiries.addProperty("inquiry_id", rs.getInt(1));
					innerInquiries.addProperty("inquiry_type", rs.getString(2));
					innerInquiries.addProperty("customer_type", rs.getString(3));
					innerInquiries.addProperty("description", rs.getString(4));
					innerInquiries.addProperty("branch_code", rs.getString(5));
					innerInquiries.addProperty("branch_name", rs.getString(6));
					innerInquiries.addProperty("address", rs.getString(7));
					array.add(innerInquiries);
					
				}
				inquiries.add("inquiry", array);
				
				con.close();
			}
			
			catch (Exception e)
			{
				e.printStackTrace();
				inquiries.addProperty("status", "error");
				return inquiries.toString();
			}
			
			return inquiries.toString();
		}
		
		//Update inquiry details
		public String updateInquiries(String inquiry_id, String inquiry_Type, String customer_Type, String desc, String branch_Code, String branch_Name, String Address) 
		{
			String output = "";
			
			try
			{
				Connection con = connect();
				
				if (con == null)
				{
					return "Error while connecting to the database for updating."; 
				}
				
				// create a prepared statement
				String query = "UPDATE inquiry SET inquiry_type=?,customer_type=?,description=?,branch_code=?, branch_name=?, address=? WHERE inquiry_id=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setString(1, inquiry_Type);
				preparedStmt.setString(2, customer_Type);
				preparedStmt.setString(3, desc);
				preparedStmt.setString(4, branch_Code);
				preparedStmt.setString(5, branch_Name);
				preparedStmt.setString(6, Address);
				preparedStmt.setInt(7, Integer.parseInt(inquiry_id));
				
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				output = "Updated successfully";
			}
			
			catch (Exception e)
			{
				output = "Error while updating the inquiry.";
				System.err.println(e.getMessage());
			}
			
			return output;
		}
		
		//Delete inquiry details
		public String deleteInquiries(String inquiry_id)
		{
			String output = "";
			
			try
			{
				Connection con = connect();
				
				if (con == null)
				{
					return "Error while connecting to the database for deleting."; 
				}
				
				// create a prepared statement
				String query = "delete from inquiry where inquiry_id=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(inquiry_id));
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				output = "Deleted successfully";
			}
			
			catch (Exception e)
			{
				output = "Error while deleting the inquiry.";
				System.err.println(e.getMessage());
			}
			
			return output;
		}
	
}

