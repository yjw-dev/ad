package com.yshyerp.vehicle.controller;

import com.yshyerp.vehicle.commons.ConstantUtil;
import com.yshyerp.vehicle.service.TrustService;
import com.yshyerp.vehicle.vo.Request;
import com.yshyerp.vehicle.vo.Response;
import com.yshyerp.vehicle.vo.TrustVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 委托书信息业务类
 */
@Slf4j
@RestController
@ResponseBody
@RequestMapping("/trust")
public class TrustController {

    @Autowired
    TrustService trustService;

    /**
     * 根据货主名称和货品查询委托书信息
     * @param request
     * @return
     */
    @PostMapping("byccustomer")
    public Response postListInit(@RequestBody Request<TrustVO> request) {
        log.info("--------------------trust/byccustomer START--------------------");
        TrustVO requestVo = request.getData();
        //请求参数校验
        if(StringUtils.isEmpty(requestVo.getCustomer()) || StringUtils.isEmpty(requestVo.getCommodity())) {
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }
        try {
            //调用业务层查询委托书信息
            List<TrustVO> trustVOList = trustService.queryTrustByCustomerAndCommodity(requestVo.getCustomer(), requestVo.getCommodity());
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, trustVOList, ConstantUtil.SUCCESS_CODE, null);
        } catch (Exception e) {
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }
    }
}
