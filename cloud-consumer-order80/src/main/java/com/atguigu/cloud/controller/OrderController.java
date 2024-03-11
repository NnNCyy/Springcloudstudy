package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
public class OrderController {
    public static final String PAYMENTSRV_URL = "http://cloud-payment-service"; //服务注册中心上的微服务名称

    @Resource
    private RestTemplate restTemplate;

    // 新增
    @PostMapping("/consumer/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO){
        return restTemplate.postForObject(PAYMENTSRV_URL+"/pay/add",payDTO,ResultData.class);
    }

    // 查询
    @GetMapping("/consumer/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PAYMENTSRV_URL+"/pay/get/"+id, ResultData.class, id);
    }

    //删除
    @DeleteMapping("/consumer/pay/del/{id}")
    public ResultData deletePay(@PathVariable("id") Integer id){
        String url = PAYMENTSRV_URL + "/pay/del/" + id;
        ResponseEntity<ResultData> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, null, ResultData.class);
        return responseEntity.getBody();
    }

    // 修改
    @PutMapping("/consumer/pay/update")
    public ResultData updatePay(@RequestBody PayDTO payDTO){
        String url = PAYMENTSRV_URL + "/pay/update";
        ResponseEntity<ResultData> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(payDTO), ResultData.class);
        return responseEntity.getBody();
    }

    @GetMapping("/consumer/pay/get/info")
    private String getInfoByConsul(){
        return restTemplate.getForObject(PAYMENTSRV_URL+"/pay/get/info" , String.class);
    }

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/discovery")
    public String discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println(element);
        }

        System.out.println("===================================");

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
        }

        return instances.get(0).getServiceId()+":"+instances.get(0).getPort();
    }
}
