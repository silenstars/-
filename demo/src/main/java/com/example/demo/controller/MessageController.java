package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Message;
import com.example.demo.entity.Warehouse;
import com.example.demo.service.MessageService;
import com.example.demo.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    public MessageService messageService;

    @RequestMapping("/get_message")
    public JSONObject getMessage(@RequestBody(required = false) JSONObject param){
        JSONObject json=new JSONObject();
        //if(!(param ==null)) {
        MessageService service = new MessageService();
        service.getMessage(param,json);
        //}
        return json;
    }
    @RequestMapping("/add_message")
    public JSONObject addMessage(@RequestBody(required = false) Message message){
        JSONObject json=new JSONObject();
        System.out.println("[MessageController/addMessage]"+ message.toString());
        MessageService service = new MessageService();
        service.addMessage(message,json);
        return json;
    }
    @RequestMapping("/delete_message")
    public JSONObject deleteMessage(@RequestBody Map<String, String> request) {
        JSONObject json = new JSONObject();
        String message_id = request.get("message_id");
        System.out.println("[MessageController/deleteMessage] Deleting message with ID: " + message_id);
        MessageService service = new MessageService();
        service.deleteMessage(message_id, json);
        return json;
    }
}
