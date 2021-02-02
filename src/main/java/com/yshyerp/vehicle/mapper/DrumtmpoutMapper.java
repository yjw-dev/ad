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

    List<Drumtmpout>  getvehiclelist0(String vehicle);
    //获取已入仓的车
    List<Drumtmpout>  getvehiclelist1(String vehicle);

    int insertSelective(Drumtmpout record);

  int   updvehicle(Map map);


    //修改
    int update(Drumtmpout record);

    //修改
     int  updaweight1(Map map);
    //修改备用1
    int  beiyong(Map map);

    //修改 状态  出仓
    int updachucahng(Map map);


    //删除 no1  vehicle
    int  updachushan(Map map);

    //删除
    int updateDd(String vehicle);


}
