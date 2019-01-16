package com.itheima.service.imp;

import com.itheima.dao.PermissionDao;

import com.itheima.domain.Permission;

import com.itheima.service.PermissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;


    @Override
    public List<Permission> findAllPermission() throws Exception {
        return permissionDao.findAllPermission();
    }

    @Override
    public int addPermission(Permission permission) {
        return permissionDao.addPermission(permission);
    }

    /**
     * 根据roleId查找permission
     * @param id
     * @return
     */
    @Override
    public List<Permission> findOtherPermission(String id) {
        return permissionDao.findOtherPermission(id);
    }
}
