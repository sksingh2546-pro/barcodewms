package com.wms1.cart;

import com.wms1.addProduct.AddProduct;
import com.wms1.addProduct.AddProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        if (productionCart.getType().equals("out")) {
            List<ProductionCart> productionCartList = productionCartRepo.getCartListByTypeName(
                    productionCart.getType(), productionCart.getUser_name(), productionCart.getName_of_item());

            if (productionCartList.size() == 0) {
                List<AddProduct> productList = addProductRepo.getProductBySkuNameList(
                        productionCart.getName_of_item(), productionCart.getUser_name());
                if (productList.size() > 0) {
                    if (productList.get(0).getQty() < productionCart.getQty()) {
                        int v = productionCart.getQty() - productList.get(0).getQty();
                        message = "{\"message\":\"Extra Added:" + v + "\"}";
                        return message;
                    } else {
                        ProductionCart production = productionCartRepo.save(productionCart);
                        if (production.getId() != 0) {
                            message = "{\"message\":\"Successful\"}";
                            return message;
                        }
                    }
                } else {
                    return message;
                }
            } else {
                List<AddProduct> productList = addProductRepo.getProductBySkuNameList(
                        productionCart.getName_of_item(), productionCart.getUser_name());
                if (productList.size() > 0) {
                    int qty = productionCartList.stream().mapToInt(ProductionCart::getQty).sum();
                    if (productList.get(0).getQty() < qty) {
                        int v = qty - productList.get(0).getQty();
                        message = "{\"message\":\"Extra Added:" + v + "\"}";
                        return message;
                    } else {
                        int a = productionCartRepo.updateQtyByBarcode(productionCart.getUser_name(), productionCart.getName_of_item(), productionCart.getQty(), productionCart.getUser_id());
                        if (a != 0) {
                            message = "{\"message\":\"Successful\"}";
                        }
                    }
                } else {
                    return message;
                }
            }
        } else {
            List<ProductionCart> productionCartList = productionCartRepo.getCartListByUserName(
                    productionCart.getUser_id(), productionCart.getType(), productionCart.getUser_name(), productionCart.getName_of_item());

            if (productionCartList.size() == 0) {
                ProductionCart production = productionCartRepo.save(productionCart);
                if (production.getId() != 0) {
                    message = "{\"message\":\"Successful\"}";
                }
            } else {
                int a = productionCartRepo.updateQtyByBarcode(productionCart.getUser_name(), productionCart.getName_of_item(), productionCart.getQty(), productionCart.getUser_id());
                if (a != 0) {
                    message = "{\"message\":\"Successful\"}";
                }
            }
        }


      /*
            if (productionCartList.size() == 0) {
                ProductionCart production = productionCartRepo.save(productionCart);
                if (production.getId() != 0) {
                    message = "{\"message\":\"Successful\"}";
                }
            } else {
                message = "{\"message\":\"Already Exist\"}";
            }
        } else {
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
        }*/
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
