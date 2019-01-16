package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.PermissionService;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/findAllPermission")
    public String findAllPermission(Model model) throws Exception {
        List<Permission> list=permissionService.findAllPermission();
        model.addAttribute("permissionList",list);
        return "permission-list";
    }

    @RequestMapping("/addPermission")
    public String addPermission(Permission permission){
        int i=permissionService.addPermission(permission);
        if (i>0){
            return "redirect:/permission/findAllPermission";
        }
        return "permission-add";
    }
}
