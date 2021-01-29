package com.yshyerp.vehicle.service;

import com.yshyerp.vehicle.entity.SCustomer;

import java.util.List;
import java.util.Map;

public interface SCustomerService {


    //查询小客户
    List<Map<String,Object>> alist(String customer);
}
