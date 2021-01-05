package com.yshyerp.vehicle.mapper;

import com.yshyerp.vehicle.entity.Djob1;
import com.yshyerp.vehicle.entity.Edrums;
import com.yshyerp.vehicle.vo.DjobVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EdrumsMapper {

    //查询VehiPlan全部信息
    List<Edrums> slist(String customer);

    /**
     * 根据客户名称查询
     * @param customer 客户名
     * @return
     */
    List<Edrums> Bycustomer(DjobVo djobVoS);


}
