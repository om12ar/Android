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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fivesquare.koda.fivesquare.Controllers.DBController;
import com.fivesquare.koda.fivesquare.Controllers.MainControlller;
import com.fivesquare.koda.fivesquare.R;

public class MyProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static String TAG = "MyProfileActivity";
    DBController db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DBController(this);

        setContentView(R.layout.myprofile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        TextView nameTV = (TextView) findViewById(R.id.profileNameTV);
        nameTV.setText(MainControlller.user.getName());
        final Button tasteActBtn = (Button) findViewById(R.id.tasteBtn);
        tasteActBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyProfileActivity.this, MyTastesActivity.class);
                startActivity(intent);
            }
        });
        final Button placesBtn = (Button) findViewById(R.id.placesBtn);
        placesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyProfileActivity.this, MyPlacesActivity.class);
                startActivity(intent);
            }
        });
    }
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Profile) {
            Intent intent = new Intent(MyProfileActivity.this, MyProfileActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Home) {
            Intent intent = new Intent(MyProfileActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_Chat) {
            Intent intent = new Intent(MyProfileActivity.this, ChatActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Notifications) {
            Intent intent = new Intent(MyProfileActivity.this, NotificationActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Search) {
            Intent intent = new Intent(MyProfileActivity.this, SearchActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Tastes) {
            Intent intent = new Intent(MyProfileActivity.this, TasteActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Logout) {
            Intent intent = new Intent(MyProfileActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
