package com.yshyerp.vehicle.mapper;

import com.yshyerp.vehicle.entity.Customer;
import com.yshyerp.vehicle.entity.DriverBlack;
import com.yshyerp.vehicle.entity.TranE;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 客户
 */
@Mapper
@Repository
public interface CustomerMapper {

    /**
     * 根据客户名称查询客户信息
     * @param customer 客户名称
     * @return
     */
    public List<Customer> getCustomerByCustomerName(String customer);


    /**
     * 根据客户名称查询客户信息
     * @return
     */
    public List<Customer> customerList();

    public List<Map<String,Object>> customerget(String customer);


    /**
     * 查询运输公司
     * @param company 运输公司
     * @return
     */
    public List<TranE> getTranE(String company);

    /**
     * 查询司机黑名单
     * @param idNo 司机身份证
     * @return
     */
    public List<DriverBlack> getDriverBlack(String idNo);


    /**
     * 根据客户查询    drumlock
     * @param
     * @return
     */

    public Customer getdrumlock(String customer);

}
