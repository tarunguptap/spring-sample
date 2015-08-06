package com.spring.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.spring.security.util.SecurityUtils;

@Embeddable
public final class AuditInfo implements Serializable {
	/**
     * 
     */
	
	public AuditInfo() {
		this.setCreatedBy(SecurityUtils.getAuthenticatedUser());
		this.createdDate = new Date();
	}
	
	private static final long serialVersionUID = -4999284044864759054L;

	@Column(name = "lastupdateddate", nullable = true, updatable = true, insertable = true)
	private Date lastUpdatedDate;

	@Column(name = "createddate", nullable = false, updatable = true, insertable = true)
	private Date createdDate;

	@OneToOne
	@JoinColumn(name = "lastupdatedby", insertable = true, updatable = true, nullable = true)
	private User updatedBy;

	@OneToOne
	@JoinColumn(name = "createdby", insertable = true, updatable = true, nullable = false)
	private User createdBy;

	/**
	 * @return Returns the createdBy.
	 * @hibernate.many-to-one column="CREATED_BY" cascade="none" not-null="true"
	 *                        foreign-key="FK_CREATED_BY" insert="true"
	 *                        update="false"
	 */
	
	public User getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * @param createdBy
	 *            The createdBy to set.
	 */
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return Returns the createdDate.
	 * @hibernate.property column="created_date" not-null="true" insert="true"
	 *                     update="false"
	 */
	
	public Date getCreatedDate() {
		return this.createdDate;
	}

	/**
	 * @param createdDate
	 *            The createdDate to set.
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return Returns the lastUpdatedDate.
	 * @hibernate.property column="last_update_date"
	 */
	
	public Date getLastUpdatedDate() {
		return this.lastUpdatedDate;
	}

	/**
	 * @param lastUpdatedDate
	 *            The lastUpdatedDate to set.
	 */
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	/**
	 * @return Returns the updatedBy.
	 * @hibernate.many-to-one column="UPDATED_BY" cascade="none"
	 *                        foreign-key="FK_UPDATED_BY"
	 */
	
	public User getUpdatedBy() {
		return this.updatedBy;
	}

	/**
	 * @param updatedBy
	 *            The updatedBy to set.
	 */
	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

}
