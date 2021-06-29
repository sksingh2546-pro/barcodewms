package com.wms1.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class ManageUserController {

    @Autowired
    ManageUserRepo manageUserRepo;

    @PostMapping("/manageSession")
    public Map<String, Boolean> manageSession(@RequestBody ManageUser manageUser){
        List<ManageUser> manageUsers= manageUserRepo.checkUser(manageUser.getUser_name(),
                manageUser.getWarehouses());
        boolean check=false;
        if(manageUsers.size()==0){
            ManageUser manageUser1=manageUserRepo.save(manageUser);
            if(manageUser1.getId()>0) {
                check = true;
            }
        }
        Map<String, Boolean> hMap=new HashMap<>();
        hMap.put("checkUser",check);
        return hMap;
    }

    @GetMapping("logout")
    public Map<String, Boolean> logout(@RequestParam("user_name") String user_name
            ,@RequestParam("warehouses") String warehouses){
        boolean check=false;
        int logout= manageUserRepo.logout(user_name,warehouses);
        if (logout>0){
            check=true;
        }
        Map<String, Boolean> hMap=new HashMap<>();
        hMap.put("logout",check);
        return hMap;
    }
}
