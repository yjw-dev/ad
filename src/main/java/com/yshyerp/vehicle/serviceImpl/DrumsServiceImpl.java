package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.entity.DrumTmp;
import com.yshyerp.vehicle.entity.Drums;
import com.yshyerp.vehicle.entity.Trust;
import com.yshyerp.vehicle.mapper.DrumsMapper;
import com.yshyerp.vehicle.service.DrumService;
import com.yshyerp.vehicle.service.DrumsService;
import com.yshyerp.vehicle.vo.DrumslopVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DrumsServiceImpl implements DrumsService {
    @Autowired
    DrumsMapper drumsMapper;

    //根据  灌号和货主查询


    @Override
    public List<Drums> deumsmap(DrumslopVo drumslopVo) {
        //查询委托书列表
        List<Drums> drumsList = drumsMapper.deumsmap(drumslopVo);
        return drumsList;
    }


    //根据  货主获取小客户    新增页面使用******
    @Override
    public List<Drums> scustomer(String scustomer) {

        List<Drums> drumsList=drumsMapper.scustomer(scustomer);


        return drumsList;
    }

    @Override
    public Drums drumsch(Map map) {
        Drums drum=drumsMapper.drumsch(map);

        return drum;
    }
}