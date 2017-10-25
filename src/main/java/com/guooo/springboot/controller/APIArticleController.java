/**
 * Date:2017年9月27日下午1:28:54
 * Copyright (c) 2017, guooo All Rights Reserved.
 *
*/

package com.guooo.springboot.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.guooo.springboot.bean.Article;
import com.guooo.springboot.service.ArticleService;

/**
 * ClassName:APIArticleController <br/>
 * Function: 结合article实体类的es测试<br/>
 * Date: 2017年9月27日 下午1:28:54 <br/>
 * 
 * @author guooo
 * @version
 * @since JDK 1.6
 * @see
 */
@RestController
@RequestMapping(value = "/article")
public class APIArticleController {

    @Autowired
    ArticleService articleService;

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public long insert()  {
        System.out.println("begin java invoke shell");
        try {
            String shpath = "/tmp/topdf.sh";//脚本路径，脚本中需要将源doc路径配置成变量，由下行程序调用时入参
            String[] str = {"源路径"};
            Process ps = Runtime.getRuntime().exec(shpath);//
            ps.waitFor();//等待转换结果

            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String result = sb.toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return 123;

    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public long save() {
        for (int i = 10000; i < 12000; i++) {
            Article article = new Article();
            article.setClickCount(Long.valueOf(i + RandomUtils.nextInt(23, i)));
            article.setAbstracts("我的一个测试" + i);
            article.setContent(i + "这是第一个测试的内容@spring-data-elasticsearch");
            article.setPostTime(new Date());
            article.setId(Long.valueOf(RandomUtils.nextLong(i, i)));
            long _id = articleService.saveArticle(article);
            System.out.println(_id);
        }
        return 23;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public void deleteArticle(long id) {
        articleService.deleteArticle(id);
    }

    @RequestMapping(value = "findOne", method = RequestMethod.POST)
    public Article findArticle(long id) {
        return articleService.findArticle(id);
    }

    @RequestMapping(value = "findArticlePageable", method = RequestMethod.POST)
    public List<Article> findArticlePageable() {
        return articleService.findArticlePageable();
    }

    @RequestMapping(value = "findArticleAll", method = RequestMethod.POST)
    public List<Article> findArticleAll() {
        return articleService.findArticleAll();
    }

    @RequestMapping(value = "findArticleSort", method = RequestMethod.POST)
    public List<Article> findArticleSort() {
        return articleService.findArticleSort();
    }

    @RequestMapping(value = "like", method = RequestMethod.POST)
    public List<Article> like(String content) {
        return articleService.like(content);
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public List<Article> search(String content) {
        return articleService.search(content);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public long update(long id) {
        return articleService.update(id);
    }
}
