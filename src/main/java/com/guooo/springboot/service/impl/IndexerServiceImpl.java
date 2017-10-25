/**
 * Project Name:spring-boot-es
 * File Name:IndexerServiceImpl.java
 * Package Name:com.guooo.springboot.service.impl
 * Date:2017年10月25日下午3:28:12
 * Copyright (c) 2017, 杭州知时信息科技有限公司 All Rights Reserved.
 *
*/

package com.guooo.springboot.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.guooo.springboot.bean.Car;
import com.guooo.springboot.service.IndexerService;

/**
 * ClassName:IndexerServiceImpl <br/>
 * Function: <br/>
 * Date:     2017年10月25日 下午3:28:12 <br/>
 * @author   guooo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
public class IndexerServiceImpl implements IndexerService{

    
    private static final String CAR_INDEX_NAME = "car_index";
    private static final String CAR_INDEX_TYPE = "car_type";

    
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    public long bulkIndex() throws Exception {
        int counter = 0;
        try {
            //判断索引是否存在
            if (!elasticsearchTemplate.indexExists(CAR_INDEX_NAME)) {
                elasticsearchTemplate.createIndex(CAR_INDEX_NAME);
            }
            Gson gson = new Gson();
            List<IndexQuery> queries = new ArrayList<IndexQuery>();
            List<Car> cars = assembleTestData();
            for (Car car : cars) {
                IndexQuery indexQuery = new IndexQuery();
                indexQuery.setId(car.getId().toString());
                indexQuery.setSource(gson.toJson(car));
                indexQuery.setIndexName(CAR_INDEX_NAME);
                indexQuery.setType(CAR_INDEX_TYPE);
                queries.add(indexQuery);
                //分批提交索引
                if (counter % 500 == 0) {
                    elasticsearchTemplate.bulkIndex(queries);
                    queries.clear();
                    System.out.println("bulkIndex counter : " + counter);
                }
                counter++;
            }
            //不足批的索引最后不要忘记提交
            if (queries.size() > 0) {
                elasticsearchTemplate.bulkIndex(queries);
            }
            elasticsearchTemplate.refresh(CAR_INDEX_NAME);
            System.out.println("bulkIndex completed.");
        } catch (Exception e) {
            System.out.println("IndexerService.bulkIndex e;" + e.getMessage());
            throw e;
        }
        
        return -1;
    }

    private List<Car> assembleTestData() {
        List<Car> cars = new ArrayList<Car>();
        for (int i = 0; i < 10000; i++) {
            cars.add(new Car(RandomUtils.nextLong(1, 11111), RandomStringUtils.randomAscii(20), RandomStringUtils.randomAlphabetic(15), BigDecimal.valueOf(78000)));
        }
        return cars;
    }
}

