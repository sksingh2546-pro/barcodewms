package com.wms1.production;

import com.wms1.addProduct.AddProduct;

import java.util.ArrayList;

public class ProductionModel {
    public ArrayList<AddProduct> getListProduction() {
        return listProduction;
    }

    public void setListProduction(ArrayList<AddProduct> listProduction) {
        this.listProduction = listProduction;
    }

    ArrayList<AddProduct> listProduction;

}
