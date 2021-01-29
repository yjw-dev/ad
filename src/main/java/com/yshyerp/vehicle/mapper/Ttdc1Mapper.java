package com.yshyerp.vehicle.mapper;

import com.yshyerp.vehicle.entity.Ttdc1;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Ttdc1Mapper {

        public List<Ttdc1> ttdclist(String dono);

}
