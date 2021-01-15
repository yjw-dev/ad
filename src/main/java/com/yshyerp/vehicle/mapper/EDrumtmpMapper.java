package com.yshyerp.vehicle.mapper;

import com.yshyerp.vehicle.entity.EDrumtmp;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EDrumtmpMapper {

    //查询全部
    public List<EDrumtmp> alist();

    //查询
    public List<EDrumtmp> elist(EDrumtmp eDrumtmp);

    //新增
    int insertSelective(EDrumtmp record);


    //根据单号jobon  逻辑删除
    int updeletmp(String jobno);


}