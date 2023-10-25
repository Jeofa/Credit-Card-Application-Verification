package com.bank.app.model;



import java.util.List;

import org.springframework.stereotype.Component;

import com.africastalking.AfricasTalking;
import com.africastalking.SmsService;
import com.africastalking.sms.Recipient;

@Component
public class Africastalking {
	private String USERNAME = "AppOner";
	private String API_KEY = "9af043b341cbf5c91b17b84042330c58f424e84fdfbd6578bda0888918b39fa2";

 
    
//    create a single send 
    public void sendSingleSms(String contact, String sms_data) {
    	AfricasTalking.initialize(USERNAME, API_KEY);
    	
    	SmsService sms = AfricasTalking.getService(AfricasTalking.SERVICE_SMS);

		String[] recipient = new String[1] ;
		recipient[0] = contact;
		
		
		/* Set your message */
		String message = sms_data;

		/* Set your shortCode or senderId */
		String from = "XXXXX"; // or "ABCDE"

//		/* That’s it, hit send and we’ll take care of the rest */
		try {
			List<Recipient> response = sms.send(message, recipient, true);
			for (Recipient recipient_data : response) {
				System.out.print(recipient_data.number);
				System.out.print(" : ");
				System.out.println(recipient_data.status);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
			
    }
//    create a bulk send
    public void sendBulkSms(String[] contact,String sms_data) {
    	AfricasTalking.initialize(USERNAME, API_KEY);

		/* Get the SMS service */
		SmsService sms = AfricasTalking.getService(AfricasTalking.SERVICE_SMS);

		/* Set the numbers you want to send to in international format */
		String[] recipients = contact;

		/* Set your message */
		String message = sms_data;

		/* Set your shortCode or senderId */
		String from = "XXXXX"; // or "ABCDE"

//		/* That’s it, hit send and we’ll take care of the rest */
		try {
			List<Recipient> response = sms.send(message, recipients, true);
			for (Recipient recipient : response) {
				System.out.print(recipient.number);
				System.out.print(" : ");
				System.out.println(recipient.status);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
    }
    
    
}


