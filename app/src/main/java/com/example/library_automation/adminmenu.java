package com.example.library_automation;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.library_automation.databinding.ActivityMenuBinding;
import com.google.android.material.navigation.NavigationView;

public class adminmenu extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration1;
    private ActivityMenuBinding binding;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //binding = ActivityMenuBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());
       // DrawerLayout drawerLayout = binding.drawerLayout;
        setContentView(R.layout.activity_adminmenu);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //setSupportActionBar(binding.appBarMenu.toolbar);
        drawerLayout = findViewById(R.id.my_drawer_layout);
        NavigationView navigationView =(NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();

       mAppBarConfiguration1 = new AppBarConfiguration.Builder(R.id.nav_adminhome, R.id.nav_adduser, R.id.nav_deleteuser,R.id.nav_readuser).setOpenableLayout(drawerLayout).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_adminmenu);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration1);
        NavigationUI.setupWithNavController(navigationView, navController);
    }
 @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.adminmenu_, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_adminmenu);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration1)
                || super.onSupportNavigateUp();
    }
}
