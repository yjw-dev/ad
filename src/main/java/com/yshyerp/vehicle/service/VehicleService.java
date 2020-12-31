package com.yshyerp.vehicle.service;


import com.yshyerp.vehicle.vo.TxtcVO;
import com.yshyerp.vehicle.vo.VehicleNoVO;

/**
 * 车辆业务类
 */
public interface VehicleService {

    /**
     * 根据车牌号、挂车号查询车辆信息
     * @param vo
     * @return
     */
    public VehicleNoVO getVehicleDetailByVehicleAndVehicle1(VehicleNoVO vo);

    /**
     * 根据箱号查询容积、箱重
     * @param txtcNo 箱号
     * @return
     */
    public TxtcVO getTxtcNoDetail(String txtcNo);

    /**
     * 校验车牌号是否已进入出入仓业务
     * @param vehicle
     * @return
     */
    public Boolean checkVehicleIsNull(String vehicle);

}
