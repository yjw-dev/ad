package com.yshyerp.vehicle.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 槽车准牵引重量
 */
@Data
public class VehiW2 implements Serializable {

    //车牌号
    private String vehicle;

    //准牵引重量
    private BigDecimal vehiW2;

    private static final long serialVersionUID = 1L;
}
