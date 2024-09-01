package com.example.apigatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * 1) GlobalFilter
 * 2) CustomFilter
 * 3) LoggingFilter - This filter only applies to second-service filter
 * ex1) GET http://localhost:8000/first-service/check
 *      Global Filter baseMessage: Spring Cloud Gateway Global Filter
 *      Global Filter Start: request id -> ac368f6a-1
 *      Custom PRE filter: request id -> ac368f6a-1
 *      Custom POST filter: response code -> 200 OK
 *      Global Filter end: response code -> 200 OK
 * ex2) GET http://localhost:8000/second-service/check
 *      Logging Filter baseMessage: Hi, there.
 *      Logging PRE Filter: request id -> 50385dfb-3
 *      Global Filter baseMessage: Spring Cloud Gateway Global Filter
 *      Global Filter Start: request id -> 50385dfb-3
 *      Custom PRE filter: request id -> 50385dfb-3
 *      Custom POST filter: response code -> 200 OK
 *      Global Filter End: response code -> 200 OK
 *      Logging POST Filter: response code -> 200 OK
 * -> LoggingFilter is first because of "Ordered.HIGHEST_PRECEDENCE"
 */
@Slf4j
@Component
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {

    public LoggingFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {

        GatewayFilter filter = new OrderedGatewayFilter((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Logging Filter baseMessage: {}", config.getBaseMessage());
            if (config.isPreLogger()) {
                log.info("Logging PRE Filter: request id -> {}", request.getId());
            }
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                if (config.isPostLogger()) {
                    log.info("Logging POST Filter: response code -> {}", response.getStatusCode());
                }
            }));
        }, Ordered.HIGHEST_PRECEDENCE); // priority

        return filter;
    }

    @Data
    public static class Config {
        // Put the configuration properties
        // Defined at application.yml
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}
