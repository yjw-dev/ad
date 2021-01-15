package com.yshyerp.vehicle.service;

import com.yshyerp.vehicle.entity.Donopict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


public interface DonopictService {

    //修改jobno
    public boolean updonpict(String jobNo, String no);


}