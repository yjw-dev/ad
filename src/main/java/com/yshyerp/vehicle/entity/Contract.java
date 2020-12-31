package com.yshyerp.vehicle.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 合同
 */
@Data
public class Contract implements Serializable {

    private Integer id;
    //合同号
    private String contractI;
    //删除标志
    private String d;
    //罐主
    private String customer;
    //合同生效时间
    private Date stDate;
    //合同结束时间
    private Date enDate;

    private Date chkDate;

    private String conLong;
    //罐号
    private String tank;
    //货品
    private String commodity;

    private BigDecimal minInput;

    private String key;

    private String lable;

    private static final long serialVersionUID = 1L;
}
