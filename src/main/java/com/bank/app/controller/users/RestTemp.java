package com.bank.app.controller.users;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.bank.app.DTO.MailRequest;
import com.bank.app.DTO.MailResponse;
import com.bank.app.model.Authentic;
import com.bank.app.service.Email.EmailService;
import com.bank.app.service.Users.UserHandler;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RestTemp {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserHandler userService;
	
	@Autowired
	private EmailService service;
	
	 @RequestMapping(value = "/testjson", method = RequestMethod.GET,   produces = MediaType.APPLICATION_JSON_VALUE	)

		    String getTest() {
		        return "json/country_state_city.json";
		    }
	 
	 @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	    public @ResponseBody
	    Object getBeers() {
	        Resource resource = new ClassPathResource("/static/json/beers.json");
	        try {
	            ObjectMapper mapper = new ObjectMapper();
	            return mapper.readValue(resource.getInputStream(), Object.class);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	
	@PostMapping("/rester")
	public ResponseEntity<Authentic> uploadToIDAPI(@RequestParam(name="file") MultipartFile multipart) {
		String api_url = "https://api.idanalyzer.com";
		HttpHeaders header = new HttpHeaders();
		header.set("apiKey", "5Cw3z1ZcMMZWDxPvocLRPZfxsImLPpHX");
		header.setContentType(MediaType.MULTIPART_FORM_DATA);

		ResponseEntity<Authentic> response = restTemplate.postForEntity(api_url, multipart, Authentic.class);
		
		return response;
	}
	
	@PostMapping("/test")
	private String getTester() {
//		userService.uploadPaySlipToLazarus();
		return "here";
	}
	
	

//	@PostMapping("/sendingEmail")
	@RequestMapping(method = RequestMethod.POST,  value="/sendingEmail",consumes = MediaType.APPLICATION_JSON_VALUE)
	public MailResponse sendEmail(@RequestBody MailRequest request) {
		Map<String, Object> model = new HashMap<>();
		model.put("Name", request.getEmpname());
		model.put("location", "Bangalore,India");
		return service.sendEmail(request, model);

	}
	

}
