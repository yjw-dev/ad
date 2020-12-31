package com.yshyerp.vehicle.service;

import com.yshyerp.vehicle.vo.ContractVO;

import java.util.List;

/**
 * 合同业务类
 */
public interface ContractService {

    /**
     * 根据罐主名称查询合同信息
     * @param tankCustomer 罐主名称
     * @param tank 罐号
     * @return
     */
    public List<ContractVO> queryContractListByCustomer(String tankCustomer, String tank) throws Exception;

}
