package com.yshyerp.vehicle.service;


import com.yshyerp.vehicle.entity.Edrums;
import com.yshyerp.vehicle.vo.DjobVo;

import java.util.List;

public interface EdrumsService {


    List<Edrums> slist(String customer);

    /**
     * 根据客户名称查询
     * @param customer 客户名
     * @return
     */
    List<Edrums> Bycustomer(DjobVo djobVo);


}
