package com.example.FrontEnd_PG4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.FrontEnd_PG4.adapters.CategoryAdapter;
import com.example.FrontEnd_PG4.beans.Category;
import com.example.FrontEnd_PG4.beans.CategoryList;
import com.example.FrontEnd_PG4.database.DBHelper;
import com.example.FrontEnd_PG4.request.ItemRequest;
import com.example.FrontEnd_PG4.request.JsonHandler;
import com.example.FrontEnd_PG4.util.ConfigUtil;
import com.example.FrontEnd_PG4.util.CountHolder;
import com.example.FrontEnd_PG4.util.CustomView;

import org.json.JSONArray;
import org.json.JSONObject;

public class AdminItemActivity extends Activity implements JsonHandler, AdapterView.OnItemClickListener {
    private ListView listView;
    private CategoryList categoryList;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_layout);
        listView = (ListView) findViewById(R.id.itemList);
        EditText editText = (EditText) findViewById(R.id.searchTxt);
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (categoryAdapter != null) {
                    categoryAdapter.getFilter().filter(s);
                }
            }
        });
        ItemRequest itemRequest = new ItemRequest(this, this, null);
        itemRequest.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.item_layout, menu);
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
            JSONArray array = jsonObject.getJSONArray("itemBeans");
            categoryList = new CategoryList();
            for (int i = 0; i < array.length(); i++) {
                JSONObject categoryBean = array.getJSONObject(i);
                Category category = new Category();
                category.setDescription(categoryBean.getString("description"));
                category.setName(categoryBean.getString("name"));
                category.setId(categoryBean.getLong("id"));
                category.setPrice(categoryBean.getDouble("price"));
                categoryList.getCategories().add(category);
            }
            categoryAdapter = new CategoryAdapter(this, categoryList);
            listView.setAdapter(categoryAdapter);
            listView.setOnItemClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ConfigUtil.setCategory(categoryList.getCategories().get(position));
        Intent intent = new Intent(this, ItemDetailActivity.class);
        startActivity(intent);
    }
}
