package com.cjp.trade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cjp.trade.domain.dto.OrderFormDTO;
import com.cjp.trade.domain.po.Order;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cjp
 * @since
 */
public interface IOrderService extends IService<Order> {

    Long createOrder(OrderFormDTO orderFormDTO);

    void markOrderPaySuccess(Long orderId);
}
