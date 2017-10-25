/**
 * Date:2017年10月10日下午1:42:31
 * Copyright (c) 2017, guooo All Rights Reserved.
 *
*/

package com.guooo.springboot.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guooo.springboot.bean.DataRtnBean;
import com.guooo.springboot.service.BlackGreyDataService;

/**
 * ClassName:APIBlackGreyDataController <br/>
 * Function: 黑灰名单服务接口<br/>
 * Date: 2017年10月10日 下午1:42:31 <br/>
 * 
 * @author guooo
 * @version
 * @since JDK 1.6
 * @see
 */
@RestController
@RequestMapping(value = "/black")
public class APIBlackGreyDataController {

    private Logger logger = Logger.getLogger(APIBlackGreyDataController.class);
    
    @Autowired
    BlackGreyDataService dataService;

    /**
     * query: 根据两元素查询用户是否是黑灰名单<br/>
     *
     * @author guooo Date:2017年10月12日上午11:37:39
     * @param name
     *            姓名，必输
     * @param num
     *            证件号码，必输
     * @param type
     *            证件类型，暂不需要
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value = "query",method=RequestMethod.POST)
    public DataRtnBean query(@RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "num", required = true) String num,
            @RequestParam(value = "type", required = false) String type) {
        logger.info("---------------------->>>Request Black Params:name=" + name +"/num="+num+"/type="+type);
        DataRtnBean data = dataService.query(name, num, type);
        return data;
    }

}
