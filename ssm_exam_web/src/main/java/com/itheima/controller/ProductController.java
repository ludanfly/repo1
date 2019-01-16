package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/findAllProduct")
    public ModelAndView findAllProduct(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                       @RequestParam(name = "pageSize", required = true, defaultValue = "3") Integer pageSize) throws Exception {
        List<Product> list = productService.findAllProduct(page, pageSize);
        PageInfo pageInfo = new PageInfo(list);
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("product-list");
        return mv;
    }

    @RequestMapping("/saveProduct")
    public String save(Product product, HttpServletRequest request) {

        int i = productService.saveProduct(product);
        if (i > 0) {
            //添加成功,跳转到查询所有     默认request.getContextPath()
            return "redirect:/product/findAllProduct";
        } else {
            return "product-add";
        }
    }

}
