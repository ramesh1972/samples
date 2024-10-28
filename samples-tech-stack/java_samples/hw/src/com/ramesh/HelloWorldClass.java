package com.ramesh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldClass {

	private static final Logger log = LoggerFactory.getLogger(HelloWorldClass.class);
	public static void main(String[] args) {
		System.out.println(args[0]);
		
		log.info("Load data...." + args[0]);
	}
}
