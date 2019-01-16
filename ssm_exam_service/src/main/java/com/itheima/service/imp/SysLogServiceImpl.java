package com.itheima.service.imp;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.SyslogDao;
import com.itheima.domain.Syslog;
import com.itheima.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SyslogDao syslogDao;

    @Override
    public void saveSysLog(Syslog syslog) {
        syslogDao.saveSyslog(syslog);
    }

    @Override
    public List<Syslog> findAllSyslog(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        return syslogDao.findAllSyslog(page, pageSize);
    }
}
