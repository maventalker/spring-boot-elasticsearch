/**
 * Date:2017年10月12日下午1:12:21
 * Copyright (c) 2017, guooo All Rights Reserved.
 *
*/

package com.guooo.springboot.bean;

/**
 * ClassName:Code <br/>
 * Function: <br/>
 * Date: 2017年10月12日 下午1:12:21 <br/>
 * 
 * @author guooo
 * @version
 * @since JDK 1.6
 * @see
 */
public enum Code {

    BLACK("BLACK", "疑似银行黑名单"), GREY("GREY", "疑似银行灰名单"), NONE("NONE", "未命中");

    private String code;

    private String msg;

    Code(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
