package com.central;

import com.central.annotation.EnableLogging;
import com.central.autoconfigure.port.PortApplicationEnvironmentPreparedEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableLogging
@EnableDiscoveryClient
@SpringBootApplication
public class ProductCenterApp {
    public static void main(String[] args) {
        //随机端口启动
        SpringApplication app = new SpringApplication(ProductCenterApp.class);
        app.addListeners(new PortApplicationEnvironmentPreparedEventListener());
        app.run(args);
    }
}
