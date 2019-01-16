package com.itheima.service;

import com.itheima.domain.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> findAllPermission() throws Exception;

    int addPermission(Permission permission);

    List<Permission> findOtherPermission(String id);
}
