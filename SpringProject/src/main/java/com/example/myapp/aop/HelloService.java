package com.example.myapp.aop;

import org.springframework.stereotype.Service;

public class HelloService implements IHelloService {

	@Override
	public String sayHello(String name) {
		System.out.println("HelloService.sayHello()메소드 실행");
		String message = "Hello!!"+name;
		return message;
	}

	@Override
	public String sayGoodbye(String name) {
		System.out.println("HelloService.sayGoodbye()메소드 실행");
		String message = "Goodbye~~"+name;
		return message;
	}

}
