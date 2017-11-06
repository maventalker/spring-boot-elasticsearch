/**
 * Date:2017年4月5日下午9:42:35
 * Copyright (c) 2017, guooo All Rights Reserved.
 *
*/
package com.guooo.springboot;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * ClassName:ZhishiAppservApplication <br/>
 * Function: spring boot 操作elasticsearch测试案例<br/>
 * Date: 2017年10月9日 下午9:42:35 <br/>
 * 
 * @author guooo
 * @version
 * @since JDK 1.6
 * @see
 */
@SpringBootApplication
public class SpringBootESLeanApplicatoin {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootESLeanApplicatoin.class, args);
    }
    
    @Bean
    public RestClient elasticsearchRestClient(){
        RestClient restClient = RestClient.builder(new HttpHost("192.168.1.103",9200,"http")).build();
        return restClient;
    }
}
