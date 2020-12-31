package com.yshyerp.vehicle.controller;

import com.yshyerp.vehicle.commons.ConstantUtil;
import com.yshyerp.vehicle.entity.EDrumtmp;
import com.yshyerp.vehicle.service.EDrumtmpService;
import com.yshyerp.vehicle.vo.Request;
import com.yshyerp.vehicle.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@ResponseBody
@RequestMapping("/edrumtmp")
public class EDrumtmpController {

    @Autowired
    private EDrumtmpService eDrumtmpService;

    @PostMapping("add")
    public Response addPost(@RequestBody Request<EDrumtmp> request) {
        //请求参数校验
        if(StringUtils.isEmpty(request)) {
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, eDrumtmpService.insert(request.getData()), ConstantUtil.SUCCESS_CODE, null);
    }



}
