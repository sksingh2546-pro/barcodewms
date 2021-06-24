package com.wms1.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ProductionCartController {
    @Autowired
    ProductionCartRepo productionCartRepo;

    @PostMapping("/addProductionCart")
    public String addCart(@RequestBody ProductionCart productionCart) {
        String message = "{\"message\":\"UnSuccessful\"}";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        productionCart.setDate(sdf.format(date));
        ProductionCart production = productionCartRepo.save(productionCart);
        if (production.getId() != 0) {
            message = "{\"message\":\"Successful\"}";
        }
        return message;
    }

    @GetMapping("/getCartList")
    public Map<String, List<ProductionCart>> getCartList(@RequestParam("user_name") String username,@RequestParam("type") String type) {
        List<ProductionCart> cartList = productionCartRepo.getCartListByUserName(username, type);
        HashMap<String, List<ProductionCart>> hashMap = new HashMap<>();
        hashMap.put("CartList", cartList);
        return hashMap;
    }

    @PostMapping("/deleteFromCart")
    public String deleteFromCart(@RequestParam int id) {
        String message = "{\"message\":\"UnSuccessful\"}";
        int delete = productionCartRepo.deleteCartItem(id);
        if (delete != 0) {
            message = "{\"message\":\"UnSuccessful\"}";
        }
        return message;
    }

}
