package org.apache.ibatis.test;


import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;

public class MyInterceptor implements Interceptor {


  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    return null;
  }

  @Override
  public Object plugin(Object target) {
    return Interceptor.super.plugin(target);
  }
}
