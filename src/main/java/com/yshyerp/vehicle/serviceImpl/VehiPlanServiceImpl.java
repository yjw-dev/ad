package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.entity.VehiPlan;
import com.yshyerp.vehicle.entity.VehiPlan1;
import com.yshyerp.vehicle.mapper.VehiPlanMapper;
import com.yshyerp.vehicle.service.VehiPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VehiPlanServiceImpl implements VehiPlanService {
    @Autowired
    private VehiPlanMapper vehiPlanMapper;


    @Override
    public List<VehiPlan1> list() {
        List<VehiPlan1> v=vehiPlanMapper.list();
        return v;
    }

    @Override
    public VehiPlan1 queryplanNoByDoNo(String planNo) {
        VehiPlan1 vehiPlan=vehiPlanMapper.queryplanNoByDoNo(planNo);
        if (vehiPlan!=null){
            return vehiPlan;
        }

        return null;
    }

    @Override
    public int insert(VehiPlan1 record) {


        return 0;
    }

    @Override
    public int insertSelective(VehiPlan1 record) {
        return vehiPlanMapper.insertSelective(record);
    }

    @Override
    public int updVehiPlan1(VehiPlan1 record) {
        return  vehiPlanMapper.updVehiPlan1(record);
    }

    @Override
    public int updVehiplanId(String planNo) {
        return vehiPlanMapper.updVehiplanId(planNo);
    }
}
