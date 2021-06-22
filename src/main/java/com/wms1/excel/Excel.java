package com.wms1.excel;

import javax.persistence.*;

@Entity
@Table(name = "excel_upload")
public class Excel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name_of_item;
    private int num_pcs;
    private float per_pcs_weight;
    private int packaging;
    private float carton_gross_weight;
    private String hsn;
    private String user_name;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName_of_item() {
        return name_of_item;
    }

    public void setName_of_item(String name_of_item) {
        this.name_of_item = name_of_item;
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

    public int getNum_pcs() {
        return num_pcs;
    }

    public void setNum_pcs(int num_pcs) {
        this.num_pcs = num_pcs;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Excel() {
    }

    public Excel(String name_of_item, int num_pcs, float per_pcs_weight, int packaging, float carton_gross_weight, String hsn, String user_name) {
        this.name_of_item = name_of_item;
        this.num_pcs = num_pcs;
        this.per_pcs_weight = per_pcs_weight;
        this.packaging = packaging;
        this.carton_gross_weight = carton_gross_weight;
        this.hsn = hsn;
        this.user_name = user_name;
    }
}
