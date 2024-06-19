package com.example.demo.entity;

public class Warehouse {
    String id;
    String warehouse_name;
    String location;
    String createtime;
    String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWarehouse_name() {
        return warehouse_name;
    }

    public void setWarehouse_name(String warehouse_name) {
        this.warehouse_name = warehouse_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "Warehouse{" +
                "id='" + id + '\'' +
                ", Warehouse_name='" + warehouse_name + '\'' +
                ", Location='" + location + '\'' +
                ", phone='"+phone+'\''+
                ", createtime='" + createtime + '\'' +
                '}';
    }
}
