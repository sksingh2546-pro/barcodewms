package com.wms1.addProduct;

import com.wms1.cart.ProductionCartRepo;
import com.wms1.production.ProductionModel;
import com.wms1.todayIn.TodayIn;
import com.wms1.todayIn.TodayInRepo;
import com.wms1.todayOut.TodayOut;
import com.wms1.todayOut.TodayOutRepo;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api")
public class AddProductController {
    @Autowired
    AddProductRepo addProductRepo;
    @Autowired
    TodayOutRepo todayOutRepo;
    @Autowired
    TodayInRepo todayInRepo;
    @Autowired
    ProductionCartRepo productionCartRepo;

    @PostMapping("insertAddProduct")
    public String insertAddProduct(@RequestBody AddProduct addProduct) {
        String message = "{\"message\":\"UnSuccessful\"}";
        addProductRepo.deleteAddProductWithZero();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<AddProduct> addProductList = addProductRepo.getProductBySkuNameList(addProduct.getName_of_item()
                , addProduct.getUser_name());
        if (addProductList.size() > 0) {
            message = "{\"message\":\"Already Exist\"}";
        } else {
            int insert = addProductRepo.insertData(addProduct.getName_of_item(), addProduct.getNo_of_pcs(),
                    addProduct.getPer_pcs_weight(), addProduct.getPackaging(), addProduct.getCarton_gross_weight(),
                    addProduct.getHsn(), sdf.format(date), addProduct.getQty(), addProduct.getUser_name());
            if (insert > 0) {
                message = "{\"message\":\"Successful\"}";
            }
            TodayIn todayIn = new TodayIn();
            todayIn.setName_of_item(addProduct.getName_of_item());
            todayIn.setNo_of_pcs(addProduct.getNo_of_pcs());
            todayIn.setPer_pcs_weight(addProduct.getPer_pcs_weight());
            todayIn.setPackaging(addProduct.getPackaging());
            todayIn.setCarton_gross_weight(addProduct.getCarton_gross_weight());
            todayIn.setHsn(addProduct.getHsn());
            todayIn.setDate(sdf.format(date));
            todayIn.setQty(addProduct.getQty());
            todayIn.setUser_name(addProduct.getUser_name());
            todayInRepo.save(todayIn);
        }
        return message;
    }


    @GetMapping("getAllList")
    public Map<String, List<AddProduct>> getBarcode(@RequestParam("user_name") String user_name) {
        List<AddProduct> addProducts = addProductRepo.getDataWithUser_name(user_name);
        HashMap<String, List<AddProduct>> hMap = new HashMap<>();
        hMap.put("barcodeList", addProducts);
        return hMap;
    }

    @GetMapping("getQtyByNameOfItem")
    public Map<String, Integer> getQtyByNameOfItem(@RequestParam("user_name") String user_name,
                                                   @RequestParam("name_of_item") String name_of_item) {
        int qty = 0;
        int value = 0;
        try {
            value = productionCartRepo.getCartQtyBySalesNo("out", user_name, name_of_item);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            qty = addProductRepo.getNameOfItem(user_name, name_of_item);
            qty -= value;
        } catch (Exception ignored) {
        }
        Map<String, Integer> hMap = new HashMap<>();
        hMap.put("qty", qty);
        return hMap;
    }

