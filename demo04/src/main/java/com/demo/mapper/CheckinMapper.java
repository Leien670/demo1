package com.demo.mapper;

import com.demo.domin.CheckIn;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CheckinMapper {

    @Results(value = {@Result(property = "cid", column = "cid",id=true),
                        @Result(property = "name" ,column = "name"),
                        @Result(property = "checkinTime",column = "checkintime"),
                        @Result(property = "num",column = "num"),
                        @Result(property = "state",column = "state"),
                        @Result(property = "room",javaType = com.demo.domin.Room.class,
                        column = "roomid",one=@One(select = "com.demo.mapper.RoomMapper.findById"))
    })
    @Select("select cid,name,roomid,checkintime,num,state from checkins")
    List<CheckIn> findAll();

    @Update("update checkins set totalprice=#{totalprice},state=2 where cid = #{cid}")
    void update(@Param("cid") int id,@Param("totalprice") int totalprice);
}
