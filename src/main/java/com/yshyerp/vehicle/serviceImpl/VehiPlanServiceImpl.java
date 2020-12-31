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
    public List<VehiPlan> list() {
        List<VehiPlan> v=vehiPlanMapper.list();
        return v;
    }

    @Override
    public VehiPlan queryplanNoByDoNo(String planNo) {
        VehiPlan vehiPlan=vehiPlanMapper.queryplanNoByDoNo(planNo);
        if (vehiPlan!=null){
            return vehiPlan;
        }

        return null;
    }

    @Override
    public int insert(VehiPlan1 record) {


        return vehiPlanMapper.insert(record);
    }
}
