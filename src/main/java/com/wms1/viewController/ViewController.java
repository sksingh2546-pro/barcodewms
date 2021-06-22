package com.wms1.viewController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {
    @RequestMapping(value = {"/"}, method = {RequestMethod.GET})
    public String login(Model model) {
        model.addAttribute("msg", (Object) "Please Enter Your Login Details");
        return "login.html";
    }

   @RequestMapping(value = {"/index"}, method = {RequestMethod.GET})
    public String index(Model model) {
        model.addAttribute("msg", (Object) "Please Enter Your Login Details");
        return "index.html";
    }
@RequestMapping(value = {"/changePassword"}, method = {RequestMethod.GET})
    public String changePassword(Model model) {
        model.addAttribute("msg", (Object) "Please Enter Your Login Details");
        return "changePassword.html";
    }
@RequestMapping(value = {"/excelImport"}, method = {RequestMethod.GET})
    public String excelImport(Model model) {
        model.addAttribute("msg", (Object) "Please Enter Your Login Details");
        return "excelImport.html";
    }
@RequestMapping(value = {"/out"}, method = {RequestMethod.GET})
    public String out(Model model) {
        model.addAttribute("msg", (Object) "Please Enter Your Login Details");
        return "out.html";
    }
@RequestMapping(value = {"/StockOut"}, method = {RequestMethod.GET})
    public String StockOut(Model model) {
        model.addAttribute("msg", (Object) "Please Enter Your Login Details");
        return "StockOut.html";
    }
@RequestMapping(value = {"/todayStock"}, method = {RequestMethod.GET})
    public String todayStock(Model model) {
        model.addAttribute("msg", (Object) "Please Enter Your Login Details");
        return "todayStock.html";
    }
@RequestMapping(value = {"/totalStock"}, method = {RequestMethod.GET})
    public String totalStock(Model model) {
        model.addAttribute("msg", (Object) "Please Enter Your Login Details");
        return "totalStock.html";
    }

@RequestMapping(value = {"/todayInExcel"}, method = {RequestMethod.GET})
    public String todayInExcel(Model model) {
        model.addAttribute("msg", (Object) "Please Enter Your Login Details");
        return "todayInExcel.html";
    }

@RequestMapping(value = {"/totalExcel"}, method = {RequestMethod.GET})
    public String totalExcel(Model model) {
        model.addAttribute("msg", (Object) "Please Enter Your Login Details");
        return "totalExcel.html";
    }

@RequestMapping(value = {"/todayOutExcel"}, method = {RequestMethod.GET})
    public String todayOutExcel(Model model) {
        model.addAttribute("msg", (Object) "Please Enter Your Login Details");
        return "todayOutExcel.html";
    }

@RequestMapping(value = {"/totalSku"}, method = {RequestMethod.GET})
    public String totalSku(Model model) {
        model.addAttribute("msg", (Object) "Please Enter Your Login Details");
        return "totalSku.html";
    }


}
