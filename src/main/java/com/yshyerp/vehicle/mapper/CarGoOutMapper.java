package com.yshyerp.vehicle.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 委托书出货过程
 */
@Mapper
@Repository
public interface CarGoOutMapper {

    /**
     * 插入数据
     * @param map
     * @return
     */
    public int insertCarGoOut(Map map);
}
