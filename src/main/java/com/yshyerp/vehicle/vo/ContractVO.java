package com.yshyerp.vehicle.vo;

import lombok.Data;

import java.util.Date;

/**
 * 合同VO
 */
@Data
public class ContractVO {

    private String customer;

    //合同号
    private String contractI;

    //合同结束日期
    private Date enDate;

    //罐号
    private String tank;

    //id
    private Integer id;

    private String key;

    private String lable;
}
