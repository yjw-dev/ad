package com.yshyerp.vehicle.mapper;

import com.yshyerp.vehicle.entity.Trust;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 委托书
 */
@Mapper
@Repository
public interface TrustMapper {

    /**
     * 根据货主名称和货品查询委托书信息
     * @param map
     * @return
     */
    public List<Trust> queryTrustByCustomerAndCommodity(Map map);

    /**
     * 根据委托书号查委托书明细
     * @param crrNo
     * @return
     */
    public Trust queryTrustByCrrNo(String crrNo);

    /**
     *
     * @param map
     * @return
     */
    public int updTrustByCrrNo(Map map);
}
