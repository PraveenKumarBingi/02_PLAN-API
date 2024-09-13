package com.password.password_utils;

import java.util.Base64;
import java.util.Base64.Encoder;

public class PasswordEncoding {
	
	
	public String encode( byte[] msg) {
		 Encoder encoder=Base64.getEncoder();
		 return  encoder.encodeToString(msg);
	}

	public void encodeToString(String mssg) {
		System.out.println("welcome to maven project");	
	}

}
