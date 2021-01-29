package com.yshyerp.vehicle.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yshyerp.vehicle.commons.ConstantUtil;
import com.yshyerp.vehicle.commons.ToolUtil;
import com.yshyerp.vehicle.entity.*;
import com.yshyerp.vehicle.mapper.DraMapper;
import com.yshyerp.vehicle.mapper.EdrumsMapper;
import com.yshyerp.vehicle.mapper.EdsRepoMapper;
import com.yshyerp.vehicle.service.*;
import com.yshyerp.vehicle.vo.Request;
import com.yshyerp.vehicle.vo.Response;
import com.yshyerp.vehicle.vo.WeighbridgeVO;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 收空桶
 */
@Slf4j
@RestController
@ResponseBody
@RequestMapping("/edrumtmp")
public class EDrumtmpController {

    @Autowired
    EDrumtmpService eDrumtmpService;

    @Autowired
    EdrumsService edrumsService;

    @Autowired
    DraService draService;

    @Autowired
    EdsRepoService edsRepoService;

    @Autowired
    TankVehicleInOutService tankVehicleInOutService;

    @Autowired
    PickingScheduleService pickingScheduleService;

    @Autowired
    SysDataService sysDataService;

    @Autowired
    DonopictService donopictService;

    @Autowired
    VehiPlanService vehiPlanService;

    @Autowired
    EdrumsMapper edrumsMapper;

    @Autowired
    DraMapper draMapper;

    @Autowired
    EdsRepoMapper edsRepoMapper;

    /**
     * 查询空桶入仓页面
     *
     * @return
     */
    @PostMapping("alist")
    public Response alist(@RequestBody Request<EDrumtmp> request) {
        if (!StringUtils.isEmpty(request.getPageNum()) && !StringUtils.isEmpty(request.getPageSize())) {
            PageHelper.startPage(request.getPageNum(), request.getPageSize());
        }
        try {
            //查询
            List<EDrumtmp> alist = eDrumtmpService.alist();
            PageInfo pageInfo = new PageInfo(alist);
            pageInfo.setList(alist);
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, pageInfo, ConstantUtil.SUCCESS_CODE, null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }
    }


    @PostMapping("threqset")
    public Response thjha(@RequestBody Request<VehiPlan1> request) {
        try {
            //提货计划V
            List<VehiPlan1> vehiPlan1 = vehiPlanService.thjhcx(request.getData().getPlanNo());
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, vehiPlan1, ConstantUtil.SUCCESS_CODE, null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }
    }


