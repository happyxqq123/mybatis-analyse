package org.apache.ibatis.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.datasource.jndi.JndiDataSourceFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.test.typeHandler.IntegerType;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.type.IntegerTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.util.List;
import java.util.Map;

public class Mybatis1 {

  public static final  String url = "jdbc:mysql://rm-2ze94q1dv8mrbg424co.mysql.rds.aliyuncs.com:3306/jeecg-boot-v3?characterEncoding=UTF-8&useUnicode=true&useSSL=false&tinyInt1isBit=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai";

  public static final String username = "ecs";

  public static final String password = "@1128ecs";

  public static void main(String[] args) {
    PooledDataSource ds = new PooledDataSource("com.mysql.cj.jdbc.Driver",url,username,password);
    ds.setPoolMaximumActiveConnections(1);
    ds.setPoolMaximumIdleConnections(1);
    ds.setPoolTimeToWait(1000);
    ds.setPoolMaximumCheckoutTime(2000);
    ds.setPoolPingEnabled(true);
    ds.setPoolPingQuery("select 1");
    ds.setDefaultAutoCommit(true);
    // MySQL wait_timeout * 1000 or less. (unit:ms)
    ds.setPoolPingConnectionsNotUsedFor(1000);
    TransactionFactory transactionFactory =new JdbcTransactionFactory();
    Environment environment = new Environment("devlopment",transactionFactory,ds);
    Configuration configuration = new Configuration(environment);

    TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
    typeHandlerRegistry.register(Integer.class, new IntegerType());
    typeHandlerRegistry.register(int.class, new IntegerType());
    typeHandlerRegistry.register(JdbcType.INTEGER, new IntegerType());

    configuration.addMapper(UserMapper.class);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    SqlSession sqlSession =  sqlSessionFactory.openSession();
  //  sqlSession.getConfiguration().addInterceptor(new MyInterceptor());
    UserMapper userMapper =  sqlSession.getMapper(UserMapper.class);
    List<Map<String,Object>> map =  userMapper.selectAll("bank_admin");
    map.forEach(stringObjectMap -> {
      System.out.println(stringObjectMap.toString());
    });
    System.out.println(userMapper.selectAll("bank_admin").size());

  }

  public static void main1(String[] args) {
    String path = UserMapper.class.getName().replace('.','/')+".xml";
    System.out.println("/"+path);
    System.out.println(UserMapper.class.getResourceAsStream(path));
    //System.out.println(((BuiltinClassLoader) UserMapper.class.getClassLoader()).findResourceAsStream(mn, name););
  }
}
