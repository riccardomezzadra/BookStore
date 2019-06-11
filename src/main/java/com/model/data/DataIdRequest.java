package com.model.data;

import com.contract.IRequest;

/**
 * @author Riccardo
 */
public class DataIdRequest implements IRequest {

    private Long id;
    private String fieldName;

    public DataIdRequest() {
    }

    public DataIdRequest(Long id) {
        this.id = id;
    }

    public DataIdRequest(Long id, String fieldName) {
        this.id = id;
        this.fieldName = fieldName;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }


}
