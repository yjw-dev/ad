package com.yshyerp.vehicle.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Weighbridge {
    //地磅1重量
    private BigDecimal weight1;
    //地磅1状态
    private String st1;
    //地磅2重量
    private BigDecimal weight2;
    //地磅2状态
    private String st2;

    private Integer id;
}


