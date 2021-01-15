package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.entity.Dra;
import com.yshyerp.vehicle.entity.EDrumtmp;
import com.yshyerp.vehicle.entity.Edrums;
import com.yshyerp.vehicle.entity.EdsRepo;
import com.yshyerp.vehicle.mapper.DraMapper;
import com.yshyerp.vehicle.mapper.EDrumtmpMapper;
import com.yshyerp.vehicle.mapper.EdrumsMapper;
import com.yshyerp.vehicle.mapper.EdsRepoMapper;
import com.yshyerp.vehicle.service.EDrumtmpService;
import org.omg.DynamicAny.DynArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
    public int insertSelective(EDrumtmp record) {

        EDrumtmp eDrumtmp = new EDrumtmp();
        eDrumtmp.setDate(new Date());

        eDrumtmpMapper.insertSelective(record);

        //新增空桶库存 e_drums表
        Edrums edrums = new Edrums();
        edrums.setCustomer(record.getCustomer());
        edrums.setScustomer(record.getsCustomer());
        edrums.setDate(record.getDate());
        edrums.setDate1(new Date());
        edrums.setN(record.getN());
        edrums.setColor(record.getColor());
        edrums.setTare(record.getTare());
        edrums.setCover(record.getCover());
        int getDrums = record.getDrums();
        edrums.setBalance(getDrums);
        edrums.setCharge(false);
        edrumsMapper.insEdrums(edrums);


        edrumsMapper.updedrums(edrums);


        //新增空桶操作表  dra空桶 表
        Dra dra = new Dra();
        dra.setIdno(record.getIdno());
        dra.setJobno(record.getJobno());
        dra.setJobno(record.getJobno());
        dra.setPlanNo(record.getPlanNo());
        dra.setCustomer(record.getCustomer());
        dra.setsCustomer(record.getsCustomer());
        dra.setBillid(record.getBillid());
        dra.setDate(record.getDate());
        dra.setVehicle(record.getVehicle());
        dra.setVehicle1(record.getVehicle1());
        dra.setN(record.getN());
        dra.setColor(record.getColor());
        dra.setTare(record.getTare());
        dra.setCover(record.getCover());
        dra.setDrums(record.getDrums());
        dra.setCome(record.getCome());
        dra.setOper("");
        //入仓时间
        dra.setTime1(record.getTime1());
        ///出仓时间
        dra.setTime2(record.getTime2());
        dra.setWeight1(record.getWeight1());
        //第二次称重
        dra.setWeight2(0);
        dra.setCharge(record.getCharge());
        dra.setOriginal(record.getOriginal());
        dra.setFaxno(record.getFaxno());
        dra.setRemarks(record.getRemarks());
        draMapper.insertSelective(dra);

        //存入     入库过程   空桶过程 eds_repo 表
        EdsRepo po=new EdsRepo();
        po.setCustomer(record.getCustomer());
        po.setsCustomer(edrums.getScustomer());
        po.setColor(record.getColor());
        po.setN(record.getN());


        Integer BalBf= edrums.getBalance()-record.getDrums();
        System.out.println(record.getDrums()+":"+edrums.getBalance());
        System.out.println("前结存 ::::::"+BalBf);
        po.setBalBf(BalBf);


        po.setNo(" ");//record.getDraNo()
        po.setDate(new Date());
        po.setTare(edrums.getTare());
        po.setIn(record.getDrums());
        System.out.println("in:"+po.getIn());
        int bal=BalBf+po.getIn();
        po.setBalance(bal);

        po.setCharge(edrums.getCharge());
        po.setCover(edrums.getCover());


        short a=0;
        po.setOut(a);
        po.setRemarks(" ");
        edsRepoMapper.insert(po);


        return 0;
    }

    //根据单号jobon  逻辑删除
    @Override
    public int updedrumtmp(String jobno) {
        return eDrumtmpMapper.updeletmp(jobno);
    }


}