package com.bank.app.controller;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class trial {

	public static void main(String[] args) {
		System.out.print("heloo");
		String filepath = "src/main/resources/static/img/slip.png";
		
		File f = new File(filepath);
		if(f.exists() && !f.isDirectory()) {
		    // do something
			OkHttpClient client = new OkHttpClient().newBuilder()
					  .build();
					MediaType mediaType = MediaType.parse("text/plain");
					@SuppressWarnings("deprecation")
					RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
					  .addFormDataPart("file","payslip",
					    RequestBody.create(MediaType.parse("application/octet-stream"),
					    new File(filepath)))
					  .build();
					System.out.print("heloo2");
					Request request = new Request.Builder()
					  .url("https://api.lazarusforms.com/api/forms/generic")
					  .method("POST", body)
					  .addHeader("orgId","9880b7682d9843cb94")
					  .addHeader("authKey", "59e3a937f44240a18896")
					  .build();
					System.out.print("heloo3");
					try {
						System.out.print("heloo4");
//						Response response = client.newCall(request).execute();
						ResponseBody responseBody = client.newCall(request).execute().body(); 
//						System.out.println(response);
						System.out.println(responseBody);
//						return response;
					} catch (IOException e) {
						e.printStackTrace();
						System.out.print("heloo4");
					}
					System.out.print("heloo5");
		}else {
			System.out.println("file not found");
		}
		

	}

}
