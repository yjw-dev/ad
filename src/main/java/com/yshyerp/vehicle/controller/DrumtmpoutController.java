package com.yshyerp.vehicle.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yshyerp.vehicle.commons.ConstantUtil;
import com.yshyerp.vehicle.entity.*;
import com.yshyerp.vehicle.service.*;
import com.yshyerp.vehicle.vo.Request;
import com.yshyerp.vehicle.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 桶装出货页面
 */
@Slf4j
@RestController
@ResponseBody
@RequestMapping("/drumtmpout")
public class DrumtmpoutController {

    @Autowired
    DrumtmpoutService drumtmpoutService;
    @Autowired
    DrumsService drumsService;
    @Autowired
    Drumtmp1Service drumtmp1Service;
    @Autowired
    VehiPlanService vehiPlanService;
    @Autowired
    SysDataService sysDataService;


    /**
     * 查询drumtmp表
     *
     * @return
     */
    @PostMapping("drumtmpoutList")
    public Response drumtmpoutList(@RequestBody Request<Drumtmpout> request) {
        if (!StringUtils.isEmpty(request.getPageNum()) && !StringUtils.isEmpty(request.getPageSize())) {
            PageHelper.startPage(request.getPageNum(), request.getPageSize());
        }
        try {
            //查询
            List<Drumtmpout> drumtmpoutList = drumtmpoutService.listdrum();
            PageInfo pageInfo = new PageInfo(drumtmpoutList);
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, pageInfo, ConstantUtil.SUCCESS_CODE, null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }
    }


    /**   1提货计划状态
     * 查询提货计划
     * 根据提货计划  新增
     */
    @PostMapping("thjhddc")
    public Response thjh(@RequestBody Request<VehiPlan1> request) {
        try {
           //提货计划V
            List<VehiPlan1> v = vehiPlanService.thjhcxddc(request.getData().getPlanNo());
//            SysData sysData = sysDataService.querySysData();
//            String jobNo = sysDataService.getSysDataId(sysData, "job_no");
            //根据提货计划新增      新增空桶入仓临时
            Drumtmpout drumtmpout = new Drumtmpout();
            drumtmpout.setNo1(v.get(0).getNo1());
            drumtmpout.setVehicle(v.get(0).getVehicle());
            drumtmpout.setTank(v.get(0).getTank());
            drumtmpout.settCustomer(v.get(0).gettCustomer());
            drumtmpout.setcCustomer(v.get(0).getcCustomer());
            drumtmpout.setCrrNo(v.get(0).getCrrNo());
            drumtmpout.setDoNo(v.get(0).getDoNo());
            drumtmpout.setCommodity(v.get(0).getCommodity());
            drumtmpout.setCommodity1(v.get(0).getCommodity1());
            drumtmpout.setTimeR("00:00:00");
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateNowStr = sdf.format(d);
            drumtmpout.setTimeR(dateNowStr);
            drumtmpout.setDelivered(v.get(0).getDelivered());
            drumtmpout.setDrums(v.get(0).getDrums());
            drumtmpout.setPacking(v.get(0).getPacking());
            drumtmpout.setNetweight(v.get(0).getNetweight());
            drumtmpout.setSerial("");
            drumtmpout.setIdno(v.get(0).getIdno());
            drumtmpout.setOriDo(v.get(0).getOriginal());
            drumtmpout.setFaxno(v.get(0).getFaxno());
            drumtmpout.setTare(v.get(0).getTare());
            drumtmpout.setCover(v.get(0).getCover());
            drumtmpout.setCharge(false);
            drumtmpout.setFeeNo("");
            drumtmpout.setStatus(v.get(0).getStatus());
            drumtmpout.setN(v.get(0).getCnew());
            drumtmpout.setColor(v.get(0).getColor());
            drumtmpout.setBatch(v.get(0).getBatch());
            drumtmpout.setType(v.get(0).getType());
            drumtmpout.setSlop(v.get(0).getSlop());
            drumtmpout.setRemarks(v.get(0).getRemarks());
            drumtmpout.setPlanNo(v.get(0).getPlanNo());
            //新增
            drumtmpoutService.insertSelective(drumtmpout);
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.error(ConstantUtil.SERVICE_ERROR_MESSAGE, ConstantUtil.SERVICE_ERROR_CODE, ConstantUtil.DOVO_ERROR_CODE);
        }
    }