    @PostMapping("updateStockData")
    public String getBarcodeProduct(@RequestBody AddProduct addProduct, @RequestParam("sales_no") String sales_no) {
        String message = "{\"message\":\"UnSuccessful\"}";
        addProductRepo.deleteAddProductWithZero();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<AddProduct> addProducts = addProductRepo.getProductBySkuNameList(addProduct.getName_of_item(),
                addProduct.getUser_name(), sdf.format(date));
        if (addProducts.size() > 0) {
            int update = addProductRepo.updateProduction(addProducts.get(0).getQty() - addProduct.getQty(),
                    addProduct.getName_of_item(), addProduct.getUser_name());
            if (update > 0) {
                message = "{\"message\":\"Updated\"}";
            }
            TodayOut todayOut = new TodayOut();
            todayOut.setName_of_item(addProduct.getName_of_item());
            todayOut.setNo_of_pcs(addProduct.getNo_of_pcs());
            todayOut.setPer_pcs_weight(addProduct.getPer_pcs_weight());
            todayOut.setPackaging(addProduct.getPackaging());
            todayOut.setCarton_gross_weight(addProduct.getCarton_gross_weight());
            todayOut.setHsn(addProduct.getHsn());
            todayOut.setDate(sdf.format(date));
            todayOut.setSales_no(sales_no);
            todayOut.setQty(addProduct.getQty());
            todayOut.setUser_name(addProduct.getUser_name());
            todayOutRepo.save(todayOut);
        }
        return message;
    }

    @GetMapping("getNameOfItem")
    public Map<String, List<AddProductModel>> getSUmOfQuantity(@RequestParam("user_name") String user_name) {
        Set<String> addProductModels = addProductRepo.getNameOfItem(user_name);
        List<AddProductModel> addProductModels1 = new ArrayList<>();
        for (String nameOfProduct : addProductModels) {
            int qty = addProductRepo.sumOfQuantity(nameOfProduct, user_name);
            List<AddProduct> addProduct = addProductRepo.getDataWithNameOfItem(nameOfProduct, user_name);
            if (addProduct.size() > 0) {
                AddProductModel addProductModel = new AddProductModel(addProduct.get(0).getName_of_item(), addProduct.get(0).getNo_of_pcs()
                        , addProduct.get(0).getPer_pcs_weight(), addProduct.get(0).getPackaging(),
                        addProduct.get(0).getCarton_gross_weight(), addProduct.get(0).getHsn(), qty, addProduct.get(0).getDate());
                addProductModels1.add(addProductModel);
            }
        }
        HashMap<String, List<AddProductModel>> hMap = new HashMap<>();
        hMap.put("sum", addProductModels1);
        return hMap;


    }

    @GetMapping("getTotalInTotalOut")
    public Map<String, List<Map<String, Integer>>> getTotalInOut(@RequestParam("user_name") String user_name) {
        int todayIn = 0, totalOut = 0, totalIn = 0, todayOut = 0;
        List<Map<String, Integer>> mainList = new ArrayList<>();
        Map<String, Integer> addCount = new HashMap<>();
        addCount.put("TotalOut", totalOut);

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        addCount.put("", totalIn);
        try {
            totalIn = addProductRepo.sumOfQuantity1(user_name);
        } catch (Exception e) {
            totalIn = 0;
        }

        try {
            totalOut = todayOutRepo.sumOfQuantity1(user_name);
        } catch (Exception e) {
            totalOut = 0;
        }

        try {
            todayIn = todayInRepo.sumOfQuantity(sdf.format(date), user_name);
        } catch (Exception e) {
            todayIn = 0;
        }

        try {
            todayOut = todayOutRepo.sumOfQuantity(sdf.format(date));
        } catch (Exception e) {
            todayOut = 0;
        }

        addCount.put("todayOut", todayOut);
        addCount.put("TotalOut", totalOut);
        addCount.put("totalIn", totalIn);
        addCount.put("todayIn", todayIn);


        mainList.add(addCount);
        Map<String, List<Map<String, Integer>>> hMap = new HashMap<>();
        hMap.put("data", mainList);
        return hMap;

    }

