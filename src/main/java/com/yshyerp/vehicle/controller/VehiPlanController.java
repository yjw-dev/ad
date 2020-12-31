package com.yshyerp.vehicle.controller;

import com.yshyerp.vehicle.commons.ConstantUtil;

import com.yshyerp.vehicle.entity.EDrumtmp;
import com.yshyerp.vehicle.entity.VehiPlan;

import com.yshyerp.vehicle.entity.VehiPlan1;
import com.yshyerp.vehicle.service.VehiPlanService;

import com.yshyerp.vehicle.vo.Request;
import com.yshyerp.vehicle.vo.Response;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * 货品业务类
 */
@Slf4j
@RestController
@ResponseBody
@RequestMapping("vehiplan")
public class VehiPlanController {

    @Autowired
    VehiPlanService vehiPlanService;

    /**
     *   查询
     *
     * @return
     */
    @PostMapping("listAll")
    public Response listAll() {
        try {
            //查询
            List<VehiPlan> v=vehiPlanService.list();
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, v, ConstantUtil.SUCCESS_CODE, null);
        }catch(Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }
    }
    /**
     * 根据PlanNo查询
     * @param data
     * @return
     */
    @PostMapping("ByPlanNo/{data}")
    public Response ByPlanNo(@PathVariable String data) {
        if(StringUtils.isEmpty(data)) {
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }
        VehiPlan v=vehiPlanService.queryplanNoByDoNo(data);
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, v, ConstantUtil.SUCCESS_CODE, null);

    }

    /**
     *  新增
     * @param
     * @return
     */

    @PostMapping("add")
    public Response addPost(@RequestBody Request<VehiPlan1> request) {
        //请求参数校验
        if(StringUtils.isEmpty(request)) {
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, vehiPlanService.insert(request.getData()), ConstantUtil.SUCCESS_CODE, null);
    }





}















