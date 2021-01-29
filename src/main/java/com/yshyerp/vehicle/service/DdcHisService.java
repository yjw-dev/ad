package com.yshyerp.vehicle.service;

import com.yshyerp.vehicle.entity.DdcHis;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface DdcHisService {

    List<DdcHis> ddclist(String type1);

//    int insert(DdcHis record);
//
//
//    int insertSelective(DdcHis record);
}