package com.wms1.addProduct;

import com.wms1.production.Production;

import java.util.ArrayList;

public class UpdateStockList {
    public ArrayList<AddProduct> getListProduction() {
        return listProduction;
    }

    public void setListProduction(ArrayList<AddProduct> listProduction) {
        this.listProduction = listProduction;
    }

    ArrayList<AddProduct> listProduction;
}
