package com.enzo.greadfood.presentation.UI.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.enzo.greadfood.R;
import com.enzo.greadfood.domain.model.Category;
import com.enzo.greadfood.domain.model.Food;
import com.enzo.greadfood.presentation.UI.adapter.AdapterRecyclerViewCategory;
import com.enzo.greadfood.presentation.UI.adapter.FoodListAdapter;
import com.enzo.greadfood.presentation.presenter.FoodListPresenter;
import com.enzo.greadfood.util.Common;
import com.enzo.greadfood.util.Injection;

import java.util.ArrayList;

public class FoodListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FoodListAdapter.OnClickFood, FoodListPresenter.View {
    ArrayList<Food> foodList = new ArrayList<>();
    ProgressDialog progressDialog;
    TextView nav_User;
    RecyclerView recyclerView;
    FoodListAdapter adapter;
    FoodListPresenter foodListPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_food_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Food list");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);

//        nav_User = (TextView) headerView.findViewById(R.id.Nav_UserName);
//        nav_User.setText(Common.currentUser.getName());
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String id = intent.getStringExtra("Menu_Id");
        foodListPresenter.getFoodListFromFireBase(id);
    }

    public void init() {
        progressDialog = new ProgressDialog(this);
        foodListPresenter = Injection.getInstance().getFoodListPresenter(this);
        adapter = new FoodListAdapter(this, this);
        recyclerView = findViewById(R.id.recyclerFood);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        adapter.setiData(new FoodListAdapter.IData() {
            @Override
            public int getCount() {
                return foodList.size();
            }

            @Override
            public Food getItem(int pos) {
                return foodList.get(pos);
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_view_food_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings_food) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_food) {
            // Handle the mebu action
            Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_card_food) {
            Toast.makeText(this, "Card", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_orders_food) {
            Toast.makeText(this, "Order", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.log_out_food) {
            Toast.makeText(this, "Log out", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClickItemFood(int position) {

    }

    @Override
    public void showFoodList(ArrayList<Food> list) {
        this.foodList = list;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
}
