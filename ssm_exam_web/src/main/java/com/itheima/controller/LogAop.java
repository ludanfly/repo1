package com.itheima.controller;
import com.itheima.domain.Syslog;
import com.itheima.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    /*  private Date visitTime;
        private String username;
        private String ip;
        private String url;
        private Long executionTime;
        private String method;*/
    private Date visitTime;
    private Class clazz;
    private Method method;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    //前置通知  开始时间visitTime，执行的类是哪一个，执行方法method
    @Before("execution(* com.itheima.controller.*.*(..))")
    public void doBefore(JoinPoint jp) {

        try {
            visitTime = new Date(System.currentTimeMillis());//当前时间
            clazz = jp.getTarget().getClass();//当前访问类-->url
            String name = jp.getSignature().getName();//当前访问的方法名
            Object[] args = jp.getArgs();//当前访问方法的参数

            //具体方法对象
            if (args == null || args.length == 0) {
               method = clazz.getMethod(name);//空参方法的获取
            } else {
                Class[] classAgrs = new Class[args.length];
                for (int i = 0; i < args.length; i++) {
                    if (args[i] instanceof Model){
                        classAgrs[i] = Model.class;
                    }else{
                        classAgrs[i] = args[i].getClass();
                    }
                }
                method = clazz.getMethod(name, classAgrs);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    //后置通知 executionTime url ip username
    @After("execution(* com.itheima.controller.*.*(..))")
    public void doAfter(JoinPoint jp) {
        try {
            long executionTime = System.currentTimeMillis() - visitTime.getTime();

            //url获取
            if (clazz != null && method != null && clazz != LogAop.class) {
                //获取类上的@RequestMapping
                RequestMapping class_Annotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);//接口向下强转
                if (class_Annotation != null) {
                    String[] class_Value = class_Annotation.value();
                    //获取方法上的@RequestMapping
                    RequestMapping method_Annotation = method.getAnnotation(RequestMapping.class);
                    if (method_Annotation != null) {
                        String[] method_value = method_Annotation.value();

                        String url = class_Value[0] + method_value[0];//只有一个值

                        String ip = request.getRemoteAddr();

                        // 当前操作的用户 可以通过securityContext获取，也可以从request.getSession中获取
                        SecurityContext context = SecurityContextHolder.getContext(); //静态方法获取
                        String username = ((User) (context.getAuthentication().getPrincipal())).getUsername();

                        //将日志相关信息封装到SysLog对象
                        Syslog sysLog = new Syslog();
                        sysLog.setExecutionTime(executionTime); //执行时长
                        sysLog.setIp(ip);
                        sysLog.setMethod("[类名] " + clazz.getName() + "[方法名] " + method.getName());
                        sysLog.setUrl(url);
                        sysLog.setUsername(username);
                        sysLog.setVisitTime(visitTime);

                        //调用Service完成操作
                        if (!"tom".equalsIgnoreCase(username)){

                            sysLogService.saveSysLog(sysLog);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}