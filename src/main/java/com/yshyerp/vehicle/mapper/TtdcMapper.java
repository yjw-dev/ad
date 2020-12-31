package com.yshyerp.vehicle.mapper;

import com.yshyerp.vehicle.entity.Ttdc;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 槽车出货记录
 */
@Mapper
@Repository
public interface TtdcMapper {

    public List<Ttdc> queryTtdcByDoNo(String doNo);
}
