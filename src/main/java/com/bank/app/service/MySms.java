package com.bank.app.service;

import com.africastalking.Callback;
import com.africastalking.SmsService;
import com.africastalking.sms.Message;
import com.africastalking.sms.Recipient;
import com.africastalking.AfricasTalking;

import java.util.List;
import java.io.IOException;
public class MySms {
	
	public static void main(String[] args) {
		/* Set your app credentials */
		String USERNAME = "AppOner";
		String API_KEY = "9ed959659b7ca3a922fb01474d3b4fd4847803f5f1148d326ace5d4245087f54";

		/* Initialize SDK */
		AfricasTalking.initialize(USERNAME, API_KEY);

		/* Get the SMS service */
		SmsService sms = AfricasTalking.getService(AfricasTalking.SERVICE_SMS);

		/* Set the numbers you want to send to in international format */
		String[] recipients = new String[] {
			"+254759082187"
				, "+254705954443"
		};

		/* Set your message */
		String message = "We are lumberjacks. We sleep all day and code all night";

		/* Set your shortCode or senderId */
		String from = "XXXXX"; // or "ABCDE"

		/* That’s it, hit send and we’ll take care of the rest */
		try {
			List<Recipient> response = sms.send(message, from, recipients, true);
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
