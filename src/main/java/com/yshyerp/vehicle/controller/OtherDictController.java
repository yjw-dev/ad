package com.yshyerp.vehicle.controller;

import com.yshyerp.vehicle.commons.ConstantUtil;
import com.yshyerp.vehicle.commons.ToolUtil;
import com.yshyerp.vehicle.entity.OtherDict;
import com.yshyerp.vehicle.service.OtherDicService;
import com.yshyerp.vehicle.vo.OtherDictVo;
import com.yshyerp.vehicle.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * 页面共有下拉框
 */

@Slf4j
@RestController
@ResponseBody
@RequestMapping("/otherdic")
public class OtherDictController {

    @Autowired
    OtherDicService otherDicService;



    /**
     * 颜色
     *
     * @return
     */
    @PostMapping("otherDictlist")
    public Response otherDictlist() {
        try {
            List<Map<String,Object>> otherDictlist = otherDicService.OtherDiclist();
            List<OtherDictVo> contract1VoList = new ArrayList<>();
//            for (OtherDict contract : otherDictlist) {
//                contract1VoList.add((OtherDictVo) ToolUtil.doCastVo(contract, new OtherDictVo()));
//            }
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, contract1VoList, ConstantUtil.SUCCESS_CODE, null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }
    }

    /**
     * 出货类型
     *
     * @return
     */
    @PostMapping("othertype")
    public Response Othertype() {
        try {
            List<OtherDict> otherDictlist = otherDicService.Othertype();
            List<OtherDictVo> contract1VoList = new ArrayList<>();
            for (OtherDict contract : otherDictlist) {
                contract1VoList.add((OtherDictVo) ToolUtil.doCastVo(contract, new OtherDictVo()));
            }
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, contract1VoList, ConstantUtil.SUCCESS_CODE, null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }
    }

    //批号
    @PostMapping("othercode")
    public Response Othercode() {
        try {
            List<OtherDict> otherDictlist = otherDicService.Othercode();
            List<OtherDictVo> contract1VoList = new ArrayList<>();
            for (OtherDict contract : otherDictlist) {
                contract1VoList.add((OtherDictVo) ToolUtil.doCastVo(contract, new OtherDictVo()));
            }
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, contract1VoList, ConstantUtil.SUCCESS_CODE, null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }
    }




}
