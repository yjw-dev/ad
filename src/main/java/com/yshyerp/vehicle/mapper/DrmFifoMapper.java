package com.yshyerp.vehicle.mapper;

import com.yshyerp.vehicle.entity.DrmFifo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 即灌即出
 */
@Mapper
@Repository
public interface DrmFifoMapper {

    /**
     * 根据提单号查询即灌即出表
     * @param doNo 提单号
     * @return
     */
    public List<DrmFifo> queryDrmFifoByDoNo(String doNo);
}
