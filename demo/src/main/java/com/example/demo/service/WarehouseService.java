package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.WarehouseDao;
import com.example.demo.entity.Warehouse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WarehouseService {
    private WarehouseDao warehouseDao;
    public void getWarehouseRecord(JSONObject param,JSONObject json) {
        WarehouseDao dao=new WarehouseDao();
        dao.getWarehouseRecord(param,json);
    }

    public void addWarehouseRecord(Warehouse warehouse, JSONObject json) {
        WarehouseDao dao=new WarehouseDao();
        dao.addWarehouseRecord(warehouse,json);
    }
    public void deleteWarehouseRecord(String id, JSONObject json) {
        WarehouseDao dao=new WarehouseDao();
        dao.deleteWarehouseRecord(id,json);
    }
    public void updateWarehouseRecord(Warehouse warehouse, JSONObject json) {
        WarehouseDao dao=new WarehouseDao();
        dao.updateWarehouseRecord(warehouse,json);
    }
    public void searchWarehouseRecord(JSONObject param, JSONObject json) {
        System.out.println("service");
        if (param == null) {
            json.put("status", "error");
            json.put("message", "参数 'param' 不能为空");
            return;
        }

        String warehouse_name = param.getString("warehouse_name");
        if (warehouse_name == null || warehouse_name.trim().isEmpty()) {
            json.put("status", "error");
            json.put("message", "名称参数缺失或为空");
            return;
        }

        try {
            List<Warehouse> Warehouses = WarehouseDao.searchWarehouseRecord(warehouse_name);
            json.put("aaData", Warehouses);
            json.put("result_code", 0);
            json.put("result_msg", "ok");
        } catch (Exception e) {
            json.put("result_code", 1);
            json.put("result_msg", "搜索仓库记录时发生错误: " + e.getMessage());
        }
    }

}
