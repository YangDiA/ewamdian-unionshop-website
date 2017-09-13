package com.ewandian.unionshop.microservice.resources.services.common;

import com.ewandian.microservices.platform.common.model.entity.BaseEntity;
import com.ewandian.microservices.platform.common.service.MongoDBServiceImpl;
import com.mongodb.WriteResult;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

/**
 * Created by YangDi on 2017/6/28.
 */
public class BaseServiceImpl<T extends BaseEntity> extends MongoDBServiceImpl implements IBaseService<T>{
    private static final String DEFAULT_OPERATOR = "system";
    private Class<T> entityClass;

    @Autowired
    protected MongoTemplate mongoTemplate;

    @SuppressWarnings("unchecked")
    public BaseServiceImpl() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        entityClass = (Class<T>) type.getActualTypeArguments()[0];
    }

    public String getOperator() {
        return DEFAULT_OPERATOR;
    }

    private Query getQuery(T dto) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        buildCriteria(criteria, dto, dto.getClass());
        query.addCriteria(criteria);
        return query;
    }

    private Query getQueryById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return query;
    }

    private Query getQueryById(T dto) {
        return getQueryById(dto.getId()+"");
    }

    /**
     * <p><b>通过反射机制，将每个成员变量名称与值设置到Criteria中</b></p>
     * 不处理以下成员变量：<br>
     *  1. 值为null;<br>
     * 	2. 被声名为static;<br>
     * 	3. 被声名为final.
     * @param criteria
     * @param obj
     * @param clazz 是否选择性修改（选择性修改：字段为空时，不修改）
     */
    private void buildCriteria(Criteria criteria, Object obj, Class<?> clazz) {
        if ("java.lang.Object".equals(clazz.getName())) {
            return;
        }

        Field[] fields = clazz.getDeclaredFields();

        try {
            for (Field field : fields) {
                String fieldName = field.getName();
                // 不处理static或final成员变量
                if (!(Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers()))) {
                    field.setAccessible(true);

                    if ("int".equals(field.getType()+"")&&(Integer)field.get(obj)==0){
                        break;
                    }
                    System.out.println(field.getType());
                    if (null != field.get(obj)) {
                        criteria.and(fieldName).is(field.get(obj));
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            return;
        } catch (IllegalAccessException e) {
            return;
        }

        buildCriteria(criteria, obj, clazz.getSuperclass());
    }

    private Update getUpdate(T dto, boolean isSelective) {
        Update update = new Update();
        buildUpdate(update, dto, dto.getClass(), isSelective);
        return update;
    }

    /**
     * 生成Update对象
     * @param update
     * @param obj
     * @param clazz
     * @param isSelective 是否选择性修改（选择性修改：字段为空时，不修改）
     */
    private void buildUpdate(Update update, Object obj, Class<?> clazz, boolean isSelective) {
        if ("java.lang.Object".equals(clazz.getName())) {
            return;
        }

        Field[] fields = clazz.getDeclaredFields();

        try {
            for (Field field : fields) {
                String fieldName = field.getName();
                // 不处理static或final成员变量
                if (!(Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers()))) {
                    field.setAccessible(true);
                    if (isSelective && null == field.get(obj)) {
                        continue;
                    }
                    update.set(fieldName, field.get(obj));
                }
            }
        } catch (IllegalArgumentException e) {
            return;
        } catch (IllegalAccessException e) {
            return;
        }

        buildUpdate(update, obj, clazz.getSuperclass(), isSelective);
    }
    @Override
    public T queryOne(T query) {
        return mongoTemplate.findOne(getQuery(query), entityClass);
    }
    @Override
    public T queryById(String id) {
        return mongoTemplate.findById(id, entityClass);
    }
    @Override
    public List<T> queryList(T query) {
        return mongoTemplate.find(getQuery(query), entityClass);
    }
    @Override
    public List<T> queryAll() {
        return mongoTemplate.findAll(entityClass);
    }
    @Override
    public long queryCount() {
        return mongoTemplate.count(null, entityClass);
    }
    @Override
    public long queryCount(T query) {
        return mongoTemplate.count(getQuery(query), entityClass);
    }
    @Override
    public T insert(T dto) {
        preInsert(dto);
        mongoTemplate.insert(dto);
        postInsert(dto);
        return dto;
    }
    @Override
    public void preInsert(T dto) {
        if (dto.getCreateDate() == null) {
            dto.setCreateDate(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        }
        if (dto.getCreateUser() == null) {
            dto.setCreateUser(getOperator());
        }
    }
    @Override
    public void postInsert(T dto) {

    }
    @Override
    public List<T> insert(List<T> list) {
        preInsert(list);
        mongoTemplate.insert(list, entityClass);
        postInsert(list);
        return list;
    }
    @Override
    public void preInsert(List<T> list) {
        for (T dto : list) {
            preInsert(dto);
        }
    }
    @Override
    public void postInsert(List<T> list) {

    }
    @Override
    public int update(T dto) {
        preUpdate(dto);
        WriteResult result = mongoTemplate.updateMulti(getQueryById(dto), getUpdate(dto, true), entityClass);
        //this.mongoTemplate.save(dto);
        postUpdate(dto);
        return result.getN();
    }
    @Override
    public void preUpdate(T dto) {
        dto.setUpdateDate(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
      /*  dto.setUpdateDate(new Date());
        dto.setCreator(getOperator());*/
    }
    @Override
    public void postUpdate(T dto) {
        // TODO Auto-generated method stub

    }

    @Override
    public int update(List<T> list) {
        //preUpdate(list);

        int count = 0;
        for (T dto : list) {
           // WriteResult result = mongoTemplate.updateMulti(getQueryById(dto), getUpdate(dto, true), entityClass);
            //count += result.getN();
            count +=this.update(dto);
        }

        //postUpdate(list);

        return count;
    }
    @Override
    public void preUpdate(List<T> list) {
        for (T dto : list) {
            dto.setUpdateDate(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        }
    }
    @Override
    public void postUpdate(List<T> list) {

    }
    @Override
    public int updateById(T dto) {
        WriteResult result = mongoTemplate.updateFirst(getQueryById(dto), getUpdate(dto, false), entityClass);
        return result.getN();
    }
    @Override
    public void preUpdateById(T dto) {
       /* dto.setUpdateDate(new Date());
        dto.setCreator(getOperator());*/
    }
    @Override
    public void postUpdateById(T dto) {

    }

    @Override
    public int updateById(List<T> list) {
        preUpdateById(list);

        int count = 0;
        for (T dto : list) {
            WriteResult result = mongoTemplate.updateFirst(getQueryById(dto), getUpdate(dto, false), entityClass);
            count += result.getN();
        }

        postUpdateById(list);

        return count;
    }
    @Override
    public void preUpdateById(List<T> list) {
      /*  for (T dto : list) {
            dto.setUpdateDate(new Date());
            dto.setCreator(getOperator());
        }*/
    }
    @Override
    public void postUpdateById(List<T> list) {

    }
    @Override
    public int updateByIdSelective(T dto) {
        preUpdateByIdSelective(dto);

        WriteResult result = mongoTemplate.updateFirst(getQueryById(dto), getUpdate(dto, true), entityClass);

        postUpdateByIdSelective(dto);

        return result.getN();
    }
    @Override
    public void preUpdateByIdSelective(T dto) {
       /* dto.setUpdateDate(new Date());
        dto.setCreator(getOperator());*/
    }
    @Override
    public void postUpdateByIdSelective(T dto) {
        // TODO Auto-generated method stub

    }

    @Override
    public int updateByIdSelective(List<T> list) {
        preUpdateByIdSelective(list);

        int count = 0;
        for (T dto : list) {
            WriteResult result = mongoTemplate.updateMulti(getQueryById(dto), getUpdate(dto, true), entityClass);
            count += result.getN();
        }

        postUpdateByIdSelective(list);

        return count;
    }
    @Override
    public void preUpdateByIdSelective(List<T> list) {
        /*for (T dto : list) {
            dto.setUpdateDate(new Date());
            dto.setCreator(getOperator());
        }*/
    }
    @Override
    public void postUpdateByIdSelective(List<T> list) {

    }

    @Override
    public void preDelete(T dto) {
        // TODO Auto-generated method stub

    }
    @Override
    public void postDelete(T dto) {
        // TODO Auto-generated method stub

    }

    @Override
    public int deleteById(String id) {
        preDeleteById(id);
        WriteResult result = mongoTemplate.remove(getQueryById(id), entityClass);
        postDeleteById(id);
        return result.getN();
    }
    @Override
    public void preDeleteById(String id) {

    }
    @Override
    public void postDeleteById(String id) {

    }
}
