package com.example.FrontEnd_PG4.request;

import android.content.Context;

import com.example.FrontEnd_PG4.util.Property;

import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * Created by Gurrala on 5/3/2015.
 */
public class AddItemRequest extends BaseRequest {
    private Property property;
    private String name, desc;
    private Double price;

    public AddItemRequest(Context ctx, JsonHandler jsonHandler, String name, Double price, String desc) {
        super(ctx, jsonHandler);
        property = new Property(ctx);
        this.name = name;
        this.price = price;
        this.desc = desc;
    }

    @Override
    protected HttpRequestBase getHttpRequest() {
        String params = "name=" + name + "&price=" + price + "&desc=" + desc;
        return new HttpPut(property.getProperty("serveraddr") + "catalog/item?" + params);
    }
}
