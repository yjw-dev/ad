package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.entity.Edrums;
import com.yshyerp.vehicle.mapper.EdrumsMapper;
import com.yshyerp.vehicle.service.EdrumsService;
import com.yshyerp.vehicle.vo.DjobVo;
import com.yshyerp.vehicle.vo.DjobVo1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class EdrumsServiceImpl implements EdrumsService {

    @Autowired
    EdrumsMapper edrumsMapper;
    @Override
    public List<Edrums> slist() {
        List<Edrums> edrumsList=edrumsMapper.slist();

        return edrumsList;
    }

    @Override
    public List<Edrums> Bycustomer(DjobVo djobVo) {
        List<Edrums> edrumsList=edrumsMapper.Bycustomer(djobVo);
        return edrumsList;
    }

    @Override
    public int insEdrums(Edrums record) {
        Edrums edrums=new Edrums();
        edrums.setDate(new Date());
        return edrumsMapper.insEdrums(record);
    }

    @Override
    public int updedrums(Edrums edrums) {

        return edrumsMapper.updedrums(edrums);
    }
}