    /**
     * 查询提货计划
     * 输入   选择提货计划
     */
    @PostMapping("thjh")
    public Response thjh(@RequestBody Request<VehiPlan1> request) {
        try {
            //提货计划V
            List<VehiPlan1> vehiPlan1 = vehiPlanService.thjh(request.getData().getPlanNo());
            SysData sysData = sysDataService.querySysData();
            String jobNo = sysDataService.getSysDataId(sysData, "job_no");
            //根据提货计划新增      新增空桶入仓临时
            EDrumtmp eDrumtmp = new EDrumtmp();
            eDrumtmp.setJobno(jobNo);
            eDrumtmp.setVehicle(vehiPlan1.get(0).getVehicle());
            eDrumtmp.setCustomer(vehiPlan1.get(0).getcCustomer());
            eDrumtmp.setsCustomer(vehiPlan1.get(0).getsCustomer());
            eDrumtmp.setDrums(vehiPlan1.get(0).getDrums());
            eDrumtmp.setIdno(vehiPlan1.get(0).getIdno());
            eDrumtmp.setOriginal(vehiPlan1.get(0).getOriginal());
            eDrumtmp.setFaxno(vehiPlan1.get(0).getFaxno());
            eDrumtmp.setRemarks(vehiPlan1.get(0).getRemarks());
            eDrumtmp.setN(vehiPlan1.get(0).getCnew());
            eDrumtmp.setColor(vehiPlan1.get(0).getColor());
            eDrumtmp.setTare(vehiPlan1.get(0).getTare());
            eDrumtmp.setCover(vehiPlan1.get(0).getCover());
            eDrumtmp.setCome(vehiPlan1.get(0).getCome());
            eDrumtmp.setPlanNo(vehiPlan1.get(0).getPlanNo());
            //新增空桶临时入仓
            eDrumtmpService.insertSelective(eDrumtmp);


            return Response.success(ConstantUtil.SUCCESS_MESSAGE, vehiPlan1, ConstantUtil.SUCCESS_CODE, null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }
    }


    /**
     * 根据indo查询
     *
     * @return
     */
    @PostMapping("edrumtmplist")
    public Response eDrumtmpList(@RequestBody Request<EDrumtmp> request) {
        try {
            //查询
            List<EDrumtmp> eDrumtmpList = eDrumtmpService.elist(request.getData());
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, eDrumtmpList, ConstantUtil.SUCCESS_CODE, null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }
    }


    /**
     * 入仓
     *
     * @param request 参数
     * @return
     */
    @PostMapping("adedrumtmp111")
    public Response insertSelectiv1e(@RequestBody Request<EDrumtmp> request) {

        EDrumtmp eDrumtmp = request.getData();

        try {
            //生成jobno
            SysData sysData = sysDataService.querySysData();
            String jobNo = sysDataService.getSysDataId(sysData, "job_no");
            //新增空桶入仓临时
            eDrumtmpService.insertSelective1(eDrumtmp);
            //更新提货计划
            pickingScheduleService.updJobNo(jobNo, eDrumtmp.getPlanNo());
            //更新donopict表   提单图片记录
            donopictService.updonpict(jobNo, eDrumtmp.getFaxno());
            //更新job_no
            sysDataService.updSysDataIdToJobNo(jobNo);
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
        } catch (Exception e) {
            //入仓保存失败
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.SAVETTDCTEMP_ERROR_CODE);
        }
    }


