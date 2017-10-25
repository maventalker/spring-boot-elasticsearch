/**
 * Date:2017年10月10日下午1:38:36
 * Copyright (c) 2017, guooo All Rights Reserved.
 *
*/

package com.guooo.springboot.bean;

import java.io.Serializable;

import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

/**
 * ClassName:BlackGreyData <br/>
 * Function: <br/>
 * Date: 2017年10月10日 下午1:38:36 <br/>
 * 
 * @author guooo
 * @version
 * @since JDK 1.6
 * @see
 */
@Data
@Document(indexName = "blackdata", type = "blackgrey", shards = 5, replicas = 1, indexStoreType = "fs", refreshInterval = "-1")
public class BlackGreyData implements Serializable {
    /**
     * serialVersionUID:
     * 
     * @since JDK 1.6
     */
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * name:姓名
     * @since JDK 1.6
     */
    private String name;

    /**
     * numType:证件类型
     * @since JDK 1.6
     */
    private String numType;

    /**
     * num:证件号码
     * @since JDK 1.6
     */
    private String num;

    private String code;
}
