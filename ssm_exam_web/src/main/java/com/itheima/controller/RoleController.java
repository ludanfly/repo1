package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.PermissionService;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/findAllRole")
    public String findAllRole(Model model) throws Exception {
        List<Role> list=roleService.findAllRole();
        model.addAttribute("roleList",list);
        return "role-list";
    }

    @RequestMapping("/saveRole")
    public String saveRole(Role role){
        int i=roleService.saveRole(role);
        if (i>0){
            return "redirect:/role/findAllRole";
        }
        return "role-add";
    }

    /**
     * 通过rid查找角色包含的资源权限
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public ModelAndView findByIdwithPermission(String id){
        Role role=roleService.findByIdwithPermission(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }

    @RequestMapping("/findByIdAndAllPermission")
    public String findByIdAndAllPermission(Model model,String id){
        List<Permission>permissionList=permissionService.findOtherPermission(id);
        model.addAttribute("roleId",id);
        model.addAttribute("permissionList",permissionList);
        return "role-permission-add";
    }

    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId") String roleId,@RequestParam(name = "ids") String[] ids){

        try {
            roleService.addPermissionToRole(roleId,ids);
            return "redirect:/role/findAllRole";
        } catch (Exception e) {
            e.printStackTrace();
            return "role-permission-add";
        }
    }
}
