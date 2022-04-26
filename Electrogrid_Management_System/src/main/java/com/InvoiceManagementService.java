package com;
import model.TransactionLogs;

import javax.ws.rs.Consumes;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
@Path("/Invoices")
public class InvoiceManagementService {

	TransactionLogs invoce = new TransactionLogs();
	@GET
	 @Path("/") 
	 @Produces(MediaType.TEXT_HTML) 
	 public String readInvoice() 
	  { 
	  return invoce.readInvoice(); 
	 }
	
	
	@POST
	 @Path("/") 
	 @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	 @Produces(MediaType.TEXT_PLAIN) 
	 public String insertInvoiceDetails(@FormParam("plantID") String plantID, 
	  @FormParam("branchName") String branchName, 
	  @FormParam("genUnits") String genUnits, 
	  @FormParam("startDate") String startDate,
	  @FormParam("endDate") String endDate)
	 { 
	  String output = invoce.insertInvoiceDetails(plantID, branchName, genUnits, startDate,endDate); 
	 return output; 
	 }
	
	
	 @PUT
	 @Path("/") 
	 @Consumes(MediaType.APPLICATION_JSON) 
	 @Produces(MediaType.TEXT_PLAIN) 
	 public String updateInvoiceDetails(String detailsData) 
	 { 
	 //Convert the input string to a JSON object 
	  JsonObject invoiceObject = new JsonParser().parse(detailsData).getAsJsonObject(); 
	 //Read the values from the JSON object
	  String invoiceID = invoiceObject.get("invoiceID").getAsString(); 
	  String plantID = invoiceObject.get("plantID").getAsString(); 
	  String branchName = invoiceObject.get("branchName").getAsString(); 
	  String genUnits = invoiceObject.get("genUnits").getAsString(); 
	  String startDate = invoiceObject.get("startDate").getAsString(); 
	  String endDate = invoiceObject.get("endDate").getAsString(); 
	  String output = invoce.updateInvoiceDetails(invoiceID, plantID, branchName, genUnits, startDate,endDate); 
	 return output; 
	 }
	
}
