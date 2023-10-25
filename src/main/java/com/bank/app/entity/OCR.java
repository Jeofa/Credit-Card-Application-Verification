package com.bank.app.entity;

import java.io.File;
import java.io.IOException;

import javax.script.ScriptException;

import com.cloudmersive.client.ImageOcrApi;
import com.cloudmersive.client.invoker.ApiClient;
import com.cloudmersive.client.invoker.ApiException;
import com.cloudmersive.client.invoker.Configuration;
import com.cloudmersive.client.invoker.auth.ApiKeyAuth;
import com.cloudmersive.client.model.ImageToTextResponse;

public class OCR {

	public static  boolean checkIDData(String filename, int id) {
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		ApiKeyAuth Apikey = (ApiKeyAuth) defaultClient.getAuthentication("Apikey");
		Apikey.setApiKey("e8d7e3f7-e2bd-4096-ad96-4a57469a11ac");
		String recognitionMode = "recognitionMode_example";
		String language = "language_example"; 
		String preprocessing = "preprocessing_example";
		ImageOcrApi apiInstance = new ImageOcrApi();
		File imageFile = new File("/home/CCVAIMG/"+filename);
		try {
		    ImageToTextResponse result = apiInstance.imageOcrPost(imageFile, recognitionMode, language, preprocessing);
		    String response = result.getTextResult();
		    if(result.getMeanConfidenceLevel() >= 0.8) {
		    	
		    	if(response.contains(String.valueOf(id))) {
		    		System.out.println("Does contain that");
		    		return true;
		    	}else {
		    		System.out.println("Does not contain that");
		    		return false;
		    	}
		    }else {
		    	System.out.println("scanning error");
		    	return false;
		    }
		    
		} catch (ApiException e) {
		    System.err.println("Exception when calling ImageOcrApi#imageOcrPost");
		    System.out.println(e.getMessage());
		    return false;
		}
	}
	
	public static  boolean checkPaySlipData(String filename,String identity) {
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		ApiKeyAuth Apikey = (ApiKeyAuth) defaultClient.getAuthentication("Apikey");
		Apikey.setApiKey("e8d7e3f7-e2bd-4096-ad96-4a57469a11ac");
		String recognitionMode = "recognitionMode_example";
		String language = "language_example"; 
		String preprocessing = "preprocessing_example";
		ImageOcrApi apiInstance = new ImageOcrApi();
		File imageFile = new File("/home/CCAVPaySlip/"+filename);
//		System.out.println( "File name "+imageFile.getName());
		try {
//			System.out.println( "File name "+imageFile.getName());
		    ImageToTextResponse result = apiInstance.imageOcrPost(imageFile, recognitionMode, language, preprocessing);
		    String response = result.getTextResult();
		    if(result.getMeanConfidenceLevel() >= 0.8) {
		    	if(response.contains(identity)) {
		    		return true;
		    	}else {
		    		return false;
		    	}
		    }else {
		    	return false;
		    }
		    
		} catch (ApiException e) {
		    System.err.println("Exception when calling ImageOcrApi#imageOcrPost");
		    System.out.println(e.getMessage());
		    return false;
		}
	}
	
	public boolean checkBankData(String filename, String identity) {
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		ApiKeyAuth Apikey = (ApiKeyAuth) defaultClient.getAuthentication("Apikey");
		Apikey.setApiKey("e8d7e3f7-e2bd-4096-ad96-4a57469a11ac");
		String recognitionMode = "recognitionMode_example";
		String language = "language_example"; 
		String preprocessing = "preprocessing_example";
		ImageOcrApi apiInstance = new ImageOcrApi();
		File imageFile = new File("/home/CCAVBankStmt/"+filename);
		try {
		    ImageToTextResponse result = apiInstance.imageOcrPost(imageFile, recognitionMode, language, preprocessing);
		    String response = result.getTextResult();
		    if(result.getMeanConfidenceLevel() >= 0.8) {
		    	if(response.contains(identity)) {
		    		return true;
		    	}else {
		    		return false;
		    	}
		    }else {
		    	return false;
		    }
		    
		} catch (ApiException e) {
		    System.err.println("Exception when calling ImageOcrApi#imageOcrPost");
		    System.out.println(e.getMessage());
		    return false;
		}
	}
	
	

	public static void main(String[] args) throws ScriptException, IOException {
		String id = "Jactone";
		String name = "new_payslip.pdf";
//		String name = "image_37598869_front.jpeg";
//		int id = 37598869;
//		System.out.println(checkIDData(name,id));
		System.out.println(checkPaySlipData(name,id));
	
	}
}
