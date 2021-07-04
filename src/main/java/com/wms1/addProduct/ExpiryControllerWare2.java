package com.wms1.addProduct;

import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExpiryControllerWare2 {
    @Autowired
    AddProductRepo addProductRepo;

    public void getExpiredDataWare2() throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<AddProduct> addProductList = (List<AddProduct>) addProductRepo.getDataWithUser_name("warehouse2");

        for (AddProduct addProduct : addProductList) {

            String timeIn = addProduct.getDate();
            String time_out = sdf.format(date);

            Date time = sdf.parse(timeIn);
            Date timeFormat = sdf.parse(time_out);

            long difference = timeFormat.getTime() - time.getTime();
            float daysBetween = (difference / (1000 * 60 * 60 * 24));
            System.out.println("Number of Days between dates: " + daysBetween);
            //temp.add(daysBetween);
            if (daysBetween > 120) {
                addProductRepo.updateProductStatus(addProduct.getName_of_item(), "warehouse2");
            }
        }
    }
}
