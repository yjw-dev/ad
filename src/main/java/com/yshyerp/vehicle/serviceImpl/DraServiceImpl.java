package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.entity.Dra;
import com.yshyerp.vehicle.mapper.DraMapper;
import com.yshyerp.vehicle.service.DraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DraServiceImpl implements DraService {
    @Autowired
    DraMapper draMapper;

    //新增  空桶记录
   public int insertSelective(Dra record){

       return draMapper.insertSelective(record);
    }
}
