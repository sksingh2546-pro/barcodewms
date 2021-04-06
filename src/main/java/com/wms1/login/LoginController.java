
package com.wms1.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LoginController {
    @Autowired
    LoginRepository loginRepository;

    @PostMapping("/changePassword")
    public String insertData(@RequestBody Login login) {
        String message = "{\"message\":\"Unsuccessful\"}";
        System.out.println(login.getUser_name()+" "+ login.getPassword());
        int insertData = loginRepository.insertData(login.getUser_name(), login.getPassword());
        if (insertData > 0) {
            message = "{\"message\":\"Successful\"}";
        }
        return message;
    }

    @GetMapping("/getLogin")
    public String getLogin(@RequestParam("user_name") String user_name, @RequestParam("password") String password) {
        List<Login> list = loginRepository.getLogin(user_name, password);
        String response = "{\"message\":\"Unsuccessful\"}";
        if (list.size() > 0) {
            response = "{\"message\":\"Successful\"}";
        }
        return response;
    }
}
