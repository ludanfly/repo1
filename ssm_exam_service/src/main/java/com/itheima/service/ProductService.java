package com.itheima.service;

import com.itheima.domain.Product;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductService {
    /**
     * 查询所有产品
     * @return
     * @throws Exception
     */
    @Select("select * from product")
    List<Product> findAllProduct(Integer page,Integer pageSize) throws Exception;
    /**
     * 添加产品
     * @param product
     * @return
     */
    int saveProduct(Product product);
}
