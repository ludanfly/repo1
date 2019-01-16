package com.itheima.service;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserInfo> findAllUserInfo(Integer page,Integer pageSize) throws Exception;

    int saveUserInfo(UserInfo userInfo);

    UserInfo findUserInfoById(String id) throws Exception;


    void addRoleToUser(String userId, String[] roleIds) throws Exception;
}
