package com.wms1;

import com.wms1.addProduct.ExpiryController;
import com.wms1.login.LoginInsertController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Wms1Application {

	public static void main(String[] args) {

		SpringApplication.run(Wms1Application.class, args);
	}


	@Bean(initMethod = "insertLoginPassword")
	public LoginInsertController insertLoginPassword() {
		return new LoginInsertController();
	}

	@Bean(initMethod = "getExpiredData")
	public ExpiryController getExpiredData() {
		return new ExpiryController();
	}



}
