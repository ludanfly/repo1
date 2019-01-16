package com.itheima.service;

import com.itheima.domain.Syslog;

import java.util.List;

public interface SysLogService {

    public void saveSysLog(Syslog syslog);

    List<Syslog> findAllSyslog(Integer page, Integer pageSize);
}
