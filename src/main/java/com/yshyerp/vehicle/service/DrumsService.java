package com.yshyerp.vehicle.service;

import com.yshyerp.vehicle.entity.Drums;
import com.yshyerp.vehicle.vo.DrumslopVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface DrumsService {

    //根据  灌号和货主查询
    public List<Drums> deumsmap(DrumslopVo drumslopVo);



}