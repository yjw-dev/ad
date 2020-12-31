package com.yshyerp.vehicle.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 车辆VO
 */
@Data
public class VehicleNoVO {

    //车牌号码
    private String txtVehicle;

    //挂车号
    private String txtVehicle1;

    //准牵引重量
    private BigDecimal txtVehiW2;

    //核定载重
    private BigDecimal txtVehiW;

    //挂车重量
    private BigDecimal txtVehiW3;
}
