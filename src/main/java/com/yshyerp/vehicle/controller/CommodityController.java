package com.yshyerp.vehicle.controller;

import com.yshyerp.vehicle.commons.ConstantUtil;
import com.yshyerp.vehicle.entity.Commodity;
import com.yshyerp.vehicle.service.CommodityService;
import com.yshyerp.vehicle.vo.Response;
import com.yshyerp.vehicle.vo.TankCVO;
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
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    CommodityService commodityService;

    /**
     * 根据罐号查询打印货品名称
     * @param data 罐号
     * @return
     */
    @GetMapping("bytank/{data}")
    public Response postListInit(@PathVariable String data) {
        log.info("--------------------commodity/bytank START--------------------");
        //请求参数校验
        if(StringUtils.isEmpty(data)) {
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }
        try {
            //查询货品打印名称
            List<TankCVO> tankCVOList = commodityService.queryTankCByTank(data);
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, tankCVOList, ConstantUtil.SUCCESS_CODE, null);
        }catch (Exception e) {
            //查询打印货品名称执行异常
            log.error(e.getMessage());
            e.printStackTrace();
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }
    }

    /**
     * 保存提货计划时查询货品是否危险品
     * @param data 货品名称
     * @return
     */
    @GetMapping("querycommoditydanger/{data}")
    public Response queryCommodityDanger(@PathVariable String data) {
        log.info("--------------------commodity/checkcommodity START--------------------");
        //请求参数校验
        if(StringUtils.isEmpty(data)) {
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }
        List<Commodity> commodityList = commodityService.queryCommodityByCommodity(data);
        if(commodityList.size()>0) {
            if(commodityList.get(0).getWx()) {
                //危险品,弹出危险品校验提示框
                return Response.success(ConstantUtil.INPUT_COMMODITY_MSG0, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.INPUT_COMMODITY_CODE0);
            }else {
                //普通货品
                return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
            }
        }
        //货品不存在
        return Response.success(ConstantUtil.MESSAGE_COMMODITY_MSG0, false, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_COMMODITY_CODE0);
    }

    /**
     * 授权密码校验
     * @param data 授权码
     * @return
     */
    @GetMapping("checkquantitypw/{data}")
    public Response checkQuantityPw(@PathVariable String data) {
        log.info("--------------------commodity/checkquantitypw START--------------------");
        if(StringUtils.isEmpty(data)) {
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE10, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, commodityService.checkQuantityPw(data), ConstantUtil.SUCCESS_CODE, null);
    }



}
