package com.yshyerp.vehicle.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 打印货品
 */
@Data
public class TankC implements Serializable {

    //id
    private Integer id;

    //删除标志
    private String d;

    //罐号
    private String tank;

    //打印货品名称
    private String comm1;

    private String key;

    private String label;

    private static final long serialVersionUID = 1L;
}
