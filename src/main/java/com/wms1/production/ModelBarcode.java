package com.wms1.production;

import java.util.*;

public class ModelBarcode {
    List<Production> barcode_data;
    String user_name;

    public List<Production> getBarcode_data() {
        return barcode_data;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setBarcode_data(List<Production> barcode_data) {
        this.barcode_data = barcode_data;
    }
}
