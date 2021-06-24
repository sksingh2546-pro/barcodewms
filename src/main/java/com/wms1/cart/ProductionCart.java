package com.wms1.cart;

import javax.persistence.*;

@Entity
@Table(name = "production_cart")
public class ProductionCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String barcode;
    private String name_of_item;
    private int no_of_pcs;
    private float per_pcs_weight;
    private int packaging;
    private float carton_gross_weight;
    private String hsn;
    private int qty;
    private String status;
    private String date;
    private String user_name;
    private String type;

    public ProductionCart(long id, String barcode, String name_of_item, int no_of_pcs, float per_pcs_weight, int packaging, float carton_gross_weight, String hsn, int qty, String status, String date, String user_name, String type) {
        this.id = id;
        this.barcode = barcode;
        this.name_of_item = name_of_item;
        this.no_of_pcs = no_of_pcs;
        this.per_pcs_weight = per_pcs_weight;
        this.packaging = packaging;
        this.carton_gross_weight = carton_gross_weight;
        this.hsn = hsn;
        this.qty = qty;
        this.status = status;
        this.date = date;
        this.user_name = user_name;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

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

    public float getPer_pcs_weight() {
        return per_pcs_weight;
    }

    public void setPer_pcs_weight(float per_pcs_weight) {
        this.per_pcs_weight = per_pcs_weight;
    }

    public int getPackaging() {
        return packaging;
    }

    public void setPackaging(int packaging) {
        this.packaging = packaging;
    }

    public float getCarton_gross_weight() {
        return carton_gross_weight;
    }

    public void setCarton_gross_weight(float carton_gross_weight) {
        this.carton_gross_weight = carton_gross_weight;
    }

    public String getHsn() {
        return hsn;
    }

    public void setHsn(String hsn) {
        this.hsn = hsn;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public ProductionCart() {
    }
}
