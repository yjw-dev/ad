package com.yshyerp.vehicle.service;

import com.yshyerp.vehicle.entity.Djob;

/**
 * 桶工作单
 */
public interface DjobService {

    /**
     * 根据罐号查询桶的总吨数
     * @param tank 罐号
     * @return
     */
    public Djob queryDjobByTank(String tank);
}
