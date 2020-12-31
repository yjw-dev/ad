package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.entity.EDrumtmp;
import com.yshyerp.vehicle.mapper.EDrumtmpMapper;
import com.yshyerp.vehicle.service.EDrumtmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EDrumtmpServiceImpl implements EDrumtmpService {
    @Autowired
    private EDrumtmpMapper eDrumtmpMapper;



    @Override
    public int insert(EDrumtmp record) {


        return eDrumtmpMapper.insert(record);
    }

    @Override
    public int insertSelective(EDrumtmp record) {
        return 0;
    }
}
