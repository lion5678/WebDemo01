package com.eeitxx.util;


public class Sayhello {

	private String name;
	
	public Sayhello(String name) {
		this.name = name;
	}

	public String sayHello(){
		return "hello " + name;
	}
}
