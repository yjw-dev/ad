package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.commons.ConstantUtil;
import com.yshyerp.vehicle.commons.ToolUtil;
import com.yshyerp.vehicle.entity.*;
import com.yshyerp.vehicle.mapper.CarGoOutMapper;
import com.yshyerp.vehicle.mapper.TankVehicleInOutMapper;
import com.yshyerp.vehicle.mapper.TrustMapper;
import com.yshyerp.vehicle.mapper.TtdcTempMapper;
import com.yshyerp.vehicle.service.TankService;
import com.yshyerp.vehicle.service.TankVehicleInOutService;
import com.yshyerp.vehicle.service.TrustService;
import com.yshyerp.vehicle.vo.TankVehicleListVO;
import com.yshyerp.vehicle.vo.WarehousingVO;
import com.yshyerp.vehicle.vo.WeighbridgeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 槽车出入仓业务类
 */
@Service
public class TankVehicleInOutServiceImpl implements TankVehicleInOutService {

    @Autowired
    TankVehicleInOutMapper tankVehicleInOutMapper;

    @Autowired
    TankService tankService;

    @Autowired
    TtdcTempMapper ttdcTempMapper;

    @Autowired
    TrustMapper trustMapper;

    @Autowired
    CarGoOutMapper carGoOutMapper;

    /**
     * 入仓列表查询
     * @param vo 查询条件
     * @return
     */
    @Override
    public List<TtdcTemp> queryTankVehicleList(TankVehicleListVO vo) throws Exception{
        return tankVehicleInOutMapper.queryTankVehicleInList(vo);
    }

    /**
     * 开启/关闭地磅
     * @param startSwitch 开关  0关1开
     * @param  ipAddress 地磅IP
     * @return
     */
    @Override
    public String updStartSwitch(String startSwitch, String ipAddress) {
        int updFlag;
        if(ipAddress.equals(ConstantUtil.WB1)) {
            //地磅1开关
            updFlag = tankVehicleInOutMapper.updStartSwitch1(startSwitch);
        }else if(ipAddress.equals(ConstantUtil.WB2)) {
            //地磅2开关
            updFlag = tankVehicleInOutMapper.updStartSwitch2(startSwitch);
        }else {
            return null;
        }
        if(updFlag==1 && "1".equals(startSwitch)) {
            //已开启
            return "1";
        }else if(updFlag==1 && "0".equals(startSwitch)) {
            //已关闭
            return "0";
        }
        return null;
    }

    /**
     * 称重
     * @param ipAddress 地磅ip
     * @return
     */
    @Override
    public WeighbridgeVO queryWeighbridge(String ipAddress) {
        WeighbridgeVO vo = new WeighbridgeVO();
        Weighbridge weighbridge = tankVehicleInOutMapper.queryWeighbridge();
        if(ipAddress.equals(ConstantUtil.WB1)) {
            //地磅1重量
            vo.setSt(weighbridge.getSt1());
            vo.setWeight(weighbridge.getWeight1());
        }else {
            vo.setSt(weighbridge.getSt2());
            vo.setWeight(weighbridge.getWeight2());
        }
        return vo;
    }

    /**
     * 获取装车台
     * @param tank 储罐号
     * @return
     */
    public String queryLcstation(String tank) {
        String lcstation = null;
        if(!tank.substring(0).equals("S") && !tank.substring(0).equals("I")) {
            Tank thisTank = tankService.queryTankInfoByTank(tank);
            String tankStation1 = thisTank.getStation1();
            String tankStation2 = thisTank.getStation2();
            String tankStation3 = thisTank.getStation3();
            if(StringUtils.isEmpty(tankStation1)) {
                return lcstation;
            }
            lcstation = tankStation1;
            Map map = new HashMap();
            map.put("station1",tankStation1);
            map.put("station2",tankStation2);
            map.put("station3",tankStation3);
            int num1=0, num2=0, num3=0;
            List<TtdcTemp> ttdcTempList = ttdcTempMapper.queryStation(map);
            for(TtdcTemp ttdcTemp : ttdcTempList) {
                if(tankStation1.equals(ttdcTemp.getStation())) {
                    num1++;
                }else if(!StringUtils.isEmpty(tankStation2) && tankStation2.equals(ttdcTemp.getStation())) {
                    num2++;
                }else if(!StringUtils.isEmpty(tankStation3) && tankStation3.equals(ttdcTemp.getStation())) {
                    num3++;
                }
            }
            if(StringUtils.isEmpty(tankStation2) && StringUtils.isEmpty(tankStation3)) {
                lcstation = tankStation1;
            }else if(StringUtils.isEmpty(tankStation3)) {
                if(num1<=num2) {
                    lcstation = tankStation1;
                }else {
                    lcstation = tankStation2;
                }
            }else if(!StringUtils.isEmpty(tankStation2) && !StringUtils.isEmpty(tankStation3)) {
                if(num1<=num2 && num1<=num3) {
                    lcstation = tankStation1;
                }else if(num2<=num3) {
                    lcstation = tankStation2;
                }else {
                    lcstation = tankStation3;
                }
            }
        }
        return lcstation;
    }

