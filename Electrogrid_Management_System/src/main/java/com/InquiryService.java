package com;

import model.Inquiry;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Inquiry")
public class InquiryService {
	Inquiry inquiryObj = new Inquiry ();
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	
	public String readInquiries()
	{
		return inquiryObj.readInquiries();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("inquiry_type") String inquiry_type,
							@FormParam("customer_type") String customer_type,
							@FormParam("description") String description,
							@FormParam("branch_code") String branch_code,
							@FormParam("branch_name") String branch_name,
							@FormParam("address") String address)
	
	{
		String output = inquiryObj.insertInquiries(inquiry_type,customer_type,description,branch_code, branch_name, address);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateInquiries(String inquiryData)
	{
		//Convert the input string to a JSON object
		JsonObject inquiryObject = new JsonParser().parse(inquiryData).getAsJsonObject();
		
		//Read the values from the JSON object
		String inquiry_id = inquiryObject.get("inquiry_id").getAsString();
		String inquiry_type = inquiryObject.get("inquiry_type").getAsString();
		String customer_type = inquiryObject.get("customer_type").getAsString();
		String description = inquiryObject.get("description").getAsString();
		String branch_code = inquiryObject.get("branch_code").getAsString();
		String branch_name = inquiryObject.get("branch_name").getAsString();
		String address = inquiryObject.get("address").getAsString();
		
		String output = inquiryObj.updateInquiries(inquiry_id, inquiry_type, customer_type, description, branch_code, branch_name, address);
		
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteInquiries(String inquiryData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(inquiryData, "", Parser.xmlParser());
		
		//Read the value from the element <requestID>
		String inquiryID = doc.select("inquiry_id").text();
		
		String output = inquiryObj.deleteInquiries(inquiryID);
		
		return output;
	}
	
}

