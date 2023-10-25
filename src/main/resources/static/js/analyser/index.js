const IDAnalyzer = require("idanalyzer"); 

// Analyze ID image by passing URL of the ID image (you may also use a local file)  
 export function performVerification(idvalue){
	
	 
  
let CoreAPI = new IDAnalyzer.CoreAPI("FcKIQp87KkBurHx4DCwJpyJUJo8T9uwB","US");  

// Enable authentication module v2 to check if ID is authentic
CoreAPI.enableAuthentication(true, 2);  
	
	let image = "/home/CCVAIMG/"+idvalue;


CoreAPI.scan({ document_primary: image }).then(function (response) {  
    if(!response.error){  
        console.log(response);  
        // All the information about this ID will be returned in an associative array  
        let data_result = response['result'];  
        let authentication_result = response['authentication'];  
        
  
        // Print result  
        console.log(`Hello your name is ${data_result['firstName']} ${data_result['lastName']}`);  
  
        // Parse document authentication results  
        if(authentication_result){  
            if(authentication_result['score'] > 0.5) {  
                console.log("The document uploaded is authentic");  
            }else if(authentication_result['score'] > 0.3){  
                console.log("The document uploaded looks little bit suspicious");  
            }else{  
                console.log("The document uploaded is fake");  
            }  
        }  
        // Parse biometric verification results  
        
    }else{  
        // API returned an error  
        console.log(response.error);  
    }  
}).catch(function (err) {  
    console.log(err.message);  
});
}

//performVerification("image_119623.jpeg");