    /**
     * 逻辑删除
     *
     * @param request
     * @return
     */
    @PostMapping("updedata")
    public Response updedata(@RequestBody Request<EDrumtmp> request) {
        try {
            Timestamp a = request.getData().getDate();
            System.out.println(a);
            int updFlag = eDrumtmpService.updeletmpdate(a);
            if (updFlag > 0) {
                return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.success(ConstantUtil.REQUEST_NULL_MESSAGE, false, ConstantUtil.SUCCESS_CODE, null);
    }


    /**
     * 新                出仓
     *
     * @param request 参数
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
    @PostMapping("adedrumtmpout")
    public Response insertSelectaaaive(@RequestBody Request<EDrumtmp> request) {
        try {
            short drums = request.getData().getDrums();
            Timestamp a = request.getData().getDate();
            System.out.println(a + ":::::");
            EDrumtmp eDrumtmp = eDrumtmpService.timestamp(a);
            System.out.println(eDrumtmp);

            //生成drano
            SysData sysData = sysDataService.querySysData();
            String drano = sysDataService.getSysDataId(sysData, "dra_no");
            //更新sysData
            sysDataService.updSysDataIdToDraNo(drano);

            Map map = new HashMap<String, Object>();
            map.put("customer", eDrumtmp.getCustomer());
            map.put("scustomer", eDrumtmp.getsCustomer());
            map.put("color", eDrumtmp.getColor());
            map.put("cover", eDrumtmp.getCover());
            map.put("tare", eDrumtmp.getTare());
            map.put("n", eDrumtmp.getN());
            map.put("charge", 0);
            Edrums edrums1 = edrumsMapper.mapedurms(map);
            if (edrums1 != null) {
                System.out.println(edrums1.getBalance() + ":" + eDrumtmp.getDrums());
                int balance = edrums1.getBalance() + eDrumtmp.getDrums();
                edrums1.setBalance(balance);
                edrums1.setDate(new Date());
                edrumsMapper.updedrums(edrums1);
            } else {
                Edrums edrumsa = new Edrums();
                edrumsa.setCustomer(eDrumtmp.getCustomer());
                edrumsa.setScustomer(eDrumtmp.getsCustomer());
                edrumsa.setColor(eDrumtmp.getColor());
                edrumsa.setCover(eDrumtmp.getCover());
                edrumsa.setTare(eDrumtmp.getTare());
                edrumsa.setN(eDrumtmp.getN());
                edrumsa.setCharge(false);
                edrumsa.setDate(new Date());
                edrumsa.setDate1(new Date());
                int balance111 = request.getData().getDrums();
                edrumsa.setBalance(balance111);
                edrumsMapper.insEdrums(edrumsa);
            }

            //新增空桶操作表  dra空桶 表
            Dra dra = new Dra();
            dra.setDraNo(drano);
            dra.setIdno(eDrumtmp.getIdno());
            dra.setJobno(eDrumtmp.getJobno());
            dra.setJobno(eDrumtmp.getJobno());
            dra.setPlanNo(eDrumtmp.getPlanNo());
            dra.setCustomer(eDrumtmp.getCustomer());
            dra.setsCustomer(eDrumtmp.getsCustomer());
            dra.setBillid(eDrumtmp.getBillid());
            dra.setDate(eDrumtmp.getDate());
            dra.setVehicle(eDrumtmp.getVehicle());
            dra.setVehicle1(eDrumtmp.getVehicle1());
            dra.setN(eDrumtmp.getN());
            dra.setColor(eDrumtmp.getColor());
            dra.setTare(eDrumtmp.getTare());
            dra.setCover(eDrumtmp.getCover());
            //实际桶数
            dra.setDrums(drums);
            dra.setCome(eDrumtmp.getCome());
            dra.setOper(request.getData().getOper());
            //入仓时间
            dra.setTime1(eDrumtmp.getTime1());
            ///出仓时间
            dra.setTime2(eDrumtmp.getTime2());
            dra.setWeight1(eDrumtmp.getWeight1());
            dra.setFaxno(eDrumtmp.getFaxno());
            //第二次称重
            dra.setWeight2(0);
            dra.setCharge(eDrumtmp.getCharge());
            dra.setOriginal(eDrumtmp.getOriginal());
            if (null != eDrumtmp.getFaxno()) {
                dra.setFaxno(eDrumtmp.getFaxno());
            } else {
                dra.setFaxno(" ");
            }
            if (null != eDrumtmp.getRemarks()) {
                dra.setRemarks(eDrumtmp.getRemarks());
            } else {
                dra.setRemarks(" ");
            }

//            edrumsa
            Map mapa = new HashMap<String, Object>();
            mapa.put("customer", eDrumtmp.getCustomer());
            mapa.put("scustomer", eDrumtmp.getsCustomer());
            mapa.put("color", eDrumtmp.getColor());
            mapa.put("cover", eDrumtmp.getCover());
            mapa.put("tare", eDrumtmp.getTare());
            mapa.put("n", eDrumtmp.getN());
            mapa.put("charge", 0);
            Edrums edrums2 = edrumsMapper.mapedurms(mapa);
            draMapper.insertSelective(dra);
            log.info(edrums2.getBalance() + ":::::::::::" + eDrumtmp.getDrums());
            Integer BalBf = edrums2.getBalance() - eDrumtmp.getDrums();
            //存入     入库过程   空桶过程 eds_repo 表
            EdsRepo po = new EdsRepo();
            po.setCustomer(eDrumtmp.getCustomer());
            po.setsCustomer(edrums2.getScustomer());
            po.setColor(eDrumtmp.getColor());
            po.setN(eDrumtmp.getN());
            System.out.println(eDrumtmp.getDrums() + ":" + edrums2.getBalance());
            System.out.println("前结存 ::::::" + BalBf);
            po.setBalBf(BalBf);
            String draa = dra.getDraNo();
            String dra2 = "DRA" + draa;
            po.setNo(dra2);//record.getDraNo()
            po.setDate(new Date());
            po.setTare(edrums2.getTare());
            po.setIn(drums);
            System.out.println("in:" + po.getIn());
            int bal = BalBf + po.getIn();
            po.setBalance(bal);
            po.setCharge(edrums2.getCharge());
            po.setCover(edrums2.getCover());
            short a1 = 0;
            po.setOut(a1);
            po.setRemarks("");
            edsRepoMapper.insert(po);
            //根据时间  确定数据    逻辑删除
            eDrumtmpService.updeletmpdate(a);
            if (eDrumtmp.getOriginal() == false || eDrumtmp.getFaxno() != null) {


            }


            return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);


        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
        return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
    }


    /**
     * 新增    空桶入仓临时表EDrumtmp      记录
     *
     * @param request 参数
     * @return
     */
    @PostMapping("adedrumtmp")
    public Response insertSelective(@RequestBody Request<EDrumtmp> request) {
        HttpServletRequest request1 = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取访问主机的IP地址
        String ipAddress = ToolUtil.getIpAddr(request1);
        WeighbridgeVO weighbridgeVO = tankVehicleInOutService.queryWeighbridge(ipAddress);
        EDrumtmp eDrumtmp = request.getData();
        //获取地磅重量，  存入
//        int getWeight=weighbridgeVO.getWeight().intValue();
//        eDrumtmp.setWeight1(getWeight);
        try {
            //生成jobno
            SysData sysData = sysDataService.querySysData();
            String jobNo = sysDataService.getSysDataId(sysData, "job_no");
            //新增空桶入仓临时
            eDrumtmpService.insertSelective(request.getData());
            //更新提货计划
            pickingScheduleService.updJobNo(jobNo, eDrumtmp.getPlanNo());
            //更新donopict表   提单图片记录
            donopictService.updonpict(jobNo, eDrumtmp.getFaxno());
            //更新job_no
            sysDataService.updSysDataIdToJobNo(jobNo);

            return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
        } catch (Exception e) {
            //入仓保存失败
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.SAVETTDCTEMP_ERROR_CODE);
        }
    }

    //查询空桶库存表   e_drums
    @PostMapping("edrumslist")
    public Response edrumslist() {
        try {
            //查询
            List<Edrums> edrumslist = edrumsService.slist();
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, edrumslist, ConstantUtil.SUCCESS_CODE, null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }
    }
//
//    /**
//     * 新增   空桶库存表
//     * @param request 参数
//     * @return
//     */
//    @PostMapping("addedrums")
//    public Response adedrums(@RequestBody Request<Edrums> request) {
//        //请求参数校验
//        if (StringUtils.isEmpty(request)) {
//            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
//        }
//        return Response.success(ConstantUtil.SUCCESS_MESSAGE, edrumsService.insEdrums(request.getData()), ConstantUtil.SUCCESS_CODE, null);
//    }


    /**
     * 修改  时间 和 桶数
     * 信息空桶库存表
     *
     * @param request 参数
     * @return
     */
    @PutMapping("updedrums")
    public Response updedrums(@RequestBody Request<Edrums> request) {
        int updFlag = edrumsService.updedrums(request.getData());
        if (updFlag > 0) {
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, edrumsService.updedrums(request.getData()), ConstantUtil.SUCCESS_CODE, null);
        }
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, false, ConstantUtil.SUCCESS_CODE, null);
    }


//    /**
//     * 新增    空桶表Dra   记录
//     * @param request 参数
//     * @return
//     */
//    @PostMapping("addra")
//    public Response insertDra(@RequestBody Request<Dra> request) {
//        //请求参数校验
//        if (StringUtils.isEmpty(request)) {
//            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
//        }
//        int a=draService.insertSelective(request.getData());
//        if (a>0){
//            return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
//        }
//        return Response.success(ConstantUtil.SUCCESS_MESSAGE, false, ConstantUtil.SUCCESS_CODE, null);
//
//    }





    /**
     * 逻辑删除
     *
     * @param request 单号jobno
     * @return
     */
    @PutMapping("updejobno")
    public Response updedrumtmp(@RequestBody Request<EDrumtmp> request) {

        int updFlag = eDrumtmpService.updedrumtmp(request.getData().getJobno());
        //修改
        int a = vehiPlanService.updplanNo(request.getData().getJobno());

        if (updFlag > 0) {
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
        }
        return Response.success(ConstantUtil.REQUEST_NULL_MESSAGE, false, ConstantUtil.SUCCESS_CODE, null);
    }


}
