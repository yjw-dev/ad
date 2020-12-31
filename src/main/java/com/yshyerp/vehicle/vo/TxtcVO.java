package com.yshyerp.vehicle.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 箱VO
 */
@Data
public class TxtcVO {
    //箱号
    private String cNo;

    //容积
    private BigDecimal vehiV;

    //箱重
    private BigDecimal vehiW;
}
