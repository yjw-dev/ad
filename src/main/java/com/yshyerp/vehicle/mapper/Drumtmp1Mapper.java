package com.yshyerp.vehicle.mapper;

import com.yshyerp.vehicle.entity.Drumtmp2;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Mapper
@Repository
public interface Drumtmp1Mapper {


  
    //获取   DRUMTMP  sum(drums)
    Drumtmp2 getCustomerByCustomerName(String customer);

    //获取  vehi_plan        sum(drums)
    Drumtmp2 getvehiplansum(String customer);

    //获取  DRUMS     sum(balance)
    Drumtmp2 getdrumsum(String customer);

    //获取 drumtmp  sum(drums)
    Drumtmp2  drumsum1(Map map);

    //修改  备用1  根据车牌号和no1修改
    int updapz1(Drumtmp2 drumtmp2);

    //修改  备用2  根据车牌号 修改  第一次称重
    int updapz2(Drumtmp2 drumtmp2);

    //修改   备用3    修改  以出仓   根据车牌号和no1修改   修改jobno
    int updapz3(Drumtmp2 drumtmp2);


}
