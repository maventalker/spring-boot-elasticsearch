/**
 * Date:2017年10月10日下午1:43:30
 * Copyright (c) 2017, guooo All Rights Reserved.
 *
*/

package com.guooo.springboot.dal;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.guooo.springboot.bean.BlackGreyData;

/**
 * ClassName:BlackGreyDataRepository <br/>
 * Function: <br/>
 * Date:     2017年10月10日 下午1:43:30 <br/>
 * @author   guooo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface BlackGreyDataRepository extends ElasticsearchRepository<BlackGreyData, Long> {

    
    public List<BlackGreyData> findByNameAndNum(String name, String num);
}

