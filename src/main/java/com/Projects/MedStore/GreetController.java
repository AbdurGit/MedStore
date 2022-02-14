package com.Projects.MedStore;
import java.util.HashMap;
import java.util.Map;

import com.Projects.MedStore.Model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class GreetController {
	private static Map<String, User> productRepo = new HashMap<>();
	static {
	// 	User user1=new User("1", "a", "pass");
	// 	User user2=new User("2","abdur","123");
	// 	User user3=new User("3", "kaniz", "1234");
	// 	User user4=new User("4", "a", "pass");
	// 	User user5=new User("5","abdur","123");
	// 	User user6=new User("6", "kaniz", "1234");
	// 	User user7=new User("7", "a", "pass");
	// 	User user8=new User("8","abdur","123");
	// 	User user9=new User("9", "kaniz", "1234");
	// 	User user10=new User("10", "kaniz", "1234");
	// 	User user11=new User("11", "kaniz", "1234");
	//    productRepo.put(user1.getId(), user1);
	//    productRepo.put(user2.getId(), user2);
	//    productRepo.put(user3.getId(), user3);
	//    productRepo.put(user4.getId(), user4);
	//    productRepo.put(user5.getId(), user5);
	//    productRepo.put(user6.getId(), user6);
	//    productRepo.put(user7.getId(), user7);
	//    productRepo.put(user8.getId(), user8);
	//    productRepo.put(user9.getId(), user9);
	//    productRepo.put(user10.getId(), user10);
	//    productRepo.put(user11.getId(), user11);

	   
	   
	}


	@GetMapping("/greeting")
	public String greeting() {
		
		return "Hi";
	}    
	@GetMapping("/test")    
	public ModelAndView showView()  {    
	ModelAndView modelAndView = new ModelAndView();    
	modelAndView.setViewName("test");        
	return modelAndView;    
	}
	
	@GetMapping("/getUser")    
public ResponseEntity<Object> getUser()  {    

 return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);   
}


@GetMapping("/user")
public String user() {
	return ("<h1>Welcome User</h1>");
}

@GetMapping("/admin")
public String admin() {
	return ("<h1>Welcome Admin</h1>");

}

@GetMapping("/random")
public String random() {
	return ("random return");
	
}

@GetMapping("/error")
public String error() {
	return ("error page");
	
}

  


}