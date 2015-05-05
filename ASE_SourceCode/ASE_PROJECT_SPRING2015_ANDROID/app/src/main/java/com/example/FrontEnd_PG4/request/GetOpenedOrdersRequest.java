package com.example.FrontEnd_PG4.request;

import android.content.Context;

import com.example.FrontEnd_PG4.util.Property;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * Created by Gurrala on 4/6/2015.
 */
public class GetOpenedOrdersRequest extends BaseRequest {
    private Property property;
    private String orderType;

    public GetOpenedOrdersRequest(Context ctx, JsonHandler jsonHandler, String orderType) {
        super(ctx, jsonHandler);
        property = new Property(ctx);
        this.orderType = orderType;
    }

    @Override
    protected HttpRequestBase getHttpRequest() {
        return new HttpGet(property.getProperty("serveraddr") + "order/" + orderType);
    }
}
