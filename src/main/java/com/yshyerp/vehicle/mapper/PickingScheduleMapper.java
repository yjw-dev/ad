package com.yshyerp.vehicle.mapper;

import com.yshyerp.vehicle.entity.VehiPlan;
import com.yshyerp.vehicle.vo.VehiPlanVO;
import com.yshyerp.vehicle.vo.TankListVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 新增提货计划
 */
@Repository
@Mapper
public interface PickingScheduleMapper {

    /**
     * 保存提货计划
     */
    public void savePickingSchedule(TankListVO vo);

    /**
     * 查询提货计划列表
     * @param vo
     * @return
     */
    public List<VehiPlan> queryPickingschedulelist(VehiPlanVO vo);

    /**
     * 查询提货计划明细
     * @param planNo 提货计划id
     * @return
     */
    public List<VehiPlan> queryPickingSchedule(String planNo);

    /**
     * 更新提货计划
     * @param vo
     * @return
     */
    public int updPickingSchedule(TankListVO vo);

    /**
     * 逻辑删除一条提货计划
     * @param planNo
     * @return
     */
    public int deletePickingSchedule(String planNo);

    /**
     * 入仓时更新JobNo
     * @param map
     * @return
     */
    public int updJobNo(Map map);
}
