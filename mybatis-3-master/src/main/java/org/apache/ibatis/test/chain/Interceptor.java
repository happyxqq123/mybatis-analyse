package org.apache.ibatis.test.chain;

public interface Interceptor {

  public Object plugin(Object obj,InterceptorChain interceptorChain);

  static class InterceptorA implements  Interceptor{

    @Override
    public Object plugin(Object obj, InterceptorChain interceptorChain) {
      System.out.println("interceptor A");
      return interceptorChain.doPlug(obj);
    }
  }

  static class InterceptorB implements  Interceptor{

    @Override
    public Object plugin(Object obj, InterceptorChain interceptorChain) {
      System.out.println("interceptorB");
      return interceptorChain.doPlug(obj);
    }
  }
}


