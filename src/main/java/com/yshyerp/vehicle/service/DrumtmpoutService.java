package com.yshyerp.vehicle.service;

import com.yshyerp.vehicle.entity.Drumtmpout;
import com.yshyerp.vehicle.entity.Tank;
import com.yshyerp.vehicle.entity.TankC;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


public interface DrumtmpoutService {

    //查询
    List<Drumtmpout> listdrum();

    //根据提pLlanNo 查询信息
    public Drumtmpout listdrumid(String planNo);

    //新增
    int insert(Drumtmpout record);

    //获取coun   对值  no1
    Drumtmpout  getcoun(String vehicle);
    //获取已入仓的车
    List<Drumtmpout>  getvehiclelist1(String vehicle);

    List<Drumtmpout>  getvehiclelist0(String vehicle);
    //修改 状态  出仓
    int updachucahng(Map map);
    int   updaweight1(Map map);

    /**
     * 根据罐号查询货品打印名称信息
     * @param tank
     * @return
     */
    public List<TankC> queryTankCListByTank(String tank);

    int  updvehicle(Map map);


    int insertSelective(Drumtmpout record);

    //修改
    int update(Drumtmpout record);

    //修改备用1
    int  beiyong(Map map);

    //删除
    int updateDd(String vehicle);

    //判断
    public  String  a(String doNo);


    //删除 no1  vehicle
    int  updachushan(Map map);

}
