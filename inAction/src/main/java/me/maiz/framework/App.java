package me.maiz.framework;

import me.maiz.framework.biz.DalService;
import me.maiz.framework.biz.impl.DalServiceImpl;
import me.maiz.framework.dal.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;

/**
 * Hello world!
 *
 */

public class App 
{

   private static Logger logger = LoggerFactory.getLogger(App.class);

    private static SqlSession sqlSession = null;

    private static DalService dalservice = new DalServiceImpl();

    static{
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            dalservice.setSqlSession(sqlSession);
        } catch (IOException e) {
           logger.error("读取文件错误",e);
        }finally {
            try {
                if(inputStream!=null)
                    inputStream.close();
            } catch (IOException e) {
                logger.error("读取文件错误",e);
            }
        }
    }


    public static void main( String[] args )
    {
        simpleCRUD();
        page();
    }

    private static void page() {

    }

    private static void simpleCRUD() {
        User newUser = newUser();
        logger.info("待插入的用户{}",newUser);
        dalservice.insert(User.class,newUser);
        logger.info("插入过后的用户{}",newUser);

        User user = dalservice.findOne(User.class, newUser.getUserId());
        logger.info("查询出的用户{}",user);


        dalservice.deleteOne(User.class,newUser.getUserId());

        User deletedUser = dalservice.findOne(User.class, newUser.getUserId());
        logger.info("删除后查询用户结果是否为空{}",deletedUser==null);
    }

    private static User newUser() {
        User user = new User();
        user.setAge(17);
        user.setUserName("王二");
        user.setBirthday(Date.valueOf("1989-02-22"));
        return user;
    }
}
