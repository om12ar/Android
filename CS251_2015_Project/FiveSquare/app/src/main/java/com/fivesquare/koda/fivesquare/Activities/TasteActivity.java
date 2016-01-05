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
import android.widget.Toast;

import com.fivesquare.koda.fivesquare.Controllers.DBController;
import com.fivesquare.koda.fivesquare.Controllers.MainControlller;
import com.fivesquare.koda.fivesquare.Models.Taste;
import com.fivesquare.koda.fivesquare.R;

import java.util.ArrayList;

/**
 * Created by Nona on 12/26/2015.
 */
public class TasteActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static String TAG = "TasteActivity";
    ArrayList<Button> tasteBtns = new ArrayList<>();
    DBController db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DBController(this);

        setContentView(R.layout.activity_tastes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initTasteList();
    }

    private void initTasteList() {

        final ArrayList<Taste> tastes = new ArrayList<>(db.getAllTastes());



        Button tasteBtn1 = (Button) findViewById(R.id.tasteBtn1);
        tasteBtn1.setText(tastes.get(0).getName());
        Button tasteBtn2 = (Button) findViewById(R.id.tasteBtn2);
        tasteBtn2.setText(tastes.get(1).getName());
        Button tasteBtn3 = (Button) findViewById(R.id.tasteBtn3);
        tasteBtn3.setText(tastes.get(2).getName());
        Button tasteBtn4 = (Button) findViewById(R.id.tasteBtn4);
        tasteBtn4.setText(tastes.get(3).getName());
        Button tasteBtn5 = (Button) findViewById(R.id.tasteBtn5);
        tasteBtn5.setText(tastes.get(4).getName());

        tasteBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addTasteToUser(MainControlller.user.getID(), tastes.get(0).getID());
                Toast.makeText(TasteActivity.this, "Taste Added ", Toast.LENGTH_SHORT).show();
            }
        });
        tasteBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addTasteToUser(MainControlller.user.getID(), tastes.get(1).getID());
                Toast.makeText(TasteActivity.this, "Taste Added ", Toast.LENGTH_SHORT).show();
            }
        });
        tasteBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addTasteToUser(MainControlller.user.getID(), tastes.get(2).getID());
                Toast.makeText(TasteActivity.this, "Taste Added ", Toast.LENGTH_SHORT).show();
            }
        });
        tasteBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addTasteToUser(MainControlller.user.getID(), tastes.get(3).getID());
                Toast.makeText(TasteActivity.this, "Taste Added ", Toast.LENGTH_SHORT).show();
            }
        });
        tasteBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addTasteToUser(MainControlller.user.getID(), tastes.get(4).getID());
                Toast.makeText(TasteActivity.this, "Taste Added ", Toast.LENGTH_SHORT).show();
            }
        });




    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Profile) {
            Intent intent = new Intent(TasteActivity.this, MyProfileActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Home) {
            Intent intent = new Intent(TasteActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_Chat) {
            Intent intent = new Intent(TasteActivity.this, ChatActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Notifications) {
            Intent intent = new Intent(TasteActivity.this, NotificationActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Search) {
            Intent intent = new Intent(TasteActivity.this, SearchActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Tastes) {
            Intent intent = new Intent(TasteActivity.this, TasteActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Logout) {
            Intent intent = new Intent(TasteActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
