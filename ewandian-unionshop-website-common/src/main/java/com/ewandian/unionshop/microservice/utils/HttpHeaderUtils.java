package com.ewandian.unionshop.microservice.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by OL on 2017/6/5.
 */
public class HttpHeaderUtils {
    /**
     * 从HttpRequest中获取Header信息
     **/
    public static HttpHeaders createHttpHeaders(HttpServletRequest request)
    {
        HttpHeaders requestHeaders = new HttpHeaders();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key =  headerNames.nextElement();
            String value = request.getHeader(key);
            requestHeaders.add(key, value);
        }
        return requestHeaders;
    }
    /**
     * 获取HttpHeaders
     **/
    public static MultiValueMap getHttpHeaders(Map httpHeaders){
        MultiValueMap multiValueMap=new LinkedMultiValueMap();
        Iterator itr=httpHeaders.keySet().iterator();
        while (itr.hasNext()){
            String key = (String) itr.next();
            List val =  (List)httpHeaders.get(key);
            multiValueMap.add(key,val.get(0));
        }
        return multiValueMap;
    }
}
