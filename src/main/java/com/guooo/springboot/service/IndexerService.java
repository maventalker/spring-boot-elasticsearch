/**
 * Date:2017年10月20日下午2:51:03
 * Copyright (c) 2017, guooo All Rights Reserved.
 *
*/

package com.guooo.springboot.service;

/**
 * ClassName:IndexerService <br/>
 * Function: ElasticsearchTemplate 形式操作elasticsearch<br/>
 * Date: 2017年10月20日 下午2:51:03 <br/>
 * 
 * @author guooo
 * @version
 * @since JDK 1.6
 * @see
 */
public interface IndexerService {

    /**
     * bulkIndex: 批量操作<br/>
     *
     * @author guooo Date:2017年10月25日下午3:29:07
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    public long bulkIndex() throws Exception;
}
