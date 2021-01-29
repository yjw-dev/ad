package com.yshyerp.vehicle.controller;

import com.yshyerp.vehicle.commons.ConstantUtil;
import com.yshyerp.vehicle.entity.DdcHis;
import com.yshyerp.vehicle.entity.EDrumtmp;
import com.yshyerp.vehicle.service.DdcHisService;
import com.yshyerp.vehicle.vo.Request;
import com.yshyerp.vehicle.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@ResponseBody
@RequestMapping("/ddchists")
public class DdcHisController {
    @Autowired
    DdcHisService ddcHisService;

    @PostMapping("ddclist")
    public Response ddclist(@RequestBody Request<DdcHis> request) {
        try {
            //查询
            List<DdcHis> ddclist =ddcHisService.ddclist(request.getData().getType1());
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, ddclist, ConstantUtil.SUCCESS_CODE, null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }
    }


}
