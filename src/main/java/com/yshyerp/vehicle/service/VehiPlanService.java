package com.yshyerp.vehicle.service;

import com.yshyerp.vehicle.entity.VehiPlan;
import com.yshyerp.vehicle.entity.VehiPlan1;

import java.util.List;

public interface VehiPlanService {

    //查询全部
    List<VehiPlan> list();

   //根据计划单号planNo查询
    public VehiPlan queryplanNoByDoNo(String planNo);

    //新增来桶计划
    int insert(VehiPlan1 record);

}
