package com.ewandian.unionshop.microservice.resources.services.common;

import com.ewandian.microservices.platform.common.model.entity.PageEntity;
import com.ewandian.microservices.platform.common.service.MongoDBService;

import java.util.List;

/**
 * Created by YangDi on 2017/6/28.
 */
public interface IBaseService<T> extends MongoDBService {
    public String getOperator();

    /**
     * 查询一个对象，如果返回的结果多于一个对象将会抛异常
     * @param query 不能为null
     * @return
     */
    public T queryOne(T query);

    public T queryById(String id);

    public List<T> queryList(T query);

    public List<T> queryAll();

    public long queryCount();

    public long queryCount(T query);

    public T insert(T dto);

    public void preInsert(T dto);

    public void postInsert(T dto);

    public List<T> insert(List<T> list);

    public void preInsert(List<T> list);

    public void postInsert(List<T> list);

    public int update(T dto);

    public void preUpdate(T dto);

    public void postUpdate(T dto);

    public int update(List<T> list);

    public void preUpdate(List<T> list);

    public void postUpdate(List<T> list);

    public int updateById(T dto);

    public void preUpdateById(T dto);

    public void postUpdateById(T dto);

    public int updateById(List<T> list);

    public void preUpdateById(List<T> list);

    public void postUpdateById(List<T> list);

    public int updateByIdSelective(T dto);

    public void preUpdateByIdSelective(T dto);

    public void postUpdateByIdSelective(T dto);

    public int updateByIdSelective(List<T> list);

    public void preUpdateByIdSelective(List<T> list);

    public void postUpdateByIdSelective(List<T> list);

    public int deleteById(String id);

    public void preDeleteById(String id);

    public void postDeleteById(String id);


    public void preDelete(T dto);

    public void postDelete(T dto);

}
