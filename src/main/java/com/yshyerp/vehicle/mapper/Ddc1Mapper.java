package com.yshyerp.vehicle.mapper;

import com.yshyerp.vehicle.entity.Ddc;
import com.yshyerp.vehicle.entity.Ddc1;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 桶装货出货记录
 */
@Mapper
@Repository
public interface Ddc1Mapper {

    /**
     * 根据提单号查询桶装货出货表
     * @param doNo
     * @return
     */
    public List<Ddc1> queryDdcByDoNo(String doNo);
}
