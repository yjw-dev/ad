package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.entity.Ddc1;
import com.yshyerp.vehicle.mapper.Ddc1Mapper;
import com.yshyerp.vehicle.service.Ddc1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Ddc1ServiceImpl implements Ddc1Service {

    @Autowired
    Ddc1Mapper ddc1Mapper;

    /**
     * 根据提单号查询 判断提货  单号是否重复
     * @param doNo
     * @return
     */
    public List<Ddc1> queryDdcByDoNo(String doNo){
        List<Ddc1> ddc1List=ddc1Mapper.queryDdcByDoNo(doNo);

return ddc1List;

    }
}
