package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.user;
@Path("/user")
public class UserService {
	
		user userObj = new user ();
		//Get consumer details
		@GET
		@Path("/consumer")
		@Produces(MediaType.APPLICATION_JSON)
		
		public String readConsumer()
		{
			return userObj.readConsumer();
		}
		//Insert consumer details
		@POST
		@Path("/consumer")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertConsumer(@FormParam("first_name") String first_name,
								@FormParam("last_name") String last_name,
								@FormParam("email") String email,
								@FormParam("username") String username,
								@FormParam("password") String password,
								@FormParam("telephone_no") int telephone_no)
		{
			String output = userObj.insertConsumer(first_name,last_name,email,username,password,telephone_no);
			return output;
		}
		//Update consumer details
		@PUT
		@Path("/consumer")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateConsumer(String consumerData)
		{
			//Convert the input string to a JSON object
			JsonObject consumerObject = new JsonParser().parse(consumerData).getAsJsonObject();
			
			//Read the values from the JSON object
			String consumer_id  = consumerObject.get("consumer_id").getAsString();
			String first_name = consumerObject.get("first_name").getAsString();
			String last_name  = consumerObject.get("last_name").getAsString();
			String email = consumerObject.get("email").getAsString();
			String username = consumerObject.get("username").getAsString();
			String password  = consumerObject.get("password").getAsString();
			String telephone_no  = consumerObject.get("telephone_no").getAsString();
			
			
			String output = userObj.updateConsumer(consumer_id, first_name, last_name, email, username, password , telephone_no);
			
			return output;
		}
		//Delete consumer details
		@DELETE
		@Path("/consumer")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteConsumer(String consumerData)
		{
			//Convert the input string to an XML document
			Document doc = Jsoup.parse(consumerData, "", Parser.xmlParser());
			
			//Read the value from the element <requestID>
			String consumerID = doc.select("consumer_id").text();
			
			String output = userObj.deleteConsumer(consumerID);
			
			return output;
		}
		//Get producer details
		@GET
		@Path("/producer")
		@Produces(MediaType.APPLICATION_JSON)
		
		public String readProducer()
		{
			return userObj.readProducer();
		}
		//Insert producer details
		@POST
		@Path("/producer")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertProducer(@FormParam("plantId") String plantId,
								@FormParam("projectId") String projectId,
								@FormParam("firstName") String firstName,
								@FormParam("lastName") String lastName,
								@FormParam("email") String email,
								@FormParam("userName") String userName,
								@FormParam("telephoneNo") int telephoneNo,
								@FormParam("password") String password)
		{
			String output = userObj.insertProducer(plantId,projectId,firstName,lastName,email,userName,telephoneNo,password);
			return output;
		}
		//Update producer details
		@PUT
		@Path("/producer")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateProducer(String producerData)
		{
			//Convert the input string to a JSON object
			JsonObject producerObject = new JsonParser().parse(producerData).getAsJsonObject();
			
			//Read the values from the JSON object
			String producerId = producerObject.get("producerId").getAsString();
			String plantId = producerObject.get("plantId").getAsString();
			String projectId  = producerObject.get("projectId").getAsString();
			String firstName = producerObject.get("firstName").getAsString();
			String lastName = producerObject.get("lastName").getAsString();
			String email  = producerObject.get("email").getAsString();
			String userName  = producerObject.get("userName").getAsString();
			String telephoneNo  = producerObject.get("telephoneNo").getAsString();
			String password  = producerObject.get("password").getAsString();
			
			String output = userObj.updateProducer(producerId, plantId, projectId, firstName, lastName, email , userName,telephoneNo,password);
			
			return output;
		}
		//Delete producer details
		@DELETE
		@Path("/producer")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteProducer(String producerData)
		{
			//Convert the input string to an XML document
			Document doc = Jsoup.parse(producerData, "", Parser.xmlParser());
			
			//Read the value from the element <requestID>
			String producerId = doc.select("producerId").text();
			
			String output = userObj.deleteProducer(producerId);
			
			return output;
		}	
	
}
