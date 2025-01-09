package com.example.myapp.di;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloMain {

	public static void main(String[] args) {
		AbstractApplicationContext context = 
				new GenericXmlApplicationContext("application-config.xml");
	}

}
