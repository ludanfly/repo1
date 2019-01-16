package com.itheima.service.imp;

import com.itheima.dao.RoleDao;
import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAllRole() throws Exception {
        return  roleDao.findAllRole();
    }

    @Override
    public int saveRole(Role role) {
        return roleDao.saveRole(role);
    }

    @Override
    public List<Role> findOtherRoles(String id) throws Exception {
        return roleDao.findOtherRoles(id);
    }

    @Override
    public Role findByIdwithPermission(String id) {
        return roleDao.findByIdwithPermission(id);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] pids) throws Exception {
        for (String pid : pids) {
            roleDao.addPermissionToRole(roleId,pid);
        }
    }
}
