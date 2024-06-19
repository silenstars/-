package com.example.demo.controller;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Delivery;
import com.example.demo.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    @Autowired
    public DeliveryService deliveryService;

    @RequestMapping("/getDeliveryRecord")
    public JSONObject getDeliveryRecord(@RequestBody(required = false) JSONObject param){
    JSONObject json=new JSONObject();
        DeliveryService service=new DeliveryService();
        service.getDeliveryRecord(param,json);
        return json;
    }
    @RequestMapping("/add_record")
    public JSONObject addDeliveryRecord(@RequestBody(required = false) Delivery delivery){
        JSONObject json=new JSONObject();
        System.out.println("[DeliveryController/addDeliveryRecord]"+ delivery.toString());
        DeliveryService service = new DeliveryService();
        service.addDeliveryRecord(delivery,json);
        return json;
    }
    @RequestMapping("/delete_record")
    public JSONObject deleteDeliveryRecord(@RequestBody Map<String, String> request) {
        JSONObject json = new JSONObject();
        String delivery_id = request.get("delivery_id");
        System.out.println("[DeliveryController/deleteDeliveryRecord] Deleting warehouse with ID: " + delivery_id);
        DeliveryService service = new DeliveryService();
        service.deleteDeliveryRecord(delivery_id, json);
        return json;
    }
    @RequestMapping(value = "/search_record", method = RequestMethod.POST)
    public JSONObject searchDeliveryRecord(@RequestBody(required = false) JSONObject param) {
        JSONObject json = new JSONObject();
        try {
            System.out.println("[DeliveryController/DeliveryRecord] " + param.toString());
            deliveryService.searchDeliveryRecord(param, json);
        } catch (Exception e) {
            json.put("result_code", 1);
            json.put("result_msg", "搜索快件记录时发生错误: " + e.getMessage());
        }
        return json;
    }
}
