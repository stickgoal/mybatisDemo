package me.maiz.framework.biz.impl;

import me.maiz.framework.biz.DalService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by Lucas on 2016-11-12.
 */
public class DalServiceImpl implements DalService {

    private SqlSession sqlSession;

    public SqlSession getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    private String getFullMapperName(String entityClassName){
        return "me.maiz.framework.dal.mapper."+entityClassName+"Mapper";
    }

    public <DO> void insert(Class<DO> entityClass,DO entity){
        sqlSession.insert(getFullMapperName(entityClass.getSimpleName())+".insert",entity);
    }


    public <DO> DO findOne(Class<DO> entityClass, int id){
        return sqlSession.selectOne(getFullMapperName(entityClass.getSimpleName())+".selectByPrimaryKey",id);
    }

    public <DO,E> List<DO> findList(Class<DO> entityClass, E example){
        return sqlSession.selectList(getFullMapperName(entityClass.getSimpleName())+".selectByExample",example);
    }

    public <DO,E> int deleteOne(Class<DO> entityClass, int id){
        return sqlSession.delete(getFullMapperName(entityClass.getSimpleName())+".deleteByPrimaryKey",id);
    }

    public <DO,E> int delete(Class<DO> entityClass, E example){
        return sqlSession.delete(getFullMapperName(entityClass.getSimpleName())+".deleteByExample",example);
    }


}
