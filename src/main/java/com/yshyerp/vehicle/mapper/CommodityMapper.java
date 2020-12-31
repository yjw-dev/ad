package com.yshyerp.vehicle.mapper;

import com.yshyerp.vehicle.entity.Commodity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 货品
 */
@Mapper
@Repository
public interface CommodityMapper {

    /**
     * 根据货品名称查询货品
     * @param commodity 货品名称
     * @return
     */
    public List<Commodity> queryCommodityByCommodity(String commodity);

    /**
     * 更新最新一次密度
     * @param map
     * @return
     */
    public Boolean updTankDensity(Map map);
}
