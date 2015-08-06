package com.spring.domain;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "ref_role")
@SQLDelete(sql = "UPDATE ref_role SET deleted = '1' WHERE roleid = ?  and -1 != ?")
@Where(clause = "deleted <> '1'")
@AttributeOverride(name = "id", column = @Column(name = "roleid"))
@GenericGenerator (name = "demo_generator", strategy = "seqhilo", parameters = {@Parameter(name = "max_lo", value = "1"), @Parameter(name = "sequence", value = "role_seq")})
public class Role extends PersistentEntity {

	private static final long serialVersionUID = -8025612872300488986L;

	@Column(name = "type")
	private String type;

	@Column(name = "deleted", nullable = false)
	private long deleted;

	@Column(name = "description")
	private String description;
	
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * @param type
	 *            The type to set.
	 */
	public void setType(String type) {
		this.type = type;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}