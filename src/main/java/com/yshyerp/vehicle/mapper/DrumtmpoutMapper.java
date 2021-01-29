package com.yshyerp.vehicle.mapper;

import com.yshyerp.vehicle.entity.Drumtmpout;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface DrumtmpoutMapper {


    //查询
    List<Drumtmpout> listdrum();


    Drumtmpout  listdrumid(String planNo);

    //新增
    int insert(Drumtmpout record);

    //获取
    Drumtmpout getcoun(String vehicle);

    int insertSelective(Drumtmpout record);

    //修改
    int update(Drumtmpout record);

    //修改
     int  updaweight1(Drumtmpout record);
    //修改备用1
    int  beiyong(Drumtmpout record);


    //删除 no1  vehicle
    int  updachushan(Map map);

    //删除
    int updateDd(String vehicle);


}
