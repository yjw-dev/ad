package com.yshyerp.vehicle.service;

import com.yshyerp.vehicle.vo.TrustVO;

import java.util.List;

public interface TrustService {

    /**
     * 根据货主名称和货品查询委托书信息
     * @param cCustomer 货主名称
     * @param commodity 货品名称
     * @return
     */
    public List<TrustVO> queryTrustByCustomerAndCommodity(String cCustomer, String commodity) throws Exception;

    /**
     * 校验委托书号
     * @param trust 委托书号
     * @param  cCustomer 货主
     * @param  commodity 货品
     * @param  quantity 提货数量
     * @param  status 流程状态（1提货计划 2入仓 3出仓）
     * @param netWeight 净重量
     * @return
     */
    public String checkTrust(String trust, String cCustomer, String commodity, double quantity, String status, int netWeight);

    /**
     * 校验备注中的委托书号
     * @param remarks 备注
     * @param pageDoNo 委托书文本框中输入的委托书号
     * @param quantity 提货数量
     * @param tempStatus 流程状态1：提货计划 2：入仓 3出仓
     * @param  netWeight 净重量
     * @return
     */
    public String checkTrustByRemarks(String remarks, String pageDoNo, double quantity, String tempStatus, int netWeight);
}
