package com.itheima.dao;

import com.itheima.domain.Syslog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SyslogDao {

    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void saveSyslog(Syslog syslog);

    @Select("select * from syslog")
    List<Syslog> findAllSyslog(Integer page, Integer pageSize);
}