    @GetMapping("generateExcelStock")
    public void generateExcelStock(HttpServletResponse response, @RequestParam("user_name") String user_name) throws IOException {
        Set<String> addProductModels = addProductRepo.getNameOfItem(user_name);
        List<AddProductModel> addProductModels1 = new ArrayList<>();
        for (String nameOfProduct : addProductModels) {
            int qty = addProductRepo.sumOfQuantity(nameOfProduct, user_name);
            List<AddProduct> addProduct = addProductRepo.getDataWithNameOfItem(nameOfProduct, user_name);
            if (addProduct.size() > 0) {
                AddProductModel addProductModel = new AddProductModel(addProduct.get(0).getName_of_item(), addProduct.get(0).getNo_of_pcs()
                        , addProduct.get(0).getPer_pcs_weight(), addProduct.get(0).getPackaging(),
                        addProduct.get(0).getCarton_gross_weight(), addProduct.get(0).getHsn(), qty, addProduct.get(0).getDate());
                addProductModels1.add(addProductModel);
            }
        }

        Workbook workbook = new HSSFWorkbook();
        HSSFCellStyle style1 = (HSSFCellStyle) workbook.createCellStyle();
        CellStyle style0 = workbook.createCellStyle();

        style0.setVerticalAlignment(VerticalAlignment.CENTER);
        style0.setAlignment(HorizontalAlignment.CENTER);

        style0.setBorderBottom(BorderStyle.THIN);
        style0.setBorderTop(BorderStyle.THIN);
        style0.setBorderLeft(BorderStyle.THIN);
        style0.setBorderRight(BorderStyle.THIN);

        style0.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style0.setFillPattern(FillPatternType.SOLID_FOREGROUND);


        style1.setAlignment(HorizontalAlignment.CENTER);
        style1.setVerticalAlignment(VerticalAlignment.CENTER);
        style1.setBorderBottom(BorderStyle.THIN);
        style1.setBorderTop(BorderStyle.THIN);
        style1.setBorderLeft(BorderStyle.THIN);
        style1.setBorderRight(BorderStyle.THIN);

        org.apache.poi.ss.usermodel.Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 10);

        org.apache.poi.ss.usermodel.Font font1 = workbook.createFont();
        font1.setFontHeightInPoints((short) 10);

        style0.setFont((org.apache.poi.ss.usermodel.Font) font);
        style0.setWrapText(true);

        style1.setFont((org.apache.poi.ss.usermodel.Font) font1);
        style1.setWrapText(true);
        Sheet sheet1 = workbook.createSheet("Today's Data");

        sheet1.setColumnWidth(0, 5000);
        sheet1.setColumnWidth(5, 5000);
        sheet1.setColumnWidth(4, 5000);
        sheet1.setColumnWidth(3, 5000);
        sheet1.setColumnWidth(2, 5000);
        sheet1.setColumnWidth(1, 5000);


        Row row0 = sheet1.createRow(0);
        row0.setHeight((short) 600);

        Cell cell0 = row0.createCell(0);
        Cell cell1 = row0.createCell(1);
        Cell cell2 = row0.createCell(2);
        Cell cell3 = row0.createCell(3);
        Cell cell4 = row0.createCell(4);
        Cell cell5 = row0.createCell(5);
        Cell cell6 = row0.createCell(6);
        Cell cell7 = row0.createCell(7);


        cell0.setCellStyle(style0);
        cell1.setCellStyle(style0);
        cell2.setCellStyle(style0);
        cell3.setCellStyle(style0);
        cell4.setCellStyle(style0);
        cell5.setCellStyle(style0);
        cell6.setCellStyle(style0);
        cell7.setCellStyle(style0);

        cell0.setCellValue("SL NO");
        cell1.setCellValue("NAME OF ITEM");
        cell2.setCellValue("NO. OF PCS");
        cell3.setCellValue("PER PCS WEIGHT ");
        cell4.setCellValue("PACKAGING");
        cell5.setCellValue("CARTON GROSS WEIGHT");
        cell6.setCellValue("HSN");
        cell7.setCellValue("QTY");
        int rowCount = 1;

