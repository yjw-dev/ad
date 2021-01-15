package com.yshyerp.vehicle.serviceImpl;


import com.yshyerp.vehicle.entity.Drumtmp2;
import com.yshyerp.vehicle.entity.Drumtmpout;
import com.yshyerp.vehicle.mapper.Drumtmp1Mapper;
import com.yshyerp.vehicle.mapper.DrumtmpoutMapper;
import com.yshyerp.vehicle.service.Drumtmp1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Drumtmp1ServiceImpl implements Drumtmp1Service {

    @Autowired
    Drumtmp1Mapper drumtmp1Mapper;
    @Autowired
    DrumtmpoutMapper drumtmpoutMapper;


    @Override
    public List<Drumtmpout> listdrum() {
        List<Drumtmpout> drumtmpoutList=drumtmpoutMapper.listdrum();
        return drumtmpoutList;
    }

    //获取  drums
   public Drumtmp2 getCustomerByCustomerName(String customer){
        return drumtmp1Mapper.getCustomerByCustomerName(customer);
    }

    @Override
    public Drumtmp2 getvehiplansum(String customer) {
        return drumtmp1Mapper.getvehiplansum(customer);
    }

    @Override
    public Drumtmp2 getdrumsum(String customer) {
        return drumtmp1Mapper.getdrumsum(customer);
    }


}
