package com.yshyerp.vehicle.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 挂车重量记录
 */
@Data
public class VehiW3 implements Serializable {

    //挂车号
    private String vehicle1;

    //挂车重量
    private BigDecimal vehiW3;


    private static final long serialVersionUID = 1L;
}