    /**
     * 手动新增
     * 输入状态
     *
     * @param request 参数
     * @return
     */
    @PostMapping("addinsert")
    public Response insert(@RequestBody Request<Drumtmpout> request) {
        //请求参数校验
        if (StringUtils.isEmpty(request)) {
            return Response.error(ConstantUtil.REQUEST_NULL_MESSAGE, ConstantUtil.CLIENT_ERROR_CODE, ConstantUtil.REQUEST_NULL_CODE);
        }
        Drumtmpout drumtmpout = request.getData();
        //获取no1  设置
        Drumtmpout drumtmpout1 = drumtmpoutService.getcoun(drumtmpout.getVehicle());
        String a = drumtmpout1.getCoun() + "";
        String sub = (a + 101).substring(0, 2);
        //验证
        drumtmpout.setNo1(sub);






        int updFlag = drumtmpoutService.insert(drumtmpout);






        if (updFlag > 0) {
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
        }
        return Response.success(ConstantUtil.REQUEST_NULL_MESSAGE, false, ConstantUtil.REQUEST_NULL_CODE, null);
    }


    /**
     * 修改
     *
     * @param request 参数
     * @return
     */
    @PutMapping("updedrums")
    public Response updedrums(@RequestBody Request<Drumtmpout> request) {
        int updFlag = drumtmpoutService.update(request.getData());
        if (updFlag > 0) {
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
        }
        return Response.success(ConstantUtil.SUCCESS_MESSAGE, false, ConstantUtil.SUCCESS_CODE, null);
    }

    /**
     * 逻辑删除
     *
     * @param request Vehicle  车牌号
     * @return
     */
    @PutMapping("updVehiplanId")
    public Response updVehiplanId(@RequestBody Request<Drumtmpout> request) {
        int updFlag = drumtmpoutService.updateDd(request.getData().getVehicle());
        if (updFlag > 0) {
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
        }
        return Response.success(ConstantUtil.REQUEST_NULL_MESSAGE, false, ConstantUtil.REQUEST_NULL_CODE, null);
    }

    /**
     * 根据PlanNo计划单号查询
     * PlanNo计划单号查询
     *
     * @param
     * @return
     */
    @PostMapping("ByPlanNo")
    public Response ByPlanNo(@RequestBody Request<Drumtmpout> request) {
        Drumtmpout d = drumtmpoutService.listdrumid(request.getData().getPlanNo());
        Map<String, Object> map = new HashMap<>();
        map.put("tank", d.getTank());
        map.put("ccustomer", d.getcCustomer());
        map.put("scustomer", d.getsCustomer());
        map.put("commodity", d.getTank());
        map.put("color", d.getColor());
        map.put("cover", d.getCover());
        map.put("tare", d.getTare());
        map.put("packing", d.getPacking());
        map.put("new", d.getN());
        map.put("batch", d.getBatch());
        if (d.getStatus() == "NORMAL") {
            //根据条件判断，同类型桶库存数量
            deace(map);
        }

        return Response.success(ConstantUtil.SUCCESS_MESSAGE, d, ConstantUtil.SUCCESS_CODE, null);
    }

    //第'+ALLTRIM(STR(t_ddcno))+'单错误，可能同类型桶库存数不够，现库存'+ALLTRIM(STR(t_total))+'桶，仓内正在装货和本次预计装货
    // 合计'+ALLTRIM(STR(t_in))+'桶，
    // 请查证',48,'億升仓储管理系统')
    public String deace(Map map) {
        //根据参数获取  桶装货库存
        Drums drums = drumsService.drumsch(map);
        int t_total = drums.getBalance();
        Drumtmp2 drumtmp2 = drumtmp1Service.drumsum1(map);
        int t_in = drumtmp2.getSum1();
        if (t_total - t_in < 0) {
            return "'第'ALLTRIM(STR(t_ddcno))+'单错误，可能同类型桶库存数不够，现库存" + t_total + "桶，仓内正在装货和本次预计装货合计" + t_in + "桶，请查证',48,'億升仓储管理系统'";
        }

        return null;

    }


}
