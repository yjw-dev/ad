package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.entity.Djob;
import com.yshyerp.vehicle.mapper.DjobMapper;
import com.yshyerp.vehicle.service.DjobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 桶工作单
 */
@Service
public class DjobServiceImpl implements DjobService {

    @Autowired
    DjobMapper djobMapper;

    /**
     * 根据罐号查询桶的总吨数
     * @param tank 罐号
     * @return
     */
    public Djob queryDjobByTank(String tank) {

        return djobMapper.queryDjobByTank(tank);
    }
}
