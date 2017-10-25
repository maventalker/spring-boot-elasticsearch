/**
 * Date:2017年4月6日下午2:29:00
 * Copyright (c) 2017, guooo All Rights Reserved.
 *
*/

package com.guooo.springboot;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * ClassName:WebConfiguration <br/>
 * Function: <br/>
 * Date: 2017年4月6日 下午2:29:00 <br/>
 * 
 * @author guooo
 * @version
 * @since JDK 1.6
 * @see
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {
    public void configureMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setWriteAcceptCharset(false);

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        /*
         * 解决对象为空时，转换json时异常，
         * 如nested exception is com.fasterxml.jackson.databind.JsonMappingException: 
         * No serializer found for class java.lang.Object and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) 
         * (through reference chain: com.zhishi.common.vo.RestAPIResult["respData"])
         */
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        converter.setObjectMapper(objectMapper);

        messageConverters.add(converter);
    }

}
