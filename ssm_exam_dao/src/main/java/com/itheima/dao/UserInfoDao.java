package com.itheima.dao;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserInfoDao {

     @Select("select * from userInfo where userName=#{userName}")
     @Results(
             value = {
                     @Result(property = "roles", column = "id", many = @Many(select = "com.itheima.dao.RoleDao.findRoleByUserId"))
             }
     )
     UserInfo findUserInfoByName(String userName) throws Exception;

     @Select("select * from userInfo where id=#{id}")
     @Results(
             value = {
                     @Result(property = "roles", column = "id", many = @Many(select = "com.itheima.dao.RoleDao.findRoleByUserId2"))
             }
     )
     UserInfo findUserInfoById(String id) throws Exception;

     @Select("select * from userInfo")
    List<UserInfo> findAllUserInfo() throws Exception;

     @Insert("insert into userInfo(email,userName,password,phoneNum,status) values(#{email},#{userName},#{password},#{phoneNum},#{status})")
    int saveUserInfo(UserInfo userInfo);


     @Insert("insert into userinfo_role values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId) throws Exception;
}
