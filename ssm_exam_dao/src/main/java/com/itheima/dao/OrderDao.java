package com.itheima.dao;

import com.itheima.domain.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrderDao {
    /**
     * 查询所有产品,多表查询一对一
     *
     * @return
     * @throws Exception
     */
    @Select("select * from orders")
    @Results(id = "orderMap",
            value = {
                    @Result(property = "product", column = "productid",
                            one = @One(select = "com.itheima.dao.ProductDao.findProductById")
                    )
            })
    List<Order> findAllOrder(Integer page,Integer pageSize) throws Exception;

    /**
     * 根据id查询订单详情
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from orders where id = #{id}")
    @Results(
            value = {
                    @Result(property = "product", column = "productid", one = @One(select = "com.itheima.dao.ProductDao.findProductById")),
                    @Result(property = "member", column = "memberId", one = @One(select = "com.itheima.dao.MemberDao.findMemberById")),
                    @Result(property = "travellers", column = "id", many = @Many(select = "com.itheima.dao.TravellerDao.findTravellerById"))
            })
    Order findById(String id) throws Exception;

}
