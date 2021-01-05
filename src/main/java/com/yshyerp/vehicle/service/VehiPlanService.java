package com.yshyerp.vehicle.service;

import com.yshyerp.vehicle.entity.VehiPlan;
import com.yshyerp.vehicle.entity.VehiPlan1;

import java.util.List;

public interface VehiPlanService {

    //查询全部
    List<VehiPlan1> list();

   //根据计划单号planNo查询
   VehiPlan1 queryplanNoByDoNo(String planNo);

    //新增来桶计划
    int insert(VehiPlan1 record);

    int insertSelective(VehiPlan1 record);

    //修改
    int updVehiPlan1(VehiPlan1 record);

    //逻辑删除
    int updVehiplanId(String planNo);
}
