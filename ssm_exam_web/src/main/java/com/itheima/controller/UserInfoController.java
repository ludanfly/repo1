package com.itheima.controller;


import com.github.pagehelper.PageInfo;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.RoleService;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     *
     * @return
     */
    @RequestMapping("/findAllUserInfo")
    public ModelAndView findAllUserInfo(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                        @RequestParam(name = "pageSize", required = true, defaultValue = "3") Integer pageSize) throws Exception {
        List<UserInfo> list=userService.findAllUserInfo(page,pageSize);
        PageInfo<UserInfo> pageInfo = new PageInfo<>(list);

        ModelAndView mv = new ModelAndView();
        mv.addObject("userList",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/saveUserInfo")
    public String saveUserInfo(UserInfo userInfo){
        int i = userService.saveUserInfo(userInfo);
        if (i>0){
            return "redirect:/userInfo/findAllUserInfo";
        }else{
            return "user-add";
        }
    }

    @RequestMapping("/findById")
    public String findById(Model model,String id) throws Exception {
        UserInfo userInfo=userService.findUserInfoById(id);
        model.addAttribute("user",userInfo);
        return "user-show";
    }

    /**
     * 根据用户id 查询未添加的角色,
     * @param id
     * @return
     */
    @RequestMapping("/findByIdAndAllRole")
    public ModelAndView findByIdAndAllRole(String id) throws Exception {
        List<Role> roles=roleService.findOtherRoles(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("userId",id);
        mv.addObject("roleList",roles);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(String userId,@RequestParam(name = "ids") String[] roleIds){
        try {
            userService.addRoleToUser(userId,roleIds);
            return "redirect:/userInfo/findAllUserInfo";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  "user-role-add";
    }
}
