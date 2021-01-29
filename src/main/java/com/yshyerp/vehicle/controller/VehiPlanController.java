package com.yshyerp.vehicle.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yshyerp.vehicle.commons.ConstantUtil;
import com.yshyerp.vehicle.commons.ToolUtil;
import com.yshyerp.vehicle.entity.*;
import com.yshyerp.vehicle.mapper.VehiWMapper;
import com.yshyerp.vehicle.service.*;
import com.yshyerp.vehicle.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 来空桶计划页面
 */
@Slf4j
@RestController
@ResponseBody
@RequestMapping("/vehiplan")
public class VehiPlanController {

    @Autowired
    VehiPlanService vehiPlanService;
    @Autowired
    Djob1Service djob1Service;

    @Autowired
    EdrumsService edrumsService;

    @Autowired
    SCustomerService sCustomerService;

    @Autowired
    CustomerService customerService;

    @Autowired
    Contract1Service contract1Service;

    @Autowired
    DrumtmpoutService drumtmpoutService;

    @Autowired
    EDrumtmpService eDrumtmpService;

    @Autowired
    OtherDicService otherDicService;
    @Autowired
    SysDataService sysDataService;
    @Autowired
    PickingScheduleService pickingScheduleService;

    @Autowired
    DonopictService donopictService;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    VehiWMapper vehiWMapper;

    /**
     * 查询vehi_plan表
     *
     * @return
     */
    @PostMapping("vehiPlanlist")
    public Response vehiPlanlist(@RequestBody Request<VehiPlan1> request) {
        if (!StringUtils.isEmpty(request.getPageNum()) && !StringUtils.isEmpty(request.getPageSize())) {
            PageHelper.startPage(request.getPageNum(), request.getPageSize());
        }
        System.out.println(request.getData().getTypea()+":"+request.getData().getPlanType());
        try {
            Map map = new HashMap<String, Object>();
            map.put("typea", request.getData().getTypea());
            map.put("planType", request.getData().getPlanType());
            //查询
            List<VehiPlan1> vehiPlanlist = vehiPlanService.list(map);
            PageInfo pageInfo = new PageInfo(vehiPlanlist);
            pageInfo.setList(vehiPlanlist);
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, pageInfo, ConstantUtil.SUCCESS_CODE, null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }
    }

