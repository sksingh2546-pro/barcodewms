package com.wms1.login;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class ManageUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String user_name;
    private String warehouses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(String warehouses) {
        this.warehouses = warehouses;
    }
}
