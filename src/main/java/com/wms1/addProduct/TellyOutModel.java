package com.wms1.addProduct;

public class TellyOutModel {

    private String name_of_item;
    private int no_of_pcs;
    private int qty;
    private String warehouse;
    private String sales_no;
    private String date;

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

    public String getSales_no() {
        return sales_no;
    }

    public void setSales_no(String sales_no) {
        this.sales_no = sales_no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public TellyOutModel(String name_of_item, int no_of_pcs, int qty, String warehouse, String sales_no, String date) {
        this.name_of_item = name_of_item;
        this.no_of_pcs = no_of_pcs;
        this.qty = qty;
        this.warehouse = warehouse;
        this.sales_no = sales_no;
        this.date = date;
    }
}