package com.bank.app.service.Verification;

import java.io.File;

import com.cloudmersive.client.PdfOcrApi;
import com.cloudmersive.client.invoker.ApiClient;
import com.cloudmersive.client.invoker.ApiException;
import com.cloudmersive.client.invoker.Configuration;
import com.cloudmersive.client.invoker.auth.ApiKeyAuth;
import com.cloudmersive.client.model.PdfToTextResponse;

public class OCR {
	
	public boolean checkdata(String filename, String identity) {
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		ApiKeyAuth Apikey = (ApiKeyAuth) defaultClient.getAuthentication("Apikey");
		Apikey.setApiKey("e20f93fa-b53a-45e6-b7e0-b11a1feaa55e");
		
		PdfOcrApi apiInstance = new PdfOcrApi();
		File imageFile = new File("src/main/resources/static/"+filename);
		String recognitionMode = "recognitionMode_example";
		String language = "language_example"; 
		String preprocessing = "preprocessing_example";
		try {
		   PdfToTextResponse result = apiInstance.pdfOcrPost(imageFile, recognitionMode, language, preprocessing);
		    System.out.println(result);
		    if(result.isSuccessful()) {
		    	String response = result.toString();
		    	if(response.contains(identity)) {
		    		return true;
		    	}
		    }else {
		    	return false;
		    }

		} catch (ApiException e) {
		    System.err.println("Exception when calling PdfOcrApi#pdfOcrPost");
		    e.printStackTrace();
		    return false;
		}
		return true;

	}

}
