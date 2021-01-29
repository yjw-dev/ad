package com.yshyerp.vehicle.service;


import com.yshyerp.vehicle.entity.OtherDict;

import java.util.List;
import java.util.Map;

public interface OtherDicService {

    //查询    颜色
    List<Map<String,Object>> OtherDiclist();


    List<Map<String,Object>>    dlist();

    //出货类型
    List<OtherDict>  Othertype();


    //批号
    List<OtherDict> Othercode();
}
