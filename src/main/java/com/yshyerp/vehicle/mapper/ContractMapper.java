package com.yshyerp.vehicle.mapper;

import com.yshyerp.vehicle.entity.Contract;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 合同
 */
@Mapper
@Repository
public interface ContractMapper {

    /**
     * 根据罐主名称查询合同列表
     * @param customer
     * @return
     */
    public List<Contract> queryContractListByCustomer(String customer);
}
