package com.wms1.login;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LoginInsertController {

    @Autowired
    LoginRepository loginRepository;

    public String insertLoginPassword(){
    	 List<Login>list=(List<Login>) loginRepository.findAll();
         if(list.size()==0){
        	 Login login=new Login();
        	 login.setPassword("12345");
        	 login.setUser_name("Admin");
          loginRepository.save(login);
         }
        return "save Addition password";
    }
}
