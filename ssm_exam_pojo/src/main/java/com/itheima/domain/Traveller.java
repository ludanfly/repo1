package com.itheima.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Traveller {
    private String id;
    private String name;
    private String sex;
    private String phoneNum;

    private Integer credentialsType;
    private String credentialsTypeStr;
    private String credentialsNum;

    private Integer travellerType;
    private String travellerTypeStr;

    public String getCredentialsTypeStr() {
        //证件类型 0身份证 1护照 2军官证
        if(credentialsType!=null) {
            if (credentialsType == 0) {
                credentialsTypeStr="身份证";
            }
            if (credentialsType == 1) {
                credentialsTypeStr="护照";
            }
            if (credentialsType == 2) {
                credentialsTypeStr="军官证";
            }
        }
        return credentialsTypeStr;
    }

    public String getTravellerTypeStr() {
        //证件类型 0身份证 1护照 2军官证
        if(travellerType!=null) {
            if (travellerType == 0) {
                travellerTypeStr="儿童";
            }
            if (travellerType == 1) {
                travellerTypeStr="中年";
            }
            if (travellerType == 2) {
                travellerTypeStr="老年人";
            }
        }
        return travellerTypeStr;
    }
}
