package com.fivesquare.koda.fivesquare.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.fivesquare.koda.fivesquare.Controllers.DBController;
import com.fivesquare.koda.fivesquare.Controllers.MainControlller;
import com.fivesquare.koda.fivesquare.Models.Place;
import com.fivesquare.koda.fivesquare.Models.Taste;
import com.fivesquare.koda.fivesquare.R;

import java.util.ArrayList;

public class MyPlacesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DBController db = new DBController(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_places);

        ArrayList<Place> myplaces= new ArrayList<>(db.getUserPlaces(MainControlller.user.getID()));
        Log.e("MYTASTE" , ""+myplaces.size());
        for(int i=0; i<myplaces.size() ;i++){
            Button myButton = new Button(this);
            myButton.setText(myplaces.get(i).getName());
            myButton.setEnabled(false);
            LinearLayout ll = (LinearLayout)findViewById(R.id.myplacesLL);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ll.addView(myButton, lp);
        }

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
            }
        });
        tasteBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addTasteToUser(MainControlller.user.getID(), tastes.get(1).getID());
            }
        });
        tasteBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addTasteToUser(MainControlller.user.getID(), tastes.get(2).getID());
            }
        });
        tasteBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addTasteToUser(MainControlller.user.getID(), tastes.get(3).getID());
            }
        });
        tasteBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addTasteToUser(MainControlller.user.getID(), tastes.get(4).getID());
            }
        });
    }
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Profile) {
            Intent intent = new Intent(MyPlacesActivity.this, HomeActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Home) {
            Intent intent = new Intent(MyPlacesActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_Chat) {
            Intent intent = new Intent(MyPlacesActivity.this, ChatActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Notifications) {
            Intent intent = new Intent(MyPlacesActivity.this, NotificationActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Search) {
            Intent intent = new Intent(MyPlacesActivity.this, SearchActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Tastes) {
            Intent intent = new Intent(MyPlacesActivity.this, TasteActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Logout) {
            Intent intent = new Intent(MyPlacesActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
