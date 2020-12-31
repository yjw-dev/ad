package com.yshyerp.vehicle.vo;

import lombok.Data;

@Data
public class Response<T> {
    //返回信息
    private String message;

    //返回主体
    private T data;

    //返回状态
    private String status ;

    //业务状态
    private String businessCode;

    public static <T> Response<T> success(String message, T data, String status, String businessCode){
        Response response = new Response();
        response.setMessage(message);
        response.setData(data);
        response.setStatus(status);
        response.setBusinessCode(businessCode);
        return response;
    }

    public static <T> Response<T> error(String message, String status, String businessCode){
        Response response = new Response();
        response.setMessage(message);
        response.setStatus(status);
        response.setBusinessCode(businessCode);
        return response;
    }
}
