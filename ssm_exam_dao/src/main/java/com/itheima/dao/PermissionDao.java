package com.itheima.dao;

import com.itheima.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {

    @Select("select * from permission where id in (select permissionId from role_Permission where roleId=#{roleId})")
    List<Permission> findPermissionByRoleId(String roleId) throws Exception;

    @Select("select * from permission")
    List<Permission> findAllPermission();

    @Insert("insert into permission(permissionname,url) values(#{permissionName},#{url})")
    int addPermission(Permission permission);

    @Select("select * from permission where id not in (select permissionId from role_Permission where roleId=#{roleId})")
    List<Permission> findOtherPermission(String id);
}
