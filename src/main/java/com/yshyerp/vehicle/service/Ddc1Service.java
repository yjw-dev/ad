package com.yshyerp.vehicle.service;

import com.yshyerp.vehicle.entity.Ddc1;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface Ddc1Service {

    /**
     * 根据提单号查询 判断提货  单号是否重复
     * @param doNo
     * @return
     */
    public List<Ddc1> queryDdcByDoNo(String doNo);
}
