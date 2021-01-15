package com.yshyerp.vehicle.service;

import com.yshyerp.vehicle.entity.Djob;
import com.yshyerp.vehicle.entity.Djob1;
import com.yshyerp.vehicle.vo.DjobVo;
import com.yshyerp.vehicle.vo.DjobVo1;

import java.util.List;

/**
 * 桶工作单
 */

public interface Djob1Service {

    /**
     * 根据客户名称查询
     * @param customer 客户名
     * @return
     */
    List<Djob1> Bycustomer(DjobVo djobVo);




}
