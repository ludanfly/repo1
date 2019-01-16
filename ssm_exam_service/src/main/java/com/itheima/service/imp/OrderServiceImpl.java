package com.itheima.service.imp;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.OrderDao;
import com.itheima.domain.Order;
import com.itheima.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> findAllOrder(Integer page,Integer pageSize) throws Exception {

        PageHelper.startPage(page,pageSize);
        //紧跟着的第一个select方法会被分页
        return orderDao.findAllOrder(page,pageSize);
    }

    @Override
    public Order findById(String id) throws Exception {
        return orderDao.findById(id);
    }
}
