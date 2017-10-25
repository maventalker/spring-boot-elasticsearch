/**
 * Date:2017年10月10日下午1:44:45
 * Copyright (c) 2017, guooo All Rights Reserved.
 *
*/

package com.guooo.springboot.service;

import com.guooo.springboot.bean.DataRtnBean;

/**
 * ClassName:BlackGreyDataService <br/>
 * Function: <br/>
 * Date: 2017年10月10日 下午1:44:45 <br/>
 * 
 * @author guooo
 * @version
 * @since JDK 1.6
 * @see
 */
public interface BlackGreyDataService {

    /**
     * query: 根据唯一业务主键查询数据<br/>
     * 优先查询黑名单数据，未命中时再查询灰名单数据，以结果为准
     *
     * @author guooo Date:2017年10月10日下午1:45:50
     * @param name
     *            姓名
     * @param num
     *            号码
     * @param type
     *            类型
     * @return 查得数据集合
     * @since JDK 1.6
     */
    public DataRtnBean query(String name, String num, String type);
    
    
    void largetQuery();
}
