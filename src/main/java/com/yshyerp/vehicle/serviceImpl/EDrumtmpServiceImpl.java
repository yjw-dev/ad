package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.entity.*;
import com.yshyerp.vehicle.mapper.DraMapper;
import com.yshyerp.vehicle.mapper.EDrumtmpMapper;
import com.yshyerp.vehicle.mapper.EdrumsMapper;
import com.yshyerp.vehicle.mapper.EdsRepoMapper;
import com.yshyerp.vehicle.service.EDrumtmpService;
import com.yshyerp.vehicle.service.SysDataService;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.omg.DynamicAny.DynArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EDrumtmpServiceImpl implements EDrumtmpService {

    @Autowired
    EDrumtmpMapper eDrumtmpMapper;

    @Autowired
    EdrumsMapper edrumsMapper;

    @Autowired
    DraMapper draMapper;

    @Autowired
    EdsRepoMapper edsRepoMapper;


    @Autowired
    SysDataService sysDataService;


    @Override
    public List<EDrumtmp> alist() {
        List<EDrumtmp> eDrumtmpList = eDrumtmpMapper.alist();

        return eDrumtmpList;

    }

    //查询
    @Override
    public List<EDrumtmp> elist(EDrumtmp eDrumtmp) {
        List<EDrumtmp> eDrumtmpList = eDrumtmpMapper.elist(eDrumtmp);

        return eDrumtmpList;
    }





    //  新增  空桶临时入仓表
    @Override
    public int insertSelective(EDrumtmp record)  {
        EDrumtmp eDrumtmp = new EDrumtmp();
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        System.out.println(ts);
//        String strDate = dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss SSS");
//        eDrumtmp.setDate(new Date());
        System.out.println(record.getCustomer()+"000000000000000000000000000000000000");
        eDrumtmpMapper.insertSelective(record);
//
//        //新增空桶库存 e_drums表
//        Edrums edrums = new Edrums();
//        edrums.setCustomer(record.getCustomer());
//        edrums.setScustomer(record.getsCustomer());
//        edrums.setDate(new Date());
//        edrums.setDate1(new Date());
//        edrums.setN(record.getN());
//        edrums.setColor(record.getColor());
//        edrums.setTare(record.getTare());
//        edrums.setCover(record.getCover());
//        int getDrums = record.getDrums();
//        edrums.setBalance(getDrums);
//        edrums.setCharge(false);
//        edrumsMapper.insEdrums(edrums);
//        edrumsMapper.updedrums(edrums);
//
//
//        try {
//            //生成drano
//            SysData sysData = sysDataService.querySysData();
//            String drano = sysDataService.getSysDataId(sysData, "dra_no");
//            //新增空桶操作表  dra空桶 表
//            Dra dra = new Dra();
//            dra.setDraNo(drano);
//            dra.setIdno(record.getIdno());
//            dra.setJobno(record.getJobno());
//            dra.setJobno(record.getJobno());
//            dra.setPlanNo(record.getPlanNo());
//            dra.setCustomer(record.getCustomer());
//            dra.setsCustomer(record.getsCustomer());
//            dra.setBillid(record.getBillid());
//            dra.setDate(record.getDate());
//            dra.setVehicle(record.getVehicle());
//            dra.setVehicle1(record.getVehicle1());
//            dra.setN(record.getN());
//            dra.setColor(record.getColor());
//            dra.setTare(record.getTare());
//            dra.setCover(record.getCover());
//            dra.setDrums(record.getDrums());
//            dra.setCome(record.getCome());
//            dra.setOper("");
//            //入仓时间
//            dra.setTime1(record.getTime1());
//            ///出仓时间
//            dra.setTime2(record.getTime2());
//            dra.setWeight1(record.getWeight1());
//            //第二次称重
//            dra.setWeight2(0);
//            dra.setCharge(record.getCharge());
//            dra.setOriginal(record.getOriginal());
//            dra.setFaxno(record.getFaxno());
//            dra.setRemarks(record.getRemarks());
//
//            draMapper.insertSelective(dra);
//
//
//        Map map = new HashMap<String, Object>();
//        map.put("customer", record.getCustomer() );
//        map.put("scustomer",record.getsCustomer() );
//        map.put("color",record.getColor());
//        map.put("cover",record.getCover());
//        map.put("tare",record.getTare());
//        map.put("n",record.getN());
//        map.put("charge",record.getCharge());
//        Edrums edrums1=edrumsMapper.mapedurms(map);
//
//        Integer BalBf= edrums1.getBalance()-record.getDrums();
//
//        //存入     入库过程   空桶过程 eds_repo 表
//        EdsRepo po=new EdsRepo();
//        po.setCustomer(record.getCustomer());
//        po.setsCustomer(edrums1.getScustomer());
//        po.setColor(record.getColor());
//        po.setN(record.getN());
//
//        System.out.println(record.getDrums()+":"+edrums1.getBalance());
//        System.out.println("前结存 ::::::"+BalBf);
//        po.setBalBf(BalBf);
//        String draa= dra.getDraNo();
//        String  dra2="DRA"+draa;
//        po.setNo(dra2);//record.getDraNo()
//        po.setDate(new Date());
//        po.setTare(edrums1.getTare());
//        po.setIn(record.getDrums());
//        System.out.println("in:"+po.getIn());
//        int bal=BalBf+po.getIn();
//        po.setBalance(bal);
//        po.setCharge(edrums1.getCharge());
//        po.setCover(edrums1.getCover());
//        short a=0;
//        po.setOut(a);
//        po.setRemarks("");
//        edsRepoMapper.insert(po);
//        }catch (Exception e){
//
//        }
        return 0;

    }

    @Override
    public int insertSelective1(EDrumtmp record) {
        return  eDrumtmpMapper.insertSelective(record);
    }

    //根据单号jobon  逻辑删除
    @Override
    public int updedrumtmp(String jobno) {
        return eDrumtmpMapper.updeletmp(jobno);
    }

    @Override
    public int updeletmpdate(Timestamp date) {
        return eDrumtmpMapper.updeletmpdate(date);
    }

    @Override
    public EDrumtmp timestamp(Timestamp date) {
        return eDrumtmpMapper.timestamp(date);
    }


}