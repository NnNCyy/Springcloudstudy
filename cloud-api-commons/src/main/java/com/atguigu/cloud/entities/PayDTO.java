package com.atguigu.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @auther zzyy
 * @create 2023-11-03 18:58
 * 一般而言，调用者不应该获悉提供者的entity资源并知道表结构关系，所以服务提供方给出的
 *      接口文档都应为DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayDTO implements Serializable {
    private Integer id;
    //支付流水号
    private String payNo;
    //订单流水号
    private String orderNo;
    //用户账号ID
    private Integer userId;
    //交易金额
    private BigDecimal amount;
}
