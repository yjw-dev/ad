package com.yshyerp.vehicle.service;

/**
 * 提单业务类
 */
public interface BillService {

    /**
     * 提单号校验（大提单）
     * @param doNo 提单号
     * @param  cCustomer 客户
     * @param  commodity 货品
     * @param  quantity 提货数量
     * @param  status 流程状态(1提货计划 2入仓 3出仓)
     * @param netWeight 净重（出仓时使用）
     * @return
     */
    public String checkBigBill(String doNo, String cCustomer, String commodity, double quantity, int status, int netWeight);

    /**
     * 非大提单校验
     * @param doNo 提单号
     * @param status 流程状态(1提货计划 2入仓 3出仓)
     * @return
     */
    public String checkBill(String doNo, String status);
}
