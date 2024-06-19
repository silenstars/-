package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // 允许跨域请求
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam String username, @RequestParam String password) {
        boolean isValid = userService.validateUser(username, password);
        Map<String, Object> response = new HashMap<>();
        if (isValid) {
            response.put("result_code", 0);
            response.put("result_msg", "登录成功");
        } else {
            response.put("result_code", 1);
            response.put("result_msg", "用户名或密码错误");
        }
        return response;
    }
}
