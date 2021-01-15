package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.entity.SCustomer;
import com.yshyerp.vehicle.mapper.SCustomerMapper;
import com.yshyerp.vehicle.service.SCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class SCustomerServiceImpl implements SCustomerService {
    @Autowired
    SCustomerMapper sCustomerMapper;
    @Override
    public List<SCustomer> alist(SCustomer customer) {
        List<SCustomer> list=sCustomerMapper.alist(customer);
        return list;
    }
}
