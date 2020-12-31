package com.yshyerp.vehicle.controller;

import com.github.pagehelper.PageInfo;
import com.yshyerp.vehicle.commons.ConstantUtil;
import com.yshyerp.vehicle.service.TankService;
import com.yshyerp.vehicle.vo.Request;
import com.yshyerp.vehicle.vo.Response;
import com.yshyerp.vehicle.vo.TankInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 储罐业务类
 */
@Slf4j
@RestController
@ResponseBody
@RequestMapping("/tank")
public class TankController {

    @Autowired
    TankService tankService;

    /**
     * 查询储罐信息
     * @return
     */
    @PostMapping("tankinfo")
    public Response postListInit() {
        log.info("--------------------tank/tankinfo START--------------------");
        try {
            //查询储罐信息
            List<TankInfoVO> tankInfoVOList = tankService.queryTankInfo();
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, tankInfoVOList, ConstantUtil.SUCCESS_CODE, null);
        }catch(Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }
    }

    /**
     * 校验储罐是否可用
     * @return
     */
//    @GetMapping("checktank/{data}")
//    public Response checkTank(@PathVariable String data) {
//        log.info("--------------------tank/checktank START--------------------");
//        //请求参数校验
//        if(StringUtils.isEmpty(data)) {
//            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
//        }
//            return tankService.checkTank(data, 0.0);
//    }


}
