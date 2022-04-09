package pers.orchard.a617.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

import static org.apache.ibatis.io.Resources.getResourceAsReader;

/**
 * @author pangd
 * @date 20210119-152303
 */
public class DaoAssign {
  private static SqlSessionFactory factory;

  static {
    String resource = "pers/orchard/a617/mybatis-config.xml";
    try {
      Reader reader = getResourceAsReader(resource);
      factory = new SqlSessionFactoryBuilder().build(reader);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static SqlSession getSession() {
    return factory.openSession();
  }
}
