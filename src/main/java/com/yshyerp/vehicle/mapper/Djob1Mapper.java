package com.yshyerp.vehicle.mapper;


import com.yshyerp.vehicle.entity.Djob1;
import com.yshyerp.vehicle.vo.DjobVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Djob1Mapper {

    /**
     * 根据大客户进行查询说
     *
     * @return
     */
    List<Djob1> Bycustomer(DjobVo djobVo);


}
