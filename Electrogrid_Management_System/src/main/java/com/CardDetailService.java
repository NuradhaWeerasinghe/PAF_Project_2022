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

import model.CardDetails;
@Path("/Details")
public class CardDetailService {
	CardDetails cObject = new CardDetails(); 
	 @GET
	 @Path("/") 
	 @Produces(MediaType.TEXT_HTML) 
	 public String readCardDetails() 
	  { 
	  return cObject.readCardDetails(); 
	 }
	 
	 
	 @POST
	 @Path("/") 
	 @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	 @Produces(MediaType.TEXT_PLAIN) 
	 public String insertCardDetails(@FormParam("cardNo") String cardNo, 
	  @FormParam("expDate") String expDate, 
	  @FormParam("expMonth") String expMonth, 
	  @FormParam("expYear") String expYear,
	  @FormParam("securityCode") String securityCode)
	 { 
		  String output = cObject.insertCardDetails(cardNo, expDate, expMonth, expYear,securityCode); 
		  return output; 
	 }
	 
	 @PUT
	 @Path("/") 
	 @Consumes(MediaType.APPLICATION_JSON) 
	 @Produces(MediaType.TEXT_PLAIN) 
	 public String updateCardDetails(String detailsData) 
	 { 
	 //Convert the input string to a JSON object 
	  JsonObject cardObject = new JsonParser().parse(detailsData).getAsJsonObject(); 
	 //Read the values from the JSON object
	  String cardDetID = cardObject.get("cardDetID").getAsString(); 
	  String cardNo = cardObject.get("cardNo").getAsString(); 
	  String expDate = cardObject.get("expDate").getAsString(); 
	  String expMonth = cardObject.get("expMonth").getAsString(); 
	  String expYear = cardObject.get("expYear").getAsString(); 
	  String securityCode = cardObject.get("securityCode").getAsString(); 
	  String output = cObject.updateCardDetails(cardDetID, cardNo, expDate, expMonth, expYear,securityCode); 
	 return output; 
	 }
	 
	 
	 @DELETE
	 @Path("/") 
	 @Consumes(MediaType.APPLICATION_XML) 
	 @Produces(MediaType.TEXT_PLAIN) 
	 public String deleteCardDetails(String detailsData) 
	 { 
	 //Convert the input string to an XML document
	  Document doc = Jsoup.parse(detailsData, "", Parser.xmlParser()); 
	  
	 //Read the value from the element 
	  String cardDetID = doc.select("cardDetID").text(); 
	  String output = cObject.deleteCardDetails(cardDetID); 
	 return output; 
	 }
	 
}
