package com.yshyerp.vehicle.service;

import com.yshyerp.vehicle.entity.Drums;
import com.yshyerp.vehicle.entity.VehiPlan;
import com.yshyerp.vehicle.entity.VehiPlan1;

import java.util.List;
import java.util.Map;

public interface VehiPlanService {

    //查询全部
    List<VehiPlan1> list(Map map);

   //根据计划单号planNo查询
   VehiPlan1 queryplanNoByDoNo(String planNo);

    //提货计划
    List<VehiPlan1> thjh(String planNo);


    //提货计划 DRA   新增
    List<VehiPlan1> thjhcx(String planNo);

    //提货计划 DDC    新增
    List<VehiPlan1> thjhcxddc(String planNo);

    //新增 桶装出货计划计划
    int insert(VehiPlan1 record);

    //新增来桶计划
    int insertSelective(VehiPlan1 record);

    //修改
    int updVehiPlan1(VehiPlan1 record);

    //逻辑删除
    int updVehiplanId(String planNo);


    //逻辑删除 jobno
    int   updplanNo(String planNo);

    //修改jobno
    int updjobno(Map map);
}
