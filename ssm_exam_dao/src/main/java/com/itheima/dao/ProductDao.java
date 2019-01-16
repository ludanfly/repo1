package com.itheima.dao;

import com.itheima.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface ProductDao {

    /**
     * 查询所有产品
     * @return
     * @throws Exception
     */
    @Select("select * from product")
    List<Product> findAllProduct(Integer page,Integer pageSize) throws Exception;


    @Select("select * from product where id=#{id}")
    List<Product> findProductById(String id) throws Exception;

    /**
     * 添加产品
     * @param product
     * @return
     */
    @Insert("insert into product values(sys_guid(),#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    int saveProduct(Product product);
}
