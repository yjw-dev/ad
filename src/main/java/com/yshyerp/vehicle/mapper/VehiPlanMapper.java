package com.yshyerp.vehicle.mapper;

import com.yshyerp.vehicle.entity.VehiPlan;
import com.yshyerp.vehicle.entity.VehiPlan1;
import com.yshyerp.vehicle.vo.TankListVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface VehiPlanMapper {
    /**
     * 新增 桶装出货计划计划
     * @mbggenerated
     */
    int insert(VehiPlan1 record);

    /**
     * 修改
     * @param record
     * @return
     */
    public int updVehiPlan1(VehiPlan1 record);

    //修改
    public int update(VehiPlan1 record);


    //新增
    int insertSelective(VehiPlan1 record);

    //查询VehiPlan全部信息
    List<VehiPlan1> list();

    //根据提pLlanNo 查询信息
    public VehiPlan1 queryplanNoByDoNo(String planNo);

    //逻辑删除
    int updVehiplanId(String planNo);

}