package org.apache.ibatis.test;

import java.util.Map;
import java.util.List;


public interface UserMapper {

  public List<Map<String,Object>> selectAll(String name);
}
