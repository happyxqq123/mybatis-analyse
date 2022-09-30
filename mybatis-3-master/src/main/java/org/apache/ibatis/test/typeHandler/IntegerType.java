package org.apache.ibatis.test.typeHandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.INTEGER)
public class IntegerType extends BaseTypeHandler<Integer> {


  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, Integer parameter, JdbcType jdbcType) throws SQLException {
    ps.setInt(i,parameter+1);
  }

  @Override
  public Integer getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return rs.getInt(columnName)+1;
  }

  @Override
  public Integer getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return rs.getInt(columnIndex);
  }

  @Override
  public Integer getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return cs.getInt(columnIndex);
  }
}
