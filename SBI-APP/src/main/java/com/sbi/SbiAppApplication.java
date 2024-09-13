package com.sbi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.password.password_utils.PasswordEncoding;


@SpringBootApplication
public class SbiAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbiAppApplication.class, args);
		
	   PasswordEncoding passwordEncoding=new PasswordEncoding();
		   passwordEncoding.encodeToString(null);

	}

}