    /**
     * 根据车牌和挂车号查询车辆信息
     *
     * @param request
     * @return
     */
    @PostMapping("vehicledetail")
    public Response getDetail(@RequestBody Request<VehiWa> request) throws Exception {
        VehiWa vehicleNoVo = request.getData();
        //车牌号
        String vehicle = vehicleNoVo.getVehicle();
        //挂车号
        String vehicle1 = vehicleNoVo.getVehicle1();
        //调用业务层查询车辆信息
        List<VehiWa> listv = new ArrayList<>();
        VehiWa vehicleNo = new VehiWa();
        List<VehiWa> list = vehicleService.getyaajw(vehicle);
        if (list.size() == 0) {
            BigDecimal a = new BigDecimal(0);
            vehicleNo.setVehiW(a);
            vehicleNo.setVehiW2(a);
            vehicleNo.setVehiW3(a);
            vehicleNo.setVehicle(null);
            vehicleNo.setVehicle1(null);
            listv.add(vehicleNo);
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, listv, ConstantUtil.SUCCESS_CODE, null);
        } else {
            BigDecimal nmsl = new BigDecimal(0);
            list.get(0).setVehiW3(nmsl);
            //查询准牵引重量
            List<VehiW2> vehiW2 = vehiWMapper.listvehiw2(vehicle);
            if (vehiW2.size() != 0) {
                list.get(0).setVehiW2(vehiW2.get(0).getVehiW2());
                List<VehiWa> vehicleNoVo2 = new ArrayList<>();
                VehiWa wa = new VehiWa();
                System.out.println(list.get(0) + ":::::::::");
                BigDecimal a = list.get(0).getVehiW();
                BigDecimal b = list.get(0).getVehiW2();
                BigDecimal c = list.get(0).getVehiW3();
                BigDecimal e = new BigDecimal(0);

                wa.setVehiW(list.get(0).getVehiW());
                wa.setVehiW2(list.get(0).getVehiW2());
                wa.setVehiW3(list.get(0).getVehiW3());
                wa.setVehicle(list.get(0).getVehicle());
                wa.setVehicle1(list.get(0).getVehicle1());
                vehicleNoVo2.add(wa);
//                System.out.println(a+":"+b+":"+c);
//                vehicleNoVo2.get(0).setVehiW(a);
//                vehicleNoVo2.get(0).setVehiW2(b);
//                vehicleNoVo2.get(0).setVehiW3(e);
//
//                System.out.println(vehicleNoVo2);
//                String  cvehicle1=list.get(0).getVehicle1();
//                vehicleNoVo2.get(0).setVehicle1(cvehicle1);
//                String vvehicle1=list.get(0).getVehicle();
//                vehicleNoVo2.get(0).setVehicle(vvehicle1);
                if (null != vehicle1) {
                    //查询挂车重量
                    List<VehiW3> vehiW3 = vehiWMapper.listvehiw3(vehicle1);
                    if (vehiW3.size() != 0) {
                        System.out.println(vehicle1);
                        vehicleNoVo2.get(0).setVehiW3(vehiW3.get(0).getVehiW3());
                        return Response.success(ConstantUtil.SUCCESS_MESSAGE, vehicleNoVo2, ConstantUtil.SUCCESS_CODE, "3");
                    } else {
                        return Response.success(ConstantUtil.SUCCESS_MESSAGE, list, ConstantUtil.SUCCESS_CODE, "4");
                    }
                } else {
                    return Response.success(ConstantUtil.SUCCESS_MESSAGE, list, ConstantUtil.SUCCESS_CODE, "2");
                }
            }
            BigDecimal a = new BigDecimal(0);
            vehicleNo.setVehiW(a);
            vehicleNo.setVehiW2(a);
            vehicleNo.setVehiW3(a);
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, list, ConstantUtil.SUCCESS_CODE, "1");
        }
    }
