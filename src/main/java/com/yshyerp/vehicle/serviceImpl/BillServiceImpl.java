package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.entity.*;
import com.yshyerp.vehicle.mapper.*;
import com.yshyerp.vehicle.service.BillService;
import com.yshyerp.vehicle.service.DrumService;
import com.yshyerp.vehicle.service.TankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * 提单业务类
 */
@Service
public class BillServiceImpl implements BillService {

    @Autowired
    DrumService drumService;

    @Autowired
    TankService tankService;

    @Autowired
    BillMapper billMapper;

    @Autowired
    DdcMapper ddcMapper;

    @Autowired
    TtdcMapper ttdcMapper;

    @Autowired
    TtdcTempMapper ttdcTempMapper;

    @Autowired
    DrmFifoMapper drmFifoMapper;

    /**
     * 提单号校验(大提单)
     * @param doNo 提单号
     * @param  cCustomer 客户
     * @param  commodity 货品
     * @param  quantity 提货数量
     * @param  status 流程状态(1提货计划 2入仓)
     * @param  netWeight 净重，出仓时使用
     * @return
     */
    public String checkBigBill(String doNo, String cCustomer, String commodity, double quantity, int status, int netWeight) {
        String[] doNoList = doNo.split(";");
        double allBalance = 0.0d;
        for(String aDoNo : doNoList) {
            Bdo thisBdo = billMapper.queryBdoByDoNo(aDoNo);
            if(null==thisBdo) {
                return "大提单号不存在";
            }else {
                Timestamp vDate = thisBdo.getVDate();
                Timestamp nowDate = new Timestamp(System.currentTimeMillis());
                if(vDate.after(nowDate)) {
                    return "大提单" + aDoNo + "已过有效期";
                }else if(!cCustomer.equals(thisBdo.getCustomer())) {
                    return "大提单的客户和输入的客户不一致";
                }else if(!commodity.equals(thisBdo.getCommodity())) {
                    return "大提单上的货物与输入的货物不一致";
                }
            }
            if(doNoList.length==1) {
                //出仓时
                allBalance = thisBdo.getBalance().doubleValue()/1000;
                //新增提货计划和入仓时
                if(1==status || 2 == status) {
                    TtdcTemp ttdcTemp = tankService.queryQuanBulkByDoNo(aDoNo);
                    DrumTmp drumTmp = drumService.queryQuanDrumByDoNo(aDoNo);
                    if (null == ttdcTemp.getQuanBulk()) {
                        ttdcTemp.setQuanBulk(BigDecimal.valueOf(0));
                    }
                    if (null == drumTmp.getQuanDrum()) {
                        drumTmp.setQuanDrum(BigDecimal.valueOf(0));
                    }
                    allBalance = allBalance - ttdcTemp.getQuanBulk().doubleValue() - drumTmp.getQuanDrum().doubleValue();
                }
            }else {
                allBalance = allBalance + thisBdo.getBalance().doubleValue();
            }
        }

        if((1==status || 2==status) && allBalance<quantity) {
            allBalance += 0.1;
            return "大提单已不够提货";
        }else if(3==status){
            if(allBalance<(netWeight/1000)) {
                return "大提单数量不够，缺" + String.valueOf(netWeight/1000-allBalance) + "MT,请查证!";
            }
        }
        return null;
    }

    /**
     * 提单号校验(非大提单)
     * @param doNo 提单号
     * @param status 流程状态（1提货计划 2入仓 3出仓）
     * @return
     */
    public String checkBill(String doNo, String status) {
        Bdo thisBdo = billMapper.queryBdoByDoNo(doNo);
        if(null!=thisBdo) {
            return "该提货单号为大提单";
        }else {
            List<Ddc> ddcList = ddcMapper.queryDdcByDoNo(doNo);
            if(null!=ddcList && ddcList.size()>0) {
                return "提货单号重复，此提货单提过桶装货。单号：" + ddcList.get(0).getDdcNo();
            }
            List<Ttdc> ttdcList = ttdcMapper.queryTtdcByDoNo(doNo);
            if(null!=ttdcList && ttdcList.size()>0) {
                return "提货单号重复，此提货单提过散装货。单号：" + ttdcList.get(0).getTtdcNo();
            }
            if(!status.equals("3")) {
                List<TtdcTemp> ttdcTempList = ttdcTempMapper.queryTtdcTempByDoNo(doNo);
                if(null!=ttdcTempList && ttdcTempList.size()>0) {
                    return "提货单号重复，此提货单已经被仓内槽车使用，工作单号：" + ttdcTempList.get(0).getJobNo();
                }
            }
            List<DrmFifo> drmFifoList = drmFifoMapper.queryDrmFifoByDoNo(doNo);
            if(null!=drmFifoList && drmFifoList.size()>0) {
                return "提货单号重复，此提货单提过即灌即出。单号："+ drmFifoList.get(0).getDoNo();
            }
        }
        return null;
    }
}
