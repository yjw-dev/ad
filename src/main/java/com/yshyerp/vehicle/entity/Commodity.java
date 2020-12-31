package com.yshyerp.vehicle.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 货品
 */
@Data
public class Commodity {
    //代码
    private String code;
    //货品名称
    private String commodity;
    //货品全称
    private String fullName;
    //中文名称
    private String cName;
    //密度
    private BigDecimal density;

    private String commCode;
    //删除标志
    private String d;
    //是否危险品
    private Boolean wx;
    //序号
    private int id;
    //优先级序号
    private BigDecimal order1;
    //闪点
    private BigDecimal flash;

    private String nature;
}
