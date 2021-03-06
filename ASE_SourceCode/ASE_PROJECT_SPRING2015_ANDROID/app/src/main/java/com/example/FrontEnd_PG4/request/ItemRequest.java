package com.example.FrontEnd_PG4.request;

import android.content.Context;

import com.example.FrontEnd_PG4.util.Property;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * Created by Gurrala on 3/15/2015.
 */
public class ItemRequest extends BaseRequest {
    private Property property;
    private Long subCategoryId;

    public ItemRequest(Context ctx, JsonHandler jsonHandler, Long subCategoryId) {
        super(ctx, jsonHandler);
        property = new Property(ctx);
        this.subCategoryId = subCategoryId;
    }

    @Override
    protected HttpRequestBase getHttpRequest() {
        String params;
        if (subCategoryId == null)
            params = "catalog/items";
        else
            params = "catalog/item/" + subCategoryId;
        return new HttpGet(property.getProperty("serveraddr") + params);
    }
}
