package com;

import model.Request;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Request")
public class RequestService {
	Request requestObj = new Request ();
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	
	public String readInquiries()
	{
		return requestObj.readRequests();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("service_type") String service_type,
							@FormParam("description") String description,
							@FormParam("city") String city,
							@FormParam("zip_code") String zip_code)
	{
		String output = requestObj.insertRequests(service_type,description,city,zip_code);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateRequests(String inquiryData)
	{
		//Convert the input string to a JSON object
		JsonObject requestObject = new JsonParser().parse(inquiryData).getAsJsonObject();
		
		//Read the values from the JSON object
		String request_id = requestObject.get("request_id").getAsString();
		String service_type = requestObject.get("service_type").getAsString();
		String description = requestObject.get("description").getAsString();
		String city = requestObject.get("city").getAsString();
		String zip_code = requestObject.get("zip_code").getAsString();
		
		String output = requestObj.updateRequests(request_id, service_type,description,city,zip_code);
		
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteRequests(String RequestData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(RequestData, "", Parser.xmlParser());
		
		//Read the value from the element <requestID>
		String requestID = doc.select("request_id").text();
		
		String output = requestObj.deleteRequests(requestID);
		
		return output;
	}

}

