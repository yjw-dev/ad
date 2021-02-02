package com.yshyerp.vehicle.service;

import com.yshyerp.vehicle.entity.Ttdc1;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface Ttdc1Service {

        public List<Ttdc1> ttdclist(String dono);

}
