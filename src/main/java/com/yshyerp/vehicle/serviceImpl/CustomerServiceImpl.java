package com.yshyerp.vehicle.serviceImpl;

import com.yshyerp.vehicle.entity.Customer;
import com.yshyerp.vehicle.entity.DriverBlack;
import com.yshyerp.vehicle.entity.TranE;
import com.yshyerp.vehicle.mapper.CustomerMapper;
import com.yshyerp.vehicle.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerMapper customerMapper;

    /**
     * 根据客户简称查询客户明细
     * @param customer 客户简称
     * @return
     */
    @Override
    public List<Customer> getCustomerByCustomerName(String customer) {
        return customerMapper.getCustomerByCustomerName(customer);
    }

    /**
     * 查询客户名称
     * @return
     */
    @Override
    public List<Customer> customerList() {
        List<Customer> customerList=customerMapper.customerList();
        return customerList;
    }

    @Override
    public List<Map<String,Object>> customerget(String customer) {
        List<Map<String,Object>> customerList=customerMapper.customerget(customer);
        return customerList;
    }

    /**
     * 校验客户是否存在
     * @param customer 客户简称
     * @return
     */
    @Override
    public boolean checkCustomer(String customer) {
        if(this.getCustomerByCustomerName(customer).size()>0) {
            return true;
        }
        return false;
    }

    /**
     * 校验公司是否能够运输危险化学品
     * @param company 运输公司全称
     * @return
     */
    @Override
    public boolean checkTransportationCompany(String company){
        List<TranE> tranEList = customerMapper.getTranE(company);
        if(null!=tranEList && tranEList.size()>0) {
            return true;
        }
        return false;
    }

    /**
     * 查询司机时候上黑名单
     * @param idNo 司机身份证号
     * @return
     */
    @Override
    public String checkDriverBlack(String idNo) {
        List<DriverBlack> driverBlackList = customerMapper.getDriverBlack(idNo);
        if(driverBlackList!=null && driverBlackList.size()>0){
            return "注意：司机" + driverBlackList.get(0).getName().trim() + "已被" + driverBlackList.get(0).getCustomer().trim()
                    + "公司列入黑名单，不能提该公司货物";
        }
        return null;
    }

}
