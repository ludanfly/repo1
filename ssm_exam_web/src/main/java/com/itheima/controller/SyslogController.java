package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Syslog;
import com.itheima.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SyslogController {

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/findAllSyslog")
    public String findAllSyslog(Model model, @RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                @RequestParam(name = "pageSize", required = true, defaultValue = "3") Integer pageSize){
        List<Syslog> syslogs=sysLogService.findAllSyslog(page,pageSize);
        PageInfo pageinfo=new PageInfo(syslogs);
        model.addAttribute("sysLogs",pageinfo);
        return "syslog-list";
    }
}
