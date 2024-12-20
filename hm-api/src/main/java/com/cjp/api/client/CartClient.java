package com.cjp.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

/**
 * @author 陈建平
 */
@FeignClient("cart-service")
public interface CartClient {
    @DeleteMapping
    void deleteCartItemByIds(@RequestParam("ids") Collection<Long> ids);
}


