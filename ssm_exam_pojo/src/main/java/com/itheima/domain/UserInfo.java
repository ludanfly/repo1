package com.itheima.domain;

import lombok.Data;

import java.util.List;

@Data
public class UserInfo {
    private String id;
    private String email;
    private String userName;
    private String password;
    private String phoneNum;
    private Integer status;
    private String statusStr;

    public String getStatusStr() {
        if (status==0){
            statusStr="关闭";
        }else if (status==1){
            statusStr="开启";
        }
        return statusStr;
    }

    private List<Role> roles;

}
