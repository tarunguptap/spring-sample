package com.spring.domain;

public interface Auditable {
	/**
	 * Instances must always return a non-null instance of AuditInfo
	 * 
	 *
	 */
	public AuditInfo getAuditInfo();
}
