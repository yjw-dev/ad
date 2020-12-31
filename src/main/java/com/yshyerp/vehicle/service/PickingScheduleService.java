package com.yshyerp.vehicle.service;

import com.yshyerp.vehicle.entity.VehiPlan;
import com.yshyerp.vehicle.vo.Response;
import com.yshyerp.vehicle.vo.VehiPlanVO;
import com.yshyerp.vehicle.vo.TankListVO;

import java.util.List;

/**
 * 新增提货计划
 */
public interface PickingScheduleService {

    /**
     * 保存提货计划
     * @param vo
     * @return
     */
    public boolean savePickingSchedule(TankListVO vo);

    /**
     * 查询槽车提货列表
     * @param vo
     * @return
     */
    public List<VehiPlan> queryPickingschedulelist(VehiPlanVO vo);

    /**
     * 查询提货计划明细
     * @param planNo 提货计划id
     * @return
     */
    public List<Object> queryPickingSchedule(String planNo) throws Exception;

    /**
     * 更新提货计划
     * @param vo
     * @return
     */
    public int updPickingSchedule(TankListVO vo);

    /**
     * 删除提货计划
     * @param planNo
     * @return
     */
    public int deletePickingSchedule(String planNo);

    /**
     * 入仓时更新jobNo
     * @param jobNo 入仓id
     * @param planNo    提货计划id
     * @return
     */
    public boolean updJobNo(String jobNo, String planNo);

}
