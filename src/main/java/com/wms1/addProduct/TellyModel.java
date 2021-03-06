package com.wms1.addProduct;

public class TellyModel {

    private String name_of_item;
    private int no_of_pcs;
    private int qty;
    private String warehouse;

    public String getName_of_item() {
        return name_of_item;
    }

    public void setName_of_item(String name_of_item) {
        this.name_of_item = name_of_item;
    }

    public int getNo_of_pcs() {
        return no_of_pcs;
    }

    public void setNo_of_pcs(int no_of_pcs) {
        this.no_of_pcs = no_of_pcs;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public TellyModel(String name_of_item, int no_of_pcs, int qty, String warehouse) {
        this.name_of_item = name_of_item;
        this.no_of_pcs = no_of_pcs;
        this.qty = qty;
        this.warehouse = warehouse;
    }
}