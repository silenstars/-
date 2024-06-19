package com.example.demo.dao;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Delivery;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class DeliveryDao {

    private final String url = "jdbc:mysql://localhost:3306/test";
    private final String username = "root";
    private final String password = "1234";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getDeliveryRecord(JSONObject param, JSONObject json) {
        System.out.println("[DeliveryDao/getDeliveryRecord]执行到这里param=null");

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {

            System.out.println("[Delivery/getDeliveryRecord]Connection连接完毕");
            System.out.println("[DeliveryDao/getDeliveryRecord]Statement创建完毕");

            String sql = "select * from delivery order by delivery_name";
            List<Object> list = new ArrayList<>();

            try (ResultSet re = statement.executeQuery(sql)) {
                while (re.next()) {
                    Map<String, String> map = new HashMap<>();
                    map.put("delivery_id", re.getString("delivery_id"));
                    map.put("delivery_name", re.getString("delivery_name"));
                    map.put("storage_name", re.getString("storage_name"));
                    map.put("storage_time", re.getString("storage_time"));
                    map.put("destination", re.getString("destination"));
                    map.put("delivery_location", re.getString("delivery_location"));
                    map.put("courier", re.getString("courier"));
                    list.add(map);
                }
            }

            json.put("Data", list);
            json.put("result_code", 0);
            json.put("result_msg", "ok");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void addDeliveryRecord(Delivery delivery, JSONObject json) {
        /*--------------------函数开始 传递进来的参数在param里--------------------*/
        System.out.println("[DeliveryDao/addDeliveryRecord]执行到这里delivery=" + delivery.toString());
        /*--------------------数据库访问 开始--------------------*/

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {

            System.out.println("[DeliveryDao/addDeliveryRecord]Connection连接完毕");
            System.out.println("[DeliveryDao/addDeliveryRecord]Statement创建完毕");

            String delivery_id = delivery.getDelivery_id();
            String delivery_name = delivery.getDelivery_name();
            String storage_name = delivery.getStorage_name();
            String storage_time = delivery.getStorage_time();
            String destination = delivery.getDestination();
            String delivery_location = delivery.getDeliveryLocation();
            String courier = delivery.getCourier();

            String sql = "INSERT INTO delivery (delivery_id, delivery_name, storage_name, storage_time, destination, delivery_location, courier) " +
                    "VALUES ('" + delivery_id + "', '" + delivery_name + "', '" + storage_name + "', '" + storage_time + "', '" + destination + "', '" + delivery_location + "', '" + courier+ "')";

            List<Object> list = new ArrayList<>();
            try {
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            /*--------------------数据库访问 结束--------------------*/
            json.put("Data", list);
            json.put("result_code", 0);
            json.put("result_msg", "ok");
            /*--------------------函数结束 返回的结果存放在json里--------------------*/

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void deleteDeliveryRecord(String delivery_id, JSONObject json) {
        /*--------------------函数开始 传递进来的参数在param里--------------------*/
        System.out.println("[DeliveryDao/deleteDeliveryRecord]执行到这里，删除的ID=" + delivery_id);
        /*--------------------数据库访问 开始--------------------*/
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {

            System.out.println("[DeliveryDao/deleteDeliveryRecord]Connection连接完毕");
            System.out.println("[DeliveryDao/deleteDeliveryRecord]Statement创建完毕");

            String sql = "DELETE FROM delivery WHERE delivery_id='" + delivery_id + "'";
            System.out.println("[DeliveryDao/deleteDeliveryRecord]SQL语句：" + sql);

            int rowsAffected = statement.executeUpdate(sql);
            if (rowsAffected > 0) {
                json.put("result_code", 0);
                json.put("result_msg", "ok");
            } else {
                json.put("result_code", 1);
                json.put("result_msg", "Record not found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            json.put("result_code", 1);
            json.put("result_msg", "Database error: " + e.getMessage());
        }
        System.out.println("[DeliveryDao/deleteDeliveryRecord]数据库访问结束");
        /*--------------------数据库访问 结束--------------------*/
        /*--------------------函数结束 返回的结果存放在json里--------------------*/
    }
    public static List<Delivery> searchDeliveryRecord(String delivery_name) {
        String sql = "SELECT * FROM delivery WHERE delivery_name LIKE ?";
        List<Delivery> list = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "1234";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + delivery_name + "%");

            try (ResultSet re = statement.executeQuery()) {
                while (re.next()) {
                    Delivery delivery = new Delivery();
                    delivery.setDelivery_id(re.getString("delivery_id"));
                    delivery.setDelivery_name(re.getString("delivery_name"));
                    delivery.setStorage_name(re.getString("storage_name"));
                    delivery.setStorage_time(re.getString("storage_time"));
                    delivery.setDestination(re.getString("destination"));
                    delivery.setDelivery_location(re.getString("delivery_location"));
                    delivery.setCourier(re.getString("courier"));
                    list.add(delivery);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }
        return list;
    }
}
