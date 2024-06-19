package com.example.demo.dao;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Message;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageDao {

    public void getMessage(JSONObject param, JSONObject json) {
        /*--------------------函数开始 传递进来的参数在param里--------------------*/
        System.out.println("[MessageDao/getMessage]执行到这里param=null");
        /*--------------------数据库访问 开始--------------------*/
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "1234";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("[MessageDao/getMessage]驱动加载完毕");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("[MessageDao/getMessage]Connection连接完毕");
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("[MessageDao/getMessage]Statement创建完毕");
        String sql = "select * from message order by addname";
        List<Map<String, String>> list = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Map<String, String> map = new HashMap<>();
                map.put("message_id", rs.getString("message_id"));
                map.put("addname", rs.getString("addname"));
                map.put("data", rs.getString("data"));
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*--------------------数据库访问 结束--------------------*/
        json.put("messageData", list);
        json.put("result_code", 0);
        json.put("result_msg", "ok");
        /*--------------------函数结束 返回的结果存放在json里--------------------*/
    }

    public void addMessage(Message message, JSONObject json) {
        /*--------------------函数开始 传递进来的参数在param里--------------------*/
        System.out.println("[MessageDao/addMessage]执行到这里message=" + message.toString());
        /*--------------------数据库访问 开始--------------------*/
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "1234";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("[MessageDao/addMessage]驱动加载完毕");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("[MessageDao/addMessage]Connection连接完毕");
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("[MessageDao/addMessage]Statement创建完毕");

        String message_id = message.getMessage_id();
        String addname = message.getAddname();
        String data = message.getData();
        String sql = "insert into message(message_id, addname, data) " +
                "values('" + message_id + "','" + addname + "','" + data + "')";

        List<Object> list = new ArrayList<>();
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*--------------------数据库访问 结束--------------------*/
        json.put("messageData", list);
        json.put("result_code", 0);
        json.put("result_msg", "ok");
        /*--------------------函数结束 返回的结果存放在json里--------------------*/
    }

    public void deleteMessage(String message_id, JSONObject json) {
        /*--------------------函数开始 传递进来的参数在param里--------------------*/
        System.out.println("[MessageDao/deleteMessage]执行到这里，删除的ID=" + message_id);
        /*--------------------数据库访问 开始--------------------*/
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "1234";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("[MessageDao/deleteMessage]驱动加载完毕");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("[MessageDao/deleteMessage]Connection连接完毕");
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("[MessageDao/deleteMessage]Statement创建完毕");

        String sql = "DELETE FROM message WHERE message_id='" + message_id + "'";
        System.out.println("[MessageDao/deleteMessage]SQL语句：" + sql);

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
        System.out.println("[MessageDao/deleteMessage]数据库访问结束");
        /*--------------------数据库访问 结束--------------------*/
        /*--------------------函数结束 返回的结果存放在json里--------------------*/
    }
}

