package me.maiz.framework.biz;


import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by Lucas on 2016-11-12.
 */
public interface DalService {


    public void setSqlSession(SqlSession session);

    /**
     * 插入数据
     * @param entityClass
     * @param entity
     */
    public <DO> void insert(Class<DO> entityClass,DO entity);

    /**
     * 根据ID查询数据
     * @param entityClass
     * @param id
     * @return
     */
    public <DO> DO findOne(Class<DO> entityClass, int id);

    /**
     * 根据条件查询一堆数据
     * @param entityClass
     * @param example
     * @return
     */
    public <DO,E> List<DO> findList(Class<DO> entityClass, E example);

    /**
     * 删除单条数据
     * @param entityClass
     * @param id
     * @return
     */
    public <DO,E> int deleteOne(Class<DO> entityClass, int id);

    /**
     * 根据条件删除一堆数据
     * @param entityClass
     * @param example
     * @return
     */
    public <DO,E>  int delete(Class<DO> entityClass, E example);

}
