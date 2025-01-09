package com.example.myapp.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

public class HelloController {
	
	IHelloService helloService = new HelloService();
	
	public HelloController(IHelloService helloService) {
		this.helloService = helloService;
	}

	public void hello(String name) {
		System.out.println("HelloController : "+ helloService.sayHello(name));
	}
}
