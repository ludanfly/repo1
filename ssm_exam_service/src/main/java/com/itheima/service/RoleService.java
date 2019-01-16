package com.itheima.service;

import com.itheima.domain.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAllRole() throws Exception;

    int saveRole(Role role);

    List<Role> findOtherRoles(String id) throws Exception;

    Role findByIdwithPermission(String id);

    void addPermissionToRole(String roleId, String[] pids) throws Exception;
}
