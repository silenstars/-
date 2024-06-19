package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.MessageDao;
import com.example.demo.entity.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private MessageDao messageDao;

    public void getMessage(JSONObject param, JSONObject json) {
        MessageDao dao = new MessageDao();
        dao.getMessage(param, json);
    }

    public void addMessage(Message message, JSONObject json) {
        MessageDao dao = new MessageDao();
        dao.addMessage(message, json);
    }

    public void deleteMessage(String message_id, JSONObject json) {
        MessageDao dao = new MessageDao();
        dao.deleteMessage(message_id, json);
    }


}
