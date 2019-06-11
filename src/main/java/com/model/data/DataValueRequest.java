package com.model.data;

import com.contract.IRequest;

/**
 * @author Riccardo
 */
public class DataValueRequest implements IRequest {
    private String id;
    private String fieldName;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
