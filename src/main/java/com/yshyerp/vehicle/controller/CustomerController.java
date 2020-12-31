package com.yshyerp.vehicle.controller;

import com.yshyerp.vehicle.commons.ConstantUtil;
import com.yshyerp.vehicle.service.CustomerService;
import com.yshyerp.vehicle.service.TankService;
import com.yshyerp.vehicle.vo.Request;
import com.yshyerp.vehicle.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 客户业务类
 */
@Slf4j
@RestController
@ResponseBody
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    TankService tankService;

    /**
     * 校验客户是否存在
     * @param data 客户名称
     * @return
     */
    @GetMapping("checkcustomer/{data}")
    public Response checkCustomer(@PathVariable String data) {
        log.info("--------------------customer/checkcustomer START--------------------");
        if(StringUtils.isEmpty(data)) {
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }else if(!customerService.checkCustomer(data)) {
            //没有查到客户
            return Response.success(ConstantUtil.MESSAGE_CUSTOMER_MSG1, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_CUSTOMER_CODE1);
        }
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
    }

    /**
     * 校验公司是否能够运输危险化学品
     * @param data 公司全名
     * @return
     */
    @GetMapping("checktransportationcompany/{data}")
    public Response checkTransportationCompany(@PathVariable String data) {
        log.info("--------------------checkcustomer/checktransportationcompany START--------------------");
        if(StringUtils.isEmpty(data)) {
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }else if(!customerService.checkTransportationCompany(data)){
            return Response.success(ConstantUtil.MESSAGE_COMMODITY_MSG1, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_COMMODITY_CODE1);
        }
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
    }
}
