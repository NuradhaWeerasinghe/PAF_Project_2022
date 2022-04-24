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

import model.Notice;

@Path("/notice")
public class NoticeService {
	
	Notice noticeObj = new Notice ();
	//Get interruptions details
	@GET
	@Path("/interruptions")
	@Produces(MediaType.APPLICATION_JSON)
	
	public String readInterruption()
	{
		return noticeObj.readInterruption();
	}
	//Insert interruptions details
	@POST
	@Path("/interruptions")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String createInterruption(@FormParam("subject") String subject,
							@FormParam("description") String description,
							@FormParam("area") String area,
							@FormParam("time") String time,
							@FormParam("date") String date,
							@FormParam("created_date") String created_date)
	{
		String output = noticeObj.createInterruption(subject,description,area,time,date,created_date);
		return output;
	}
	//Update interruptions details
	@PUT
	@Path("/interruptions")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateInterruption(String InterruptionData)
	{
		//Convert the input string to a JSON object
		JsonObject InterruptionObject = new JsonParser().parse(InterruptionData).getAsJsonObject();
		
		//Read the values from the JSON object
		String Interruption_id  = InterruptionObject.get("Interruption_id").getAsString();
		String subject = InterruptionObject.get("subject").getAsString();
		String description  = InterruptionObject.get("description").getAsString();
		String area = InterruptionObject.get("area").getAsString();
		String time = InterruptionObject.get("time").getAsString();
		String date  = InterruptionObject.get("date").getAsString();
		String created_date  = InterruptionObject.get("created_date").getAsString();
		
		
		String output = noticeObj.updateInterruption(Interruption_id, subject, description, area, time, date , created_date);
		
		return output;
	}
	//Delete interruptions details
	@DELETE
	@Path("/interruptions")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteInterruption(String InterruptionData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(InterruptionData, "", Parser.xmlParser());
		
		//Read the value from the element <InterruptionID>
		String InterruptionID = doc.select("Interruption_id").text();
		
		String output = noticeObj.deleteInterruptions(InterruptionID);
		
		return output;
	}
	//Get promotions details
	@GET
	@Path("/promotions")
	@Produces(MediaType.APPLICATION_JSON)
	
	public String readPromotion()
	{
		return noticeObj.readPromotion();
	}
	//Insert promotions details
	@POST
	@Path("/promotions")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPromotion(@FormParam("subject") String subject,
							@FormParam("description") String description,
							@FormParam("fromDate") String fromDate,
							@FormParam("toDate") String toDate,
							@FormParam("conditions") String conditions,
							@FormParam("createdDate") String createdDate)
	{
		String output = noticeObj.insertPromotion(subject,description,fromDate,toDate,conditions,createdDate);
		return output;
	}
	//Update promotions details
	@PUT
	@Path("/promotions")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePromotion(String PromotionData)
	{
		//Convert the input string to a JSON object
		JsonObject PromotionObject = new JsonParser().parse(PromotionData).getAsJsonObject();
		
		//Read the values from the JSON object
		String PromotionId  = PromotionObject.get("PromotionId").getAsString();
		String subject = PromotionObject.get("subject").getAsString();
		String description  = PromotionObject.get("description").getAsString();
		String fromDate = PromotionObject.get("fromDate").getAsString();
		String toDate= PromotionObject.get("toDate").getAsString();
		String conditions= PromotionObject.get("conditions").getAsString();
		String createdDate= PromotionObject.get("createdDate").getAsString();
		
		
		String output = noticeObj.updatePromotion(PromotionId, subject, description, fromDate, toDate, conditions , createdDate);
		
		return output;
	}
	//Delete promotions details
	@DELETE
	@Path("/promotions")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePromotion(String PromotionData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(PromotionData, "", Parser.xmlParser());
		
		//Read the value from the element <InterruptionID>
		String PromotionId = doc.select("PromotionId").text();
		
		String output = noticeObj.deletePromotion(PromotionId);
		
		return output;
	}	

}
