package com;


import model.TransactionLogs;


import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;




@Path("/Bills")
public class BillService {
	
	TransactionLogs bills = new TransactionLogs();
	@GET
	 @Path("/") 
	 @Produces(MediaType.TEXT_HTML) 
	 public String readBill() 
	  { 
	  return bills.readBill(); 
	 }
	
	
	
	
	
}
