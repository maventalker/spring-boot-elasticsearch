/**
 * Date:2017年9月27日上午11:37:12
 * Copyright (c) 2017, guooo All Rights Reserved.
 *
*/

package com.guooo.springboot.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

/**
 * ClassName:Article <br/>
 * Function: 测试类<br/>
 * Date: 2017年9月27日 上午11:37:12 <br/>
 * 
 * @author guooo
 * @version
 * @since JDK 1.6
 * @see
 */
@Data
@Document(indexName = "article_index", type = "article", shards = 5, replicas = 1, indexStoreType = "fs", refreshInterval = "-1")
public class Article implements Serializable {

    /**
     * serialVersionUID:
     * 
     * @since JDK 1.6
     */
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    /** 标题 */
    private String title;
    /** 摘要 */
    private String abstracts;
    /** 内容 */
    private String content;
    /** 发表时间 */
    @Field(format = DateFormat.date_time, index = FieldIndex.no, store = true, type = FieldType.Object)
    private Date postTime;
    /** 点击率 */
    private Long clickCount;

}
