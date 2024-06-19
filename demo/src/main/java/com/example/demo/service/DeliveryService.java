package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.DeliveryDao;
import com.example.demo.entity.Delivery;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class DeliveryService {
    private DeliveryDao deliveryDao;
    public void getDeliveryRecord(JSONObject param,JSONObject json){
        DeliveryDao dao=new DeliveryDao();
        dao.getDeliveryRecord(param,json);
    }
    public void addDeliveryRecord(Delivery delivery, JSONObject json){
        DeliveryDao dao=new DeliveryDao();
        dao.addDeliveryRecord(delivery,json);
    }
    public void deleteDeliveryRecord(String delivery_id, JSONObject json){
        DeliveryDao dao=new DeliveryDao();
        dao.deleteDeliveryRecord(delivery_id,json);
    }
    public void searchDeliveryRecord(JSONObject param, JSONObject json) {
        System.out.println("service");
        if (param == null) {
            json.put("status", "error");
            json.put("message", "参数 'param' 不能为空");
            return;
        }

        String delivery_name = param.getString("delivery_name");
        if (delivery_name == null || delivery_name.trim().isEmpty()) {
            json.put("status", "error");
            json.put("message", "名称参数缺失或为空");
            return;
        }

        try {
            List<Delivery> Deliveries = DeliveryDao.searchDeliveryRecord(delivery_name);
            json.put("Data", Deliveries);
            json.put("result_code", 0);
            json.put("result_msg", "ok");
        } catch (Exception e) {
            json.put("result_code", 1);
            json.put("result_msg", "搜索商品记录时发生错误: " + e.getMessage());
        }
    }
}
