package com.yshyerp.vehicle.controller;

import com.yshyerp.vehicle.commons.ConstantUtil;
import com.yshyerp.vehicle.service.VehicleService;
import com.yshyerp.vehicle.vo.Request;
import com.yshyerp.vehicle.vo.Response;
import com.yshyerp.vehicle.vo.TxtcVO;
import com.yshyerp.vehicle.vo.VehicleNoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 车辆业务类
 */
@Slf4j
@RestController
@ResponseBody
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    /**
     * 根据车牌和挂车号查询车辆信息
     * @param request
     * @return
     */
    @PostMapping("vehicledetail")
    public Response getDetail(@RequestBody Request<VehicleNoVO> request) {
        log.info("--------------------vehicle/vehicledetail START--------------------");
        VehicleNoVO vehicleNoVo = request.getData();
        //车牌号
        String vehicle = vehicleNoVo.getTxtVehicle();
        //挂车号
        String vehicle1 = vehicleNoVo.getTxtVehicle1();
        //校验请求参数是否为空
        if(null==vehicle || null==vehicle1) {
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }
        //调用业务层查询车辆信息
        VehicleNoVO responseVo = vehicleService.getVehicleDetailByVehicleAndVehicle1(vehicleNoVo);
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, responseVo, ConstantUtil.SUCCESS_CODE, null);
    }

    //根据箱号查询容积、箱重
    @GetMapping("vehicletxtcnodetail/{data}")
    public Response getTxtcNoDetail(@PathVariable String data) {
        log.info("--------------------vehicle/vehicletxtcnodetail START--------------------");
        if(StringUtils.isEmpty(data)) {
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }
        TxtcVO txtcVo = vehicleService.getTxtcNoDetail(data);
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, txtcVo, ConstantUtil.SUCCESS_CODE, null);
    }
}
