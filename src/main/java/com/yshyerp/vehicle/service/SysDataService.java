package com.yshyerp.vehicle.service;

import com.yshyerp.vehicle.entity.SysData;

/**
 * 系统配置类
 */
public interface SysDataService {

    /**
     * 获取Id
     * @param sysData
     * @param idName
     * @return
     */
    public String getSysDataId(SysData sysData, String idName) throws Exception;

    /**
     * 查询sysData表
     * @return
     */
    public SysData querySysData();

    /**
     * 更新id
     * @param planNo
     * @return
     */
    public int updSysDataIdToPlanNo(String planNo);

    /**
     * 更新id
     * @param jobNo
     * @return
     */
    public int updSysDataIdToJobNo(String jobNo);

    /**
     * 更新id
     * @param draNo
     * @return
     */
    public int updSysDataIdToDraNo(String draNo);
}
