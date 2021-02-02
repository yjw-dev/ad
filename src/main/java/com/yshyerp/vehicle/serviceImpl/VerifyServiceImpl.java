package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.entity.*;
import com.yshyerp.vehicle.mapper.*;
import com.yshyerp.vehicle.service.VerifyService;
import org.apache.tomcat.util.security.Escape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

//校验
@Service
public class VerifyServiceImpl implements VerifyService {

    @Autowired
    Ddc1Mapper ddc1Mapper;
    @Autowired
    Ttdc1Mapper ttdc1Mapper;
    @Autowired
    DrmFifoMapper drmFifoMapper;
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    CommodityMapper commodityMapper;
    @Autowired
    TranEMapper tranEMapper;
    @Autowired
    SlopaMapper slopaMapper;
    @Autowired
    DrumsMapper drumsMapper;
    @Autowired
    EdrumsMapper edrumsMapper;

    public String wtsjyan(String dono, String ccustomer) {
        List<Ddc1> list = ddc1Mapper.queryDdcByDoNo(dono);
        if (null != list && list.size() > 1) {
            return "提货单号重复，此提货单提过桶装货，单号" + list.get(0).getDoNo() + "";
        }
        List<Ttdc1> list1 = ttdc1Mapper.ttdclist(dono);
        if (null != list1 && list1.size() > 1) {
            return "提货单号重复，此提货单提过散装货，单号" + list1.get(0).getDoNo() + "";
        }
        List<DrmFifo> list2 = drmFifoMapper.queryDrmFifoByDoNo(dono);
        if (null != list2 && list2.size() > 1) {
            return "提货单号重复，此提货单提过即灌即出，单号" + list2.get(0).getDoNo() + "";
        }
        List<Customer> list3 = customerMapper.getCustomerByCustomerName(ccustomer);
        if (null == list3) {
            return "无此货主！，请查证";
        }
        return null;
    }

    @Override
    public String huochujyan(String commodity, String status, String nameyy) {
        List<Commodity> list = commodityMapper.queryCommodityByCommodity(commodity);
        if (null == list) {
            return "无此货品！，请查证！";
        }
        if (status == "2") {
            List<TranE> tranE = tranEMapper.list(nameyy);
            if (null == tranE) {
                return "该企业未在允许的危险品运输企业中";
            }
            return "本车辆装载危险货品，请不要忘记扫描证件";
        } else if (status == "3") {

        }


        return null;
    }

    @Override
    public String balancejyan(String balance, String combo1, Map map, Map map1, Map map2,Short drums) {
        if (combo1 == "SLOP") {
            Slop slop = slopaMapper.slopmapcs(map);
            if (null == slop) {
                return "输入条件的废油不存在,此单输入无效 ";
            }
            if (slop.getBalance() < drums) {
                return "可提货数量少于要提的数量,此单输入无效";
            }
        }
        if (combo1=="NORMAL"){
          Drums drums1=drumsMapper.drumsch(map1);
          if (null==drums1){
              return "输入条件的桶装货物不存在,此单输入无效";
          }else if (drums1.getLock()){
              return "该类型货物已经被锁定,请联系操作工程师查询原因";
          }else if (drums1.getBalance()<drums){
              return "可提货数量少于要提的数量,此单输入无效";
          }
        }
        if (combo1=="EMPTY"){
            Edrums edrums=edrumsMapper.mapedurms(map2);
            if (null==edrums){
                return "输入条件的空桶不存在,此单输入无效";
            }else if (edrums.getBalance()<drums){
                return "可提货数量少于要提的数量,此单输入无效";
            }

        }


             return null;
    }


}