        for (AddProductModel addProductModel : addProductModels1) {
            Row row1 = sheet1.createRow(rowCount++);
            Cell slNo = row1.createCell(0);
            Cell nameOfItem = row1.createCell(1);
            Cell perPcsWeight = row1.createCell(3);
            Cell noOfPcs = row1.createCell(2);
            Cell packaging = row1.createCell(4);
            Cell cartoonGrossWeight = row1.createCell(5);
            Cell hsn = row1.createCell(6);
            Cell qty = row1.createCell(7);


            slNo.setCellStyle(style1);
            nameOfItem.setCellStyle(style1);
            noOfPcs.setCellStyle(style1);
            packaging.setCellStyle(style1);
            cartoonGrossWeight.setCellStyle(style1);
            perPcsWeight.setCellStyle(style1);
            hsn.setCellStyle(style1);
            qty.setCellStyle(style1);

            slNo.setCellValue(rowCount - 1);
            nameOfItem.setCellValue(addProductModel.getName_of_item());
            noOfPcs.setCellValue(addProductModel.getNo_of_pcs());
            perPcsWeight.setCellValue(addProductModel.getPer_pcs_weight());
            packaging.setCellValue(addProductModel.getPackaging());
            cartoonGrossWeight.setCellValue(addProductModel.getCarton_gross_weight());
            hsn.setCellValue(addProductModel.getHsn());
            qty.setCellValue(addProductModel.getQty());
        }
        response.setHeader("content-disposition", "attachment;filename=Production Report_" + ".xls");
        workbook.write(response.getOutputStream());


    }


    @GetMapping("getXmlModelData")
    public Map<String, List<TellyModel>> getXmlData(@RequestParam("user_name") String user_name) {
        Set<String> xmlModels = addProductRepo.getNameOfItem(user_name);
        List<TellyModel> xmlModels1 = new ArrayList<>();
        for (String nameOfProduct : xmlModels) {
            int qty = addProductRepo.sumOfQuantity(nameOfProduct, user_name);
            List<AddProduct> addProduct = addProductRepo.getDataWithNameOfItem(nameOfProduct, user_name);
            if (addProduct.size() > 0) {
                TellyModel tellyModel = new TellyModel(addProduct.get(0).getName_of_item(),
                        addProduct.get(0).getNo_of_pcs(), qty
                        , addProduct.get(0).getUser_name());

                xmlModels1.add(tellyModel);
            }
        }
        HashMap<String, List<TellyModel>> hMap = new HashMap<>();
        hMap.put("stock", xmlModels1);
        return hMap;


    }


    @GetMapping("getProductWithBarcode")
    public Map<String, List<AddProduct>> getProductWithBarcode(@RequestParam("name_of_item") String name_of_item,
                                                               @RequestParam("user_name") String user_name) {
        addProductRepo.deleteAddProductWithZero();
        List<AddProduct> addProductList = addProductRepo.getProductBySkuNameList(name_of_item, user_name);
        HashMap<String, List<AddProduct>> hMap = new HashMap<>();
        hMap.put("data", addProductList);
        return hMap;
    }

    @GetMapping("/getExpireList")
    public Map<String, List<AddProduct>> getExpireList(@RequestParam("user_name") String user_name) {

        List<AddProduct> getExpireList = addProductRepo.getExpireList(user_name);
        Map<String, List<AddProduct>> hMap = new HashMap<>();
        hMap.put("expire", getExpireList);

        return hMap;
    }


    @PostMapping("/addProductionByList")
    public String insertProductByList(@RequestBody ProductionModel addProduct, @RequestParam("type") String type,
                                      @RequestParam("user_id") String user_id) {
        String[] message = {"{\"message\":\"UnSuccessful\"}"};
        addProductRepo.deleteAddProductWithZero();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        addProduct.getListProduction().forEach(addProduct1 -> {

            List<AddProduct> addProductList = addProductRepo.getProductBySkuNameList(addProduct1.getName_of_item()
                    , addProduct1.getUser_name());
            if (addProductList.size() > 0) {
                int update = addProductRepo.updateProduction(addProductList.get(0).getQty() + addProduct1.getQty(),
                        addProduct1.getName_of_item(), addProduct1.getUser_name());
                if (update > 0) {
                    message[0] = "{\"message\":\"Updated\"}";
                    int a = productionCartRepo.deleteCartItem(addProduct1.getUser_name(), addProduct1.getName_of_item(), type, user_id );
                }
            } else {
                int insert = addProductRepo.insertData(addProduct1.getName_of_item(), addProduct1.getNo_of_pcs(),
                        addProduct1.getPer_pcs_weight(), addProduct1.getPackaging(), addProduct1.getCarton_gross_weight(),
                        addProduct1.getHsn(), sdf.format(date), addProduct1.getQty(), addProduct1.getUser_name());
                if (insert > 0) {
                    message[0] = "{\"message\":\"Successful\"}";
                    int a = productionCartRepo.deleteCartItem(addProduct1.getUser_name(), addProduct1.getName_of_item(), type, user_id);
                }
                TodayIn todayIn = new TodayIn();
                todayIn.setName_of_item(addProduct1.getName_of_item());
                todayIn.setNo_of_pcs(addProduct1.getNo_of_pcs());
                todayIn.setPer_pcs_weight(addProduct1.getPer_pcs_weight());
                todayIn.setPackaging(addProduct1.getPackaging());
                todayIn.setCarton_gross_weight(addProduct1.getCarton_gross_weight());
                todayIn.setHsn(addProduct1.getHsn());
                todayIn.setDate(sdf.format(date));
                todayIn.setQty(addProduct1.getQty());
                todayIn.setUser_name(addProduct1.getUser_name());
                todayInRepo.save(todayIn);
            }
        });

        return message[0];
    }

    @PostMapping("updateStockDataList")
    public String updateStockDataList(@RequestBody UpdateStockList updateStockList, @RequestParam("sales_no") String sales_no) {
        String[] message = {"{\"message\":\"UnSuccessful\"}"};
        addProductRepo.deleteAddProductWithZero();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        updateStockList.listProduction.forEach(addProduct -> {
                    List<AddProduct> addProducts = addProductRepo.getProductBySkuNameList(addProduct.getName_of_item(),
                            addProduct.getUser_name());
                    if (addProducts.size() > 0) {
                        if (addProduct.getQty() <= addProducts.get(0).getQty()) {
                            int update = addProductRepo.updateProduction(addProducts.get(0).getQty() - addProduct.getQty(),
                                    addProduct.getName_of_item(), addProduct.getUser_name());
                            if (update > 0) {
                                message[0] = "{\"message\":\"Updated\"}";
                                int a = productionCartRepo.deleteCartItem(addProduct.getUser_name(), addProduct.getName_of_item(), addProduct.getType(), addProduct.getUser_id(),addProduct.getSales_no());
                                int b = a;
                            }
                            TodayOut todayOut = new TodayOut();
                            todayOut.setName_of_item(addProduct.getName_of_item());
                            todayOut.setNo_of_pcs(addProduct.getNo_of_pcs());
                            todayOut.setPer_pcs_weight(addProduct.getPer_pcs_weight());
                            todayOut.setPackaging(addProduct.getPackaging());
                            todayOut.setCarton_gross_weight(addProduct.getCarton_gross_weight());
                            todayOut.setHsn(addProduct.getHsn());
                            todayOut.setDate(sdf.format(date));
                            todayOut.setSales_no(addProduct.getSales_no());
                            todayOut.setQty(addProduct.getQty());
                            todayOut.setUser_name(addProduct.getUser_name());
                            todayOutRepo.save(todayOut);
                        }
                    } else {
                        int a = productionCartRepo.deleteCartItem(addProduct.getUser_name(), addProduct.getName_of_item(), addProduct.getType(), addProduct.getUser_id(),addProduct.getSales_no());
                    }
                }
        );
        return message[0];
    }

}
