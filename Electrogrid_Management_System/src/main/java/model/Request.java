package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Request {
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
	public String insertRequests(String service_Type, String desc, String city, String zip_code)
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
			String query = " insert into request (`request_id`,`service_type`,`description`,`city`,`zip_code`)"
								+ " values (?, ?, ?, ?, ?)";
				
			PreparedStatement preparedStmt = con.prepareStatement(query);
				
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, service_Type);
			preparedStmt.setString(3, desc);
			preparedStmt.setString(4, city);
			preparedStmt.setString(5, zip_code);
			
				
				
			// execute the statement
			preparedStmt.execute();
			con.close();
				
			output = "Inserted successfully";
		}
			
		catch (Exception e)
		{
			output = "Error while inserting the request.";
			System.err.println(e.getMessage());
		}
			
		return output;
	}
	
	//Read inquiry details
	public String readRequests()
	{
		JsonObject requests = new JsonObject();
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for reading."; 
			}
			
			String query = "select * from request";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// Prepare the json to be displayed
			JsonArray array = new JsonArray();
			
			// iterate through the rows in the result set
			while (rs.next()) {
				JsonObject innerInquiries= new JsonObject();
				
				innerInquiries.addProperty("request_id", rs.getInt(1));
				innerInquiries.addProperty("service_type", rs.getString(2));
				innerInquiries.addProperty("description", rs.getString(3));
				innerInquiries.addProperty("city", rs.getString(4));
				innerInquiries.addProperty("zip_code", rs.getString(5));
				array.add(innerInquiries);
				
			}
			requests.add("Request", array);
			
			con.close();
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
			requests.addProperty("status", "error");
			return requests.toString();
		}
		
		return requests.toString();
	}
	
	//Update inquiry details
	public String updateRequests(String request_id, String service_Type,String desc, String city, String zip_code) 
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
			String query = "UPDATE request SET service_type=?,description=?, city=?, zip_code=? WHERE request_id=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, service_Type);
			preparedStmt.setString(2, desc);
			preparedStmt.setInt(3, Integer.parseInt(request_id));
			preparedStmt.setString(4, city);
			preparedStmt.setString(5, zip_code);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Updated successfully";
		}
		
		catch (Exception e)
		{
			output = "Error while updating the request.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	//delete request details
	public String deleteRequests(String request_id)
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
			String query = "delete from request where request_id=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(request_id));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Deleted successfully";
		}
		
		catch (Exception e)
		{
			output = "Error while deleting the request.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

}

