/**
 * Date:2017年9月27日下午1:25:11
 * Copyright (c) 2017, guooo All Rights Reserved.
 *
*/

package com.guooo.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.guooo.springboot.bean.Article;
import com.guooo.springboot.dal.ArticleSearchRepository;
import com.guooo.springboot.service.ArticleService;

/**
 * ClassName:ArticleServiceImpl <br/>
 * Function: article es 操作<br/>
 * Date: 2017年9月27日 下午1:25:11 <br/>
 * 
 * @author guooo
 * @version
 * @since JDK 1.6
 * @see
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    final int page = 0;
    final int size = 10;

    /* 搜索模式 */
    String SCORE_MODE_SUM = "sum"; // 权重分求和模式
    Float MIN_SCORE = 10.0F; // 由于无相关性的分值默认为 1 ，设置权重分最小值为 10

    Pageable pageable = new PageRequest(page, size);

    @Autowired
    ArticleSearchRepository repository;

    @Override
    public long saveArticle(Article article) {
        Article result = repository.save(article);
        return result.getId();
    }

    @Override
    public void deleteArticle(long id) {
        repository.delete(id);
    }

    @Override
    public Article findArticle(long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Article> findArticlePageable() {

        return repository.findAll(pageable).getContent();
    }

    @Override
    public List<Article> findArticleAll() {
        Iterable<Article> iterables = repository.findAll();
        List<Article> articles = new ArrayList<>();
        for (Article article : iterables) {
            articles.add(article);
        }
        return articles;
    }

    @Override
    public List<Article> findArticleSort() {
        List<Order> orders = new ArrayList<>();
        Order order = new Order(Direction.ASC, "clickCount");
        orders.add(order);
        Sort sort = new Sort(orders);
        Iterable<Article> iterables = repository.findAll(sort);
        List<Article> articles = new ArrayList<>();
        for (Article article : iterables) {
            articles.add(article);
        }
        return articles;
    }

    @Override
    public List<Article> like(String content) {
        SearchQuery query = getSearchQuery(content, page, size);
        Iterable<Article> articles = repository.search(query);
        List<Article> list = new ArrayList<>();
        for (Article article : articles) {
            list.add(article);
        }
        return list;
    }

    private SearchQuery getSearchQuery(String searchContent, int page, int size) {
        // 短语匹配到的搜索词，求和模式累加权重分
        // 权重分查询 https://www.elastic.co/guide/cn/elasticsearch/guide/current/function-score-query.html
        //   - 短语匹配 https://www.elastic.co/guide/cn/elasticsearch/guide/current/phrase-matching.html
        //   - 字段对应权重分设置，可以优化成 enum
        //   - 由于无相关性的分值默认为 1 ，设置权重分最小值为 10
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
                .add(QueryBuilders.matchPhraseQuery("abstracts", searchContent),
                        ScoreFunctionBuilders.weightFactorFunction(1000))
                .add(QueryBuilders.matchPhraseQuery("content", searchContent),
                        ScoreFunctionBuilders.weightFactorFunction(500))
                .scoreMode(SCORE_MODE_SUM).setMinScore(MIN_SCORE);

        // 分页参数
        Pageable pageable = new PageRequest(page, size);
        return new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(functionScoreQueryBuilder).build();
    }

    @Override
    public List<Article> search(String content) {
        return repository.findByAbstractsAndContent(content, content);
    }

    @Override
    public long update(long id) {
        Article article = repository.findOne(id);
        article.setTitle("test");
        Article retun = repository.save(article);
        System.out.println(retun.getId()+"更新的数据");
        return retun.getId();
    }

}
