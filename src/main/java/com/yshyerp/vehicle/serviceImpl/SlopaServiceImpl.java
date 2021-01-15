package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.entity.Slop;
import com.yshyerp.vehicle.mapper.SlopaMapper;
import com.yshyerp.vehicle.service.SlopaService;
import com.yshyerp.vehicle.vo.DrumslopVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlopaServiceImpl implements SlopaService {

    @Autowired
   SlopaMapper slopaMapper;



    //根据  灌号和货主查询
    public List<Slop> slopmap(DrumslopVo drumslopVo){
        //查询委托书列表
        List<Slop> slopList = slopaMapper.slopmap(drumslopVo);
        return slopList;


    }


    


}