package com.cjp.gateway.filter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.AntPathMatcher;
import com.cjp.gateway.config.AuthProperties;
import com.cjp.gateway.utils.JwtTool;
import com.hmall.common.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author 陈建平
 *
 */
@RequiredArgsConstructor
@Component
@EnableConfigurationProperties(AuthProperties.class)
public class AuthGlobalFilter implements GlobalFilter , Ordered {

    private final AuthProperties authProperties;

    private final JwtTool jwtTool;
    //匹配器
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.登录校验，先获取request
        ServerHttpRequest request = exchange.getRequest();
        //2.判断是否需要登录拦截---yaml配置里面
        if(isExclude(request.getPath().toString())){
            //放行2
            return chain.filter(exchange);
        }
        //3.获取token
        String token = null;
        List<String> headers = request.getHeaders().get("authorization");
        if(!CollUtil.isEmpty(headers)){
            //校验不为空
            token = headers.get(0);
        }
        //4，校验并解析token
        Long userId = null;
        try {
            //校验解析
           userId = jwtTool.parseToken(token);
        }catch (UnauthorizedException e){
            //拦截为401
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //完结，不能往下走了
            return response.setComplete();
        }
        //5.传递用户信息
        //System.out.println("userId = " + userId);
        String userInfo = userId.toString();
        //向请求头放用户信息
        ServerWebExchange build = exchange.mutate()
                .request(builder -> builder.header("user-info", userInfo))
                .build();
        //6.放行
        return chain.filter(build);
    }

    private boolean isExclude(String path) {
        for (String pathPattern : authProperties.getExcludePaths()) {
           if(antPathMatcher.match(pathPattern, path)){
               return true;
           }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
