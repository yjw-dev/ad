package com.yshyerp.vehicle.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 储罐VO
 */
@Data
public class TankInfoVO {

    //罐号
    private String tank;

    //罐主
    private String tCustomer;

    //货主
    private String cCustomer;

    //货品
    private String commodity;

    //数量(公斤)
    private Integer quantity;

    //可出货数量(公斤) quantity-q1
    private BigDecimal qDifference;

    //最低发油量
    private BigDecimal lowestVol;
}

