package com.itheima.dao;

import com.itheima.domain.Product;
import com.itheima.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//@Repository
public interface TravellerDao {


    @Select("select * from TRAVELLER where id in (select TRAVELLERID from ORDER_TRAVELLER where ORDERID=#{id})")
    List<Traveller> findTravellerById(String id) throws Exception;

}
