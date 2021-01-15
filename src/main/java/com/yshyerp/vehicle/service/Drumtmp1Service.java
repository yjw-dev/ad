package com.yshyerp.vehicle.service;

import com.yshyerp.vehicle.entity.Drumtmp2;
import com.yshyerp.vehicle.entity.Drumtmpout;

import java.util.List;


public interface Drumtmp1Service {

    //查询
    List<Drumtmpout> listdrum();

    //获取  drums
    Drumtmp2 getCustomerByCustomerName(String customer);

    //获取  sum drums
    Drumtmp2 getvehiplansum(String customer);


    //获取  DRUMS     sum(balance)
    Drumtmp2 getdrumsum(String customer);
}
