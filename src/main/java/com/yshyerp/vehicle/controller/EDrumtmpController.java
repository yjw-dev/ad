package com.yshyerp.vehicle.controller;


import com.yshyerp.vehicle.commons.ConstantUtil;
import com.yshyerp.vehicle.commons.ToolUtil;
import com.yshyerp.vehicle.entity.*;
import com.yshyerp.vehicle.service.*;
import com.yshyerp.vehicle.vo.Request;
import com.yshyerp.vehicle.vo.Response;
import com.yshyerp.vehicle.vo.WeighbridgeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    /**
     * 查询空桶入仓页面
     *
     * @return
     */
    @PostMapping("alist")
    public Response alist() {
        try {
            //查询
            List<EDrumtmp> alist = eDrumtmpService.alist();
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, alist, ConstantUtil.SUCCESS_CODE, null);
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
     * 新增    空桶入仓临时表EDrumtmp      记录
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
            SysData sysData = sysDataService.querySysData();
            String jobNo = sysDataService.getSysDataId(sysData, "job_no");


                //新增空桶入仓临时
                eDrumtmpService.insertSelective(request.getData());

                //更新提货计划
               pickingScheduleService.updJobNo(jobNo, eDrumtmp.getPlanNo());
                //更新donopict表   提单图片记录
                donopictService.updonpict(jobNo,eDrumtmp.getFaxno());

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

    /**
     * 新增   空桶库存表
     * @param request 参数
     * @return
     */
    @PostMapping("addedrums")
    public Response adedrums(@RequestBody Request<Edrums> request) {
        //请求参数校验
        if (StringUtils.isEmpty(request)) {
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, edrumsService.insEdrums(request.getData()), ConstantUtil.SUCCESS_CODE, null);
    }


    /**
     * 修改  时间 和 桶数
     *     信息空桶库存表
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


    /**
     * 新增    空桶表Dra   记录
     * @param request 参数
     * @return
     */
    @PostMapping("addra")
    public Response insertDra(@RequestBody Request<Dra> request) {
        //请求参数校验
        if (StringUtils.isEmpty(request)) {
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, draService.insertSelective(request.getData()), ConstantUtil.SUCCESS_CODE, null);
    }


    /**
     * 新增      --空桶过程表
     * @param request 参数
     * @return
     */
    @PostMapping("addedsrepo")
    public Response addEdsRepo(@RequestBody Request<EdsRepo> request) {
        //请求参数校验
        if (StringUtils.isEmpty(request)) {
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, edsRepoService.insert(request.getData()), ConstantUtil.SUCCESS_CODE, null);
    }


    /**
     * 逻辑删除
     * @param request 单号jobno
     * @return
     */
    @PutMapping("updejobno")
    public Response updedrumtmp(@RequestBody Request<EDrumtmp> request) {
        int updFlag = eDrumtmpService.updedrumtmp(request.getData().getJobno());
        if (updFlag > 0) {
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
        }
        return Response.success(ConstantUtil.REQUEST_NULL_MESSAGE, false, ConstantUtil.SUCCESS_CODE, null);
    }





}
