/**
 * Date:2017年9月13日下午3:11:10
 * Copyright (c) 2017, guooo All Rights Reserved.
 *
*/

package com.guooo.springboot.bean;

import lombok.Data;

/**
 * ClassName:DataBean <br/>
 * Function: 返回结果<br/>
 * Date: 2017年9月13日 下午3:11:10 <br/>
 * 
 * @author guooo
 * @version
 * @since JDK 1.6
 * @see
 */
@Data
public class DataRtnBean {

    /**
     * code:查询结果
     * 
     * @since JDK 1.6
     */
    public String code;

    /**
     * msg:结果详情
     * 
     * @since JDK 1.6
     */
    public String msg;

}
