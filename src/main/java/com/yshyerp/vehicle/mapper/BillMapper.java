package com.yshyerp.vehicle.mapper;

import com.yshyerp.vehicle.entity.Bdo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 大提单
 */
@Mapper
@Repository
public interface BillMapper {

    /**
     * 根据提单号查询提单明细
     * @param doNo 提单号
     * @return
     */
    public Bdo queryBdoByDoNo(String doNo);
}
