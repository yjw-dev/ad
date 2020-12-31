package com.yshyerp.vehicle.mapper;

import com.yshyerp.vehicle.entity.Djob;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DjobMapper {

    /**
     * 根据罐号查询桶的总吨数
     * @param tank 罐号
     * @return
     */
    public Djob queryDjobByTank(String tank);
}
