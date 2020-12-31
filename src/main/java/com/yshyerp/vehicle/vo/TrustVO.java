package com.yshyerp.vehicle.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 委托书VO
 */
@Data
public class TrustVO {

    //委托书号
    private String crrNo;

    //客户名称(货主)
    private String customer;

    //货品名称
    private String commodity;

    //报关数量
    private BigDecimal quantity;

    //报关日期
    private Timestamp passDate;

    //结余数量
    private BigDecimal balance;
}
