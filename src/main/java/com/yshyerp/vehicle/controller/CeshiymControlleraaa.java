//package com.yshyerp.vehicle.controller;
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.yshyerp.vehicle.commons.ConstantUtil;
//import com.yshyerp.vehicle.entity.Drums;
//import com.yshyerp.vehicle.entity.Drumtmpout;
//import com.yshyerp.vehicle.entity.TankC;
//import com.yshyerp.vehicle.service.DrumsService;
//import com.yshyerp.vehicle.service.DrumtmpoutService;
//import com.yshyerp.vehicle.vo.Request;
//import com.yshyerp.vehicle.vo.Response;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Slf4j
//@RestController
//@ResponseBody
//@RequestMapping("/a")
//public class CeshiymControlleraaa {
//
//    @Autowired
//    DrumsService drumsService;
//    @Autowired
//    DrumtmpoutService drumtmpoutService;
//
//    @PostMapping("drumtmpoutList")
//    public Response drumtmpoutList(@RequestBody Request<Drums> request) {
//
//        try {
//            List<Drums> drumsList=drumsService.scustomer(request.getData().getsCustomer());
//            return Response.success(ConstantUtil.SUCCESS_MESSAGE, drumsList, ConstantUtil.SUCCESS_CODE, null);
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error(e.getMessage());
//            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
//        }
//    }
//
//    @PostMapping("dac")
//    public Response drumtmaapoutList(@RequestBody Request<TankC> request) {
//
//        try {
//            List<TankC> tankCList=drumtmpoutService.queryTankCListByTank(request.getData().getTank());
//            return Response.success(ConstantUtil.SUCCESS_MESSAGE, tankCList, ConstantUtil.SUCCESS_CODE, null);
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error(e.getMessage());
//            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
//        }
//    }
//
//
//}
