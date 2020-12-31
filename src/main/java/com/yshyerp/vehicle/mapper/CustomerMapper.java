package com.yshyerp.vehicle.mapper;

import com.yshyerp.vehicle.entity.Customer;
import com.yshyerp.vehicle.entity.DriverBlack;
import com.yshyerp.vehicle.entity.TranE;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
