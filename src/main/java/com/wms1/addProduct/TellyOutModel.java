package com.wms1.addProduct;

public class TellyOutModel {

    private String name_of_item;
    private int no_of_pcs;
    private int qty;
    private float per_pcs_weight;
    private float packaging;
    private float carton_weight;
    private String hsn;
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

    public float getPer_pcs_weight() {
        return per_pcs_weight;
    }

    public void setPer_pcs_weight(float per_pcs_weight) {
        this.per_pcs_weight = per_pcs_weight;
    }

    public float getPackaging() {
        return packaging;
    }

    public void setPackaging(float packaging) {
        this.packaging = packaging;
    }

    public float getCarton_weight() {
        return carton_weight;
    }

    public void setCarton_weight(float carton_weight) {
        this.carton_weight = carton_weight;
    }

    public String getHsn() {
        return hsn;
    }

    public void setHsn(String hsn) {
        this.hsn = hsn;
    }

    public TellyOutModel(String name_of_item, int no_of_pcs, int qty, float per_pcs_weight, float packaging, float carton_weight, String hsn, String warehouse, String sales_no, String date) {
        this.name_of_item = name_of_item;
        this.no_of_pcs = no_of_pcs;
        this.qty = qty;
        this.per_pcs_weight = per_pcs_weight;
        this.packaging = packaging;
        this.carton_weight = carton_weight;
        this.hsn = hsn;
        this.warehouse = warehouse;
        this.sales_no = sales_no;
        this.date = date;
    }
}