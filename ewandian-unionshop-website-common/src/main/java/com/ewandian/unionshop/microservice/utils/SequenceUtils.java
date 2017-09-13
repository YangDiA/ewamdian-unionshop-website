package com.ewandian.unionshop.microservice.utils;

import com.ewandian.microservices.platform.common.model.entity.Result;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YangDi on 2017/7/3.
 */
public class SequenceUtils {
    /**
     * 获取序列号
     **/
    public static Result getSerial(String systemNo, String seqName, MultiValueMap httpHeaders, RestTemplate restTemplate){
        Map filter = new HashMap();
        filter.put("systemNo", systemNo);
        filter.put("seqName", seqName);
        HttpEntity httpEntity = new HttpEntity(filter, httpHeaders);
        String url = "http://sequence-api-service/seq/getSerial";
        ResponseEntity<Result> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<Result>() {
        });
        return responseEntity.getBody();
    }
}
