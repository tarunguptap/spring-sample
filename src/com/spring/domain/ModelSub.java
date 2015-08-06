package com.spring.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="ref_modsub")
@AttributeOverride(name = "id", column = @Column(name = "modsubid"))
@GenericGenerator (name = "demo_generator", strategy = "seqhilo", parameters = {@Parameter(name = "max_lo", value = "1"), @Parameter(name = "sequence", value = "modsub_seq")})
public class ModelSub extends PersistentEntity {
	
	private static final long serialVersionUID = 1L;

	@Column(name="userid",nullable = false, length = 50)
	private String uid;
	
	@Column(name="username",nullable = false, length = 50)
	private String uname;
	
	@Embedded
	private AuditInfo auditInfo;
	
	public ModelSub() {
		// TODO Auto-generated constructor stub
	}
	
	public ModelSub(String uid, String uname) {
		this.uid = uid;
		this.uname = uname;
	}
	
	public ModelSub(String uname) {
		this.uname = uname;
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}

	public AuditInfo getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}
	
}
