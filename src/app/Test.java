package app;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.ClassByStudentNameServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2.得到执行sql的对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //3.执行sql语句
        ApplicationContext act = new ClassPathXmlApplicationContext("ClassForStrudent.xml");
        ClassByStudentNameServiceImpl nameService = (ClassByStudentNameServiceImpl)act.getBean("nameService");
        /*for (Student i:list
        ) {
            System.out.println(i.getName());
        }*/
        List<String> list1=sqlSession.selectList("findStudentAndClass",nameService.getStudentList());
        for (String s:list1
        ) {
            System.out.println(s);
        }

        //5.关闭数据库
        sqlSession.close();
    }
}
