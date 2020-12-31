package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.commons.ConstantUtil;
import com.yshyerp.vehicle.commons.ToolUtil;
import com.yshyerp.vehicle.entity.VehiPlan;
import com.yshyerp.vehicle.mapper.PickingScheduleMapper;
import com.yshyerp.vehicle.service.CommodityService;
import com.yshyerp.vehicle.service.ContractService;
import com.yshyerp.vehicle.service.PickingScheduleService;
import com.yshyerp.vehicle.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 新增提货计划
 */
@Service
public class PickingScheduleServiceImpl implements PickingScheduleService {

    @Autowired
    PickingScheduleMapper pickingScheduleMapper;

    @Autowired
    CommodityService commodityService;

    @Autowired
    ContractService contractService;

    @Override
    public boolean savePickingSchedule(TankListVO vo) {
        pickingScheduleMapper.savePickingSchedule(vo);
        return true;
    }

    @Override
    public List<VehiPlan> queryPickingschedulelist(VehiPlanVO vo) {
        return pickingScheduleMapper.queryPickingschedulelist(vo);
    }

    /**
     * 查询提货计划明细
     * @param planNo 提货计划id
     * @return
     */
    @Override
    public List<Object> queryPickingSchedule(String planNo) throws Exception{
        List<VehiPlan> VehiPlanList = pickingScheduleMapper.queryPickingSchedule(planNo);
        VehiPlan vehiPlan = null;
        List<TankCVO> tankCVOList = null;
        List<ContractVO> contractVOList = null;
        TankListVO tankListVO = new TankListVO();
        if(null!=VehiPlanList && VehiPlanList.size()>0){
            vehiPlan = VehiPlanList.get(0);
            tankListVO = this.doCastVo(vehiPlan, tankListVO);
            //打印名称下拉列表
            if(!StringUtils.isEmpty(tankListVO.getComm1())) {
                tankCVOList = commodityService.queryTankCByTank(tankListVO.getTank());
            }
            //合同下拉列表
            contractVOList = contractService.queryContractListByCustomer(tankListVO.getTankCustomer(), tankListVO.getTank());
        }
        List<Object> dataList = new ArrayList<Object>();
        dataList.add(tankListVO);
        dataList.add(tankCVOList);
        dataList.add(contractVOList);
        return dataList;
    }

    private TankListVO doCastVo(VehiPlan vehiPlan, TankListVO tankListVO) {
        tankListVO.setTxtVehicle1(vehiPlan.getVehicle1().trim());
        tankListVO.setTxtVehicle(vehiPlan.getVehicle().trim());
        tankListVO.setPlanNo(vehiPlan.getPlanNo().trim());
        tankListVO.setBdo(vehiPlan.getBdo()==true ? "1" : "0");
        tankListVO.setBillid(vehiPlan.getBillid());
        tankListVO.setBoxNo(vehiPlan.getBoxNo().trim());
        tankListVO.setComm1(vehiPlan.getCommodity1().trim());
        tankListVO.setTank(vehiPlan.getTank().trim());
        tankListVO.setCommodity(vehiPlan.getCommodity().trim());
        tankListVO.setContractI(vehiPlan.getContractI().trim());
        tankListVO.setCrrNo(vehiPlan.getCrrNo().trim());
        tankListVO.setCustomer(vehiPlan.getCustomer().trim());
        tankListVO.setDelivered(vehiPlan.getDelivered().trim());
        tankListVO.setDoNo(vehiPlan.getDoNo().trim());
        tankListVO.setEffectiveDays(vehiPlan.getVDate().intValue());
        tankListVO.setEstimateDate(vehiPlan.getDDate());
        tankListVO.setFaxno(vehiPlan.getFaxNo().trim());
        tankListVO.setIdno(vehiPlan.getIdNo().trim());
        tankListVO.setOriginal(vehiPlan.getOriginal()==true ? "1" : "0");
        tankListVO.setPlanType(vehiPlan.getPlanType().trim());
        tankListVO.setQuantity(vehiPlan.getQuantity().doubleValue());
        tankListVO.setRemarks(vehiPlan.getRemarks().trim());
        tankListVO.setTankCustomer(vehiPlan.getTankCustomer().trim());
        tankListVO.setTxtVehiW(vehiPlan.getVehiW()!=null ? vehiPlan.getVehiW().doubleValue() : null);
        tankListVO.setTxtVehiW2(vehiPlan.getVehiW2()!=null ? vehiPlan.getVehiW2().doubleValue() : null);
        tankListVO.setTxtVehiW3(vehiPlan.getVehiW3()!=null ? vehiPlan.getVehiW3().doubleValue() : null);
        tankListVO.setVehiV(vehiPlan.getVehiV()!=null ? vehiPlan.getVehiV().doubleValue() : null);
        tankListVO.setVehiW1(vehiPlan.getVehiW1()!=null ? vehiPlan.getVehiW1().doubleValue() : null);
        return tankListVO;
    }

    /**
     * 更新提货计划
     * @param vo
     * @return
     */
    @Override
    public int updPickingSchedule(TankListVO vo) {
        return pickingScheduleMapper.updPickingSchedule(vo);
    }

    /**
     * 逻辑删除一条提货计划
     * @param planNo
     * @return
     */
    @Override
    public int deletePickingSchedule(String planNo) {
        return pickingScheduleMapper.deletePickingSchedule(planNo);
    }

    /**
     * 入仓时更新jobNo
     * @param jobNo 入仓id
     * @param planNo    提货计划id
     * @return
     */
    @Override
    public boolean updJobNo(String jobNo, String planNo) {
        Map map = new HashMap();
        map.put("jobNo", jobNo);
        map.put("planNo", planNo.trim());
        int updFlag = pickingScheduleMapper.updJobNo(map);
        if(1==updFlag) {
            return true;
        }
        return false;
    }

}
