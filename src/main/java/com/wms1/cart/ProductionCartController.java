package com.wms1.cart;

import com.wms1.addProduct.AddProduct;
import com.wms1.addProduct.AddProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ProductionCartController {
    @Autowired
    ProductionCartRepo productionCartRepo;
    @Autowired
    AddProductRepo addProductRepo;

    @PostMapping("/addProductionCart")
    public String addCart(@RequestBody ProductionCart productionCart) {
        String message = "{\"message\":\"UnSuccessful\"}";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        productionCart.setDate(sdf.format(date));
        List<ProductionCart> productionCartList = productionCartRepo.getCartListByBarcode(productionCart.getBarcode());
        List<AddProduct> addProductList = addProductRepo.getBarcodeList(productionCart.getBarcode()
                , productionCart.getUser_name());
        if (productionCartList.size() == 0 && addProductList.size() == 0) {
            ProductionCart production = productionCartRepo.save(productionCart);
            if (production.getId() != 0) {
                message = "{\"message\":\"Successful\"}";
            }
        } else {
            message = "{\"message\":\"Already Exist\"}";
        }
        return message;
    }

    @GetMapping("/getCartList")
    public Map<String, List<ProductionCart>> getCartList(@RequestParam("user_id") String user_id, @RequestParam("type") String type, @RequestParam("user_name") String username) {
        List<ProductionCart> cartList = productionCartRepo.getCartListByUserName(user_id, type, username);
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
