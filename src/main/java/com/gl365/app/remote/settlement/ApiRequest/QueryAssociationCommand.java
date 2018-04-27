package com.gl365.app.remote.settlement.ApiRequest;

/**
 * Created by wangmeihua on 2017/6/12.
 */
public class QueryAssociationCommand {
    private String ownerType;
    private String ownerId;
    private String type;

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
