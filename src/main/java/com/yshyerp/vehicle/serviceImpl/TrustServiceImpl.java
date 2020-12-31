package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.commons.ToolUtil;
import com.yshyerp.vehicle.entity.DrumTmp;
import com.yshyerp.vehicle.entity.Trust;
import com.yshyerp.vehicle.entity.TtdcTemp;
import com.yshyerp.vehicle.mapper.TrustMapper;
import com.yshyerp.vehicle.service.DrumService;
import com.yshyerp.vehicle.service.TankService;
import com.yshyerp.vehicle.service.TrustService;
import com.yshyerp.vehicle.vo.TrustVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 委托书业务类
 */
@Service
public class TrustServiceImpl implements TrustService {

    @Autowired
    DrumService drumService;

    @Autowired
    TankService tankService;

    @Autowired
    TrustMapper trustMapper;

    /**
     * 根据货主名称和货品查询委托书信息
     * @param cCustomer 货主名称
     * @param commodity 货品名称
     * @return
     */
    @Override
    public List<TrustVO> queryTrustByCustomerAndCommodity(String cCustomer, String commodity) throws Exception{
        List<TrustVO> trustVoList = new ArrayList<TrustVO>();
        Map requestMap = new HashMap<String, Object>();
        requestMap.put("cCustomer", cCustomer);
        requestMap.put("commodity", commodity);
        //查询委托书列表
        List<Trust> trustList = trustMapper.queryTrustByCustomerAndCommodity(requestMap);
        //将查询结果转换成VO
        for(Trust trust : trustList) {
            TrustVO trustVo = new TrustVO();
            trustVo = (TrustVO)ToolUtil.doCastVo(trust, trustVo);
            trustVoList.add(trustVo);
        }
        return trustVoList;
    }

    /**
     * 委托书号校验
     * @param trust 委托书号
     * @param  cCustomer 货主
     * @param  commodity 货品
     * @param  quantity 提货数量
     * @param   status 流程状态（1提货计划 2入仓 3出仓）
     * @param netWeight 净重量
     * @return
     */
    @Override
    public String checkTrust(String trust, String cCustomer, String commodity, double quantity, String status, int netWeight) {
        String[] trustList = trust.split(";");
        double allBalance = 0.0d;
        for(String aTrust : trustList) {
            Trust thisTrust = trustMapper.queryTrustByCrrNo(aTrust);
            if(thisTrust==null) {
                return "委托书号不存在或被加锁，请查证";
            }else if(!cCustomer.equals(thisTrust.getCustomer().trim())
                    && !"MOMENTIVE".equals(cCustomer) && !"HEXION".equals(thisTrust.getCustomer())) {
                return "委托书的客户和输入的客户不一致，请查证";
            }else if(!commodity.equals(thisTrust.getCommodity().trim())) {
                return "委托书上的货物与输入的货物不一致";
            }else {
                if(trustList.length==1) {
                    //出仓时
                    allBalance = thisTrust.getBalance().doubleValue();
                    //提货计划和入仓时
                    if("1".equals(status) || "2".equals(status)) {
                        TtdcTemp ttdcTemp = tankService.queryQuanBulkByCrrNo(aTrust);
                        DrumTmp drumTmp = drumService.queryQuanDrumByCrrNo(aTrust);
                        if(null==ttdcTemp.getQuanBulk()) {
                            ttdcTemp.setQuanBulk(BigDecimal.valueOf(0));
                        }
                        if(null==drumTmp.getQuanDrum()) {
                            drumTmp.setQuanDrum(BigDecimal.valueOf(0));
                        }
                        allBalance = allBalance - ttdcTemp.getQuanBulk().doubleValue() - drumTmp.getQuanDrum().doubleValue();
                    }
                }else {
                    allBalance = allBalance + thisTrust.getBalance().doubleValue();
                }
            }
        }

        if(("1".equals(status) || "2".equals(status)) && allBalance+0.2<quantity) {
            return "委托书已不够提货";
        }else if("3".equals(status) && (allBalance<netWeight/1000)) {
            return "委托书数量不够，缺" + String.valueOf(netWeight/1000-allBalance) + "MT,请查证";
        }
        return null;
    }

    /**
     * 校验备注中的委托书号
     * @param remarks 备注
     * @param pageDoNo 委托书文本框中输入的委托书号
     * @param quantity 提货数量
     * @param tempStatus 流程状态1：提货计划 2：入仓 3出仓
     * @return
     */
    @Override
    public String checkTrustByRemarks(String remarks, String pageDoNo, double quantity, String tempStatus, int netWeight) {
        String[] remarksDoNo = remarks.split("MT");
        double sumQty1 = 0.0d;
        for(int i=0; i<remarksDoNo.length; i++) {
            remarksDoNo[i] = remarksDoNo[i].substring(remarksDoNo[i].lastIndexOf(";")+1);
            String[] doNoAndQty1 = remarksDoNo[i].split(":");
            Trust trust = trustMapper.queryTrustByCrrNo(doNoAndQty1[0]);
            if(null==trust) {
                return "没有找到委托书" + doNoAndQty1[0] + "或被加锁，请检查你的备注栏输入是否正确";
            }else if(Double.valueOf(doNoAndQty1[1])>trust.getBalance().doubleValue()) {
                return "委托书"+ doNoAndQty1[0] +"数量不够，请检查你的备注栏输入是否正确";
            }else if(pageDoNo.indexOf(doNoAndQty1[0])==-1) {
                return "备注栏输入的委托书" + doNoAndQty1[0] + "在委托书栏内没有输入，请查证";
            }
            sumQty1 = sumQty1 + Double.valueOf(doNoAndQty1[1]);
        }
        //新增提货计划和入仓时
        if(sumQty1>quantity && ("2".equals(tempStatus) || "1".equals(tempStatus))) {
            return "备注栏输入的委托书数量大于提货数量，请查证";
        }
        //出仓时
        if("3".equals(tempStatus)) {
            if(netWeight/1000<sumQty1) {
                return "你的备注栏输入的委托书数量大于实际出货量 ，请查证";
            }
        }
        return null;
    }

}
