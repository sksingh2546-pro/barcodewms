package com.wms1.excel;

import com.wms1.addProduct.AddProductRepo;
import com.wms1.production.Production;
import com.wms1.production.ProductionRepo;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.Action;
import java.util.*;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ExcelController {
    @Autowired
    ExcelRepo excelRepo;
    @Autowired
    ProductionRepo productionRepo;
  @Autowired
    AddProductRepo addProductRepo;

    @PostMapping("uploadExcel")
    public String uploadExcel(@RequestParam("file") MultipartFile multipartFile
            ,@RequestParam("user_name") String user_name) {
        List<Excel> excelList= (List<Excel>) excelRepo.findAll();
        String response = "{\"message\":\"Unsuccessful\"}";
        if(excelList.size()>0){
            response = "{\"message\":\"Already\"}";
        }
        else {
        Workbook workbook = null;
        try {
            workbook = (Workbook) new XSSFWorkbook(multipartFile.getInputStream());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);



        for (int i = 1; i <= sheet.getLastRowNum(); ++i) {
            try {
                if (sheet.getRow(i).getCell(0).getCellType() != CellType.BLANK) {
                    try {

                        Excel excel = new Excel(sheet.getRow(i).getCell(0).getStringCellValue().trim(),
                                (int)sheet.getRow(i).getCell(1).getNumericCellValue(),
                                (float) sheet.getRow(i).getCell(2).getNumericCellValue(),
                               (int) sheet.getRow(i).getCell(3).getNumericCellValue(),
                                (float) sheet.getRow(i).getCell(4).getNumericCellValue(),
                                String.valueOf((long) sheet.getRow(i).getCell(5).getNumericCellValue())
                                 ,user_name);
                        int count=sheet.getRow(i).getPhysicalNumberOfCells();
                        System.out.println(count);
                        Excel excel1 = excelRepo.save(excel);


                        if (excel1.getId()!=0) {
                            response = "{\"message\":\"Successful\"}";
                        }

                    } catch (Exception e2) {
                        System.out.println("Exception :  " + e2.getMessage());
                    }
                }
            }catch (Exception e3){
                System.out.println("Exception"+e3.getMessage());
            }
        }}
        return response;
    }



    @PostMapping("addSku")
    public String addSku(@RequestBody Excel excel){
        String message = "Not Save";
        if(excelRepo.getNameOfItemList(excel.getName_of_item(),excel.getUser_name()).size()>0){
           message = "Already";
        }
        else {
            Excel ex = excelRepo.save(excel);

            if (ex.getId() != 0) {
                message = "Save";
            }
        }
        return message;
    }

    @PostMapping("/updateSku")
    public String updateSku(@RequestBody Excel excel,@RequestParam("nameOfItem") String nameOfItem){
        boolean check=false;
        String message="Not Update";
        if(!excel.getName_of_item().equalsIgnoreCase(nameOfItem)){
            if(excelRepo.getNameOfItemList(excel.getName_of_item(),excel.getUser_name()).size()>0){
              message="Already";
              check=true;
            }
        }
          if(!check) {
              int update = excelRepo.updateSku(excel.getName_of_item(), excel.getHsn(), excel.getCarton_gross_weight(),
                      excel.getPackaging(), excel.getPer_pcs_weight(), excel.getNum_pcs(), nameOfItem, excel.getUser_name());
              if (update > 0) {

                  productionRepo.updateProduction(excel.getNum_pcs(), excel.getName_of_item(), excel.getPer_pcs_weight(),
                          excel.getPackaging(), excel.getCarton_gross_weight(), excel.getHsn(), nameOfItem, excel.getUser_name());
                  addProductRepo.updateAddProduction(excel.getNum_pcs(), excel.getName_of_item(), excel.getPer_pcs_weight(),
                          excel.getPackaging(), excel.getCarton_gross_weight(), excel.getHsn(), nameOfItem, excel.getUser_name());
                  message = "Update";
              }
          }
        return message;
    }

    @GetMapping("getNameDetails")
    public Map<String, List<Excel>>getAllList(@RequestParam("name_of_item")String name_of_item,
                                              @RequestParam("user_name")String user_name){
        List<Excel>excels=excelRepo.getNameOfItemList(name_of_item,user_name);
        HashMap<String,List<Excel>>hMap=new HashMap<>();
        hMap.put("itemDetails",excels);
        return hMap;
    }
    @GetMapping("getNameItemList")
    public Map<String,Set<String>>getNameItemList(@RequestParam("user_name")String user_name){
        Set<String>temp=new HashSet<>();
        List<Excel>excelList=excelRepo.getNameOfItemList1(user_name);
       // List<Excel>excelList= (List<Excel>) excelRepo.findAll();
        for (Excel excel:excelList){
            temp.add(excel.getName_of_item());
        }
    HashMap<String,Set<String>>hMap=new HashMap<>();
        hMap.put("ItemList",temp);
        return hMap;
    }

    @GetMapping("getAllData")
    public Map<String,List<Excel>>getExcel(@RequestParam("user_name") String user_name){
        HashMap<String,List<Excel>>hMap=new HashMap<>();
        hMap.put("product", (List<Excel>) excelRepo.getNameOfItemList1(user_name));
        return hMap;
    }

}
