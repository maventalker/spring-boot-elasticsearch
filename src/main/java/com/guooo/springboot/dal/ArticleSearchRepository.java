/**
 * Date:2017年9月27日上午11:42:09
 * Copyright (c) 2017, guooo All Rights Reserved.
 *
*/

package com.guooo.springboot.dal;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.guooo.springboot.bean.Article;

public interface ArticleSearchRepository extends ElasticsearchRepository<Article, Long>{

    
    List<Article> findByAbstractsAndContent(String abstracts, String content);
}

