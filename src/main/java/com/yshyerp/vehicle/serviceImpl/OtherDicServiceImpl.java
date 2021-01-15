package com.yshyerp.vehicle.serviceImpl;


import com.yshyerp.vehicle.entity.OtherDict;
import com.yshyerp.vehicle.mapper.OtherDictMapper;
import com.yshyerp.vehicle.service.OtherDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OtherDicServiceImpl implements OtherDicService {
    @Autowired
    OtherDictMapper otherDictMapper;

    //查询全部
   public List<OtherDict> OtherDiclist(){
        List<OtherDict> otherDictlist=otherDictMapper.OtherDictlist();

        return otherDictlist;
    }

    @Override
    public List<OtherDict> Othertype() {
       List<OtherDict> othertype=otherDictMapper.Othertype();


        return othertype;
    }

    @Override
    public List<OtherDict> Othercode() {
        List<OtherDict> othercode=otherDictMapper.Othercode();

        return othercode;
    }


}
