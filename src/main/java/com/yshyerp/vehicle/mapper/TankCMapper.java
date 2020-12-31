package com.yshyerp.vehicle.mapper;

import com.yshyerp.vehicle.entity.TankC;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 货品打印名称
 */
@Mapper
@Repository
public interface TankCMapper {

    /**
     * 根据罐号查询货品打印名称信息
     * @param tank
     * @return
     */
    public List<TankC> queryTankCListByTank(String tank);
}
