package com.yshyerp.vehicle.mapper;

import com.yshyerp.vehicle.entity.SysData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 系统配置表
 */
@Repository
@Mapper
public interface SysDataMapper {

    /**
     * 查询系统配置表
     * @return
     */
    public SysData querySysData();

    /**
     * 更新Id
     * @param planNo
     * @return
     */
    public int updSysDataIdToPlanNo(String planNo);

    /**
     * 更新Id
     * @param jobNo
     * @return
     */
    public int updSysDataIdToJobNo(String jobNo);
}
