package com.yshyerp.vehicle.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yshyerp.vehicle.commons.ConstantUtil;
import com.yshyerp.vehicle.entity.*;
import com.yshyerp.vehicle.mapper.DonopictMapper;
import com.yshyerp.vehicle.service.*;
import com.yshyerp.vehicle.vo.Request;
import com.yshyerp.vehicle.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    @Autowired
    VerifyService verifyService;
    @Autowired
    CustomerService customerService;
    @Autowired
    DonopictMapper doNoPictMapper;


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


    /**
     * 1提货计划状态
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
            //未入仓
            drumtmpout.setIsin(false);
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
        //未入仓
        drumtmpout.setIsin(false);

        int updFlag = drumtmpoutService.insert(drumtmpout);
        if (updFlag > 0) {
            return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
        }
        return Response.success(ConstantUtil.REQUEST_NULL_MESSAGE, false, ConstantUtil.REQUEST_NULL_CODE, null);
    }


    //入仓  判断
    @PostMapping("pdrc")
    public Response pdrc(@RequestBody Request<Drumtmpout> request) throws Exception {
        Map mapes = new HashMap();
        List<Drumtmpout> list = drumtmpoutService.getvehiclelist0(request.getData().getVehicle());
        int c = 0;
        for (int i = 0; i < list.size(); i++) {
            c = c++;
            if (list.get(i).getStatus() != "empty") {
                verifyService.huochujyan(list.get(i).getCommodity(), "0", null);
            }
            if (list.get(i).getStatus() == "empty" || list.get(i).getStatus() != "SLOP" || list.get(i).getStatus() != "DRMIN") {

            }
            short lcfee = 0;
            String lccode = "";
            boolean lcp_bar = false;
            if (list.get(i).getStatus() == "NORMAL" && null != list.get(i).getsCustomer()) {
                Map map = new HashMap<String, Object>();
                map.put("tank", list.get(i).getTank());
                map.put("ccustomer", list.get(i).getcCustomer());
                map.put("scustomer", list.get(i).getsCustomer());
                map.put("commodity ", list.get(i).getCommodity());
                map.put("color", list.get(i).getColor());
                map.put("cover", list.get(i).getCover());
                map.put("tare", list.get(i).getTare());
                map.put("packing", list.get(i).getPacking());
                map.put("n", list.get(i).getN());
                map.put("batch", list.get(i).getBatch());
                Drums drums = drumsService.drumsch(map);

                Date date = new Date();// 创建Date类型对象
                //创建SimpleDateFormat类型对象、 "yyyy-MM-dd HH:ss:mm.SSS"是正则式，
                // 分别表示年月日时分秒毫秒
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm.SSS");
                String datenew = df.format(new Date());
                //当前时间
                Date d1 = df.parse(datenew);
                String drumsgetDate1 = df.format(drums.getDate1());
                //drums表时间
                Date d2 = df.parse(drumsgetDate1);

                System.out.println((d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000) + "天");
                lcfee = Short.parseShort("(d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000)");
                Map mapupda = new HashMap();
                mapupda.put("fee", lcfee);
                mapupda.put("code", lccode);
                mapupda.put("p_bar", lcp_bar);
                mapupda.put("no1", list.get(i).getNo1());
                int a = drumtmpoutService.beiyong(mapupda);
            }
            if (list.get(i).getStatus() == "NORMAL") {
                //校验最低桶出货限制
                asumtomer(list.get(i).getcCustomer());
                Map mapa = new HashMap<String, Object>();
                mapa.put("tank", list.get(i).getTank());
                mapa.put("ccustomer", list.get(i).getcCustomer());
                mapa.put("scustomer", list.get(i).getsCustomer());
                mapa.put("commodity ", list.get(i).getCommodity());
                mapa.put("color", list.get(i).getColor());
                mapa.put("cover", list.get(i).getCover());
                mapa.put("tare", list.get(i).getTare());
                mapa.put("packing", list.get(i).getPacking());
                mapa.put("n", list.get(i).getN());
                mapa.put("batch", list.get(i).getBatch());
                Drums drums = drumsService.drumsch(mapa);

                Integer t_total = drums.getBalance();
                //校验可能同类型桶库存数不够
                maxget(list.get(i).getcCustomer(), t_total, c);

                lccode = drums.getCode1() + drums.getCode2();
                lcp_bar = drums.getpBar() && !drums.getpBar1() ? true : false;
                Map mapupda = new HashMap();
                mapupda.put("fee", lcfee);
                mapupda.put("code", lccode);
                mapupda.put("p_bar", lcp_bar);
                mapupda.put("no1", list.get(i).getNo1());
                int a = drumtmpoutService.beiyong(mapupda);
            }


            mapes.put("weight1", 0);
            mapes.put("vehicle", request.getData().getVehicle());
            //称重
            int a = drumtmpoutService.updaweight1(mapes);
            mapes.clear();

            //生成jobNo
            SysData sysData = sysDataService.querySysData();
            String jobNo = sysDataService.getSysDataId(sysData, "job_no");
            mapes.put("jobNo", jobNo);
            mapes.put("vehicle", request.getData().getVehicle());
            mapes.put("no1", request.getData().getNo1());
            drumtmpoutService.updachucahng(mapes);
            mapes.clear();
            mapes.put("jobNo", jobNo);
            mapes.put("vehicle", request.getData().getPlanNo());
            vehiPlanService.updddca(mapes);
            //更新job_no
            sysDataService.updSysDataIdToJobNo(jobNo);
            //更新提单图片记录表



        mapes.clear();
        boolean orido=list.get(i).getOriDo();
        if (orido=false && !StringUtils.isEmpty(list.get(i).getFaxno())) {
            String[] faxNoArray = list.get(i).getFaxno().split(";");
            for (String faxNo : faxNoArray) {
                mapes.clear();
                mapes.put("jobNo", jobNo);
                mapes.put("no", faxNo);
                doNoPictMapper.updDoNoPictToJobNo(mapes);
            }
        }
        }

        return Response.success(ConstantUtil.REQUEST_NULL_MESSAGE, false, ConstantUtil.REQUEST_NULL_CODE, null);
    }


    //数量校验
    public String asumtomer(String ccustomer) {
        //根据货主  获取  DrumLock
        List<Customer> customer = customerService.getCustomerByCustomerName(ccustomer);
        BigDecimal lock = customer.get(0).getDrumLock();
        int t_lock = lock.intValue();
        System.out.println(t_lock + "t_lock");
        //根据货主    DRUMTMP  sum(drums)
        Drumtmp2 drumtmp2 = drumtmp1Service.getCustomerByCustomerName(ccustomer);
        Integer t_in = drumtmp2.getSum1();
        System.out.println(t_in + "t_in");
        //获取  vehi_plan        sum(drums)
//        Drumtmp2 drumtmp3=drumtmp1Service.getvehiplansum(ccustomer);
//        Integer t_in2=drumtmp3.getSum1();
        //获取  DRUMS     sum(balance)
        Drumtmp2 drumtmp5 = drumtmp1Service.getdrumsum(ccustomer);
        Integer t_total = drumtmp5.getSum1();
        System.out.println(t_total + "t_total");
        if (t_total - t_in < t_lock && t_lock > 0) {
            return "该客户" + ccustomer + "已设定最低桶出货限制，库存数不得小于" + t_lock + "桶，请查证',48,SOFTNAME";
        }
        return null;
    }

    //数量校验
    public String maxget(String ccustomer, Integer t_total, int c) {

        //根据货主    DRUMTMP  sum(drums)
        Drumtmp2 drumtmp2 = drumtmp1Service.getCustomerByCustomerName(ccustomer);
        Integer t_in = drumtmp2.getSum1();
        System.out.println(t_in + "t_in");
        if (t_total - t_in < 0) {
            return "'第" + c + "单错误，可能同类型桶库存数不够，现库存" + t_total + "桶，" +
                    "仓内正在装货和本次预计装货合计" + t_in + "桶，请查证'";
        }
        return null;
    }


    /**
     * 状态3
     * 修改车牌号
     *
     * @param request 参数
     * @return
     */
    @PostMapping("updagetvehicle")
    public Response updagetvehicle(@RequestBody Request<Drumtmpout> request) {
        Drumtmpout drumtmpout = request.getData();
        List<Drumtmpout> list = drumtmpoutService.getvehiclelist1(drumtmpout.getVehicle());
        if (null == list) {
            return Response.success(ConstantUtil.MESSAGE_NEW_IDCAR, false, ConstantUtil.SUCCESS_CODE, null);
        } else {
            Map<String, Object> map = new HashMap();
            String vehiclea = request.getData().getVehiclea();
            String vehicle = request.getData().getVehicle();
            map.put("vehiclea", vehiclea);
            map.put("vehicle", vehicle);
            int a = drumtmpoutService.updvehicle(map);
            if (a > 0) {
                return Response.success(ConstantUtil.SUCCESS_MESSAGE, true, ConstantUtil.SUCCESS_CODE, null);
            }
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
