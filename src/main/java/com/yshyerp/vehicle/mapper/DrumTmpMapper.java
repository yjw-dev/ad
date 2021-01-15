package com.yshyerp.vehicle.mapper;

import com.yshyerp.vehicle.entity.DrumTmp;
import com.yshyerp.vehicle.vo.DrumTmpVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 桶装出货临时记录表
 */
@Mapper
@Repository
public interface DrumTmpMapper {

    /**
     * 根据委托书号查询桶装出货临时记录表
     * @param crrNo 委托书号
     * @return
     */
    public DrumTmp queryQuanDrumByCrrNo(String crrNo);

    /**
     * 根据提单号查询桶装出货临时记录表
     * @param doNo 提单号
     * @return
     */
    public DrumTmp queryQuanDrumByDoNo(String doNo);

    int insert(DrumTmpVo record);



}
