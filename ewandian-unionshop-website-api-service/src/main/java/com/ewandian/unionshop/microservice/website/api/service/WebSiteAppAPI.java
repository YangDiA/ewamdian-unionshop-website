package com.ewandian.unionshop.microservice.website.api.service;

import com.ewandian.microservices.platform.common.MDCHystrixConcurrencyStrategy;
import com.ewandian.microservices.platform.config.RestTemplateConfig;
import com.netflix.hystrix.strategy.HystrixPlugins;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;

/**
 * @author wonderful
 * @date 2017-4-20
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableCircuitBreaker
@Configuration
@ComponentScan({"com.ewandian.unionshop.microservice.api.service", "com.ewandian.unionshop.microservice.common",
        "com.ewandian.unionshop.microservice.website.api.service.resources","com.ewandian.microservices.platform"
})
public class WebSiteAppAPI
{
    private static final Logger LOG = LoggerFactory.getLogger(WebSiteAppAPI.class);
    static {
        // for localhost testing only
        LOG.warn("Will now disable hostname check in SSL, only to be used during development");
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, sslSession) -> true);
    }
    @Value("${app.rabbitmq.host:192.168.10.88}")
    String rabbitMqHost;

    @Value("${app.sso.authHost:192.168.10.88}")
    String authHost;

    @Value("${app.sso.authPort:9080}")
    int authPort;

    @Value("${app.sso.authPassowrd:password}")
    String authPassowrd;

    @Value("${app.sso.authUser:admin}")
    String authUser;


    /**
     *
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        LOG.info("Create RabbitMqCF for host: {}", rabbitMqHost);
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitMqHost);
        return connectionFactory;
    }

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() throws Exception {
        RestTemplateConfig restTemplateConfig = new RestTemplateConfig();
        return new RestTemplate(restTemplateConfig.createSecureTransport(authUser,authPassowrd,authHost,authPort));
    }


    public static void main( String[] args )
    {
        LOG.info("Register MDCHystrixConcurrencyStrategy");
        HystrixPlugins.getInstance().registerConcurrencyStrategy(new MDCHystrixConcurrencyStrategy());
        SpringApplication.run(WebSiteAppAPI.class, args);
    }
}
