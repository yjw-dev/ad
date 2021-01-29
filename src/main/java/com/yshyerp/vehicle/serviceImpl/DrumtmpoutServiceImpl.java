package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.entity.*;
import com.yshyerp.vehicle.mapper.*;
import com.yshyerp.vehicle.service.DrumtmpoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DrumtmpoutServiceImpl implements DrumtmpoutService {

    @Autowired
    DrumtmpoutMapper drumtmpoutMapper;
    @Autowired
    TankCMapper tankCMapper;
    @Autowired
    Ddc1Mapper ddc1Mapper;
    @Autowired
    Ttdc1Mapper ttdc1Mapper;
    @Autowired
    DrmFifoMapper drmFifoMapper;


    //查询
    public List<Drumtmpout> listdrum() {
        List<Drumtmpout> drumtmpoutList = drumtmpoutMapper.listdrum();
        return drumtmpoutList;
    }

    @Override
    public Drumtmpout listdrumid(String planNo) {
        Drumtmpout  drumtmpout = drumtmpoutMapper.listdrumid(planNo);
        return drumtmpout;
    }


    //新增
    public int insert(Drumtmpout record) {
      int a=drumtmpoutMapper.insert(record);
      return a;
    }

    @Override
    public Drumtmpout getcoun(String vehicle) {
        Drumtmpout drumtmpout=drumtmpoutMapper.getcoun(vehicle);
        return drumtmpout;
    }

    //根据  灌号tank 获取    comm1      新增加入***
    @Override
    public List<TankC> queryTankCListByTank(String tank) {
        List<TankC> tankCList=tankCMapper.queryTankCListByTank(tank);
        if (tankCList==null){

        }
        return tankCList;
    }


    //修改
    @Override
    public int update(Drumtmpout record) {
        //获取修改的数据
        Drumtmpout drumtmpout=drumtmpoutMapper.listdrumid(record.getPlanNo());
        int a=drumtmpoutMapper.update(drumtmpout);
        return a;
    }

    @Override
    public int beiyong(Drumtmpout record) {
        int a=drumtmpoutMapper.beiyong(record);
        return a;
    }

    //根据车牌号  逻辑删除
    @Override
    public int updateDd(String vehicle) {
        int a=drumtmpoutMapper.updateDd(vehicle);
        return a;
    }


     public  int insertSelective(Drumtmpout record){
        int a=drumtmpoutMapper.insertSelective(record);
        return a;
    }

     //判断
    public String  a(String dono){
        // 根据提单号查询 判断提货  单号是否重复
        List<Ddc1> ddc1List=ddc1Mapper.queryDdcByDoNo(dono);
        if (ddc1List.size()>0){
            return "提货单号重复，此提货单提过桶装货，单号"+ddc1List.get(0).getDoNo()+"提示";
        }
        List<Ttdc1> ttdc1s=ttdc1Mapper.ttdclist(dono);
        if (ttdc1s.size()>0){
            return "提货单号重复,此提货单提过散装货，，单号"+ddc1List.get(0).getDoNo()+"提示";
        }
         List<DrmFifo> queryDrmFifoByDoNo=drmFifoMapper.queryDrmFifoByDoNo(dono);
        if (queryDrmFifoByDoNo.size()>0){
            return "提货单号重复，此提货单提过即灌即出，单号"+ddc1List.get(0).getDoNo()+"提示";
        }

        return null;

    }

    @Override
    public int updachushan(Map map) {
        int a=drumtmpoutMapper.updachushan(map);
        return a;
    }


}
