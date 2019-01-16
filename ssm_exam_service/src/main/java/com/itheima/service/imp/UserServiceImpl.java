package com.itheima.service.imp;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.UserInfoDao;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoDao userInfoDao;

    //注入加密类
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserInfo userInfo=null;
        try {
           userInfo = userInfoDao.findUserInfoByName(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将对userinfo对象转为UserDetails并获得权限
        User user = new User(userInfo.getUserName(), userInfo.getPassword(), userInfo.getStatus()==0?false:true, true, true, true, getAuthorities(userInfo.getRoles()));
        return user;
    }

    public List<SimpleGrantedAuthority> getAuthorities(List<Role> roles){
        List<SimpleGrantedAuthority> list=new ArrayList<>();
        for (Role role : roles) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+role.getRoleName());
            list.add(authority);
        }
        return list;
    }

    @Override
    public List<UserInfo> findAllUserInfo(Integer page,Integer pageSize) throws Exception {
        PageHelper.startPage(page,pageSize);
        List<UserInfo> list= userInfoDao.findAllUserInfo();
        return list;
    }

    @Override
    public int saveUserInfo(UserInfo userInfo) {
        //加密密码
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        return userInfoDao.saveUserInfo(userInfo);
    }

    @Override
    public UserInfo findUserInfoById(String id) throws Exception {
       return userInfoDao.findUserInfoById(id);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) throws Exception {
        for (String roleId : roleIds) {

            userInfoDao.addRoleToUser(userId,roleId);
        }
    }


}
