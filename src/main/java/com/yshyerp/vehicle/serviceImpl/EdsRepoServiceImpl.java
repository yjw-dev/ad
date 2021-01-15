package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.entity.EdsRepo;

import com.yshyerp.vehicle.mapper.EdsRepoMapper;
import com.yshyerp.vehicle.service.EdsRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EdsRepoServiceImpl implements EdsRepoService {

    @Autowired
    EdsRepoMapper edsRepoMapper;

    //新增
    public int insert(EdsRepo record){
        return edsRepoMapper.insert(record);
    }
}