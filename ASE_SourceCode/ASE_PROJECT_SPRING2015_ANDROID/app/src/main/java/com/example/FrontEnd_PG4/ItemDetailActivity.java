package com.example.FrontEnd_PG4;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.FrontEnd_PG4.beans.Category;
import com.example.FrontEnd_PG4.request.AddItemRequest;
import com.example.FrontEnd_PG4.request.JsonHandler;
import com.example.FrontEnd_PG4.request.UpdateItemRequest;
import com.example.FrontEnd_PG4.util.ConfigUtil;

import org.json.JSONObject;

public class ItemDetailActivity extends Activity implements JsonHandler {
    private EditText name, price, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail_activity);
        name = (EditText) findViewById(R.id.item_detail_name);
        price = (EditText) findViewById(R.id.item_detail_price);
        desc = (EditText) findViewById(R.id.item_detail_desc);
        if (!getIntent().getBooleanExtra("new", false)) {
            Category category = ConfigUtil.getCategory();
            name.setText(category.getName());
            price.setText(category.getPrice() + "");
            desc.setText(category.getDescription());
        }
    }

    public void onSubmit(View v) {
        String nameStr = name.getText().toString();
        String descStr = desc.getText().toString();
        Double priceStr = Double.valueOf(price.getText().toString());
        if (!getIntent().getBooleanExtra("new", false)) {
            UpdateItemRequest updateItemRequest = new UpdateItemRequest(this, this, nameStr, priceStr, descStr);
            updateItemRequest.execute();
        } else {
            AddItemRequest addItemRequest = new AddItemRequest(this, this, nameStr, priceStr, descStr);
            addItemRequest.execute();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void parseJson(String jsonResult) {
        try {
            JSONObject jsonObject = new JSONObject(jsonResult);
            if (jsonObject.getBoolean("success")) {
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(this, "Could not update", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Could not update", Toast.LENGTH_LONG).show();
        }
    }
}
