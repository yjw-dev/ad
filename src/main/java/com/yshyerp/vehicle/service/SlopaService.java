package com.yshyerp.vehicle.service;

import com.yshyerp.vehicle.entity.Slop;
import com.yshyerp.vehicle.vo.DrumslopVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface SlopaService {




    //根据  灌号和货主查询
    public List<Slop> slopmap(DrumslopVo drumslopVo);




}