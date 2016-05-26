package com.example.limitrof.myapplication;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private RecyclerView recyclerView;

    private void initRecycleView(){
       /* recyclerView=(RecyclerView) findViewById(R.id.list_of_expenses);
        //mast set layout-manager ror work with layouts
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ExpensesAdapter expensesAdapter=new ExpensesAdapter(getExpenses());
        recyclerView.setAdapter(expensesAdapter);*/
    }
    private List<Expense> getExpenses(){
        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense("Cinema","120"));
        expenses.add(new Expense("Cinema2","9120"));
        expenses.add(new Expense("Cinema3","8120"));
        expenses.add(new Expense("Cinema4","7120"));
        return expenses;
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupActionBar();
        setupDrawerLayout();
        //initRecycleView();
        if(savedInstanceState == null){
            replaceFragment(new ExpensesFragment());
        }
    }
//for work with activity
private void replaceFragment(Fragment fragment){
    String backStackName = fragment.getClass().getName();
    FragmentManager manager = getSupportFragmentManager();
    boolean fragmentPopped = manager.popBackStackImmediate(backStackName,0);
    if(! fragmentPopped && manager.findFragmentByTag(backStackName) != null){
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.main_container,fragment,backStackName);
        ft.addToBackStack(backStackName);
        ft.commit();
    }
}

    private void setupActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    private void setupDrawerLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        setTitle(getString(R.string.app_name));
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        switch (item.getItemId()){
            case R.id.drawer_expenses:
                CategoriesFragment cfv = new CategoriesFragment();
                replaceFragment(cfv);
                break;
            case R.id.drawer_categories:
                CategoriesFragment cf = new CategoriesFragment();
                replaceFragment(cf);
                break;

        }
        return true;

    }
}
