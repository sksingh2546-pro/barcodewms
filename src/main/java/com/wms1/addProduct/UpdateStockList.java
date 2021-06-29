package com.wms1.addProduct;

import com.wms1.cart.ProductionCart;

import java.util.ArrayList;

public class UpdateStockList {
    public ArrayList<ProductionCart> getListProduction() {
        return listProduction;
    }

    public void setListProduction(ArrayList<ProductionCart> listProduction) {
        this.listProduction = listProduction;
    }

    ArrayList<ProductionCart> listProduction;
}
