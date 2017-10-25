/**
 * Date:2017年9月27日下午1:24:08
 * Copyright (c) 2017, guooo All Rights Reserved.
 *
*/

package com.guooo.springboot.service;

import java.util.List;

import com.guooo.springboot.bean.Article;

/**
 * ClassName:ArticleService <br/>
 * Function: <br/>
 * Date: 2017年9月27日 下午1:24:08 <br/>
 * 
 * @author guooo
 * @version
 * @since JDK 1.6
 * @see
 */
public interface ArticleService {

    /**
     * saveArticle: 写入<br/>
     *
     * @author guooo Date:2017年9月27日下午3:20:06
     * @param article
     * @return
     * @since JDK 1.6
     */
    long saveArticle(Article article);

    /**
     * deleteArticle: 删除，并未真正删除，只是查询不到<br/>
     *
     * @author guooo Date:2017年9月27日下午3:20:08
     * @param id
     * @since JDK 1.6
     */
    void deleteArticle(long id);

    /**
     * findArticle: <br/>
     *
     * @author guooo Date:2017年9月27日下午3:20:10
     * @param id
     * @return
     * @since JDK 1.6
     */
    Article findArticle(long id);

    /**
     * findArticlePageable: <br/>
     *
     * @author guooo Date:2017年9月27日下午3:20:13
     * @return
     * @since JDK 1.6
     */
    List<Article> findArticlePageable();

    /**
     * findArticleAll: <br/>
     *
     * @author guooo Date:2017年9月27日下午3:20:15
     * @return
     * @since JDK 1.6
     */
    List<Article> findArticleAll();

    /**
     * findArticleSort: <br/>
     *
     * @author guooo Date:2017年9月27日下午3:20:18
     * @return
     * @since JDK 1.6
     */
    List<Article> findArticleSort();

    /**
     * like: <br/>
     *
     * @author guooo Date:2017年9月27日下午3:20:20
     * @param content
     * @return
     * @since JDK 1.6
     */
    List<Article> like(String content);

    /**
     * search: <br/>
     *
     * @author guooo Date:2017年9月27日下午3:20:22
     * @param content
     * @return
     * @since JDK 1.6
     */
    List<Article> search(String content);

    /**
     * update: es没有修改操作，结合save操作完成<br/>
     *
     * @author guooo Date:2017年9月27日下午3:20:25
     * @param id
     * @return
     * @since JDK 1.6
     */
    long update(long id);
}
