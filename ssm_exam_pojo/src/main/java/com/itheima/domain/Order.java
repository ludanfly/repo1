package com.itheima.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
public class Order {
    private String id;
    private String orderNum;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date orderTime;
    private String orderTimeStr;
    private int orderStatus;
    private String orderStatusStr;
    private int peopleCount;
    private Product product;
    private List<Traveller> travellers;
    private Member member;
    private Integer payType;
    private String payTypeStr;
    private String orderDesc;

    public String getPayTypeStr() {
        //支付方式:0 支付宝 1微信 2其他
        if(payType==0){
            payTypeStr="支付宝";
        }
        if(payType==1){
            payTypeStr="微信";
        }
        if(payType==1){
            payTypeStr="其他";
        }
        return payTypeStr;
    }

    public String getOrderStatusStr() {
        if(orderStatus==0){
            orderStatusStr="关闭";
        }
        if(orderStatus==1){
            orderStatusStr="开启";
        }
        return orderStatusStr;
    }

    public String getOrderTimeStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss");
        String format = sdf.format(orderTime);
        return format;
    }
}
