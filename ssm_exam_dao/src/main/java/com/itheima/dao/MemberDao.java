package com.itheima.dao;

import com.itheima.domain.Member;
import com.itheima.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//@Repository
public interface MemberDao {


    @Select("select * from member where id=#{id}")
    Member findMemberById(String id) throws Exception;

}
