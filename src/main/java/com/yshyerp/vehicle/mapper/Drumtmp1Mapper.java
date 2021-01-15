package com.yshyerp.vehicle.mapper;

import com.yshyerp.vehicle.entity.Drumtmp2;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface Drumtmp1Mapper {


  
    //获取   DRUMTMP  sum(drums)
    Drumtmp2 getCustomerByCustomerName(String customer);

    //获取  vehi_plan        sum(drums)
    Drumtmp2 getvehiplansum(String customer);

    //获取  DRUMS     sum(balance)
    Drumtmp2 getdrumsum(String customer);


}
