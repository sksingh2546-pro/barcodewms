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
    public String generateOrder(@RequestBody Out out)
    {   String message="Unsuccessful";
        Out out1=outRepo.save(out);
        if(out1.getId()!=0){
            message="Successful";
        }
        return message;
    }

    @GetMapping("/getSalesNo")
    public Map<String, List<Out>> getSalesNo(){
        Map<String, List<Out>> hMap=new HashMap<>();
        hMap.put("salesNo",outRepo.getOutData());
        return  hMap;
    }

    @PostMapping("/updateSalesNo")
    public String updateSalesNo(@RequestParam("sales_no") String sales_no,@RequestParam("user_name")String user_name)
    {   String message = "{\"message\":\"Unsuccessful\"}";
        int update=outRepo.updateStatus(sales_no,user_name);
        if(update>0){
                message = "{\"message\":\"Successful\"}";
        }
        return  message;
    }
}
