package com.yshyerp.vehicle.mapper;

import com.yshyerp.vehicle.entity.TtdcTemp;
import com.yshyerp.vehicle.entity.Weighbridge;
import com.yshyerp.vehicle.vo.TankVehicleListVO;
import com.yshyerp.vehicle.vo.WeighbridgeVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 槽车出入仓业务类
 */
@Mapper
@Repository
public interface TankVehicleInOutMapper {

    /**
     * 查询槽车入仓列表
     * @param vo
     * @return
     */
    public List<TtdcTemp> queryTankVehicleInList(TankVehicleListVO vo);

    /**
     * 开启/关闭地磅1开关
     * @param startSwitch  0关闭1开启
     * @return
     */
    public int updStartSwitch1(String startSwitch);

    /**
     * 开启/关闭地磅2开关
     * @param startSwitch  0关闭1开启
     * @return
     */
    public int updStartSwitch2(String startSwitch);

    /**
     * 获取地磅重量
     * @return
     */
    public Weighbridge queryWeighbridge();

}
