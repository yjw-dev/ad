package com.yshyerp.vehicle.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yshyerp.vehicle.commons.ConstantUtil;
import com.yshyerp.vehicle.commons.ToolUtil;
import com.yshyerp.vehicle.entity.Commodity;
import com.yshyerp.vehicle.entity.SysData;
import com.yshyerp.vehicle.entity.TtdcTemp;
import com.yshyerp.vehicle.service.*;
import com.yshyerp.vehicle.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 槽车出入仓业务类
 */
@Slf4j
@RestController
@ResponseBody
@RequestMapping("/tankinout")
public class TankVehicleInOutController {

    @Autowired
    TankVehicleInOutService tankVehicleInOutService;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    TankService tankService;

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

    @Autowired
    PickingScheduleService pickingScheduleService;

    @Autowired
    DocumentService documentService;

    /**
     * 查询入仓列表页面
     * @param request 查询条件
     * @return
     */
    @PostMapping("tankinlist")
    public Response postListInit(@RequestBody Request<TankVehicleListVO> request) {
        log.info("--------------------tankinout/tankinlist START--------------------");
        if(!StringUtils.isEmpty(request.getPageNum())&& !StringUtils.isEmpty(request.getPageSize())) {
            PageHelper.startPage(request.getPageNum(), request.getPageSize());
        }
        try {
            List<TtdcTemp> ttdcTempList = tankVehicleInOutService.queryTankVehicleList(request.getData());
            List<TankVehicleInOutResponseVO> tankVehicleInOutResponseVOList = new ArrayList<TankVehicleInOutResponseVO>();
            for(TtdcTemp ttdcTemp : ttdcTempList) {
                tankVehicleInOutResponseVOList.add((TankVehicleInOutResponseVO)ToolUtil.doCastVo(ttdcTemp, new TankVehicleInOutResponseVO()));
            }
            PageInfo pageInfo = new PageInfo(ttdcTempList);
            pageInfo.setList(tankVehicleInOutResponseVOList);
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, pageInfo, ConstantUtil.SUCCESS_CODE, null);
        }catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }
    }

    /**
     * 地磅开关接口
     * @param data 启动1/关闭0称重功能
     * @return
     */
    @GetMapping("startswitch/{data}")
    public Response setStartSwitch(@PathVariable String data) {
        if(StringUtils.isEmpty(data)) {
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }
        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取访问主机的IP地址
        String ipAddress = ToolUtil.getIpAddr(request);
        String flag = tankVehicleInOutService.updStartSwitch(data, ipAddress);
        if(null!=flag) {
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, flag, ConstantUtil.SUCCESS_CODE, null);
        }
        return Response.error(ConstantUtil.WEIGHBRIDGE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.WEIGHBRIDGE_ERROR_CODE);
    }

    /**
     * 查询地磅重量
     * @return
     */
    @GetMapping("queryswitch")
    public Response queryWeighbridge() {
        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取访问主机的IP地址
        String ipAddress = ToolUtil.getIpAddr(request);
        WeighbridgeVO weighbridgeVO = tankVehicleInOutService.queryWeighbridge(ipAddress);
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, weighbridgeVO, ConstantUtil.SUCCESS_CODE, null);
    }

    /**
     * 入仓校验
     * @param request
     * @return
     */
    //身份证、委托书、挂车号、箱号、4个重量、提货数量、货主、送货地点
    @PostMapping("checkwarehousing")
    public Response checkWarehousing(@RequestBody Request<WarehousingVO> request) {
        WarehousingVO vo = request.getData();
        //请求参数空值校验
        Response response = this.checkWarehousingRequestParamsNull(vo);
        if(null!=response) {
            return response;
        }
        //业务校验
        response = this.checkWarehousingRequestParams(vo);
        if(null!=response) {
            return response;
        }
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
    }

    /**
     * 入仓请求参数空值校验
     * @param vo
     * @return
     */
    private Response checkWarehousingRequestParamsNull(WarehousingVO vo) {
        if(StringUtils.isEmpty(vo.getIdno())) {
            //司机身份证号不能为空
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE2, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }else if(StringUtils.isEmpty(vo.getCustomer())) {
            //货主不能为空
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE4, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }else if(StringUtils.isEmpty(vo.getCrrNo())) {
            //委托书号不能为空
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE6, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }else if(vo.getQuantity()==0) {
            //提货数量不能为0
            return Response.success(ConstantUtil.MESSAGE_QUANTITY_MSG1, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_QUANTITY_CODE1);
        }else if(StringUtils.isEmpty(vo.getTxtVehicle1())) {
            //挂车号不能为空
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE13, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }else if (StringUtils.isEmpty(vo.getWeight1()) || 0==vo.getWeight1().doubleValue()) {
            //地磅称重异常
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE14, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }
        return null;
    }

    private Response checkWarehousingRequestParams(WarehousingVO vo) {
        //校验车辆是否已入仓
        if(!vehicleService.checkVehicleIsNull(vo.getTxtVehicle())){
            return Response.success(ConstantUtil.MESSAGE_VEHICLE_CODE1, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_VEHICLE_MSG1);
        }
        //储罐校验
        String message = tankService.checkTank(vo.getTank(), vo.getQuantity());
        if(!ConstantUtil.CHECK_SUCCESS.equals(message)) {
            return Response.success(message, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_TANK_CODE0);
        }
        //校验货主是否存在
        if(!customerService.checkCustomer(vo.getCustomer())) {
            return Response.success(ConstantUtil.MESSAGE_CUSTOMER_MSG1, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_CUSTOMER_CODE1);
        }
        //校验提货数量
        String quantityMessage = commodityService.checkQuantity(vo.getTank(),vo.getVehiV(),vo.getTxtVehiW(),
                vo.getQuantity(), vo.getTankCustomer(),vo.getTxtVehiW2(),vo.getTxtVehiW3(),vo.getVehiW1(),"2", null, 0);
        if(!StringUtils.isEmpty(quantityMessage)) {
            if("EXXON规定提货数量控制在安全装货范围之内，如要放行，请输入授权密码：".equals(quantityMessage)){
                return Response.success(quantityMessage, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.INPUT_QUANTITY_CODE0);
            } else{
                return Response.success(quantityMessage, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_QUANTITY_CODE0);
            }
        }
        //委托书号校验
        String trustMessage = trustService.checkTrust(vo.getCrrNo(), vo.getCustomer(), vo.getCommodity(), vo.getQuantity(),"2", 0);
        if(!StringUtils.isEmpty(trustMessage)) {
            return Response.success(trustMessage, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_CRRNO_CODE0);
        }
        //大提单校验
        if("1".equals(vo.getBdo())) {
            String bDoMessage = billService.checkBigBill(vo.getDoNo(), vo.getCustomer(), vo.getCommodity(),
                    vo.getQuantity(),2, 0);
            if(null!=bDoMessage) {
                return Response.success(bDoMessage, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_DONO_CODE0);
            }
        }else {
            String bDoMessage = billService.checkBill(vo.getDoNo(), "2");
            if(!StringUtils.isEmpty(bDoMessage)) {
                return Response.success(bDoMessage, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_DONO_CODE0);
            }
        }
        //勾选正本提单时不需要输入传真号，不勾选时必须输入传真号
        if(StringUtils.isEmpty(vo.getFaxno()) && "0".equals(vo.getOriginal())) {
            return Response.error(ConstantUtil.MESSAGE_DONO_MSG1, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.MESSAGE_DONO_CODE1);
        }
        //司机黑名单校验
        String customerMessage = customerService.checkDriverBlack(vo.getIdno());
        if(!StringUtils.isEmpty(customerMessage)) {
            return Response.success(ConstantUtil.MESSAGE_CUSTOMER_CODE2, false, ConstantUtil.SUCCESS_CODE, customerMessage);
        }
        //备注校验(校验委托书)
        if(!StringUtils.isEmpty(vo.getRemarks())) {
            String remarksMessage = trustService.checkTrustByRemarks(vo.getRemarks(), vo.getDoNo(), vo.getQuantity(),"2", 0);
            if(null!=remarksMessage) {
                return Response.success(remarksMessage, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_REMARKS_CODE0);
            }
        }
        //箱重必填项校验：罐主是"EXXOX"并且箱号不为空并且箱重<=0并且箱号不等于车牌号并且箱号不等于挂车好
        if("EXXON".equals(vo.getTankCustomer()) && !StringUtils.isEmpty(vo.getBoxNo()) && vo.getVehiW1()<=0.0d
                && !vo.getBoxNo().equals(vo.getTxtVehicle()) && !vo.getBoxNo().equals(vo.getTxtVehicle1())) {
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE7, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
    }


    /**
     * 保存入仓信息
     * @param request
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
    @PostMapping("savewarehousing")
    public Response addPost(@RequestBody Request<WarehousingVO> request) {
        WarehousingVO vo = request.getData();
        HttpServletRequest httpRequest =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取访问主机的IP地址,用来判断几号地磅
        String ipAddress = ToolUtil.getIpAddr(httpRequest);
        if(ConstantUtil.WB1.equals(ipAddress)) {
            vo.setWb("1");
        }else {
            vo.setWb("2");
        }
        try {
            //获取装车台
            String lcstation = tankVehicleInOutService.queryLcstation(vo.getTank());
            if(null == lcstation) {
                //灌车台安排出错
                return Response.error(ConstantUtil.LCSTATION_ERROR_MESSAGE, ConstantUtil.SUCCESS_CODE, ConstantUtil.LCSTATION_ERROR_CODE);
            }
            vo.setStation(lcstation);
            SysData sysData = sysDataService.querySysData();
            String jobNo = sysDataService.getSysDataId(sysData, "job_no");
            vo.setJobNo(jobNo);
            //保存入仓信息
            tankVehicleInOutService.saveTtdcTemp(vo);
            //更新提货计划
            pickingScheduleService.updJobNo(jobNo, vo.getPlanNo());
            //更新job_no
            sysDataService.updSysDataIdToJobNo(jobNo);
            //保存摄像头截图

            //打印入仓单

            return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
        }catch (Exception e) {
            //入仓保存失败
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.SAVETTDCTEMP_ERROR_CODE);
        }
    }

    /**
     * 打开出仓页面（查询入仓明细）
     * @param data 入仓工作单号jobNo
     * @return
     */
    @GetMapping("ttdctempdetail")
    public Response queryTtdcTempDetail(@PathVariable String data) {
        if(StringUtils.isEmpty(data)) {
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }
        WarehousingVO vo = tankVehicleInOutService.queryTtdcTempDetail(data);
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, vo, ConstantUtil.SUCCESS_CODE, null);
    }

    /**
     * 出仓校验
     * @param request
     * @return
     */
    @PostMapping("checkoutwarehouse")
    public Response checkOutWarehouse(@RequestBody Request<WarehousingVO> request) {
        WarehousingVO vo = request.getData();
        //请求参数空值校验
        Response response = this.checkOutWarehouseRequestParamsNull(vo);
        if(null!=response) {
            return response;
        }
        response = this.checkOutWarehouseRequestParams(vo);
        if(null!=response) {
            return  response;
        }
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
    }

    /**
     * 出仓请求参数校验
     * @param vo
     * @return
     */
    private Response checkOutWarehouseRequestParamsNull(WarehousingVO vo) {
        if(StringUtils.isEmpty(vo.getOperator1())) {
            //操作员1不能为空
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE15, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }
        if(StringUtils.isEmpty(vo.getSealNo())) {
            //封条号不能为空
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE16, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }
        if(StringUtils.isEmpty(vo.getCheckCode())) {
            //校验码不能为空
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE17, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }
        return null;
    }

    /**
     * 出仓业务校验
     * @param vo
     * @return
     */
    private Response checkOutWarehouseRequestParams(WarehousingVO vo) {
        //地磅位置校验
        HttpServletRequest httpRequest =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取访问主机的IP地址,用来判断几号地磅
        String ipAddress = ToolUtil.getIpAddr(httpRequest);
        if(ConstantUtil.WB1.equals(ipAddress) && !vo.getWb().equals("1")) {
            return Response.success(ConstantUtil.MESSAGE_TTDCTEMP_MSG0, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_TTDCTEMP_CODE0);
        }else if(ConstantUtil.WB2.equals(ipAddress) && !vo.getWb().equals("2"))
            return Response.success(ConstantUtil.MESSAGE_TTDCTEMP_MSG0, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_TTDCTEMP_CODE0);
        //验证校验码（jobNo最后两位）
        if(!vo.getJobNo().substring(4, vo.getJobNo().length()).equals(vo.getCheckCode())) {
            return Response.success(ConstantUtil.MESSAGE_TTDCTEMP_MSG1, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_TTDCTEMP_CODE1);
        }
        //备注校验(校验委托书)
        if(!StringUtils.isEmpty(vo.getRemarks())) {
            String remarksMessage = trustService.checkTrustByRemarks(vo.getRemarks(), vo.getDoNo(), vo.getQuantity(),"3", vo.getNetWeight());
            if(null!=remarksMessage) {
                return Response.success(remarksMessage, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_REMARKS_CODE0);
            }
        }
        //提货数量校验
        String quantityMessage = commodityService.checkQuantity(vo.getTank(),vo.getVehiV(),vo.getTxtVehiW(), vo.getQuantity(),
                vo.getTankCustomer(),vo.getTxtVehiW2(),vo.getTxtVehiW3(),vo.getVehiW1(),"3", vo.getTxtVehicle(), vo.getNetWeight());
        if(!StringUtils.isEmpty(quantityMessage)) {
            return Response.success(quantityMessage, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_QUANTITY_CODE0);
        }

        //委托书号校验
        String trustMessage = trustService.checkTrust(vo.getCrrNo(), vo.getCustomer(), vo.getCommodity(), vo.getQuantity(),"3", vo.getNetWeight());
        if(!StringUtils.isEmpty(trustMessage)) {
            return Response.success(trustMessage, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_CRRNO_CODE0);
        }
        //提单号校验
        if("1".equals(vo.getBdo())) {
            //大提单校验
            String bDoMessage = billService.checkBigBill(vo.getDoNo(), vo.getCustomer(), vo.getCommodity(),
                    vo.getQuantity(),3, vo.getNetWeight());
            if(null!=bDoMessage) {
                return Response.success(bDoMessage, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_DONO_CODE0);
            }
        }else {
            String bDoMessage = billService.checkBill(vo.getDoNo(), "3");
            if(!StringUtils.isEmpty(bDoMessage)) {
                return Response.success(bDoMessage, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_DONO_CODE0);
            }
        }
        //储罐校验
        String message = tankService.checkTank(vo.getTank());
        if(!ConstantUtil.CHECK_SUCCESS.equals(message)) {
            return Response.success(message, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_TANK_CODE0);
        }
        //仓内仓外地磅校验
        if(Math.abs(vo.getNetWeight()-vo.getEeWeight())>50) {
            String weightMessage = "提醒：本次车辆净重与车台净重差异" + Math.abs(vo.getNetWeight()-vo.getEeWeight()) + "Kg,请查证";
            return Response.success(weightMessage, false,  ConstantUtil.SUCCESS_CODE, ConstantUtil.WEIGHBRIDGE_MESSAGE_CODE);
        }
        return null;
    }

    /**
     * 校验危险货品是否上传扫描件
     * @param vo
     * @return
     */
    @PostMapping("checkdocument")
    public Response checkDocument(WarehousingVO vo) {
        //证件扫描提醒
        List<Commodity> commodityList = commodityService.queryCommodityByCommodity(vo.getCommodity());
        if(commodityList.size()>0) {
            //如果货品是危险品
            if(commodityList.get(0).getWx()) {
                Timestamp tempdate1 = vo.getDate1();
                String time1 = vo.getTime1();
                String documentCode = documentService.createDocumentCode(tempdate1, time1);
                if(documentService.queryDocumentByCode(documentCode).size()>0) {
                    String msg = "该车辆" + vo.getTxtVehicle() + "装载危险货品，还没有进行证件扫描，是否继续？";
                    return Response.success(msg, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.INPUT_COMMODITY_CODE1);
                }
            }
        }
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
    }

    /**
     * 条码扫描校验
     * @param vo
     * @return
     */
    @PostMapping("checkdinp")
    public Response checkDinp(WarehousingVO vo) {
        //条码扫描校验
        if((vo.getCommodity().equals("DINP") && vo.getQuantity()<0 && !vo.getStatus().equals("10")) ||
                (vo.getTank().substring(0,1).equals("T") && vo.getQuantity()>0 && !vo.getStatus().equals("10"))) {
            //该车辆没有完成正常条码扫描流程，放行吗？
            return Response.success(ConstantUtil.INPUT_TTDCTEMP_MSG0, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.INPUT_TTDCTEMP_CODE0);
        }
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
    }

    /**
     * 保存出仓信息
     * @param request
     * @return
     */
    public Response saveOutWarehouse(@RequestBody Request<WarehousingVO> request) {
        try {
            WarehousingVO vo = request.getData();
            //生成出仓Id
            SysData sysData = sysDataService.querySysData();
            String cdcNo = sysDataService.getSysDataId(sysData, "cdc_no");
            vo.setCdcNo(cdcNo);
            //保存出仓信息
            boolean saveFlag = tankVehicleInOutService.saveOutWarehouse(vo);

        }catch (Exception e) {
            //出仓保存失败
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.SAVETTDC_ERROR_CODE);
        }

        return null;
    }



}
