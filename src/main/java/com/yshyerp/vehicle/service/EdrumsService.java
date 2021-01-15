package com.yshyerp.vehicle.service;


import com.yshyerp.vehicle.entity.Edrums;
import com.yshyerp.vehicle.entity.EdsRepo;
import com.yshyerp.vehicle.vo.DjobVo;
import com.yshyerp.vehicle.vo.DjobVo1;

import java.util.List;

public interface EdrumsService {


    List<Edrums> slist();

    /**
     * 根据客户名称查询
     * @param
     * @return
     */
    List<Edrums> Bycustomer(DjobVo djobVo);


    //新增空桶
    int  insEdrums(Edrums record);

    //修改
    int updedrums(Edrums edrums);
}
