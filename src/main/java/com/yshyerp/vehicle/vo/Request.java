package com.yshyerp.vehicle.vo;

import lombok.Data;

@Data
public class Request<T>{

    //请求主体
    private T data;

    //分页（当前页）
    private Integer pageNum;

    //分页（页大小）
    private Integer pageSize;

    //系统来源
    private String sysSource;

}
