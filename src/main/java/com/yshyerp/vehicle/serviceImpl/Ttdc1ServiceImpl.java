package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.entity.Ttdc1;
import com.yshyerp.vehicle.mapper.Ttdc1Mapper;
import com.yshyerp.vehicle.service.Ttdc1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  Ttdc1ServiceImpl implements Ttdc1Service {
        @Autowired
        Ttdc1Mapper ttdc1Mapper;

        public List<Ttdc1> ttdclist(String dono){
                List<Ttdc1> list=ttdc1Mapper.ttdclist(dono);
         return list;
        }

}
