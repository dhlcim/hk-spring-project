package com.kopo.hkcode.pojo;

public class ConsolePrinter implements Printer {
	@Override
	public void print(String message) {
		// TODO Auto-generated method stub
		System.out.println("console call " + message);
	}

}
