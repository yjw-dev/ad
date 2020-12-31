package com.yshyerp.vehicle.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 箱
 */
@Data
public class CnoV implements Serializable {

    //箱号
    private String cNo;

    //容积
    private BigDecimal vehiV;

    //箱重
    private BigDecimal vehiW;

    //工作单号
    private String jobno;

    private static final long serialVersionUID = 1L;
}
