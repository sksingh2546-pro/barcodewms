package com.wms1.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OutController {

    @Autowired
    OutRepo outRepo;

    @PostMapping("/generateOrder")
    public String generateOrder(@RequestBody Out out) {
        String message = "Unsuccessful";
        if (outRepo.checkOutData(out.getSales_no(), out.getUser_name()).size() > 0) {
            message = "Already";
        } else {
            Out out1 = outRepo.save(out);
            if (out1.getId() != 0) {
                message = "Successful";
            }
        }
        return message;
    }

    @GetMapping("/getSalesNo")
    public Map<String, List<Out>> getSalesNo(@RequestParam("user_name") String user_name) {
        Map<String, List<Out>> hMap = new HashMap<>();
        hMap.put("salesNo", outRepo.getOutDataStatus0(user_name));
        return hMap;
    }

    @GetMapping("/getSalesNoWithUser")
    public Map<String, List<Out>> getSalesNoWithUser(@RequestParam("user_name") String user_name) {
        Map<String, List<Out>> hMap = new HashMap<>();
        hMap.put("salesNo", outRepo.getOutData(user_name));
        return hMap;
    }

    @PostMapping("/updateSalesNo")
    public String updateSalesNo(@RequestParam("sales_no") String sales_no, @RequestParam("user_name") String user_name) {
        String message = "{\"message\":\"Unsuccessful\"}";
        int update = outRepo.updateStatus(sales_no, user_name);
        if (update > 0) {
            message = "{\"message\":\"Successful\"}";
        }
        return message;
    }
  @PostMapping("/updateSalesNo1")
    public String updateSalesNo1(@RequestParam("sales_no") String sales_no, @RequestParam("user_name") String user_name) {
        String message = "{\"message\":\"Unsuccessful\"}";
        int update = outRepo.updateStatus1(sales_no, user_name);
        if (update > 0) {
            message = "{\"message\":\"Successful\"}";
        }
        return message;
    }
}
