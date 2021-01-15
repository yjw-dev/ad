package com.yshyerp.vehicle.mapper;

import com.yshyerp.vehicle.entity.Djob1;
import com.yshyerp.vehicle.entity.Edrums;
import com.yshyerp.vehicle.entity.TtdcTemp;
import com.yshyerp.vehicle.vo.DjobVo;
import com.yshyerp.vehicle.vo.DjobVo1;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface EdrumsMapper {

    //查询VehiPlan全部信息
    List<Edrums> slist();

    //根据多字段查询
    public Edrums mapedurms(Map map);



    /**
     * 根据客户名称查询
     */
    List<Edrums> Bycustomer(DjobVo djobVo);

    //新增空桶
    int  insEdrums(Edrums record);


    //修改
    int updedrums(Edrums edrums);

}
