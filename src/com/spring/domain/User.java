package com.spring.domain;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "ref_user")
@SQLDelete(sql = "UPDATE ref_user SET deleted = '1' WHERE userid = ?  and -1 != ?")
@Where(clause = "deleted <> '1'")
@AttributeOverride(name = "id", column = @Column(name = "userid"))
@GenericGenerator (name = "demo_generator", strategy = "seqhilo", parameters = {@Parameter(name = "max_lo", value = "1"), @Parameter(name = "sequence", value = "user_seq")})
public class User extends PersistentEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "username", length = 255)
	private String username;

	@Column(name = "password", length = 255)
	private String password;

	@Column(name = "active")
	private Boolean active;

	@Column(name = "deleted", nullable = false)
	private long deleted;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "fs_user_role", joinColumns = { @JoinColumn(name = "userid") }, inverseJoinColumns = { @JoinColumn(name = "roleid") })
	private Set<Role> roles;
	
	private AuditInfo auditInfo = new AuditInfo();

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * @return the password
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * @return the deleted
	 */
	public long getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted
	 *            the deleted to set
	 */
	public void setDeleted(long deleted) {
		this.deleted = deleted;
	}

	/**
	 * @return the roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public AuditInfo getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}
}
