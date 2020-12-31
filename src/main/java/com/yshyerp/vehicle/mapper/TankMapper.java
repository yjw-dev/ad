package com.yshyerp.vehicle.mapper;

import com.yshyerp.vehicle.entity.Tank;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 储罐
 */
@Mapper
@Repository
public interface TankMapper {

    /**
     * 查询储罐信息
     * @return
     */
    public List<Tank> queryTankInfo();

    /**
     * 查询储罐明细
     * @return
     */
    public Tank queryTankInfoByTank(String tank);

    /**
     * 从船计量表中查询密度
     * @param commodity
     * @return
     */
    public String queryDensityByCrcTable(String commodity);

}
