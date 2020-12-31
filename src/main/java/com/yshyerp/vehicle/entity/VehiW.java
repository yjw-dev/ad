package com.yshyerp.vehicle.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 车辆
 */
@Data
public class VehiW implements Serializable {

    //车牌号
    private String vehicle;

    //挂车号
    private String vehicle1;

    //核定载重
    private BigDecimal vehiW;

    //容积
    private BigDecimal vehiV;

    //首次称重
    private Integer weight1;

    //工作单号
    private String jobno;

    private static final long serialVersionUID = 1L;

}