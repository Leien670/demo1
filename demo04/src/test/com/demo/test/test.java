package com.demo.test;


import com.demo.mapper.CheckinMapper;
import com.demo.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class test {
    @Test
    public void test2(){
        SqlSession sqlSession= SqlSessionUtil.getSqlSession();
        CheckinMapper checkinMapper = sqlSession.getMapper(CheckinMapper.class);

        checkinMapper.findAll().forEach(checkIn ->
                System.out.println(checkIn.getName()));

        SqlSessionUtil.close(sqlSession);
    }
}
