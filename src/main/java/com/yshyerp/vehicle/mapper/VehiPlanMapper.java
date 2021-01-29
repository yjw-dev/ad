package com.yshyerp.vehicle.mapper;

import com.yshyerp.vehicle.entity.VehiPlan;
import com.yshyerp.vehicle.entity.VehiPlan1;
import com.yshyerp.vehicle.vo.TankListVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

   //提货计划
   List<VehiPlan1>  thjh(String planNo);



    //新增
    int insertSelective(VehiPlan1 record);

    //查询VehiPlan全部信息
    List<VehiPlan1> list(Map map);


    //提货计划DRA      新增
    List<VehiPlan1> thjhcx(String planNo);

    //提货计划 DDC    新增
    List<VehiPlan1> thjhcxddc(String planNo);



    //根据提pLlanNo 查询信息
    public VehiPlan1 queryplanNoByDoNo(String planNo);

    //逻辑删除
    int updVehiplanId(String planNo);

    //逻辑删除   jobno
    int   updplanNo(String planNo);

    //修改   jobno
    int updjobno(Map map);


}