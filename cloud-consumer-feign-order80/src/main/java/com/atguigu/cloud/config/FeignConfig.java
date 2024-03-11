package com.atguigu.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    //配置feign的请求重试机制
    @Bean
    public Retryer myRetryer(){
        //最多重试次数 3(1+2)，初始间隔时间100ms，重试间最大间隔时间1s
        return new Retryer.Default(100,1,3);
        //return Retryer.NEVER_RETRY;
    }

    /**
     * OpenFeign日志打印功能
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

}
