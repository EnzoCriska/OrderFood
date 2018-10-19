package com.enzo.greadfood.presentation.UI.activity;

import android.app.ProgressDialog;
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
import com.enzo.greadfood.presentation.UI.adapter.AdapterRecyclerView;
import com.enzo.greadfood.presentation.presenter.HomePresenter;
import com.enzo.greadfood.util.Common;
import com.enzo.greadfood.util.CustomLog;
import com.enzo.greadfood.util.Injection;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterRecyclerView.OnClickPhoto, HomePresenter.View {
    private TextView nav_User;
    private RecyclerView recyclerView;
    private ArrayList<Category> list = new ArrayList<>();
    private AdapterRecyclerView adapter;
    private HomePresenter homePresenter;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
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

        nav_User = (TextView) headerView.findViewById(R.id.Nav_UserName);
        nav_User.setText(Common.currentUser.getName());
        init();
    }

    public void init(){
        progressDialog = new ProgressDialog(this);
        homePresenter = Injection.getInstance().getHomePresenter(this);
        adapter = new AdapterRecyclerView(this, this);
        recyclerView = findViewById(R.id.recyclerMenu);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        adapter .setiData(new AdapterRecyclerView.IData() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Category getItem(int pos) {
                return list.get(pos);
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        homePresenter.getCategoryFromFireBase();
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
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu) {
            // Handle the mebu action
        } else if (id == R.id.nav_card) {

        } else if (id == R.id.nav_orders) {

        } else if (id == R.id.log_out) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClickItemPhoto(int position) {
        CustomLog.i("HOME", "View details");
    }

    @Override
    public void showCategory(ArrayList<Category> list) {
        this.list = list;
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
