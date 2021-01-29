package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.entity.Drums;
import com.yshyerp.vehicle.entity.VehiPlan;
import com.yshyerp.vehicle.entity.VehiPlan1;
import com.yshyerp.vehicle.mapper.VehiPlanMapper;
import com.yshyerp.vehicle.service.VehiPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VehiPlanServiceImpl implements VehiPlanService {
    @Autowired
    private VehiPlanMapper vehiPlanMapper;


    @Override
    public List<VehiPlan1> list(Map map) {
        List<VehiPlan1> v=vehiPlanMapper.list(map);
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
    public List<VehiPlan1> thjh(String planNo) {
        List<VehiPlan1> vehiPlan1=vehiPlanMapper.thjh(planNo);
        return vehiPlan1;
    }

    @Override
    public List<VehiPlan1> thjhcx(String planNo) {
        List<VehiPlan1>  vehiPlan1=vehiPlanMapper.thjhcx(planNo);
        return vehiPlan1;
    }

    @Override
    public List<VehiPlan1> thjhcxddc(String planNo) {
        List<VehiPlan1>  vehiPlan1=vehiPlanMapper.thjhcxddc(planNo);
        return vehiPlan1;
    }


    @Override
    public int insert(VehiPlan1 record) {
        int a=vehiPlanMapper.insert(record);
        return a;
    }

    @Override
    public int insertSelective(VehiPlan1 record) {
        int a=vehiPlanMapper.insertSelective(record);
        return a;
    }

    @Override
    public int updVehiPlan1(VehiPlan1 record) {
        int a=vehiPlanMapper.updVehiPlan1(record);
        return  a;
    }

    @Override
    public int updVehiplanId(String planNo) {
        int a=vehiPlanMapper.updVehiplanId(planNo);;
        return a;
    }

    @Override
    public int updplanNo(String planNo) {
        int a=vehiPlanMapper.updplanNo(planNo);
        return a;
    }

    @Override
    public int updjobno(Map map) {

        int a=vehiPlanMapper.updjobno(map);
        return a;
    }
}
