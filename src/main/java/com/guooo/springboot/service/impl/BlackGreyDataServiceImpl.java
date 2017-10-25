/**
 * Date:2017年10月10日下午1:44:58
 * Copyright (c) 2017, guooo All Rights Reserved.
 *
*/

package com.guooo.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.PrefixQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.guooo.springboot.bean.BlackGreyData;
import com.guooo.springboot.bean.Code;
import com.guooo.springboot.bean.DataRtnBean;
import com.guooo.springboot.dal.BlackGreyDataRepository;
import com.guooo.springboot.service.BlackGreyDataService;

/**
 * ClassName:BlackGreyDataServiceImpl <br/>
 * Function: BlackGreyDataRepository具体查询语句<br/>
 * Date: 2017年10月10日 下午1:44:58 <br/>
 * 
 * @author guooo
 * @version
 * @since JDK 1.6
 * @see
 */
@Service
public class BlackGreyDataServiceImpl implements BlackGreyDataService {

    private Logger logger = Logger.getLogger(BlackGreyDataServiceImpl.class);

    final int page = 50;
    final int size = 10;
    Pageable pageable = new PageRequest(page, size);

    @Autowired
    BlackGreyDataRepository repository;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public DataRtnBean query(String name, String num, String type) {
        DataRtnBean bean = new DataRtnBean();
        BoolQueryBuilder blackBoolBuilder = QueryBuilders.boolQuery();
        blackBoolBuilder.must(QueryBuilders.termQuery("fname", name)).must(QueryBuilders.termQuery("fnum", num)).must(QueryBuilders.termQuery("dataType", "black"));
        Iterable<BlackGreyData> black = repository.search(blackBoolBuilder);

        List<BlackGreyData> datas = new ArrayList<>();
        for (BlackGreyData blackData : black) {
            datas.add(blackData);
            logger.info("---------------------->>>Request Black result = 【" + black + "】");
        }

        //黑名单未命中时，再查询灰名单数据
        if (CollectionUtils.isEmpty(datas)) {
            BoolQueryBuilder greyBoolBuilder = QueryBuilders.boolQuery();
            greyBoolBuilder.must(QueryBuilders.termQuery("fname", name)).must(QueryBuilders.termQuery("fnum", num)).mustNot(QueryBuilders.termQuery("dataType", "black"));
            Iterable<BlackGreyData> grey = repository.search(greyBoolBuilder);

            List<BlackGreyData> greyDatas = new ArrayList<>();
            for (BlackGreyData greyData : grey) {
                greyDatas.add(greyData);
                logger.info("---------------------->>>Request Grey result = 【" + black + "】");
            }
            if (CollectionUtils.isEmpty(greyDatas)) {
                bean.setCode(Code.NONE.getCode());
                bean.setMsg(Code.NONE.getMsg());
            } else {
                bean.setCode(Code.GREY.getCode());
                bean.setMsg(Code.GREY.getMsg());
            }
        } else {
            bean.setCode(Code.BLACK.getCode());
            bean.setMsg(Code.BLACK.getMsg());
        }
        return bean;
    }

    @Override
    public void largetQuery() {
        logger.info("----------------------->>>>>>>");
//        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("fnumtype", "00");
//        FuzzyQueryBuilder fuzzyQueryBuilder = new FuzzyQueryBuilder("fnumtype", "00");
        PrefixQueryBuilder prefixQueryBuilder  = new PrefixQueryBuilder("fnumtype", "00");
        SearchQuery query = new NativeSearchQueryBuilder()
                .withIndices("blackdata")
                .withTypes("blackgrey")
                .withQuery(prefixQueryBuilder)
                .withPageable(pageable)
                .build();
        long scollTimeInMillis = 100000;
        String scrollId = elasticsearchTemplate.scan(query, scollTimeInMillis, false);
        boolean hasRecords = true;
        long totalCount = 0;
            Page<BlackGreyData> page = elasticsearchTemplate.scroll(scrollId, scollTimeInMillis, BlackGreyData.class);
            if (page.hasContent()) {
                logger.info("page number:{}" + page.getNumberOfElements() + "////"+page.getContent().size());
                logger.info(page.getContent() + "+++++++++++");
                totalCount += page.getNumberOfElements();
            } else {
                hasRecords = false;
            }

        //clear scroll
        elasticsearchTemplate.clearScroll(scrollId);
    }

}
