package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class user {
	//A common method to connect to the DB
		private Connection connect()
		{
			Connection con = null;
					
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user_management_db", "root", "");
			}
					
			catch (Exception e)
			{
				e.printStackTrace();
			}
					
			return con;
		}
		
		//Insert consumer dtails to DB table
		public String insertConsumer(String first_name, String last_name, String email, String username, String password, int telephone_no )
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
				String query = " insert into consumer (`consumer_id`,`first_name`,`last_name`,`email`,`username`, `password`, `telephone_no`)"
									+ " values (?, ?, ?, ?, ?, ?, ?)";
					
				PreparedStatement preparedStmt = con.prepareStatement(query);
					
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, first_name);
				preparedStmt.setString(3, last_name);
				preparedStmt.setString(4, email);
				preparedStmt.setString(5, username);
				preparedStmt.setString(6, password);
				preparedStmt.setInt(7, telephone_no);
					
				// execute the statement
				preparedStmt.execute();
				con.close();
					
				output = "Inserted successfully";
			}
				
			catch (Exception e)
			{
				output = "Error while inserting the consumer.";
				System.err.println(e.getMessage());
			}
				
			return output;
		}
		
		//Read consumer details
		public String readConsumer()
		{
			JsonObject consumers= new JsonObject();
				
			try
			{
				Connection con = connect();
					
				if (con == null)
				{
					return "Error while connecting to the database for reading."; 
				}
					
				String query = "select * from consumer";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
					
				// Prepare the json to be displayed
				JsonArray array = new JsonArray();
					
				// iterate through the rows in the result set
				while (rs.next()) {
					JsonObject innerComsumers= new JsonObject();
						
					innerComsumers.addProperty("consumer_id", rs.getInt(1));
					innerComsumers.addProperty("first_name", rs.getString(2));
					innerComsumers.addProperty("last_name", rs.getString(3));
					innerComsumers.addProperty("email", rs.getString(4));
					innerComsumers.addProperty("username", rs.getString(5));
					innerComsumers.addProperty("password", rs.getString(6));
					innerComsumers.addProperty("telephone_no", rs.getInt(7));
					array.add(innerComsumers);
						
				}
				consumers.add("consumer", array);
					
				con.close();
			}
				
			catch (Exception e)
			{
				e.printStackTrace();
				consumers.addProperty("status", "error");
				return consumers.toString();
			}
				
			return consumers.toString();
		}
		
		//Update consumer details
		public String updateConsumer(String consumer_id, String first_name, String last_name, String email, String username, String password, String telephone_no) 
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
				String query = "UPDATE consumer SET first_name=?,last_name=?,email=?,username=?,password=?, telephone_no=? WHERE consumer_id=?";
					
				PreparedStatement preparedStmt = con.prepareStatement(query);
					
				// binding values
				preparedStmt.setString(1, first_name);
				preparedStmt.setString(2, last_name);
				preparedStmt.setString(3, email);
				preparedStmt.setString(4, username);
				preparedStmt.setString(5, password);
				preparedStmt.setString(6, telephone_no);
				preparedStmt.setInt(7, Integer.parseInt(consumer_id));
					
				// execute the statement
				preparedStmt.execute();
				con.close();
					
				output = "Updated successfully";
			}
				
			catch (Exception e)
			{
				output = "Error while updating the consumer.";
				System.err.println(e.getMessage());
			}
				
			return output;
		}
		//Delete consumer details
		public String deleteConsumer(String consumer_id)
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
				String query = "delete from consumer where consumer_id=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(consumer_id));
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				output = "Deleted successfully";
			}
			
			catch (Exception e)
			{
				output = "Error while deleting the consumer.";
				System.err.println(e.getMessage());
			}
			
			return output;
		}
		//Insert Producer details
		
		public String insertProducer(String plantId, String projectId, String firstName, String lastName, String email,String userName, int telephoneNo,String password)
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
				String query = " insert into producer (`producerId`,`plantId`,`projectId`,`firstName`,`lastName`, `email`, `userName`,`telephoneNo`,`password`)"
									+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
					
				PreparedStatement preparedStmt = con.prepareStatement(query);
					
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, plantId);
				preparedStmt.setString(3, projectId);
				preparedStmt.setString(4, firstName);
				preparedStmt.setString(5, lastName);
				preparedStmt.setString(6, email);
				preparedStmt.setString(7, userName);
				preparedStmt.setInt(8, telephoneNo);
				preparedStmt.setString(9, password);
					
				// execute the statement
				preparedStmt.execute();
				con.close();
					
				output = "Inserted successfully";
			}
				
			catch (Exception e)
			{
				output = "Error while inserting the consumer.";
				System.err.println(e.getMessage());
			}
				
			return output;
		}
		
		//Read producer details
		
		public String readProducer()
		{
			JsonObject producers= new JsonObject();
				
			try
			{
				Connection con = connect();
					
				if (con == null)
				{
					return "Error while connecting to the database for reading."; 
				}
					
				String query = "select * from producer";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
					
				// Prepare the json to be displayed
				JsonArray array = new JsonArray();
					
				// iterate through the rows in the result set
				while (rs.next()) {
					JsonObject innerProducers= new JsonObject();
					//producerId`,`plantId`,`projectId`,`firstName`,`lastName`, `email`, `userName`,`telephoneNo`,`password`	
					innerProducers.addProperty("producerId", rs.getInt(1));
					innerProducers.addProperty("plantId", rs.getString(2));
					innerProducers.addProperty("projectId", rs.getString(3));
					innerProducers.addProperty("firstName", rs.getString(4));
					innerProducers.addProperty("lastName", rs.getString(5));
					innerProducers.addProperty("email", rs.getString(6));
					innerProducers.addProperty("userName", rs.getString(7));
					innerProducers.addProperty("telephoneNo", rs.getInt(8));
					innerProducers.addProperty("password", rs.getString(9));
					array.add(innerProducers);
						
				}
				producers.add("producer", array);
					
				con.close();
			}
				
			catch (Exception e)
			{
				e.printStackTrace();
				producers.addProperty("status", "error");
				return producers.toString();
			}
				
			return producers.toString();
		}
		//Update producer details
		public String updateProducer(String producerId, String plantId, String projectId, String firstName, String lastName, String email, String userName,String telephoneNo,String password)
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
				String query = "UPDATE producer SET plantId=?,projectId=?,firstName=?,lastName=?,email=?,userName=?,telephoneNo=?,password=? WHERE producerId=?";
					
				PreparedStatement preparedStmt = con.prepareStatement(query);
					
				// binding values
				preparedStmt.setString(1, plantId);
				preparedStmt.setString(2, projectId);
				preparedStmt.setString(3, firstName);
				preparedStmt.setString(4, lastName);
				preparedStmt.setString(5, email);
				preparedStmt.setString(6, userName);
				preparedStmt.setString(7, telephoneNo);
				preparedStmt.setString(8, password);
				preparedStmt.setInt(9, Integer.parseInt(producerId));
					
				// execute the statement
				preparedStmt.execute();
				con.close();
					
				output = "Updated successfully";
			}
				
			catch (Exception e)
			{
				output = "Error while updating the producer.";
				System.err.println(e.getMessage());
			}
				
			return output;
		}
		//Delete producer details
		public String deleteProducer(String producerId)
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
				String query = "delete from producer where producerId=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(producerId));
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				output = "Deleted successfully";
			}
			
			catch (Exception e)
			{
				output = "Error while deleting the consumer.";
				System.err.println(e.getMessage());
			}
			
			return output;
		}


}
