package com.yshyerp.vehicle.service;

import com.yshyerp.vehicle.entity.TtdcTemp;
import com.yshyerp.vehicle.vo.TankVehicleListVO;
import com.yshyerp.vehicle.vo.WarehousingVO;
import com.yshyerp.vehicle.vo.WeighbridgeVO;

import java.sql.Timestamp;
import java.util.List;

/**
 * 槽车出入仓业务类
 */
public interface TankVehicleInOutService {

    /**
     * 入仓列表查询
     * @param vo 查询条件
     * @return
     */
    public List<TtdcTemp> queryTankVehicleList(TankVehicleListVO vo) throws Exception;

    /**
     * 设置磅秤开启关闭
     * @param startSwitch 开关 0关1开
     * @param  ipAddress 地磅ip
     * @return
     */
    public String updStartSwitch(String startSwitch, String ipAddress);

    /**
     * 称重
     * @param ipAddress 地磅ip
     * @return
     */
    public WeighbridgeVO queryWeighbridge(String ipAddress);

    /**
     * 获取装车台
     * @param tank 储罐号
     * @return
     */
    public String queryLcstation(String tank);

    /**
     * 保存入仓信息
     * @param vo
     * @return
     */
    public void saveTtdcTemp(WarehousingVO vo);

    /**
     * 根据jobNo查询入仓明细
     * @param jobNo
     * @return
     */
    public WarehousingVO queryTtdcTempDetail(String jobNo);

    /**
     * 保存出仓信息相关业务
     * @param vo
     * @return
     */
    public boolean saveOutWarehouse(WarehousingVO vo);

}