    @Override
    public void saveTtdcTemp(WarehousingVO vo) {
        Date date = new Date();
        Timestamp date0 = new Timestamp(new Date().getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String sDate = formatter.format(date);
        vo.setTime0(sDate.substring(11));
        vo.setDate0(date0);
        ttdcTempMapper.saveTtdcTemp(vo);
    }

    @Override
    public WarehousingVO queryTtdcTempDetail(String jobNo) {
        return this.transforVo(ttdcTempMapper.queryTtdcTempDetail(jobNo));
    }

    private WarehousingVO transforVo(TtdcTemp ttdcTemp) {
        WarehousingVO vo = new WarehousingVO();
        vo.setWb(ttdcTemp.getWb());
        vo.setDate0(ttdcTemp.getDate0());
        vo.setTime0(ttdcTemp.getTime0());
        vo.setTimeR(ttdcTemp.getTimeR());
        vo.setStation(ttdcTemp.getStation());
        vo.setJobNo(ttdcTemp.getJobNo());
        vo.setTank(ttdcTemp.getTank());
        vo.setBdo(ttdcTemp.getBdo()==true ? "1" : "0");
        vo.setBillid(ttdcTemp.getBillid());
        vo.setBoxNo(ttdcTemp.getBoxNo());
        vo.setComm1(ttdcTemp.getCommodity1());
        vo.setCommodity(ttdcTemp.getCommodity());
        vo.setCrrNo(ttdcTemp.getCrrNo());
        vo.setCustomer(ttdcTemp.getCustomer());
        vo.setDelivered(ttdcTemp.getDelivered());
        vo.setDoNo(ttdcTemp.getDoNo());
        vo.setFaxno(ttdcTemp.getFixNo());
        vo.setIdno(ttdcTemp.getIdNo());
        vo.setOriginal(ttdcTemp.getOriginal()==true ? "1" : "2");
        vo.setPlanNo(ttdcTemp.getPlanNo());
        vo.setWeight1(ttdcTemp.getWeight1().intValue());
        vo.setVehiW1(ttdcTemp.getVehiW1().doubleValue());
        vo.setVehiV(ttdcTemp.getVehiV().doubleValue());
        vo.setTxtVehiW(ttdcTemp.getVehiW().doubleValue());
        vo.setTxtVehiW2(ttdcTemp.getVehiW2().doubleValue());
        vo.setTxtVehiW3(ttdcTemp.getVehiW3().doubleValue());
        vo.setRemarks(ttdcTemp.getRemarks());
        vo.setCameraFile1(null);
        vo.setStatus(ttdcTemp.getStatus());
        vo.setDate1(ttdcTemp.getDate1());
        vo.setTime1(ttdcTemp.getTime1());
        vo.setEeWeight(ttdcTemp.getEeWeight().intValue());
        return vo;
    }

    @Override
    public boolean saveOutWarehouse(WarehousingVO vo) {
        //保存图片

        //更新委托书结存及委托书出货过程

        return true;
    }

    private boolean wCrrNo(WarehousingVO vo) {
//        String lcDoNo = vo.getDoNo().substring(0, 30);
//        String lcVehicle = vo.getTxtVehicle();
//        String lcNo = "CDC" + vo.getCdcNo();
//        Date lcDate = new Date();
//        String lcCustomer = vo.getCustomer();
//        String lcCommodity = vo.getCommodity();
//        String tank = vo.getTank();
//        double tBaTemp = vo.getNetWeight()/1000;
//        String remark = vo.getRemarks();
//
//        //委托书框中的委托书号
//        String txtCrrNos = vo.getCrrNo();
//        //备注中的委托书号
//        ArrayList<String> remarkCrrNoList = new ArrayList<String>();
//        String rCrrNo;
//        double rQty;
//        if(StringUtils.isEmpty(remark)) {
//            remarkCrrNoList = ToolUtil.analysisRemark(remark);
//        }
//        if(remarkCrrNoList.size()!=0) {
//            for(String remarkCrrNo : remarkCrrNoList) {
//                //String[0]=crrNo   String[1]=qty
//                String[] remarkCrrNoAndQty = remarkCrrNo.split(":");
//                rCrrNo = remarkCrrNoAndQty[0];
//                rQty = Double.valueOf(remarkCrrNoAndQty[1]);
//                txtCrrNos = this.rejectRepeatCrrNo(txtCrrNos, rCrrNo);
//            }
//        }
//
//        /////////////////////////////////////////////////////////////////////////
//        for(int i=0; i<remarksCrrNo.length; i++) {
//            remarksCrrNo[i] = remarksCrrNo[i].substring(remarksCrrNo[i].lastIndexOf(";")+1);
//            //QTY=doNoAndQty1[1]   R_CRR_NO1=doNoAndQty1[0]
//            String[] doNoAndQty1 = remarksCrrNo[i].split(":");
//            String qty = doNoAndQty1[1];
//            double dQty = Double.valueOf(qty);
//            String rCrrNo1 = doNoAndQty1[0];
//            String topCrrNo1 = rCrrNo1 + ";";
//            String lastCrrNo1 = ";" + rCrrNo1;
//            //剔除remark中的crrNo
//
//            Trust trust = trustMapper.queryTrustByCrrNo(rCrrNo1);
//            boolean tDone = false;
//            double dBalance = 0;
//            if(null != trust.getBalance()) {
//                dBalance = trust.getBalance().doubleValue();
//                if(dQty>=dBalance) {
//                    dQty = dBalance;
//                }
//                tBaTemp = tBaTemp-dQty;
//                if(dBalance<=0) {
//                    tDone = true;
//                }
//            }
//            HashMap map = new HashMap();
//            map.put("date2", lcDate);
//            map.put("balance", dBalance-dQty);
//            map.put("done", tDone);
//            map.put("crrNo", rCrrNo1);
//            trustMapper.updTrustByCrrNo(map);
//            map.clear();
//            map.put("crrNo", rCrrNo1);
//            map.put("doNo", lcDoNo);
//            map.put("vehicle", lcVehicle);
//            map.put("no", lcNo);
//            map.put("date2", lcDate);
//            map.put("customer", lcCustomer);
//            map.put("commodity", lcCommodity);
//            map.put("balBf", dBalance);
//            map.put("tank", tank);
//            map.put("balance", dBalance-dQty);
//            map.put("crrQty", dQty);
//            carGoOutMapper.insertCarGoOut(map);
//        }
//        String[] crrNo1Array = crrNo1.split(";");
//        double[] qtyArray = new double[crrNo1Array.length];
//        String marks = vo.getRemarks().trim();
//        for(int i=0; i<crrNo1Array.length; i++) {
//            Trust trust = trustMapper.queryTrustByCrrNo(crrNo1Array[i]);
//            if(null != trust.getBalance()) {
//                qtyArray[i] = trust.getBalance().doubleValue();
//            }else {
//                qtyArray[i] = 0.0;
//            }
//        }
//        for(int i=0; i<crrNo1Array.length; i++) {
//            if(qtyArray[i]<tBaTemp) {
//                if(StringUtils.isEmpty(remarks)) {
//                   remarks = crrNo1Array[i] + ":" + qtyArray[i] + "MT";
//                }else {
//                    remarks = remarks + ";" + crrNo1Array[i] + ":" + qtyArray[i] + "MT";
//                }
//            }
//            //else if(){
//
//            //}
//        }



        return true;
    }

    /**
     * 剔除remarks中已存在的crrNo
     * @param crrNo 控件中的crrNo
     * @param remarkCrrNo remark中的crrNo
     * @return
     */
    private String rejectRepeatCrrNo(String crrNo, String remarkCrrNo) {
        if(crrNo.equals(remarkCrrNo)) {
            return crrNo;
        }else if (crrNo.startsWith(remarkCrrNo)) {
            //remark中的crrNo正好是crrNo1中的第一条数据：111222;333;555/111222; -> 333;555
            return  crrNo.split(remarkCrrNo+";")[1];
        }else if(crrNo.endsWith(remarkCrrNo))  {
            //remark中的crrNo正好是crrNo1中的最后一条数据：111222;333;555/;555 -> 111222;333
            return crrNo.split(remarkCrrNo)[0];
        }else if(crrNo.indexOf(remarkCrrNo+";")!=-1) {
            //remark中的crrNo存在在于crrNo1中：111222;333;555/333; ->111222;555
            String[] splitCrrNo = crrNo.split(remarkCrrNo+";");
            return  splitCrrNo[0] + splitCrrNo[1];
        }
        return crrNo;
    }
}
