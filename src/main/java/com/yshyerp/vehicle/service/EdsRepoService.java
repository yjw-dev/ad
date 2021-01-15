package com.yshyerp.vehicle.service;

import com.yshyerp.vehicle.entity.EdsRepo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


public interface EdsRepoService {



    //新增
    int insert(EdsRepo record);
}