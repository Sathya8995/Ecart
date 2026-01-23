package com.ecart.ecart.dto;

public class OrderCreated {
    private String refId;

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public OrderCreated(String refId) {
        this.refId = refId;
    }
}
