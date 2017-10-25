/**
 * Date:2017年10月20日下午2:50:15
 * Copyright (c) 2017, guooo All Rights Reserved.
 *
*/

package com.guooo.springboot.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * ClassName:Car <br/>
 * Function: <br/>
 * Date: 2017年10月20日 下午2:50:15 <br/>
 * 
 * @author guooo
 * @version
 * @since JDK 1.6
 * @see
 */
@Document(indexName = "carIndex", type = "carType", shards = 1, replicas = 0)
public class Car implements Serializable {
    /**
     * serialVersionUID:
     * @since JDK 1.6
     */
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    private String brand;
    private String model;
    private BigDecimal amount;

    public Car(Long id, String brand, String model, BigDecimal amount) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}