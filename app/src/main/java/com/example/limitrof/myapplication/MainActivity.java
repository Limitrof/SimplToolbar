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
    //private RecyclerView recyclerView;

    private void initRecycleView(){
      /*  recyclerView=(RecyclerView) findViewById(R.id.list_of_expenses);
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
//method which get fragment  2.
private void replaceFragment(Fragment fragment){
    String backStackName = fragment.getClass().getName();// get name from fragment
    FragmentManager manager = getSupportFragmentManager();// get object which stored history of the fragments using
    boolean fragmentPopped = manager.popBackStackImmediate(backStackName,0);//pop the last fragment transition from the manager's fragment back stack. If there is nothing to pop, false is returned +  performs the operation immediately inside of the call
    if( !fragmentPopped && manager.findFragmentByTag(backStackName) == null){//if chosen fragment not current (selected right now) && new fragment Name exist (not null)
        FragmentTransaction ft = manager.beginTransaction();//starting transaction
        ft.replace(R.id.main_container,fragment,backStackName);//at first transaction step - replace old fragment on NEW fragment (fragment) with name (backStackName)
        ft.addToBackStack(backStackName);//at second transaction step - add new fragments name to stack (for using in future if "PREVIOUS" click)
        ft.commit();//start both transaction steps
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
                ExpensesFragment cfv = new ExpensesFragment();
                replaceFragment(cfv);
                break;
            case R.id.drawer_categories:
                CategoriesFragment cf = new CategoriesFragment();
                replaceFragment(cf);
                break;
            case R.id.drawer_statistics:
                StatisticsFragment sf = new StatisticsFragment();
                replaceFragment(sf);
                break;
            case R.id.drawer_settings:
                SettingsFragment stf = new SettingsFragment();
                replaceFragment(stf);
                break;

        }
        return true;

    }
}
