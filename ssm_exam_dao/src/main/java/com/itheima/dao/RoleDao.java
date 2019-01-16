package com.itheima.dao;

import com.itheima.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {


    @Select("select * from role where id in (select roleId from userInfo_role where userId=#{userId})")
    List<Role> findRoleByUserId(String userId) throws Exception;

    @Select("select * from role ")
    List<Role> findAllRole() throws Exception;

    @Select("select * from role where id in (select roleId from userInfo_role where userId=#{userId2})")
    @Results(
            value = {
                    @Result(property = "permissions", column = "id", many = @Many(select = "com.itheima.dao.PermissionDao.findPermissionByRoleId"))
            }
    )
    List<Role> findRoleByUserId2(String userId) throws Exception;

    @Insert("insert into role(rolename,roledesc) values(#{roleName},#{roleDesc})")
    int saveRole(Role role);

    @Select("select * from role where id not in (select roleId from userInfo_role where userId=#{id})")
    List<Role> findOtherRoles(String id)throws Exception;

    @Select("select * from role where id =#{id}")
    @Results(
            value = {
                    @Result(property = "permissions", column = "id", many = @Many(select = "com.itheima.dao.PermissionDao.findPermissionByRoleId"))
            }
    )
    Role findByIdwithPermission(String id);

    @Insert("insert into ROLE_PERMISSION values(#{permissionId},#{roleId}) ")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String pid) throws Exception;
}
