package com.spring.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "ref_labelconstant")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue(value="QC_STATUS_RESPONSE")
public class LabelConstant {

    

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue
    @Column(name = "labelconstantid")
    private Long id = new Long(-1);
	@Version
	private Integer version;
	@Column(name = "code")
	private String code;
	@Column(name = "localizedtext_labelcode")
    private String localizedText;
	@Column(name = "nonlocalizedtext")
    private String nonLocalizedText;

    public LabelConstant() {
	}
    
    public LabelConstant(String code, String localizedText,
			String nonLocalizedText) {
		super();
		this.code = code;
		this.localizedText = localizedText;
		this.nonLocalizedText = nonLocalizedText;
	}



	/**
     * @return Returns the code.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * @param code
     *            The code to set.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return Returns the text.
     */
    public final String getText() {
        if (this.localizedText == null)
            return this.nonLocalizedText;
        else
            return this.localizedText;
    }

    /**
     * @return the localizedText
     */
    public String getLocalizedText() {
        return this.localizedText;
    }

    /**
     * @param localizedText
     *            the localizedText to set
     */
    public void setLocalizedText(String localizedText) {
        this.localizedText = localizedText;
    }

    /**
     * @return the nonLocalizedText
     */
    String getNonLocalizedText() {
        return this.nonLocalizedText;
    }

    /**
     * @param nonLocalizedText
     *            the nonLocalizedText to set
     */
    public void setNonLocalizedText(String nonLocalizedText) {
        this.nonLocalizedText = nonLocalizedText;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((this.code == null) ? 0 : this.code.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final LabelConstant other = (LabelConstant) obj;
        if (this.code == null) {
            if (other.code != null)
                return false;
        } else if (!this.code.equals(other.code))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.getText();
    }

}
