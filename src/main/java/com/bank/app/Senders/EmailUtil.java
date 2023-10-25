package com.bank.app.Senders;

public interface EmailUtil {

	void sendEmail(String toAddress, String subject, String body);
}
