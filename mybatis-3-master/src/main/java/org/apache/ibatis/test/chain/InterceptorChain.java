package org.apache.ibatis.test.chain;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class InterceptorChain {

  private List<Interceptor> interceptors = new LinkedList<>();
  private Iterator<Interceptor> iterator = null;

  public void addInterceptor(Interceptor interceptor){
    interceptors.add(interceptor);
  }

  public Object doPlug(Object obj){
    if(iterator == null){
      iterator = interceptors.iterator();
    }
    if(iterator.hasNext()){
      Interceptor interceptor  = iterator.next();
      return interceptor.plugin(obj,this);
    }
    return obj;
  }

  public static void main(String[] args) {
    InterceptorChain interceptorChain = new InterceptorChain();
    Interceptor interceptorA = new Interceptor.InterceptorA();
    Interceptor interceptorB = new Interceptor.InterceptorB();
    interceptorChain.addInterceptor(interceptorA);
    interceptorChain.addInterceptor(interceptorB);
    Object obj = new Object();
    interceptorChain.doPlug(obj);

  }

}
