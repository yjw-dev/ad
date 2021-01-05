package com.yshyerp.vehicle.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yshyerp.vehicle.commons.ConstantUtil;

import com.yshyerp.vehicle.commons.ToolUtil;
import com.yshyerp.vehicle.entity.*;

import com.yshyerp.vehicle.service.*;


import com.yshyerp.vehicle.vo.*;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
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

    /**
     *   查询vehi_plan表
     *
     * @return
     */
    @PostMapping("vehiPlanlist")
    public Response vehiPlanlist() {
        try {
            //查询
            List<VehiPlan1> vehiPlanlist=vehiPlanService.list();
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, vehiPlanlist, ConstantUtil.SUCCESS_CODE, null);
        }catch(Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }
    }


    /**
     *   查询小客户
     * @return
     */
    @PostMapping("scustomerlist")
    public Response scustomerlist() {
        try {
            //查询
            List<SCustomer> scustomerlist=sCustomerService.alist();
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, scustomerlist, ConstantUtil.SUCCESS_CODE, null);
        }catch(Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }
    }

    /**
     *   查询大客户
     * @return
     */
    @PostMapping("customerList")
    public Response customerList() {
        try {
            //查询
            List<Customer> customerList=customerService.customerList();
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, customerList, ConstantUtil.SUCCESS_CODE, null);
        }catch(Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }
    }
    /**
     * 根据PlanNo计划单号查询
     * @param data   PlanNo计划单号查询
     * @return
     */
    @PostMapping("ByPlanNo/{data}")
    public Response ByPlanNo(@PathVariable String data) {
        if(StringUtils.isEmpty(data)) {
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }
        VehiPlan1 v=vehiPlanService.queryplanNoByDoNo(data);
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, v, ConstantUtil.SUCCESS_CODE, null);

    }

    /**
     *  新增
     * @param request     参数
     * @return
     */
    @PostMapping("add")
    public Response addPost(@RequestBody Request<VehiPlan1> request) {
        //请求参数校验
        if(StringUtils.isEmpty(request)) {
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }
        VehiPlan1 vehiPlan1=new VehiPlan1();
        vehiPlan1.setrDate(new Date());
        vehiPlanService.insertSelective(vehiPlan1);
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, vehiPlanService.insertSelective(request.getData()), ConstantUtil.SUCCESS_CODE, null);
    }

    /**
     *     修改
     * @param request    参数
     * @return
     */
    @PutMapping("updVehiPlan")
    public Response updatePut(@RequestBody Request<VehiPlan1> request) {
        VehiPlan1 vehiPlan1 = request.getData();
        int updFlag = vehiPlanService.updVehiPlan1(vehiPlan1);
        System.out.println(vehiPlanService.updVehiPlan1(request.getData()));
        if(updFlag>0) {
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
        }
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, false, ConstantUtil.SUCCESS_CODE, null);
    }
    /**
     *  逻辑删除
     * @param request  PlanNo单号
     * @return
     */
    @PutMapping("updVehiplanId")
    public Response updVehiplanId(@RequestBody Request<VehiPlan1> request) {
        int updFlag = vehiPlanService.updVehiplanId(request.getData().getPlanNo());
        System.out.println(vehiPlanService.updVehiplanId(request.getData().getPlanNo()));
        if(updFlag>0) {
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
        }
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, false, ConstantUtil.SUCCESS_CODE, null);
    }
    /**
     *  根据用户名查询桶数基本信息
     * @param
     * @return
     */
    @PostMapping("Bycustomer")
    public Response Bycustomer(@RequestBody Request<DjobVo> request) {
        log.info("--------------------pickingSchedule/pickingschedulelist START--------------------");
        if(!StringUtils.isEmpty(request.getPageNum())&& !StringUtils.isEmpty(request.getPageSize())) {
            PageHelper.startPage(request.getPageNum(), request.getPageSize());
        }
        try {
            List<Djob1> djob1s=djob1Service.Bycustomer(request.getData());
            List<Edrums> edrums=edrumsService.Bycustomer(request.getData());
            List<DjobVo> djobVoList = new ArrayList<DjobVo>();
            for(Djob1 djob1:djob1s) {
                djobVoList.add((DjobVo)ToolUtil.doCastVo(djob1, new DjobVo()));
            }

            PageInfo pageInfo = new PageInfo(djob1s);

            pageInfo.setList(djobVoList);

            return Response.success(ConstantUtil.SUCCESS_MESSAGE, pageInfo,ConstantUtil.SUCCESS_CODE, null);
       }catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }






    }





}















