package com.fivesquare.koda.fivesquare.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.fivesquare.koda.fivesquare.Controllers.DBController;
import com.fivesquare.koda.fivesquare.R;

/**
 * Created by Nona on 12/26/2015.
 */
public class NotificationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {


    private static String TAG = "NotificationActivity";
    DBController db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DBController(this);

        //setContentView(R.layout.notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Profile) {
            Intent intent = new Intent(NotificationActivity.this, MyProfileActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Home) {
            Intent intent = new Intent(NotificationActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_Chat) {
            Intent intent = new Intent(NotificationActivity.this, ChatActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Notifications) {
            Intent intent = new Intent(NotificationActivity.this, NotificationActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Search) {
            Intent intent = new Intent(NotificationActivity.this, SearchActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Tastes) {
            Intent intent = new Intent(NotificationActivity.this, TasteActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Logout) {
            Intent intent = new Intent(NotificationActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
