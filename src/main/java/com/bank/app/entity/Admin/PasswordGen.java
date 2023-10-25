package com.bank.app.entity.Admin;

import java.util.Random;

public class PasswordGen {


	
	public char[] passwordGenerator() {
		String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Small_chars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*_=+-/.?<>)";
  
  
        String values = Capital_chars + Small_chars +
                        numbers + symbols;
        
        int passwordLen = 10;
        Random rndm_method = new Random();
        char[] password = new char[passwordLen];
  
        for (int i = 0; i < passwordLen; i++)
        {
           password[i] = values.charAt(rndm_method.nextInt(values.length()));
        }
        
        return password;
	}

}
