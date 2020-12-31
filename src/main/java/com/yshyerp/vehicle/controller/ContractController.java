package com.yshyerp.vehicle.controller;

import com.yshyerp.vehicle.commons.ConstantUtil;
import com.yshyerp.vehicle.service.ContractService;
import com.yshyerp.vehicle.vo.ContractVO;
import com.yshyerp.vehicle.vo.Request;
import com.yshyerp.vehicle.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 合同业务类
 */
@Slf4j
@RestController
@ResponseBody
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    ContractService contractService;

    /**
     * 根据罐主名称查询合同下拉框
     * @param request
     * @return
     */
    @PostMapping("bycustomer")
    public Response postListInit(@RequestBody Request<ContractVO> request) {
        log.info("--------------------contract/bycustomer START--------------------");
        ContractVO vo = request.getData();
        //罐主
        String tankCustomer = vo.getCustomer();
        //罐号
        String tank = vo.getTank();
        //请求参数校验
        if(StringUtils.isEmpty(tankCustomer) || StringUtils.isEmpty(tank)) {
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }else {
            //调用业务层查询合同信息
            try {
                List<ContractVO> contractVOList = contractService.queryContractListByCustomer(tankCustomer, tank);
                if(null!=contractVOList && contractVOList.size()>0) {
                    return Response.success(ConstantUtil.SUCCESS_MESSAGE, contractVOList, ConstantUtil.SUCCESS_CODE, null);
                }else {
                    return Response.success(ConstantUtil.MESSAGE_CONTRACT_MSG1, null, ConstantUtil.SUCCESS_CODE, ConstantUtil.MESSAGE_CONTRACT_CODE1);
                }
            }catch (Exception e) {
                log.error(e.getMessage());
                e.printStackTrace();
                return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
            }
        }
    }
}
