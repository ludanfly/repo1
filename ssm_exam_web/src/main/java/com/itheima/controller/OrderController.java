package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Order;
import com.itheima.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/findAllOrders")
    public ModelAndView findAllOrders(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                      @RequestParam(name = "pageSize", required = true, defaultValue = "3") Integer pageSize) throws Exception {
        List<Order> ordersList = orderService.findAllOrder(page,pageSize);
        //使用pageInfo对象将分页信息传递到页面:
        PageInfo pageInfo = new PageInfo(ordersList);
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-list");
        return mv;
    }

    @RequestMapping("/findById")
    public String findById(Model model, String id) throws Exception {
        Order order = orderService.findById(id);
        model.addAttribute("orders",order);
        return "orders-show";
    }
}
