package com.spring.domain;
import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
@MappedSuperclass
public abstract class PersistentEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  @Id 
	  @GeneratedValue(generator="demo_generator" ,strategy = GenerationType.SEQUENCE)
	  private Long id = new Long(-1L);
	  
	  @Version
	  private Integer version;

	/**
	 * @return Returns the version.
	 * @hibernate.version column="VERSION" unsaved-value="null"
	 */
	
	public Integer getVersion() {
		return this.version;
	}

	/**
	 * @param version
	 *            The version to set.
	 */
	void setVersion(Integer versionID) {
		this.version = versionID;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public boolean isPersistent() {
		return version != null;
	}
}