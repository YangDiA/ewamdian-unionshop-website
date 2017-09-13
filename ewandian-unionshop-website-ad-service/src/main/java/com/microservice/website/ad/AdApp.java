package com.microservice.website.ad;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by YangDi on 2017/7/19.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class AdApp {
    private static final Logger LOG = LoggerFactory.getLogger(AdApp.class);

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Value("${app.rabbitmq.host:192.168.10.88}")
    String rabbitMqHost;

    @Value("${crm.websitemaster.mongodb.host:192.168.10.25}")
    private String host;

    @Value("${crm.websitemaster.mongodb.port:27017}")
    private int port;

    @Value("${crm.websitemaster.mongodb.database:website}")
    private String mongoDB;

    @Value("${crm.websitemaster.mongodb.userName:}")
    private String userName;

    @Value("${crm.websitemaster.mongodb.password:}")
    private String password;

    @Value("${crm.websitemaster.mongodb.socketTimeoutMS:3000}")
    private int socketTimeout;

    @Value("${crm.websitemaster.mongodb.connectionTimeout:3000}")
    private int connectionTimeout;

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

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {

        // Set credentials
        if( userName != null && userName.trim().equals("") ) userName = null;
        if( password != null && password.trim().equals("") ) password = null;
        MongoCredential credential = null;
        if(userName != null  && password != null) {
            credential = MongoCredential.createCredential(userName, mongoDB,password.toCharArray());
        }
        ServerAddress serverAddress = new ServerAddress(host, port);

        // Mongo Client
        MongoClient mongoClient = null;
        if (credential != null ){
            mongoClient =new MongoClient(serverAddress, Arrays.asList(credential));
        }else{
            mongoClient =new MongoClient(serverAddress);
        }

        // Mongo DB Factory
        SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(        mongoClient, mongoDB);

        return simpleMongoDbFactory;
    }

    public @Bean
    MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(AdApp.class, args);
    }
}
