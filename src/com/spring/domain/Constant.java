 
package com.spring.domain;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "ref_constant")
@SQLDelete(sql = "UPDATE ref_constant SET deleted = '1' WHERE constantid = ?  and -1 != ?")
@Where(clause = "deleted <> '1'")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue(value="CONSTANT")
@AttributeOverride(name = "id", column = @Column(name = "constantid"))
@GenericGenerator (name = "demo_generator", strategy = "seqhilo", parameters = {@Parameter(name = "max_lo", value = "1"), @Parameter(name = "sequence", value = "constant_seq")})
public class Constant extends PersistentEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "code", length = 255, nullable=false, unique=true)
	private String code;

	@Column(name = "value", length = 255)
	private String value;
	
	@Column(name = "deleted", nullable = false, columnDefinition = "int default 0")
	private long deleted;
	
	@Embedded
	private AuditInfo auditInfo;

	public AuditInfo getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}

	public String getCode() {
		return this.code;
	}
	
	public String getValue() {
		return this.value;
	}

	
	public long getDeleted() {
		return deleted;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public void setValue(String description) {
		this.value = description;
	}
	
	public void setDeleted(long deleted) {
		this.deleted = deleted;
	}


	public enum Code implements Serializable {
		BASE_FLIPSWAP_IMAGE_URL, DISCREPANCY_PERCENT, PRICE_EXPIRATION_DAYS
		
	}

	

	/**
	 * @param obj
	 * @return
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof Constant))
			return false;

		Constant c = (Constant) obj;
		return new EqualsBuilder().append(c.getCode(), this.getCode()).append(
				c.getClass(), this.getClass()).isEquals();
	}

	/**
	 * @return
	 */
	public int hashCode() {
		return new HashCodeBuilder(3, 5).append(getCode()).append(getClass())
				.toHashCode();
	}
}
