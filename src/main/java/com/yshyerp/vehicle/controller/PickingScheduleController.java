package com.yshyerp.vehicle.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yshyerp.vehicle.commons.ConstantUtil;
import com.yshyerp.vehicle.commons.ToolUtil;
import com.yshyerp.vehicle.entity.SysData;
import com.yshyerp.vehicle.entity.VehiPlan;
import com.yshyerp.vehicle.service.*;

import com.yshyerp.vehicle.vo.VehiPlanVO;
import com.yshyerp.vehicle.vo.TankListVO;
import com.yshyerp.vehicle.vo.Request;
import com.yshyerp.vehicle.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * 提货计划列表页面
 */
@Slf4j
@RestController
@ResponseBody
@RequestMapping("/pickingschedule")
public class PickingScheduleController {

    @Autowired
    PickingScheduleService pickingScheduleService;

    @Autowired
    TankService tankService;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    CustomerService customerService;

    @Autowired
    CommodityService commodityService;

    @Autowired
    TrustService trustService;

    @Autowired
    BillService billService;

    @Autowired
    SysDataService sysDataService;

    /**
     * 提货计划报错校验
     * @param request
     * @return
     */
    @PostMapping("plancheckall")
    public Response planCheckAll(@RequestBody Request<TankListVO> request) {
        log.info("--------------------pickingSchedule/checkall START--------------------");
        TankListVO pickingScheduleVO = request.getData();
        //请求参数空值校验
        Response response = this.checkPlanRequestParamsNull(pickingScheduleVO);
        if(null!=response) {
            return response;
        }
        //请求参数业务校验
        response = this.checkPlanRequestParams(pickingScheduleVO);
        if(null!=response){
            return response;
        }
        //校验通过
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
    }

