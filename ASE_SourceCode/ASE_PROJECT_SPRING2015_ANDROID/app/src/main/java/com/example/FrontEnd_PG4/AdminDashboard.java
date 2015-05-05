package com.example.FrontEnd_PG4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Gurrala on 2/25/2015.
 */
public class AdminDashboard extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_dashboard);
    }

    public void onChangeItemDetails(View view) {
        Intent intent = new Intent(this, AdminItemActivity.class);
        intent.putExtra("subCategoryId", 1);
        startActivity(intent);
    }

    public void onViewOrders(View view) {
        Intent intent = new Intent(this, EmployeeDashboard.class);
        intent.putExtra("admin", true);
        startActivity(intent);
    }

    public void onAddItem(View view) {
        Intent intent = new Intent(this, ItemDetailActivity.class);
        intent.putExtra("new", true);
        startActivity(intent);
    }
}