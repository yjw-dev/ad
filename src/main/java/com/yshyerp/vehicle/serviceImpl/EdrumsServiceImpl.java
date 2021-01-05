package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.entity.Edrums;
import com.yshyerp.vehicle.mapper.EdrumsMapper;
import com.yshyerp.vehicle.service.EdrumsService;
import com.yshyerp.vehicle.vo.DjobVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EdrumsServiceImpl implements EdrumsService {

    @Autowired
    EdrumsMapper edrumsMapper;
    @Override
    public List<Edrums> slist(String customer) {
        List<Edrums> edrumsList=edrumsMapper.slist(customer);

        return edrumsList;
    }

    @Override
    public List<Edrums> Bycustomer(DjobVo djobVo) {
        List<Edrums> edrumsList=edrumsMapper.Bycustomer(djobVo);
        return edrumsList;
    }
}
