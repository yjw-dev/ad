package com.yshyerp.vehicle.service;

import com.yshyerp.vehicle.entity.Customer;

import java.util.List;

/**
 * 客户业务类
 */
public interface CustomerService {

    /**
     * 根据客户简称查询客户信息
     * @param customer 客户简称
     * @return
     */
    public List<Customer> getCustomerByCustomerName(String customer);

    /**
     * 校验客户是否存在
     * @param customer 客户简称
     * @return
     */
    public boolean checkCustomer(String customer);

    /**
     * 校验公司是否能够运输危险化学品
     * @param company
     * @return
     */
    public boolean checkTransportationCompany(String company);

    /**
     * 查询司机时候上黑名单
     * @param idNo 司机身份证号
     * @return
     */
    public String checkDriverBlack(String idNo);
}
