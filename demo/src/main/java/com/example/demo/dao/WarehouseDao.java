package com.example.demo.dao;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Warehouse;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

public class WarehouseDao {
    public void getWarehouseRecord(JSONObject param, JSONObject json) {
        /*--------------------函数开始 传递进来的参数在param里--------------------*/
        System.out.println("[WarehouseDao/getWarehosueRecord]执行到这里param=null");
        /*--------------------数据库访问 开始--------------------*/
        String url="jdbc:mysql://localhost:3306/test";
        String username="root";
        String password="1234";
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //加载数据库驱动
        System.out.println("[WarehouseDao/getWarehosueRecord]驱动加载完毕");
        Connection connection=null;
        try {
            connection= DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //通过自带的函数建立与数据库链接
        System.out.println("[WarehouseDao/getWarehosueRecord]Connection连接完毕");
        Statement statement=null;
        try {
            statement=connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //建立statement对象进行数据库操作
        System.out.println("[DeviceDao/getWarehouseRecord]Statement创建完毕");
        String sql="select * from warehouse order by warehouse_name";
        List list=new ArrayList();
        //这段代码的目的是准备执行 SQL 查询，并且准备一个容器来存储查询结果。
        try {
            ResultSet rs=statement.executeQuery(sql);
            //executeQuery() 方法，获取查询结果集 ResultSet 对象。该对象包含了从数据库中检索到的数据。
            while(rs.next()){
                Map map=new HashMap();
                //这段代码执行了之前定义的 SQL 查询语句，并将查询结果封装到一个 HashMap 中，然后将这个 HashMap 添加到之前创建的 ArrayList 中。
                map.put("id",rs.getString("id"));
                map.put("warehouse_name",rs.getString("warehouse_name"));
                //map.put() 方法用于将从数据库中检索到的每个字段的值存储到一个 HashMap 对象中。
                map.put("location",rs.getString("location"));
                map.put("createtime",rs.getString("createtime"));

                map.put("phone",rs.getString("phone"));
                //在循环中，通过 rs.getString("字段名") 方法获取当前行中每个列的值，并将其存储到一个 HashMap 对象中。
                // 每次循环都会创建一个新的 HashMap 对象，并将该行数据存储到其中
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*--------------------数据库访问 结束--------------------*/
        json.put("aaData",list);
        json.put("result_code",0);
        json.put("result_msg","ok");
        /*--------------------函数结束 返回的结果存放在json里--------------------*/
    }

    public void addWarehouseRecord(Warehouse warehouse, JSONObject json) {
        /*--------------------函数开始 传递进来的参数在param里--------------------*/
        System.out.println("[DeviceDao/addDeviceRecord]执行到这里device="+ warehouse.toString());
        /*--------------------数据库访问 开始--------------------*/
        String url="jdbc:mysql://localhost:3306/test";
        String username="root";
        String password="1234";
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("[DeviceDao/addDeviceRecord]驱动加载完毕");
        Connection connection=null;
        try {
            connection= DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("[DeviceDao/addDeviceRecord]Connection连接完毕");
        Statement statement=null;
        try {
            statement=connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("[DeviceDao/addDeviceRecord]Statement创建完毕");

        String id= warehouse.getId();
        String warehouse_name= warehouse.getWarehouse_name();
        String location= warehouse.getLocation();
        String phone=warehouse.getPhone();
        String createtime=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
        String sql="insert into warehouse(id,warehouse_name,location,phone,createtime)" +
                " values('"+id+"','"+warehouse_name+"','"+location+"','"+phone+"','"+createtime+"')";

        List list=new ArrayList();
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*--------------------数据库访问 结束--------------------*/
        json.put("aaData",list);
        json.put("result_code",0);
        json.put("result_msg","ok");
        /*--------------------函数结束 返回的结果存放在json里--------------------*/
    }
    public void deleteWarehouseRecord(String id, JSONObject json) {
        /*--------------------函数开始 传递进来的参数在param里--------------------*/
        System.out.println("[WarehouseDao/deleteWarehouseRecordById]执行到这里，删除的ID=" + id);
        /*--------------------数据库访问 开始--------------------*/
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "1234";
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("[WarehouseDao/deleteWarehouseRecordById]驱动加载完毕");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("[WarehouseDao/deleteWarehouseRecordById]Connection连接完毕");
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("[WarehouseDao/deleteWarehouseRecordById]Statement创建完毕");

        String sql = "DELETE FROM warehouse WHERE id='" + id + "'";
        System.out.println("[WarehouseDao/deleteWarehouseRecordById]SQL语句：" + sql);

        try {
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
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("[WarehouseDao/deleteWarehouseRecordById]数据库访问结束");
        /*--------------------数据库访问 结束--------------------*/
        /*--------------------函数结束 返回的结果存放在json里--------------------*/
    }
    public void updateWarehouseRecord(Warehouse warehouse, JSONObject json) {
        /*--------------------函数开始 传递进来的参数在param里--------------------*/
        System.out.println("[WarehouseService/updateWarehouseRecord] 执行到这里 warehouse = " + warehouse.toString());
        /*--------------------数据库访问 开始--------------------*/
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "1234";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("[WarehouseService/updateWarehouseRecord] 驱动加载完毕");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("[WarehouseService/updateWarehouseRecord] Connection连接完毕");
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("[WarehouseService/updateWarehouseRecord] Statement创建完毕");

        String id = warehouse.getId();
        String warehouseName = warehouse.getWarehouse_name();
        String location = warehouse.getLocation();
        String phone = warehouse.getPhone();
        String updateTime = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
        String sql = "update warehouse set warehouse_name = '" + warehouseName + "', location = '" + location + "', phone = '" + phone + "', updatetime = '" + updateTime + "' where id = '" + id + "'";

        List<Object> list = new ArrayList<>();
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*--------------------数据库访问 结束--------------------*/
        json.put("aaData", list);
        json.put("result_code", 0);
        json.put("result_msg", "ok");
        /*--------------------函数结束 返回的结果存放在json里--------------------*/
    }

    public static List<Warehouse> searchWarehouseRecord(String warehouse_name) {
        String sql = "SELECT * FROM warehouse WHERE warehouse_name LIKE ?";
        List<Warehouse> list = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "1234";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + warehouse_name + "%");

            try (ResultSet re = statement.executeQuery()) {
                while (re.next()) {
                    Warehouse warehouse = new Warehouse();
                    warehouse.setId(re.getString("id"));
                    warehouse.setWarehouse_name(re.getString("warehouse_name"));
                    warehouse.setLocation(re.getString("location"));
                    warehouse.setPhone(re.getString("phone"));
                    warehouse.setCreatetime(re.getString("createtime"));
                    list.add(warehouse);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }
        return list;
    }
}

