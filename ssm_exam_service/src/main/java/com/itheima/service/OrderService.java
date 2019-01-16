package com.itheima.service;

import com.itheima.domain.Order;

import java.util.List;

public interface OrderService {

    List<Order> findAllOrder(Integer page,Integer pageSize) throws Exception;

    Order findById(String id) throws Exception;;

}
