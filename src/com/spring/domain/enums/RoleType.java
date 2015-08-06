package com.spring.domain.enums;

public enum RoleType {
	ADMIN("label.admin", "label.admin"),  API("label.api", "label.api");
	  
	  private final String attributeName;
	  private final String attributeKey;
	  
	  private RoleType(String attributeKey, String attributeName)
	  {
	    this.attributeKey = attributeKey;
	    this.attributeName = attributeName;
	  }
	  
	  public String getKey()
	  {
	    return this.attributeKey;
	  }
	  
	  public String getValue()
	  {
	    return this.attributeName;
	  }
	  
	  public String toString()
	  {
	    return this.attributeName;
	  }
	  
	  public static RoleType findType(String key)
	  {
	    for (RoleType type : RoleType.values()) {
	      if (type.getKey().equals(key)) {
	        return type;
	      }
	    }
	    return null;
	  }
}