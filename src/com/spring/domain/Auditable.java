package com.spring.domain;

public interface Auditable {
	/**
	 * Instances must always return a non-null instance of AuditInfo
	 * 
	 * @hibernate.component class="com.flipswap.domain.AuditInfo"
	 */
	public AuditInfo getAuditInfo();
}
