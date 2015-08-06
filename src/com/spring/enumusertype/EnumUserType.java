package com.spring.enumusertype;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;

public class EnumUserType
  implements UserType, ParameterizedType, Serializable
{
  private static final long serialVersionUID = 1L;
  private Class clazz = null;
  
  public void setParameterValues(Properties params)
  {
    String enumClassName = params.getProperty("enumClassName");
    if (enumClassName == null) {
      throw new MappingException("enumClassName parameter not specified");
    }
    try
    {
      this.clazz = Class.forName(enumClassName);
    }
    catch (ClassNotFoundException e)
    {
      throw new MappingException("enumClass " + enumClassName + 
        " not found", e);
    }
  }
  
  private static final int[] SQL_TYPES = { 12 };
  
  public int[] sqlTypes()
  {
    return SQL_TYPES;
  }
  
  public Class returnedClass()
  {
    return this.clazz;
  }
  
  public Object nullSafeGet(ResultSet resultSet, String[] names, Object owner)
    throws HibernateException, SQLException
  {
    String name = resultSet.getString(names[0]);
    Object result = null;
    if (!resultSet.wasNull()) {
      result = Enum.valueOf(this.clazz, name);
    }
    return result;
  }
  
  public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index)
    throws HibernateException, SQLException
  {
    if (value == null) {
      preparedStatement.setNull(index, 12);
    } else {
      preparedStatement.setString(index, ((Enum)value).name());
    }
  }
  
  public Object deepCopy(Object value)
    throws HibernateException
  {
    return value;
  }
  
  public boolean isMutable()
  {
    return false;
  }
  
  public Object assemble(Serializable cached, Object owner)
    throws HibernateException
  {
    return cached;
  }
  
  public Serializable disassemble(Object value)
    throws HibernateException
  {
    return (Serializable)value;
  }
  
  public Object replace(Object original, Object target, Object owner)
    throws HibernateException
  {
    return original;
  }
  
  public int hashCode(Object x)
    throws HibernateException
  {
    return x.hashCode();
  }
  
  public boolean equals(Object x, Object y)
    throws HibernateException
  {
    if (x == y) {
      return true;
    }
    if ((x == null) || (y == null)) {
      return false;
    }
    return x.equals(y);
  }
}
