package com.example.demoX;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;

//the above imports are replaced by below for @RequestParam
//This is the new comment from the GitHub repository....
//This is a 2nd comment from IntelliJ to Github repository

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication	////annotation to show that this is a Spring project, above import will be added based on this.
@RestController  //annotation for get, post, put mapping to work below

public class DemoXApplication {

	public static void main(String[] args) {
		System.out.println("Welcome back to Spring Boot!");
		SpringApplication.run(DemoXApplication.class, args);
	}

	//to create an end point to display some contents in browser
	@GetMapping("/")   //referring to the home page
	public String Display()
	{
		return "This is the home page of the application ";
	}

	@GetMapping("/product/{name}/{quantity}")   //referring to the product page, which expects a value in item variable
	//http://localhost:8080/product/banana	pass value in the browser address like this
	//http://localhost:8080/product/orange/66	passing two values

	//@RequestParam deals with query parameters, while @PathVariable extracts values from URI templates in the URL path
	public String Display(@PathVariable String name, @PathVariable int quantity)
	{
		return "This is the product page of the application "+name +" Quantity: "+quantity;
	}

	@RequestMapping("/repeat")
	//http://localhost:8080/repeat?word=hello&times=4
	public String repeat(@RequestParam String word, @RequestParam int times)
	{
		String returnString = " ";
		for(int i = 0; i < times; i++)
		{
			returnString += word +" - "; //by just returning this will print word once. so add all and then return later below
		}
		return returnString;
	}

	@RequestMapping("/employee")
	public Employee getTicket() {
		Employee emp = new Employee("Jonny", "02098998899", 34);

		return emp;
	}

	@RequestMapping("/employee2")
	//http://localhost:8080/employee2?name=Danny
	public  Object getEmployee(@RequestParam String name) { //object class used not Employee as return type

		Employee emp = new Employee();
		emp.name = name;
		emp.age = 23;
		emp.phone = "0202002022";
		// no ticket available
//		Employee emp2 = new Employee();
//
//		if (emp2 == null)
//		{
//			return new ResponseEntity<Employee>(HttpStatus.I_AM_A_TEAPOT);
//		}

		return emp;
	}

	@GetMapping ("/print")
	ResponseEntity<String> print()
	{
		return new ResponseEntity<>("Hello ResponseEntity! ", HttpStatus.OK);
	}

	@GetMapping("/age")
	ResponseEntity<String> age(	@RequestParam("yearOfBirth") int yearOfBirth) {

		if (yearOfBirth > 2024) {
			return new ResponseEntity<>(
					"Year of birth cannot be in the future",
					HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(
				"Your age is " + yearOfBirth,
				HttpStatus.OK);
	}
}




