package com.wms1.production;

import com.wms1.addProduct.AddProduct;
import com.wms1.config.BarcodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.awt.image.BufferedImage;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;




@RestController
@RequestMapping("/api")
public class ProductionController{
    @Autowired
    ProductionRepo productionRepo;
    @PostMapping(value = "insertProduction",produces = IMAGE_PNG_VALUE)
    public String insertProduction(@RequestBody ModelBarcode modelBarcode) throws Exception {

        String message = "{\"message\":\"UnSuccessful\"}";

        for (Production production : modelBarcode.getBarcode_data()) {
            System.out.println(modelBarcode.getUser_name());
            List<Production> productionList = productionRepo.getBarcodeList(production.getBarcode(),
                    modelBarcode.getUser_name());
            if (productionList.size() > 0) {
                message ="{\"message\":\"Already Exist\"}";
            } else {
                productionRepo.insertData(production.getBarcode(), production.getName_of_item(), production.getPer_pcs_weight(),
                        production.getPackaging(), production.getCarton_gross_weight(),
                        production.getHsn(),production.getNum_pcs(),modelBarcode.getUser_name());

                message ="{\"message\":\"Successful\"}";
            }
        }
        return  message;
    }

    @GetMapping("getProductBarcodeList")
    public Map<String,List<Production>>getProductBarcode(@RequestParam("barcode")String barcode,
                                                         @RequestParam("user_name") String user_name){
        List<Production>addProducts=productionRepo.getBarcodeList(barcode,user_name);
        HashMap<String,List<Production>>hMap=new HashMap<>();
        hMap.put("productDetails",addProducts);
        return hMap;
    }



}
