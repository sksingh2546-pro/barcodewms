package com.wms1.out;

import javax.persistence.*;

@Entity
@Table(name="sales_no")
public class Out {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String sales_no;
    private int status;
    private String user_name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSales_no() {
        return sales_no;
    }

    public void setSales_no(String sales_no) {
        this.sales_no = sales_no;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
