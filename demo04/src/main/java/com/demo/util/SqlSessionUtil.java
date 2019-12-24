package com.demo.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class SqlSessionUtil {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try{
            //读取主配置文件
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            //加载并按照配置文件得描述生成sqlSessionFactory
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }

    public static void close(SqlSession sqlSession){
        if(sqlSession!=null){
            sqlSession.commit();
        }
    }
}
