package com.itheima.service.imp;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAllProduct(Integer page,Integer pageSize) throws Exception {
        PageHelper.startPage(page,pageSize);
        return productDao.findAllProduct(page,pageSize);
    }

    @Override
    public int saveProduct(Product product) {
        return productDao.saveProduct(product);
    }
}
