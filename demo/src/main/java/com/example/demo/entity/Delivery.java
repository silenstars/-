package com.example.demo.entity;

public class Delivery {
    String delivery_id;
    String delivery_name;
    String storage_name;
    String storage_time;
    String destination;
    String delivery_location;
    String courier;
    public String getDelivery_id() {
        return delivery_id;
    }

    public void setDelivery_id(String delivery_id) {
        this.delivery_id = delivery_id;
    }

    public String getDelivery_name() {
        return delivery_name;
    }

    public void setDelivery_name(String delivery_name) {
        this.delivery_name = delivery_name;
    }

    public String getStorage_name() {
        return storage_name;
    }

    public void setStorage_name(String storage_name) {
        this.storage_name = storage_name;
    }

    public String getStorage_time(){return storage_time;}

    public void setStorage_time(String storage_time) {
        this.storage_time = storage_time;
    }

    public String getDestination(){return destination;}

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeliveryLocation() {
        return delivery_location;
    }

    public void setDelivery_location(String delivery_location) {
        this.delivery_location = delivery_location;
    }

    public String getCourier(){return courier;}

    public void setCourier(String courier) {
        this.courier = courier;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "delivery_id='" + delivery_id + '\'' +
                ", Delivery_name='" + delivery_name + '\'' +
                ", Storage_name='" + storage_name + '\'' +
                ", storage_time='" + storage_time + '\'' +
                ", destination='" + destination + '\'' +
                ", delivery_Location='" + delivery_location + '\'' +
                ", courier='" + courier + '\'' +
                '}';
    }
}
