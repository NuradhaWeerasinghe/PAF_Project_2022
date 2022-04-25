package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Notice {

	//A common method to connect to the DB
			private Connection connect()
			{
				Connection con = null;
						
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					//Provide the correct details: DBServer/DBName, username, password
					con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/notification_Management", "root", "");
				}
						
				catch (Exception e)
				{
					e.printStackTrace();
				}
						
				return con;
			}
			
			//Insert interruptions details to DB table
			public String createInterruption(String subject,String description,String area,String time,String date,String created_date )
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
					String query = " insert into interruptions (`Interruption_id`,`subject`,`description`,`area`,`time`, `date`, `created_date`)"
										+ " values (?, ?, ?, ?, ?, ?, ?)";
						
					PreparedStatement preparedStmt = con.prepareStatement(query);
						
					// binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, subject);
					preparedStmt.setString(3, description);
					preparedStmt.setString(4, area);
					preparedStmt.setString(5, time);
					preparedStmt.setString(6, date);
					preparedStmt.setString(7, created_date);
						
					// execute the statement
					preparedStmt.execute();
					con.close();
						
					output = "Interruption Notice created successfully";
				}
					
				catch (Exception e)
				{
					output = "Error while inserting the consumer.";
					System.err.println(e.getMessage());
				}
					
				return output;
			}
			
			//Read Interruption details
			public String readInterruption()
			{
				JsonObject interruptions = new JsonObject();
					
				try
				{
					Connection con = connect();
						
					if (con == null)
					{
						return "Error while connecting to the database for reading."; 
					}
						
					String query = "select * from interruptions";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
						
					// Prepare the json to be displayed
					JsonArray array = new JsonArray();
						
					// iterate through the rows in the result set
					while (rs.next()) {
						JsonObject innerInterruptions= new JsonObject();
							
						innerInterruptions.addProperty("Interruption_id", rs.getInt(1));
						innerInterruptions.addProperty("subject", rs.getString(2));
						innerInterruptions.addProperty("description", rs.getString(3));
						innerInterruptions.addProperty("area", rs.getString(4));
						innerInterruptions.addProperty("time", rs.getString(5));
						innerInterruptions.addProperty("date", rs.getString(6));
						innerInterruptions.addProperty("created_date", rs.getString(7));
						array.add(innerInterruptions);
							
					}
					interruptions.add("interruption", array);
						
					con.close();
				}
					
				catch (Exception e)
				{
					e.printStackTrace();
					interruptions.addProperty("status", "error");
					return interruptions.toString();
				}
					
				return interruptions.toString();
			}
			
			//Update interruption details
			public String updateInterruption(String Interruption_id,String subject,String description,String area,String time,String date,String created_date) 
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
					String query = "UPDATE interruptions SET subject=?,description=?,area=?,time=?,date=?, created_date=? WHERE Interruption_id=?";
						
					PreparedStatement preparedStmt = con.prepareStatement(query);
						
					// binding values
					preparedStmt.setString(1, subject);
					preparedStmt.setString(2, description);
					preparedStmt.setString(3, area);
					preparedStmt.setString(4, time);
					preparedStmt.setString(5, date);
					preparedStmt.setString(6, created_date);
					preparedStmt.setInt(7, Integer.parseInt(Interruption_id));
						
					// execute the statement
					preparedStmt.execute();
					con.close();
						
					output = "Updated successfully";
				}
					
				catch (Exception e)
				{
					output = "Error while updating the Interruption.";
					System.err.println(e.getMessage());
				}
					
				return output;
			}
			//Delete Interruption details
			public String deleteInterruptions(String Interruption_id)
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
					String query = "delete from interruptions where Interruption_id=?";
					
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(Interruption_id));
					
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
			
			//Insert promotion  details
			
			public String insertPromotion(String subject,String description,String fromDate,String toDate,String conditions,String createdDate)
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
					String query = " insert into promotions (`PromotionId`,`subject`,`description`,`fromDate`,`toDate`,`conditions`, `createdDate`)"
										+ " values (?, ?, ?, ?, ?, ?, ?)";
						
					PreparedStatement preparedStmt = con.prepareStatement(query);
						
					// binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, subject);
					preparedStmt.setString(3, description);
					preparedStmt.setString(4, fromDate);
					preparedStmt.setString(5, toDate);
					preparedStmt.setString(6, conditions);
					preparedStmt.setString(7, createdDate);
						
					// execute the statement
					preparedStmt.execute();
					con.close();
						
					output = "Inserted successfully";
				}
					
				catch (Exception e)
				{
					output = "Error while adding the promotion.";
					System.err.println(e.getMessage());
				}
					
				return output;
			}
			
			//Read promotion details
			
			public String readPromotion()
			{
				JsonObject promotions= new JsonObject();
					
				try
				{
					Connection con = connect();
						
					if (con == null)
					{
						return "Error while connecting to the database for reading."; 
					}
						
					String query = "select * from promotions";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
						
					// Prepare the json to be displayed
					JsonArray array = new JsonArray();
						
					// iterate through the rows in the result set
					while (rs.next()) {
						JsonObject innerPromotions= new JsonObject();
						//producerId`,`plantId`,`projectId`,`firstName`,`lastName`, `email`, `userName`,`telephoneNo`,`password`	
						innerPromotions.addProperty("PromotionId", rs.getInt(1));
						innerPromotions.addProperty("subject", rs.getString(2));
						innerPromotions.addProperty("description", rs.getString(3));
						innerPromotions.addProperty("fromDate", rs.getString(4));
						innerPromotions.addProperty("toDate", rs.getString(5));
						innerPromotions.addProperty("conditions", rs.getString(6));
						innerPromotions.addProperty("createdDate", rs.getString(7));
						
						array.add(innerPromotions);
							
					}
					promotions.add("promotion", array);
						
					con.close();
				}
					
				catch (Exception e)
				{
					e.printStackTrace();
					promotions.addProperty("status", "error");
					return promotions.toString();
				}
					
				return promotions.toString();
			}
			//Update Promotions details
			public String updatePromotion(String PromotionId, String subject, String description,String fromDate,String toDate,String conditions ,String createdDate)
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
					String query = "UPDATE promotions SET subject=?,description=?,fromDate=?,toDate=?,conditions=?,createdDate=? WHERE PromotionId=?";
						
					PreparedStatement preparedStmt = con.prepareStatement(query);
						
					// binding values
					preparedStmt.setString(1, subject);
					preparedStmt.setString(2, description);
					preparedStmt.setString(3, fromDate);
					preparedStmt.setString(4, toDate);
					preparedStmt.setString(5, conditions);
					preparedStmt.setString(6, createdDate);
					preparedStmt.setInt(7, Integer.parseInt(PromotionId));
						
					// execute the statement
					preparedStmt.execute();
					con.close();
						
					output = "Updated successfully";
				}
					
				catch (Exception e)
				{
					output = "Error while updating the promotion.";
					System.err.println(e.getMessage());
				}
					
				return output;
			}
			//Delete Promotion details
			public String deletePromotion(String PromotionId)
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
					String query = "delete from promotions where PromotionId=?";
					
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(PromotionId));
					
					// execute the statement
					preparedStmt.execute();
					con.close();
					
					output = "Deleted successfully";
				}
				
				catch (Exception e)
				{
					output = "Error while deleting the Promotion.";
					System.err.println(e.getMessage());
				}
				
				return output;
			}


}
