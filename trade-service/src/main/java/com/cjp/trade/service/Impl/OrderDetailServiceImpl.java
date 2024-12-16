package com.cjp.trade.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.cjp.trade.domain.po.OrderDetail;
import com.cjp.trade.mapper.OrderDetailMapper;
import com.cjp.trade.service.IOrderDetailService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单详情表 服务实现类
 * </p>
 *
 * @author cjp
 * @since
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {

}
