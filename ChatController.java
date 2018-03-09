package com.jcg.examples.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.Entity;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.google.gson.Gson;
import com.jcg.examples.viewBean.ApiVO;
import java.net.URI;
import java.net.URISyntaxException;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;

import jdk.nashorn.internal.runtime.regexp.joni.Matcher;

@Controller
public class ChatController {

	@RequestMapping(value="/chatSupport")

	public @ResponseBody ModelAndView chat(@RequestBody String body, HttpServletRequest request){
		ModelAndView model = new ModelAndView("supportChat");
		
		//Reset user context in db
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://57d6c12f89f5cf3b4200023e-trialchatbot.rhcloud.com:55276/chatbot", "admin71gRStw", "26FaVvBK24dr");
			Statement stmt = conn.createStatement();  
			int rs = stmt.executeUpdate("UPDATE user SET context = '' WHERE olb_id='54112256'"); 
			System.out.println("----------onload - start-----------");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		}
		
		return model;

	}
	
	
	@RequestMapping(value="/chatFromUser", produces="application/json")

	public @ResponseBody JSONObject chatFromUser(@RequestBody String body, HttpServletRequest request) throws JSONException{
		//ModelAndView model = new ModelAndView("supportChat");
		System.out.println("req:"+request);
		System.out.println("req param map:"+request.getParameterMap());
		System.out.println("req body:"+body);
		System.out.println("req param map:"+request.getParameterMap().get("result"));
		
		Gson gson = new Gson();
		ApiVO apiDetails= gson.fromJson(body, ApiVO.class);
		
		System.out.println("card:"+apiDetails.getResult().getParameters().getCard());
		
		Map<String, String> responseMap = new HashMap<String, String>();
		responseMap.put("speech", "hi speech");
		responseMap.put("displayText", "hi text");
		
		//String response= "{\"speech\": \"hi speech\", \"displayText\": \"hello text\"}";
		JSONObject jsonResponse = new JSONObject(responseMap.toString());
		
		return jsonResponse;

	}
	
	@RequestMapping(value="/chatMsgFromUser", method=RequestMethod.POST, headers="Accept=*/*", produces="application/json")

	public @ResponseBody void chatMsgFromUser(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException{
		//ModelAndView model = new ModelAndView("supportChat");
		StringBuffer buffer = new StringBuffer();
		Gson gson = new Gson();
		Map<String, String> responseMap = new HashMap<String, String>();
		String botans = "";
		String question = request.getParameter("question");
		
		/*if("block_credit_card".equalsIgnoreCase(question) || "block credit card".equalsIgnoreCase(question)){
			
			botans = "To block your lost credit card, please contact our PhoneBanking officers at any of our 24 hour PhoneBanking numbers. "
					+ "<p> We also have the facility of hotlisting / blocking the Credit Cards through online banking:</p>"
					+ "<p> Step 1:-Login to online banking site using your user Id and Password.</p> "
					+ "<p> Step 2:-Go to Account Details and Choose the credit card to be blocked.</p>"
					+ "<p> Step 3:-Click on Block Credit Card.</p> "
					+ "<p> Step 4:-Select the reason for hotlisting. </p>"
					+ "<p> Step 5:-Select if you wish to have the card reissued.</p>";
			botans = "Please choose the card. <p><a href</p>"
		}else if("block_debit_card".equalsIgnoreCase(question) || "block debit card".equalsIgnoreCase(question)){
			botans = "The Process for Hotlisting/blocking the Debit Cards through online banking:- "
			+ "<p>Step 1:-Login to online banking site using your user Id and Password.</p>"
			+ "<p>Step 2:-Go to Account Details and Choose manage card option.</p>" 
			+ "<p>Step 3:-If there are more than one Debit Card linked to your account, you will get a list of the same. Click on the Debit Card No. you wish to hotlist.</p>" 
			+ "<p>Step 4:-Select the reason for hotlisting, put the remarks and confirm.</p>" 
			+ "<p>Step 5:-You will receive the confirmation that the Card has been hotlisted online.</p>";
		}else*/ if("interest_rates".equalsIgnoreCase(question) || question.contains("interest") || question.contains("rate")){
			botans = "<p>The detailed interest rates are available in the following links - </p><p>For Mortgage interest rates: </p>"+
						"<p><a href='http://www.bankrate.com/mortgage.aspx?' target='_blank'>Mortgage Rates</a></p>"+
					"<p>For Refinance interest rates: </p>"+
						"<p><a href='http://www.bankrate.com/refinance.aspx?' target='_blank'>Refinance Rates</a></p>"+
						"<p>For Home equity loan rates: </p>"+
						"<p><a href='http://www.bankrate.com/home-equity.aspx?' target='_blank'>Home Equity Loan Rates</a></p>"+
						"<p>For CD rates: </p>"+
						"<p><a href='http://www.bankrate.com/cd.aspx?' target='_blank'>CD Rates</a></p>"+
						"<p>For Savings rates: </p>"+
						"<p><a href='http://www.bankrate.com/banking/savings/rates/' target='_blank'>Savings Rates</a></p>";
			
		}else if("reset_pin".equalsIgnoreCase(question) || "reset pin".equalsIgnoreCase(question) || "pin".equalsIgnoreCase(question)){
			botans = "<p>To reset your debit card PIN, please log on to our online banking site - <a href= 'http://www.tridibbank.com' target='_blank'>"
					+ "Welcome to Tridib Bank</a> After logging in, go to Account Details -> Manage Card section. Under Manage card section, select the debit card"
					+ " and choose 'Reset Debit PIN'. The newly set PIN will be mailed to the current statement address. </p>";
		}else if("branch_address".equalsIgnoreCase(question) || question.contains("branch") || question.contains("address")){
			botans = "<p>Please provide the five digit zip code.</p>";
		}else if("zip".equalsIgnoreCase(question)){
			botans = "<p>The branches available near by are - </p><ul><li>Branch/ATM at PARK WEST VILLA, 1000 VILLAGE MARKET PL, MORRISVILLE, NC 27513-7515, <strong>919-123-456</strong></li>"
					+ "<li>Branch/ATM at NORTHWOODS MKT, 977 N HARRISON AVE, CARY, NC 27513-3904, <strong>919-123-345</strong></li>"
			+ "<li>Branch/ATM at MAYFAIR PLAZA, 924 KILDAIRE FARM RD, CARY, NC 27513-3923, <strong>919-123-000</strong></li></ul>";
		}else if("block_card".equalsIgnoreCase(question)){
			botans = "Card is blocked";
		}else if("help".equalsIgnoreCase(question)){
			botans = "Following services are available."
					+ "<p style='line-height: 35px;'><a href='#' id='stdLinks' data-question='balance' data-userqs='What is my balance?' class='button' data-url=''> Balance </a></p>"
					+ "<p style='line-height: 35px;'><a href='#' id='stdLinks' data-question='transaction' data-userqs='Transactions' class='button' data-url=''> Transactions </a></p>"
					+ "<p style='line-height: 35px;'><a href='#' id='stdLinks' data-question='spent' data-userqs='Spent' class='button' data-url=''> Spent</a></p>"
					+ "<p style='line-height: 35px;'><a href='#' id='stdLinks' data-question='bills due' data-userqs='Bills due' class='button' data-url=''> Bills Due</a></p>"
					+ "<p style='line-height: 35px;'><a href='#' id='stdLinks' data-question='dispute' data-userqs='Dispute' class='button' data-url=''> Dispute</a></p>";
		}else if("block_card_Visa_1234".equalsIgnoreCase(question)){
			botans = "Visa 1234 card is blocked";
		}else if("block_card_Mac_1345".equalsIgnoreCase(question)){
			botans = "Mac 1345 card is blocked";
		}else if("block_card_Debit_5647".equalsIgnoreCase(question)){
			botans = "Debit 5647 card is blocked";
		}else{
			
			//botans = "Sorry, I couldn't get you.";
			botans = conversationAPI(question);
			System.out.println(botans);
			
			if(null == botans || "" == botans){
				botans = "Sorry, I couldn't get you.";
			}
			
		}
		
		//String response= "{\"speech\": \"hi speech\", \"displayText\": \"hello text\"}";
		OutputStream outStream = response.getOutputStream();
		buffer.append(gson.toJson(botans));
		outStream.write(buffer.toString().getBytes());

	}
	
	public static String conversationAPI(String input){
		ConversationService service = new ConversationService("2016-09-20");
		service.setUsernameAndPassword("fae8a157-a382-43e2-bddb-59ef82946481", "mdqboUrRRbyO");
		String workspaceId ="04c1f7c2-c655-46f8-9527-809809fc4282";
		
		//Context
		Map<String, Object> context = new HashMap<String, Object>();
		Map<String, Object> contextFromWatson = new HashMap<String, Object>();
		
		Connection conn = null;
		String action = "";
		String msg = "";
		String ansFromBot = "";
		String noOfTrans = "";
		String dateForBill = "";
		String retailName = "";
		
		//get context from db, if empty pass empty context to watson, else get the last context from db
		try {
			conn = DriverManager.getConnection("jdbc:mysql://57d6c12f89f5cf3b4200023e-trialchatbot.rhcloud.com:55276/chatbot", "admin71gRStw", "26FaVvBK24dr");
			Statement stmt = conn.createStatement();  
			ResultSet rs = stmt.executeQuery("SELECT context FROM user WHERE olb_id = '54112256'");
			
			
			while(rs.next()){
				if(null != rs.getString(1) && !rs.getString(1).isEmpty()){
					//System.out.println("context read from previous chat:"+rs.getString(1));
					String value = rs.getString(1);
					context = jsonToMap (new JSONObject(value));
				}
				//System.out.println("context sent to watson:"+context);
			}
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		}
		
		MessageRequest newMessage = new MessageRequest.Builder().inputText(input).context(context ).build();
		
		
		//System.out.println("new msg:"+newMessage);
		
		MessageResponse response = service.message(workspaceId, newMessage).execute();
		System.out.println("response:"+response);
		contextFromWatson = response.getContext();
		//System.out.println("context from watson: "+contextFromWatson);
		
		//update the context retrieved from watson to db
		try {
			conn = DriverManager.getConnection("jdbc:mysql://57d6c12f89f5cf3b4200023e-trialchatbot.rhcloud.com:55276/chatbot", "admin71gRStw", "26FaVvBK24dr");
			Statement stmt = conn.createStatement();  
			int rs = stmt.executeUpdate("UPDATE user SET context = '"+contextFromWatson+"' WHERE olb_id = '54112256'");  
			
			ResultSet rs1 = stmt.executeQuery("SELECT context FROM user WHERE olb_id = '54112256'");
			while(rs1.next()){
				System.out.println("Inserted context from watson");
			}
			
			System.out.println("output action:"+response.getOutput().get("action"));
			System.out.println("output  text:"+response.getOutput().get("text"));
			action = ""+response.getOutput().get("action");
			msg = ""+response.getOutput().get("text");
			List<Entity> entity= response.getEntities();
					
			
			ArrayList<String> transDetails = new ArrayList<String>();
			ArrayList<List> transList = new ArrayList<List>();
			
			
			if("balance".equalsIgnoreCase(action)){
				ResultSet rs2 = stmt.executeQuery("select available_bal from account_balance where olb_id = '54112256'");
				while(rs2.next()){
					if(null != rs2.getString(1) && !rs2.getString(1).isEmpty()){
						//System.out.println("context read from previous chat:"+rs.getString(1));
						ansFromBot = "The available balance is $"+ rs2.getString(1);
					}
				}
			}else if("transaction".equalsIgnoreCase(action)){
				
				if(null != entity){
					for(Entity var:entity){
						if("sys-number".equalsIgnoreCase(var.getEntity())){
							noOfTrans = var.getValue();
						}
					}

					ResultSet rs2 = stmt.executeQuery("select date, tran_desc, tran_amount from transaction_details where olb_id = '54112256' order by date DESC LIMIT "+noOfTrans);


					while(rs2.next()){
						transDetails = new ArrayList<String>();
						if(null != rs2.getString(1) && null != rs2.getString(2) && null != rs2.getString(3)){
							//System.out.println("context read from previous chat:"+rs.getString(1));

							transDetails.add(rs2.getString(1));
							transDetails.add(rs2.getString(2));
							transDetails.add(rs2.getString(3));
							transList.add(transDetails);
						}

					}

					StringBuffer str2 = new StringBuffer();
					str2.append("<table><tr><th>Date</th><th>Desc</th><th>Amount</th></tr>");
					DateFormat originalFormat = new SimpleDateFormat("yyyy-dd-MM");
					DateFormat targetFormat = new SimpleDateFormat("MM/dd/yy");
					for(List ls : transList){

						/*Date date = originalFormat.parse((String) ls.get(0));
					String formattedDate = targetFormat.format(date);*/

						str2.append("<tr><td>"+ls.get(0)+"</td><td>"+ls.get(1)+"</td><td>$"+ls.get(2)+"</td></tr>");

					}
					str2.append("</table");
					ansFromBot = str2.toString();
				}
				
			}else if("bill".equalsIgnoreCase(action)){
				Date today = new Date();
				Date dateObj = null;
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				
				String currentDate = formatter.format(today);
				String date1 = currentDate;
				for(Entity var:entity){
					if("period".equalsIgnoreCase(var.getEntity())){
						dateForBill = var.getValue();
						System.out.println("duration"+dateForBill);
					}
				}
				if("today".equalsIgnoreCase(dateForBill)){
					date1 = formatter.format(today);
					System.out.println(date1);
					
				}else if("tomorrow".equalsIgnoreCase(dateForBill)){
					Calendar c = Calendar.getInstance(); 
					c.setTime(today); 
					c.add(Calendar.DATE, 1);
					dateObj = c.getTime();
					date1 = formatter.format(dateObj);
					System.out.println(date1);
				}else{
					Calendar c = Calendar.getInstance(); 
					c.setTime(today); 
					c.add(Calendar.DATE, 7);
					dateObj = c.getTime();
					date1 = formatter.format(dateObj);
					System.out.println(date1);
					dateForBill = "one week";
				}
				

				ResultSet rs2 = stmt.executeQuery("select sum(tran_amount) from payment_details where (payment_date between '"+currentDate+"' and '"+date1+"' ) and (olb_id = '54112256')");
				
				
				boolean hasResults = true;
				System.out.println("result:"+rs2.toString());
				String amt = "0";
				while(rs2.next()){
					
					amt = rs2.getString(1);
					
					hasResults = false;
					
				}
				if(hasResults){
					ansFromBot = "No bills due!";
				}else{
					ansFromBot = "Total outstanding bill amount for "+dateForBill+" is $"+amt;
				}
				
			}else if("spent".equalsIgnoreCase(action)){
				String maxDate="";
				String minDate="";
				String amount = "0";
				for(Entity var:entity){
					if("retail_name".equalsIgnoreCase(var.getEntity())){
						retailName = var.getValue();
						System.out.println("retailer_name:"+retailName);
					}
				}
				
				if(null != retailName && "" != retailName){
					ResultSet rs2 = stmt.executeQuery("select max(date) from transaction_details where olb_id = '54112256' and tran_desc like '%"+retailName.toLowerCase()+"%'");
					while(rs2.next()){
						maxDate = rs2.getString(1);
					}
					
					ResultSet rs3 = stmt.executeQuery("select min(date) from transaction_details where olb_id = '54112256' and tran_desc like '%"+retailName.toLowerCase()+"%'");
					while(rs3.next()){
						minDate = rs3.getString(1);
					}
					
					ResultSet rs4 = stmt.executeQuery("select sum(tran_amount) from transaction_details where olb_id = '54112256' and tran_desc like '%"+retailName.toLowerCase()+"%'");
					while(rs4.next()){
						amount = rs4.getString(1);
					}
					
				}
				
				if(minDate.equalsIgnoreCase(maxDate)){
					ansFromBot = "You have spent $"+amount+ " on "+minDate;
				}else{
					ansFromBot = "You have spent $"+amount+ " between "+maxDate+ " and "+minDate;
				}
				
				
			}else if("blockcard".equalsIgnoreCase(action)){
				/*String cardAction = ""; 
				if(null != entity){
					for(Entity var:entity){
						if("CardType".equalsIgnoreCase(var.getEntity())){
							cardAction = var.getValue();
							System.out.println("card type:"+cardAction);
						}
					}
									
		
					if("credit".equalsIgnoreCase(cardAction)){
						ansFromBot = "To block your lost credit card, please contact our PhoneBanking officers at any of our 24 hour PhoneBanking numbers. "
								+ "<p> We also have the facility of hotlisting / blocking the Credit Cards through online banking:</p>"
								+ "<p> Step 1:-Login to online banking site using your user Id and Password.</p> "
								+ "<p> Step 2:-Go to Account Details and Choose the credit card to be blocked.</p>"
								+ "<p> Step 3:-Click on Block Credit Card.</p> "
								+ "<p> Step 4:-Select the reason for hotlisting. </p>"
								+ "<p> Step 5:-Select if you wish to have the card reissued.</p>";
					}else{
						ansFromBot = "The Process for Hotlisting/blocking the Debit Cards through online banking:- "
								+ "<p>Step 1:-Login to online banking site using your user Id and Password.</p>"
								+ "<p>Step 2:-Go to Account Details and Choose manage card option.</p>" 
								+ "<p>Step 3:-If there are more than one Debit Card linked to your account, you will get a list of the same. Click on the Debit Card No. you wish to hotlist.</p>" 
								+ "<p>Step 4:-Select the reason for hotlisting, put the remarks and confirm.</p>" 
								+ "<p>Step 5:-You will receive the confirmation that the Card has been hotlisted online.</p>";
					}
					
				}*/
				
				ansFromBot = "Please choose the card."
						+ "<p style='line-height: 35px;'><a href='#' id='stdLinks' data-question='block_card_Visa_1234' data-userqs='Block Visa 1234 card' class='button' data-url=''> Visa 1234 </a></p>"
						+ "<p style='line-height: 35px;'><a href='#' id='stdLinks' data-question='block_card_Mac_1345' data-userqs='Block Mac 1345 card' class='button' data-url=''> Mac 1345 </a></p>"
						+ "<p style='line-height: 35px;'><a href='#' id='stdLinks' data-question='block_card_Debit_5647' data-userqs='Block Debit 5647 card' class='button' data-url=''> Debit 5647</a></p>";			
				
			}else if("dispute".equalsIgnoreCase(action)){
				
				String clientPhoneNumber = "";
				
				ResultSet rs2 = stmt.executeQuery("select mobile_no from user where olb_id = '54112256'");
				
				while(rs2.next()){
					clientPhoneNumber = rs2.getString(1);
				}
				
				if("" != clientPhoneNumber){
					ansFromBot = makeOutboundCall(clientPhoneNumber);
					
				}else{
					ansFromBot = "Phone number is not yet registered with the bank.";
				}
				
			}else if("enroll".equalsIgnoreCase(action)){
				
				ansFromBot = "<p>The detailed interest rates are available in the following links - </p><p>For Mortgage interest rates: </p>"+
						"<p><a href='http://www.bankrate.com/mortgage.aspx?' target='_blank'>Mortgage Rates</a></p>"+
					"<p>For Refinance interest rates: </p>"+
						"<p><a href='http://www.bankrate.com/refinance.aspx?' target='_blank'>Refinance Rates</a></p>"+
						"<p>For Home equity loan rates: </p>"+
						"<p><a href='http://www.bankrate.com/home-equity.aspx?' target='_blank'>Home Equity Loan Rates</a></p>"+
						"<p>For CD rates: </p>"+
						"<p><a href='http://www.bankrate.com/cd.aspx?' target='_blank'>CD Rates</a></p>"+
						"<p>For Savings rates: </p>"+
						"<p><a href='http://www.bankrate.com/banking/savings/rates/' target='_blank'>Savings Rates</a></p>";
			
				
			}else{
				String msg2 = msg.replace("[", "");
				ansFromBot = msg2.replace("]", "");
								
			}
			
				
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		}
		
		return ansFromBot;
	}


	private static String makeOutboundCall(String toNumber) {
		String ansFromBot = "";
		try{
			System.out.println("inside out call");
			Twilio.init("ACfaf705ecdfa9632e9d41c3cbdb94a451", "ebddfe1b515e0c3c49e78454f3381d19");

			Call call = Call.creator(new PhoneNumber(toNumber), new PhoneNumber("+15084434500"),
					new URI("http://demo.twilio.com/docs/voice.xml")).create();
			
			ansFromBot = "For dispute related queries, support team of customer experience center will contact you shortly.";
		}catch (Exception e){
			System.out.println("exception swalloed in making call");
			ansFromBot = "For dispute related queries, support team of customer experience center will contact you shortly.";
		}

		System.out.println("Call made using twilio");
		return ansFromBot;
	} 
	
	public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
        Map<String, Object> retMap = new HashMap<String, Object>();

        if(json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }

    public static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for(int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }
    
    /*public static void main(String[] args) throws URISyntaxException {
        
        Twilio.init("ACfaf705ecdfa9632e9d41c3cbdb94a451", "ebddfe1b515e0c3c49e78454f3381d19");

		Call call = Call.creator(new PhoneNumber("+918220794710"), new PhoneNumber("+15084434500"),
		    new URI("http://demo.twilio.com/docs/voice.xml")).create();

		System.out.println(call.getSid());
    }*/
	
    
}
