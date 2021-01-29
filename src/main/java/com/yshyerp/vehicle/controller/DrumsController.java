package com.yshyerp.vehicle.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yshyerp.vehicle.commons.ConstantUtil;
import com.yshyerp.vehicle.commons.ToolUtil;
import com.yshyerp.vehicle.entity.*;
import com.yshyerp.vehicle.service.*;
import com.yshyerp.vehicle.vo.DrumslopVo;
import com.yshyerp.vehicle.vo.Request;
import com.yshyerp.vehicle.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * 验证00
 */

@Slf4j
@RestController
@ResponseBody
@RequestMapping("/drumscon")
public class DrumsController {

    @Autowired
    DrumsService drumsService;
    @Autowired
    SlopaService slopaService;
    @Autowired
    VehiPlanService vehiPlanService;
    @Autowired
    CustomerService customerService;
    @Autowired
    Drumtmp1Service drumtmp1Service;





    /**
     *    根据  灌号和货主查询
     *      参数：
     *      灌号      tank
     *      货主      ccustomer
     * @return
     */
    @PostMapping("drumslist")
    public Response drumslist(@RequestBody Request<DrumslopVo> request) {

        try {
            List<Drums> drumsList = drumsService.deumsmap(request.getData());
            List<Slop> slopList=slopaService.slopmap(request.getData());
            List<DrumslopVo> drumslopVos = new ArrayList<DrumslopVo>();
            System.out.println(request.getData().getCCustomer());
            for (Drums drums : drumsList) {
                drumslopVos.add((DrumslopVo) ToolUtil.doCastVo(drums, new DrumslopVo()));
           }
            for (Slop slop : slopList) {
                drumslopVos.add((DrumslopVo) ToolUtil.doCastVo(slop, new DrumslopVo()));
            }
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, drumslopVos, ConstantUtil.SUCCESS_CODE, null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }
    }
    /**
     * 新增  桶装出货计划
     *
     * @param request 参数
     * @return
     */
    @PostMapping("addinsert")
    public Response insert(@RequestBody Request<VehiPlan1> request) {
        System.out.println(request.getData().getcCustomer());
        System.out.println(asumtomer(request.getData().getcCustomer()));
        //请求参数校验
//        if (StringUtils.isEmpty(request)) {
//            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
//        }
//        asumtomer(request.getData().getcCustomer());
//        System.out.println( asumtomer(request.getData().getcCustomer()));
//        VehiPlan1 vehiPlan1 = request.getData();
//        vehiPlan1.setrDate(new Date());
//        int updFlag =  vehiPlanService.insert(vehiPlan1);
//        if (updFlag > 0) {
//            return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
//        }
        return Response.success(ConstantUtil.REQUEST_NULL_MESSAGE, false, ConstantUtil.REQUEST_NULL_CODE, null);
    }


    //数量校验
    public String asumtomer(String ccustomer){
        //根据货主  获取  DrumLock
        List<Customer> customer=customerService.getCustomerByCustomerName(ccustomer);
        BigDecimal lock=customer.get(0).getDrumLock();
        int  t_lock=lock.intValue();
        System.out.println(t_lock+"t_lock");
        //根据货主    DRUMTMP  sum(drums)
        Drumtmp2 drumtmp2=drumtmp1Service.getCustomerByCustomerName(ccustomer);
        Integer t_in=drumtmp2.getSum1();
        System.out.println(t_in+"t_in");
        //获取  vehi_plan        sum(drums)
//        Drumtmp2 drumtmp3=drumtmp1Service.getvehiplansum(ccustomer);
//        Integer t_in2=drumtmp3.getSum1();
        //获取  DRUMS     sum(balance)
        Drumtmp2 drumtmp5=drumtmp1Service.getdrumsum(ccustomer);
        Integer t_total=drumtmp5.getSum1();
        System.out.println(t_total+"t_total");
        if (t_total-t_in< t_lock && t_lock>0){
            return "该客户"+ccustomer+"已设定最低桶出货限制，库存数不得小于"+t_lock+"桶，请查证',48,SOFTNAME";
        }
        return null;
    }





















}