    /**
     * 保存提货计划
     * @param request
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
    @PostMapping("savepickingschedule")
    public Response addPost(@RequestBody Request<TankListVO> request) {
        log.info("--------------------pickingSchedule/savepickingschedule START--------------------");
        try {
            //生成新数据的planNo
            SysData sysData = sysDataService.querySysData();
            String planNo = sysDataService.getSysDataId(sysData, "plan_no");
            TankListVO vo = request.getData();
            vo.setPlanNo(planNo);
            //保存提货计划
            pickingScheduleService.savePickingSchedule(vo);
            //更新sysData
            sysDataService.updSysDataIdToPlanNo(planNo);
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
        }catch(Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.SAVEVEHIPLAN_ERROR_CODE);
        }
    }

    /**
     * 查询提货计划列表
     * @param request
     * @return
     */
    @PostMapping("pickingschedulelist")
    public Response postListInit(@RequestBody Request<VehiPlanVO> request) {
        log.info("--------------------pickingSchedule/pickingschedulelist START--------------------");
        if(!StringUtils.isEmpty(request.getPageNum())&& !StringUtils.isEmpty(request.getPageSize())) {
            PageHelper.startPage(request.getPageNum(), request.getPageSize());
        }
        try {
            List<VehiPlan> vehiPlanList = pickingScheduleService.queryPickingschedulelist(request.getData());
            List<VehiPlanVO> vehiPlanVOList = new ArrayList<VehiPlanVO>();
            for(VehiPlan vehiPlan:vehiPlanList) {
                vehiPlanVOList.add((VehiPlanVO)ToolUtil.doCastVo(vehiPlan, new VehiPlanVO()));
            }
            PageInfo pageInfo = new PageInfo(vehiPlanList);
            pageInfo.setList(vehiPlanVOList);
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, pageInfo,ConstantUtil.SUCCESS_CODE, null);
        }catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }

    }

    /**
     * 查询提货计划明细
     * @param data id
     * @return
     */
    @GetMapping("querypickingschedule/{data}")
    public Response getUpdateInit(@PathVariable String data) {
        log.info("--------------------pickingSchedule/querypickingschedule START--------------------");
        try {
            List<Object> object = pickingScheduleService.queryPickingSchedule(data);
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, object, ConstantUtil.SUCCESS_CODE, null);
        }catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }
    }

    /**
     * 更新提货计划
     * @param request
     * @return
     */
    @PutMapping("updpickingschedule")
    public Response updatePut(@RequestBody Request<TankListVO> request) {
        log.info("--------------------pickingSchedule/updpickingschedule START--------------------");
        TankListVO tankListVO = request.getData();
        int updFlag = pickingScheduleService.updPickingSchedule(tankListVO);
        System.out.println(pickingScheduleService.updPickingSchedule(request.getData()));
        if(updFlag>0) {
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
        }
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, false, ConstantUtil.SUCCESS_CODE, null);
    }

    /**
     * 删除提货计划
     * @param data planNo
     * @return
     */
    @PutMapping("deletepickingschedule/{data}")
    public Response delete(@PathVariable String data) {
        log.info("--------------------pickingSchedule/deletepickingschedule START--------------------");
        if(StringUtils.isEmpty(data)) {
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }
        int deleteFlag = pickingScheduleService.deletePickingSchedule(data);
        if(deleteFlag>0) {
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
        }
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, false, ConstantUtil.SUCCESS_CODE, null);
    }


    ////////////////////////////////////////// /////////////////////////////////////////////////////
    /**
     * 提货计划阶段请求参数空值校验
     * @param vo 请求对象
     * @return
     */
    private Response checkPlanRequestParamsNull(TankListVO vo) {
        if (StringUtils.isEmpty(vo.getTxtVehicle())) {
            //车牌号码不能为空
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE3, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }else if(StringUtils.isEmpty(vo.getTankCustomer())) {
            //罐主不能为空
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE11, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }else if(StringUtils.isEmpty(vo.getCustomer())) {
            //货主不能为空
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE4, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }else if(StringUtils.isEmpty(vo.getDoNo())) {
            //提单号不能为空
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE5, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }else if(StringUtils.isEmpty(vo.getCrrNo())) {
            //委托书号不能为空
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE6, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }else if(vo.getQuantity()==0) {
            //提货数量不能为0
            return Response.success(ConstantUtil.MESSAGE_QUANTITY_MSG1, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_QUANTITY_CODE1);
        }else if (StringUtils.isEmpty(vo.getContractI())) {
            //合同号不能为空
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE12, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }
        return null;
    }

    /**
     * 提货计划阶段请求参数业务校验
     * @param pickingScheduleVO
     * @return
     */
    private Response checkPlanRequestParams(TankListVO pickingScheduleVO) {
        //提货数量
        double quantity = pickingScheduleVO.getQuantity();
        //貨主
        String customer = pickingScheduleVO.getCustomer();
        //货品名称
        String commodity = pickingScheduleVO.getCommodity();
        //提单号
        String doNo = pickingScheduleVO.getDoNo();
        //箱号
        String cNo = pickingScheduleVO.getBoxNo();

        //储罐校验
        String message = tankService.checkTank(pickingScheduleVO.getTank(), quantity);
        if(!ConstantUtil.CHECK_SUCCESS.equals(message)) {
            return Response.success(message, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_TANK_CODE0);
        }
        //校验货主是否存在
        if(!customerService.checkCustomer(customer)) {
            return Response.success(ConstantUtil.MESSAGE_CUSTOMER_MSG1, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_CUSTOMER_CODE1);
        }
        //委托书号校验
        String trustMessage = trustService.checkTrust(pickingScheduleVO.getCrrNo(), customer, commodity, quantity,"1", 0);
        if(!StringUtils.isEmpty(trustMessage)) {
            return Response.success(trustMessage, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_CRRNO_CODE0);
        }
        //备注校验(校验委托书)
        if(!StringUtils.isEmpty(pickingScheduleVO.getRemarks())) {
            String remarksMessage = trustService.checkTrustByRemarks(pickingScheduleVO.getRemarks(), doNo, quantity,"1", 0);
            if(null!=remarksMessage) {
                return Response.success(remarksMessage, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_REMARKS_CODE0);
            }
        }
        //勾选正本提单时不需要输入传真号，不勾选时必须输入传真号
        if(StringUtils.isEmpty(pickingScheduleVO.getFaxno()) && "0".equals(pickingScheduleVO.getOriginal())) {
            return Response.error(ConstantUtil.MESSAGE_DONO_MSG1, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.MESSAGE_DONO_CODE1);
        }
        //大提单校验
        if("1".equals(pickingScheduleVO.getBdo())) {
            String bDoMessage = billService.checkBigBill(doNo, customer, commodity, quantity,1, 0);
            if(null!=bDoMessage) {
                return Response.success(bDoMessage, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_DONO_CODE0);
            }
        }else {
            String bDoMessage = billService.checkBill(doNo, "1");
            if(!StringUtils.isEmpty(bDoMessage)) {
                return Response.success(bDoMessage, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_DONO_CODE0);
            }
        }
        //箱重必填项校验：罐主是"EXXOX"并且箱号不为空并且箱重<=0并且箱号不等于车牌号并且箱号不等于挂车好
        if("EXXON".equals(pickingScheduleVO.getTankCustomer()) && !StringUtils.isEmpty(cNo) && pickingScheduleVO.getVehiW1()<=0.0d
                && !cNo.equals(pickingScheduleVO.getTxtVehicle()) && !cNo.equals(pickingScheduleVO.getTxtVehicle1())) {
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE7, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }
        return null;
    }

}
