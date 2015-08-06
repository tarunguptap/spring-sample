package com.spring.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringUtils;

public class CustomEnumEditor extends PropertyEditorSupport {
    private final Class enumClass;

    public CustomEnumEditor(Class enumClass) {
        this.enumClass = enumClass;
    }

    /**
     * @return
     */
    public String getAsText() {
        return (getValue() == null ? null : ((Enum) getValue()).name());
    }

    /**
     * @param text
     * @throws java.lang.IllegalArgumentException
     */
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isBlank(text) || StringUtils.equalsIgnoreCase(text, "-1")) {
            // treat empty String as null value
            setValue(null);
        } else {
            setValue(Enum.valueOf(this.enumClass, text));
        }
    }
}