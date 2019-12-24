package com.demo.service;

import com.demo.domin.CheckIn;
import com.demo.mapper.CheckinMapper;
import com.demo.mapper.RoomMapper;
import com.demo.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CheckinService {

    private CheckinMapper checkinsMapper;
    private SqlSession sqlSession;
    private RoomMapper roomMapper;

    private void init(){
        sqlSession = SqlSessionUtil.getSqlSession();
        checkinsMapper = sqlSession.getMapper(CheckinMapper.class);
        roomMapper = sqlSession.getMapper(RoomMapper.class);
    }

    public List<CheckIn> findAll(){
        init();
        List<CheckIn> list = checkinsMapper.findAll();
        SqlSessionUtil.close(sqlSession);
        return list;
    }

    public void update(int cid,int totalprice,int roomid){
        //房间结账事务：1.完成入住信息结账修改，2.释放房间状态
        init();
        checkinsMapper.update(cid,totalprice);
        roomMapper.update(roomid,1);
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }
}