//
//    /**
//     * 根据车牌和挂车号查询车辆信息
//     * @param request
//     * @return
//     */
//    @PostMapping("vehicledetail1")
//    public Response getDetail1(@RequestBody Request<VehiWa> request) throws  Exception {
//        VehiWa vehicleNoVo = request.getData();
//        //车牌号
//        String vehicle = vehicleNoVo.getVehicle();
//        //挂车号
//        String vehicle1 = vehicleNoVo.getVehicle1();
//        //调用业务层查询车辆信息
//        VehiWa  vehicleNo = new VehiWa();
//        List<VehiWa> list = vehicleService.getyaajw(vehicle);
//        if (list.size()==0){
//            BigDecimal  a=new BigDecimal(0);
//            vehicleNo.setVehiW(a);
//            vehicleNo.setVehiW2(a);
//            vehicleNo.setVehiW3(a);
//            vehicleNo.setVehicle(null);
//            vehicleNo.setVehicle1(null);
//            return Response.success(ConstantUtil.SUCCESS_MESSAGE, vehicleNo, ConstantUtil.SUCCESS_CODE, null);
//        }
//        //查询准牵引重量
//        VehiW2 vehiW2 = vehiWMapper.getVehiW2ByVehicle(vehicle);
//        if (vehiW2!=null){
//            list.get(0).setVehiW2(vehiW2.getVehiW2());
//            VehiWa vehicleNoVo2=list.get(0);
//            if (null!=vehicle1){
//                //查询挂车重量
//                VehiW3 vehiW3 = vehiWMapper.getVehiW3ByVehicle1(vehicle1);
//                System.out.println(vehicle1);
//                vehicleNoVo2.setVehiW3(vehiW3.getVehiW3());
//                return Response.success(ConstantUtil.SUCCESS_MESSAGE, vehicleNoVo2, ConstantUtil.SUCCESS_CODE, null);
//            }else {
//                return Response.success(ConstantUtil.SUCCESS_MESSAGE, list, ConstantUtil.SUCCESS_CODE, null);
//            }
//        }
//        BigDecimal  a=new BigDecimal(0);
//        vehicleNo.setVehiW(a);
//        vehicleNo.setVehiW2(a);
//        vehicleNo.setVehiW3(a);
//        return Response.success(ConstantUtil.SUCCESS_MESSAGE, vehicleNo, ConstantUtil.SUCCESS_CODE, null);
//
//    }

    /**
     * 查询小客户
     *
     * @return
     */
    @PostMapping("scustomerlist")
    public Response scustomerlist(@RequestBody Request<SCustomer> request) {
        try {
            Map<String, Object> responseMap = new HashMap<>();
            //查询
            List<Map<String, Object>> scustomerlist = sCustomerService.alist(request.getData().getCustomer());
            List<Contract1Vo> contract1VoList = new ArrayList<>();
            List<Contract> contractList = contract1Service.queryContractListByCustomer(request.getData().getCustomer());
            for (Contract contract : contractList) {
                contract1VoList.add((Contract1Vo) ToolUtil.doCastVo(contract, new Contract1Vo()));
            }
            List<Contract2Vo> vos = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i < contract1VoList.size(); i++) {
                //  字段ContractI
                String contractI = contract1VoList.get(i).getContractI();
                //时间
                String str = sdf.format(contract1VoList.get(i).getEnDate());
                //分割   tack
                String[] b = contract1VoList.get(i).getTank().split("/");
                if (b != null) {
                    for (int j = 0; j < b.length; j++) {
                        Contract2Vo contract2Vo = new Contract2Vo();
                        contract2Vo.setContractI(contractI);
                        contract2Vo.setTank(b[j] + " " + str);
                        vos.add(contract2Vo);
                    }
                }
            }
            List<Djob1> djob1s = djob1Service.Bycustomer(request.getData().getCustomer());
            List<Edrums> edrums = edrumsService.Bycustomer(request.getData().getCustomer());
            List<DjobVo> djobVoList = new ArrayList<DjobVo>();
            for (Djob1 djob1 : djob1s) {
                djobVoList.add((DjobVo) ToolUtil.doCastVo(djob1, new DjobVo()));
            }
            for (Edrums edrums2 : edrums) {
                djobVoList.add((DjobVo) ToolUtil.doCastVo(edrums2, new DjobVo()));
            }
            responseMap.put("djobVoList", djobVoList);
            responseMap.put("scustomerlist", scustomerlist);
            responseMap.put("vos", vos);
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, responseMap, ConstantUtil.SUCCESS_CODE, null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }
    }

    /**
     * 查询大客户
     *
     * @return
     */
    @GetMapping("customerList")
    public Response customerList() {
        try {
            //查询所有客户
            List<Map<String, Object>> customerList = customerService.customerget("");
            //颜色
            List<Map<String, Object>> otherDictlist = otherDicService.OtherDiclist();
            //新旧
            List<Map<String, Object>> dlist = otherDicService.dlist();
            //桶来源
            List<Map<String, Object>> list = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            map.put("key", "格瑞夫");
            map.put("label", "格瑞夫");
            list.add(map);
            map = new HashMap<>();
            map.put("key", "帆顺");
            map.put("label", "帆顺");
            list.add(map);
            map = new HashMap<>();
            map.put("key", "岐胜");
            map.put("label", "岐胜");
            list.add(map);
            map = new HashMap<>();
            map.put("key", "其它");
            map.put("label", "其它");
            list.add(map);
//            if (request.getData().getCustomer()!=null){
            //根据客户名称  查询小客户
//                List<Map<String,Object>> scustomerlist = sCustomerService.alist("");
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("CustomerList", customerList);
            responseMap.put("otherDictlist", otherDictlist);
//          responseMap.put("ScustomerList", scustomerlist);
            responseMap.put("dlist", dlist);
            responseMap.put("list", list);
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, responseMap, ConstantUtil.SUCCESS_CODE, null);
//            }
//            return Response.success(ConstantUtil.SUCCESS_MESSAGE, customerList, ConstantUtil.SUCCESS_CODE, null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }
    }

    /**
     * 根据PlanNo计划单号查询
     *
     * @param data PlanNo计划单号查询
     * @return
     */
    @GetMapping("ByPlanNo/{data}")
    public Response ByPlanNo(@PathVariable String data) {

        try {
            //查询所有客户
            List<Map<String, Object>> customerList = customerService.customerget("");
            //颜色
            List<Map<String, Object>> otherDictlist = otherDicService.OtherDiclist();
            //新旧
            List<Map<String, Object>> dlist = otherDicService.dlist();
            //桶来源
            List<Map<String, Object>> list = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            map.put("key", "格瑞夫");
            map.put("label", "格瑞夫");
            list.add(map);
            map = new HashMap<>();
            map.put("key", "帆顺");
            map.put("label", "帆顺");
            list.add(map);
            map = new HashMap<>();
            map.put("key", "岐胜");
            map.put("label", "岐胜");
            list.add(map);
            map = new HashMap<>();
            map.put("key", "其它");
            map.put("label", "其它");
            list.add(map);
            //根据客户名称  查询小客户
            List<Map<String, Object>> scustomerlist = sCustomerService.alist("");
            VehiPlan1 v = vehiPlanService.queryplanNoByDoNo(data);
            if (!v.getJobno().equals("")) {
                return Response.success(ConstantUtil.MESSAGE_TTDCTEMP_JOBNO, 500, ConstantUtil.SUCCESS_CODE, null);
            }
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("CustomerList", customerList);
            responseMap.put("otherDictlist", otherDictlist);
            responseMap.put("ScustomerList", scustomerlist);
            responseMap.put("dlist", dlist);
            responseMap.put("list", list);
            responseMap.put("v", v);
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, responseMap, ConstantUtil.SUCCESS_CODE, null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }

    }

    /**
     * 新增  提货计划   空桶入仓计划
     *
     * @param request 参数
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
    @PostMapping("addinsertSelective")
    public Response insertSelective(@RequestBody Request<VehiPlan1> request) {
        try {

            //生成新数据的planNo
            SysData sysData = sysDataService.querySysData();
            String planNo = sysDataService.getSysDataId(sysData, "plan_no");

            VehiPlan1 vehiPlan1 = request.getData();
            vehiPlan1.setrDate(new Date());
            vehiPlan1.setPlanType("DRA");
            vehiPlan1.setPlanNo(planNo);
            //新增计划
            int updFlag = vehiPlanService.insertSelective(vehiPlan1);
            //更新planNo
            sysDataService.updSysDataIdToPlanNo(planNo);
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Response.success(ConstantUtil.REQUEST_NULL_MESSAGE, false, ConstantUtil.REQUEST_NULL_CODE, null);
        }
    }
//            //新增空桶入仓临时
//            EDrumtmp eDrumtmp=new EDrumtmp();
//            eDrumtmp.setJobno(jobNo);
//            eDrumtmp.setVehicle(vehiPlan1.getVehicle());
//            eDrumtmp.setCustomer(vehiPlan1.getcCustomer());
//            eDrumtmp.setsCustomer(vehiPlan1.getsCustomer());
//            eDrumtmp.setDrums(vehiPlan1.getDrums());
//            eDrumtmp.setIdno(vehiPlan1.getIdno());
//            eDrumtmp.setOriginal(vehiPlan1.getOriginal());
//            eDrumtmp.setFaxno(vehiPlan1.getFaxno());
//            eDrumtmp.setRemarks(vehiPlan1.getRemarks());
//            eDrumtmp.setN(vehiPlan1.getCnew());
//            eDrumtmp.setColor(vehiPlan1.getColor());
//            eDrumtmp.setTare(vehiPlan1.getTare());
//            eDrumtmp.setCover(vehiPlan1.getCover());
//            eDrumtmp.setCome(vehiPlan1.getCome());
//            eDrumtmp.setPlanNo(vehiPlan1.getRemarks());
//            //新增空桶临时入仓
//            eDrumtmpService.insertSelective(eDrumtmp);
//            Map requestMap = new HashMap<String, Object>();
//            requestMap.put("jobno", jobNo);
//            requestMap.put("planNo", vehiPlan1.getRemarks());
//            vehiPlanService.updjobno(requestMap);
//            //更新提货计划
//            pickingScheduleService.updJobNo(jobNo, eDrumtmp.getPlanNo());
//            //更新donopict表   提单图片记录
//            donopictService.updonpict(jobNo,eDrumtmp.getFaxno());
    //更新job_no
//         sysDataService.updSysDataIdToJobNo(jobNo);
//            //新增空桶库存 e_drums表
//            Edrums edrums=new Edrums();
//            edrums.setCustomer(vehiPlan1.gettCustomer());
//            edrums.setScustomer(vehiPlan1.getsCustomer());
//            edrums.setColor(vehiPlan1.getColor());
//            edrums.setN(vehiPlan1.getCnew());
//            edrums.setCover(vehiPlan1.getCover());
//            edrums.setDate(new Date());
//            edrums.setDate1(new Date());
//            edrums.setTare(vehiPlan1.getTare());
//            edrums.setCharge(false);
//            int a=vehiPlan1.getDrums();
//            edrums.setBalance(a);
//            edrumsService.insEdrums(edrums);


    // 新增桶装出货计划  新增  出货页面表
    @PostMapping("addinsertSelective2")
    public Response insertSelective2(@RequestBody Request<VehiPlan1> request) {
        try {
            //生成新数据的planNo
            SysData sysData = sysDataService.querySysData();
            String planNo = sysDataService.getSysDataId(sysData, "plan_no");
            VehiPlan1 vehiPlan1 = request.getData();
            vehiPlan1.setPlanNo(planNo);
            //桶装出货  标识类型
            vehiPlan1.setPlanType("DDC");
            vehiPlan1.setrDate(new Date());
            //新增计划
            int updFlag = vehiPlanService.insertSelective(vehiPlan1);
            //更新planNo
            sysDataService.updSysDataIdToPlanNo(planNo);
//
//        Drumtmpout drumtmpout = new Drumtmpout();
//        drumtmpout.setNo1(vehiPlan1.getNo1());
//        drumtmpout.setVehicle(vehiPlan1.getVehicle());
//        drumtmpout.setTank(vehiPlan1.getTank());
//        drumtmpout.settCustomer(vehiPlan1.gettCustomer());
//        drumtmpout.setcCustomer(vehiPlan1.getcCustomer());
//        drumtmpout.setCrrNo(vehiPlan1.getCrrNo());
//        drumtmpout.setDoNo(vehiPlan1.getDoNo());
//        drumtmpout.setCommodity(vehiPlan1.getCommodity());
//        drumtmpout.setCommodity1(vehiPlan1.getCommodity1());
//        drumtmpout.setTime1("00:00:00");
//        drumtmpout.setDate1(vehiPlan1.getdDate());
//        drumtmpout.setDelivered(vehiPlan1.getDelivered());
//        drumtmpout.setDrums(vehiPlan1.getDrums());
//        drumtmpout.setPacking(vehiPlan1.getPacking());
//        drumtmpout.setNetweight(vehiPlan1.getNetweight());
//        drumtmpout.setSerial("");
//        drumtmpout.setIdno(vehiPlan1.getIdno());
//        drumtmpout.setOriDo(vehiPlan1.getOriginal());
//        drumtmpout.setFaxno(vehiPlan1.getFaxno());
//        drumtmpout.setTare(vehiPlan1.getTare());
//        drumtmpout.setCover(vehiPlan1.getCover());
//        drumtmpout.setCharge(false);
//        drumtmpout.setFeeNo("0");
//        drumtmpout.setStatus(vehiPlan1.getStatus());
//        drumtmpout.setN(vehiPlan1.getCnew());
//        drumtmpout.setColor(vehiPlan1.getColor());
//        drumtmpout.setBatch(vehiPlan1.getBatch());
//        drumtmpout.setType(vehiPlan1.getType());
//        drumtmpout.setSlop(vehiPlan1.getSlop());
//        drumtmpout.setRemarks(vehiPlan1.getRemarks());
//        drumtmpout.setPlanNo(vehiPlan1.getPlanNo());
//
//
//        drumtmpout.setDdcNo("");
//        drumtmpout.setTimeR("");
//        //出仓日期
//        drumtmpout.setDate2(new Date());
//        //出仓时间
//        drumtmpout.setTime2("00:00:00");
//        //入仓重量
//        drumtmpout.setWeight1(100);
//        //出仓重量
//        drumtmpout.setWeight2(0);
//        //空值
//        drumtmpout.setVehicle1("");
//        drumtmpout.setsCustomer("");
//        drumtmpout.setBillid(0);
//        drumtmpout.setStation("");
//        drumtmpout.setSendno("");
//        short a=0;
//        drumtmpout.setFee(a);
//        drumtmpout.setJobno("");
//        drumtmpout.setCode("");
//        drumtmpout.setpBar(false);
//        //入仓标识
//        drumtmpout.setIsin(false);
//        //新增   桶装出货临时记录
//        int updFlaga =  drumtmpoutService.insert(drumtmpout);


            return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Response.success(ConstantUtil.REQUEST_NULL_MESSAGE, false, ConstantUtil.REQUEST_NULL_CODE, null);
        }
    }

    /**
     * 修改信息
     *
     * @param request 参数
     * @return
     */
    @PutMapping("updVehiPlan")
    public Response updatePut(@RequestBody Request<VehiPlan1> request) {
        VehiPlan1 vehiPlan1 = request.getData();
        int updFlag = vehiPlanService.updVehiPlan1(vehiPlan1);
        if (updFlag > 0) {
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
        }
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, false, ConstantUtil.SUCCESS_CODE, null);
    }

    /**
     * 逻辑删除
     *
     * @param request PlanNo单号
     * @return
     */
    @PutMapping("updVehiplanId")
    public Response updVehiplanId(@RequestBody Request<VehiPlan1> request) {
        int updFlag = vehiPlanService.updVehiplanId(request.getData().getPlanNo());

        if (updFlag > 0) {
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
        }
        return Response.success(ConstantUtil.REQUEST_NULL_MESSAGE, false, ConstantUtil.REQUEST_NULL_CODE, null);
    }

//    /**
//     * 根据用户名查询桶数基本信息   合并Edrums，Djob1表
//     */
//    @PostMapping("Bycustomer")
//    public Response Bycustomer(@RequestBody Request<DjobVo> request) {
//        try {
//            List<Djob1> djob1s = djob1Service.Bycustomer(request.getData());
////            List<Edrums> edrums = edrumsService.Bycustomer(request.getData());
////            List<DjobVo> djobVoList = new ArrayList<DjobVo>();
//            for (Djob1 djob1 : djob1s) {
//                 djobVoList.add((DjobVo) ToolUtil.doCastVo(djob1, new DjobVo()));
//              }
////            for (Edrums edrums2 : edrums) {
////                djobVoList.add((DjobVo) ToolUtil.doCastVo(edrums2, new DjobVo()));
////            }
//            return Response.success(ConstantUtil.SUCCESS_MESSAGE, djobVoList, ConstantUtil.SUCCESS_CODE, null);
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error(e.getMessage());
//            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
//        }
//    }


}















