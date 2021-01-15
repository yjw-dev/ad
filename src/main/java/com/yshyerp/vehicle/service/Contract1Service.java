package com.yshyerp.vehicle.service;

import com.yshyerp.vehicle.entity.Contract;
import com.yshyerp.vehicle.vo.ContractVO;

import java.util.List;


public interface Contract1Service {

    /**
     * 根据罐主名称查询合同信息
     * @param customer 罐主名称
     * @return
     */
    public List<Contract> queryContractListByCustomer(String customer);

}
