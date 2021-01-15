package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.entity.Djob1;
import com.yshyerp.vehicle.mapper.Djob1Mapper;
import com.yshyerp.vehicle.service.Djob1Service;
import com.yshyerp.vehicle.vo.DjobVo;
import com.yshyerp.vehicle.vo.DjobVo1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Djob1ServiceImpl implements Djob1Service {
    @Autowired
    private Djob1Mapper djob1Mapper;


    @Override
    public List<Djob1> Bycustomer(DjobVo djobVo) {
        List<Djob1> djob1=djob1Mapper.Bycustomer(djobVo);
        if (djob1!=null){
            return djob1;
        }

        return null;
    }



}
