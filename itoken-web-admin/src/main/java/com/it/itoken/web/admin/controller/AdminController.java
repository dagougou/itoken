package com.it.itoken.web.admin.controller;

import com.it.itoken.web.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wjh
 * @create 2019-09-27 21:35
 */
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping(value = {"","index"})
    public String index(){
        return "index";
    }
}
