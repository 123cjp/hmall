package com.cjp.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cjp.pay.domain.dto.PayApplyDTO;
import com.cjp.pay.domain.dto.PayOrderFormDTO;
import com.cjp.pay.domain.po.PayOrder;


/**
 * <p>
 * 支付订单 服务类
 * </p>
 *
 * @author cjp
 * @since
 */
public interface IPayOrderService extends IService<PayOrder> {

    String applyPayOrder(PayApplyDTO applyDTO);

    void tryPayOrderByBalance(PayOrderFormDTO payOrderFormDTO);
}
