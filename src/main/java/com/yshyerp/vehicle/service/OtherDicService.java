package com.yshyerp.vehicle.service;


import com.yshyerp.vehicle.entity.OtherDict;

import java.util.List;

public interface OtherDicService {

    //查询    颜色
    List<OtherDict> OtherDiclist();


    //出货类型
    List<OtherDict>  Othertype();


    //批号
    List<OtherDict> Othercode();
}
