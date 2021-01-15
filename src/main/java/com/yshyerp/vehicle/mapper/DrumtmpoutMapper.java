package com.yshyerp.vehicle.mapper;

import com.yshyerp.vehicle.entity.Drumtmpout;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DrumtmpoutMapper {


    List<Drumtmpout> listdrum();

    int insert(Drumtmpout record);



    int insertSelective(Drumtmpout record);


}
