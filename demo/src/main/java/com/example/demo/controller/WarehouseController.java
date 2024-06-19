package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Warehouse;
import com.example.demo.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Map;




@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    public WarehouseService warehouseService;

    @RequestMapping("/get_record")
    public JSONObject getWarehouseRecord(@RequestBody(required = false) JSONObject param){
        JSONObject json=new JSONObject();
        //if(!(param ==null)) {
            WarehouseService service = new WarehouseService();
            service.getWarehouseRecord(param,json);
        //}
        return json;
    }
    @RequestMapping("/add_record")
    public JSONObject addWarehouseRecord(@RequestBody(required = false) Warehouse warehouse){
        JSONObject json=new JSONObject();
        System.out.println("[WarehouseController/addWarehouseRecord]"+ warehouse.toString());
        WarehouseService service = new WarehouseService();
        service.addWarehouseRecord(warehouse,json);
        return json;
    }
    @RequestMapping("/delete_record")
    public JSONObject deleteWarehouseRecord(@RequestBody Map<String, String> request) {
        JSONObject json = new JSONObject();
        String id = request.get("id");
        System.out.println("[WarehouseController/deleteWarehouseRecord] Deleting warehouse with ID: " + id);
        WarehouseService service = new WarehouseService();
        service.deleteWarehouseRecord(id, json);
        return json;
    }
    @RequestMapping("/update_record")
    public JSONObject updateWarehouseRecord(@RequestBody(required = false) Warehouse warehouse) {
        JSONObject json = new JSONObject();
        System.out.println("[WarehouseController/updateWarehouseRecord] " + warehouse.toString());
        WarehouseService service = new WarehouseService();
        service.updateWarehouseRecord(warehouse, json);
        return json;
    }


    @RequestMapping(value = "/search_record", method = RequestMethod.POST)
    public JSONObject searchWarehouseRecord(@RequestBody(required = false) JSONObject param) {
        JSONObject json = new JSONObject();
        try {
            System.out.println("[WarehouseController/searchWarehouseRecord] " + param.toString());
            warehouseService.searchWarehouseRecord(param, json);
        } catch (Exception e) {
            json.put("result_code", 1);
            json.put("result_msg", "搜索仓库记录时发生错误: " + e.getMessage());
        }
        return json;
    }
}
