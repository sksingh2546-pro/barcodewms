package com.wms1.addProduct;

import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExpiryController {
     @Autowired
     AddProductRepo addProductRepo;

    public void getExpiredData() throws ParseException {
        Date date =new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        List<AddProduct>addProductList= (List<AddProduct>) addProductRepo.findAll();

        for(AddProduct addProduct:addProductList) {


            String timeIn =addProduct.getDate();
            String time_out = sdf.format(date);

            Date time = sdf.parse(timeIn);
            Date timeFormat = sdf.parse(time_out);

            long difference = timeFormat.getTime() - time.getTime();
            float daysBetween = (difference / (1000 *60 * 60 * 24));
            System.out.println("Number of Days between dates: " + daysBetween);
            //temp.add(daysBetween);
            if (daysBetween > 120) {
                addProductRepo.updateProductStatus(addProduct.getBarcode());
                }

        }

    }

}
