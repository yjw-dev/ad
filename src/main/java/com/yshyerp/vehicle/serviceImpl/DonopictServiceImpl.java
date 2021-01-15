package com.yshyerp.vehicle.serviceImpl;


import com.yshyerp.vehicle.mapper.DonopictMapper;
import com.yshyerp.vehicle.service.DonopictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DonopictServiceImpl implements DonopictService {

    @Autowired
    DonopictMapper donopictMapper;
    //修改


    @Override
    public boolean updonpict(String jobNo, String no) {

        Map map = new HashMap();
        map.put("jobNo", jobNo);
        map.put("no", no);
        int updFlag = donopictMapper.updonpict(map);
        if(1==updFlag) {
            return true;
        }
        return false;

    }
}