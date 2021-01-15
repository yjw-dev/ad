package com.yshyerp.vehicle.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yshyerp.vehicle.commons.ConstantUtil;
import com.yshyerp.vehicle.commons.ToolUtil;
import com.yshyerp.vehicle.entity.*;
import com.yshyerp.vehicle.service.*;
import com.yshyerp.vehicle.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 来空桶计划页面
 */
@Slf4j
@RestController
@ResponseBody
@RequestMapping("/vehiplan")
public class VehiPlanController {

    @Autowired
    VehiPlanService vehiPlanService;
    @Autowired
    Djob1Service djob1Service;

    @Autowired
    EdrumsService edrumsService;

    @Autowired
    SCustomerService sCustomerService;

    @Autowired
    CustomerService customerService;

    @Autowired
    Contract1Service contract1Service;



    /**
     * 查询vehi_plan表
     *
     * @return
     */
    @PostMapping("vehiPlanlist")
    public Response vehiPlanlist(@RequestBody Request<VehiPlan1> request) {
        if(!StringUtils.isEmpty(request.getPageNum())&& !StringUtils.isEmpty(request.getPageSize())) {
            PageHelper.startPage(request.getPageNum(), request.getPageSize());
        }
        try {
            //查询
            List<VehiPlan1> vehiPlanlist = vehiPlanService.list();
            PageInfo pageInfo = new PageInfo(vehiPlanlist);
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, pageInfo, ConstantUtil.SUCCESS_CODE, null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }
    }



    /**
     * 根据罐主名称查询合同下拉框
     *
     * @param request
     * @return
     */
    @PostMapping("contractList")
    public Response contractList(@RequestBody Request<Contract> request) {
        try {
            List<Contract1Vo> contract1VoList = new ArrayList<>();
            List<Contract> contractList = contract1Service.queryContractListByCustomer(request.getData().getCustomer());
            for (Contract contract : contractList) {
                contract1VoList.add((Contract1Vo) ToolUtil.doCastVo(contract, new Contract1Vo()));
            }
            List<Contract2Vo> vos = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i < contract1VoList.size(); i++) {
                //   字段ContractI
                String contractI = contract1VoList.get(i).getContractI();
                //时间
                String str = sdf.format(contract1VoList.get(i).getEnDate());
                //分割   tack
                String[] b = contract1VoList.get(i).getTank().split("/");
                if (b != null) {
                    for (int j = 0; j < b.length; j++) {
                        Contract2Vo contract2Vo = new Contract2Vo();
                        contract2Vo.setContractI(contractI);
                        contract2Vo.setTank(b[j] + " " + str);
                        vos.add(contract2Vo);
                    }
                }
            }
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, vos, ConstantUtil.SUCCESS_CODE, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
    }

    /**
     * 查询小客户
     *
     * @return
     */
    @PostMapping("scustomerlist")
    public Response scustomerlist(@RequestBody Request<SCustomer> request) {
        try {
            //查询
            List<SCustomer> scustomerlist = sCustomerService.alist(request.getData());
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, scustomerlist, ConstantUtil.SUCCESS_CODE, null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }
    }

    /**
     * 查询大客户
     *
     * @return
     */
    @PostMapping("customerList")
    public Response customerList() {
        try {
            //查询
            List<Customer> customerList = customerService.customerList();
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, customerList, ConstantUtil.SUCCESS_CODE, null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }
    }

    /**
     * 根据PlanNo计划单号查询
     *
     * @param data PlanNo计划单号查询
     * @return
     */
    @PostMapping("ByPlanNo/{data}")
    public Response ByPlanNo(@PathVariable String data) {
        if (StringUtils.isEmpty(data)) {
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }
        VehiPlan1 v = vehiPlanService.queryplanNoByDoNo(data);
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, v, ConstantUtil.SUCCESS_CODE, null);
    }







    /**
     * 新增
     *
     * @param request 参数
     * @return
     */
    @PostMapping("addinsertSelective")
    public Response insertSelective(@RequestBody Request<VehiPlan1> request) {
        //请求参数校验
        if (StringUtils.isEmpty(request)) {
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }
        VehiPlan1 vehiPlan1 = new VehiPlan1();
        vehiPlan1.setrDate(new Date());
        int updFlag = vehiPlanService.insertSelective(request.getData());
        if (updFlag > 0) {
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
        }
        return Response.success(ConstantUtil.REQUEST_NULL_MESSAGE, false, ConstantUtil.REQUEST_NULL_CODE, null);


    }

    /**
     * 修改信息
     *
     * @param request 参数
     * @return
     */
    @PutMapping("updVehiPlan")
    public Response updatePut(@RequestBody Request<VehiPlan1> request) {
        VehiPlan1 vehiPlan1 = request.getData();
        int updFlag = vehiPlanService.updVehiPlan1(vehiPlan1);
        if (updFlag > 0) {
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
        }
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, false, ConstantUtil.SUCCESS_CODE, null);
    }

    /**
     * 逻辑删除
     *
     * @param request PlanNo单号
     * @return
     */
    @PutMapping("updVehiplanId")
    public Response updVehiplanId(@RequestBody Request<VehiPlan1> request) {
        int updFlag = vehiPlanService.updVehiplanId(request.getData().getPlanNo());
        System.out.println(vehiPlanService.updVehiplanId(request.getData().getPlanNo()));
        if (updFlag > 0) {
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
        }
        return Response.success(ConstantUtil.REQUEST_NULL_MESSAGE, false, ConstantUtil.REQUEST_NULL_CODE, null);
    }

    /**
     * 根据用户名查询桶数基本信息   合并Edrums，Djob1表
     *
     * @param
     * @return
     */
    @PostMapping("Bycustomer")
    public Response Bycustomer(@RequestBody Request<DjobVo> request) {

//        if(!StringUtils.isEmpty(request.getPageNum())&& !StringUtils.isEmpty(request.getPageSize())) {
//            PageHelper.startPage(request.getPageNum(), request.getPageSize());
//        }
        try {
            List<Djob1> djob1s = djob1Service.Bycustomer(request.getData());
            List<Edrums> edrums = edrumsService.Bycustomer(request.getData());
            List<DjobVo> djobVoList = new ArrayList<DjobVo>();
            for (Djob1 djob1 : djob1s) {
                djobVoList.add((DjobVo) ToolUtil.doCastVo(djob1, new DjobVo()));
            }
            for (Edrums edrums2 : edrums) {
                djobVoList.add((DjobVo) ToolUtil.doCastVo(edrums2, new DjobVo()));
            }



            return Response.success(ConstantUtil.SUCCESS_MESSAGE, djobVoList, ConstantUtil.SUCCESS_CODE, null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }
    }


}















