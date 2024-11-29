package org.dinky;

import org.dinky.security.NoExitSecurityManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.proxy.DruidDriver;

import lombok.SneakyThrows;

/**
 * Dinky Starter
 *
 * @since 2021/5/28
 */
@EnableTransactionManagement
@SpringBootApplication(exclude = FreeMarkerAutoConfiguration.class)
@EnableCaching
public class Dinky {

    static {
        System.setProperty("log4j2.isThreadContextMapInheritable", "true");
    }

    @SneakyThrows
    public static void main(String[] args) {
        // Prevent System.exit calls
        System.setSecurityManager(new NoExitSecurityManager());
        // Initialize the JDBC Driver, because the number of packages is very large, so it needs to be executed
        // asynchronously and loaded in advance
        // chinese: 初始化JDBC Driver，因为包的数量特别庞大，所以这里需要异步执行，并提前加载Driver
        new Thread(DruidDriver::getInstance).start();

        SpringApplication app = new SpringApplication(Dinky.class);
        app.run(args);
    }
}
