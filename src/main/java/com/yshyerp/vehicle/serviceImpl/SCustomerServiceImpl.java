package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.entity.SCustomer;
import com.yshyerp.vehicle.mapper.SCustomerMapper;
import com.yshyerp.vehicle.service.SCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service

public class SCustomerServiceImpl implements SCustomerService {
    @Autowired
    SCustomerMapper sCustomerMapper;
    @Override
    public List<Map<String,Object>> alist(String customer) {
        List<Map<String,Object>> list=sCustomerMapper.alist(customer);
        return list;
    }
}
