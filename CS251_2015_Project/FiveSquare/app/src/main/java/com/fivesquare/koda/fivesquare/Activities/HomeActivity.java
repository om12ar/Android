package com.fivesquare.koda.fivesquare.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fivesquare.koda.fivesquare.Controllers.DBController;
import com.fivesquare.koda.fivesquare.Controllers.MainControlller;
import com.fivesquare.koda.fivesquare.Models.Place;
import com.fivesquare.koda.fivesquare.R;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final String TAG = "HomeActivity";
    DBController db  = new DBController(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView welcomeTextView = (TextView) findViewById(R.id.welcomeTextView);
        welcomeTextView.setText("Welcome "+ MainControlller.getUser().getName());
       setSupportActionBar(toolbar);
        
        fillPlaces();
        
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void fillPlaces() {
        final ArrayList<Place> places =  new ArrayList<>(db.getAllPlaces());
        Log.e(TAG+ "places" , ""+places.size() );
        for(int i =1 ;i <= 4 ; i++){
            String nameid = "pNameTV"+i;
            int resID = getResources().getIdentifier(nameid, "id", "com.fivesquare.koda.fivesquare");
            TextView plcNameTV = (TextView) findViewById(resID);
            nameid = "description"+i;
            resID = getResources().getIdentifier(nameid, "id", "com.fivesquare.koda.fivesquare");
            TextView desc = (TextView) findViewById(resID);
            nameid = "ratingBar"+i;
            resID = getResources().getIdentifier(nameid, "id", "com.fivesquare.koda.fivesquare");
            RatingBar ratingBar = (RatingBar)findViewById(resID);
            ratingBar.setActivated(false);

            plcNameTV.setText(places.get(i-1).getName());
            desc.setText(places.get(i-1).getDescription());
            ratingBar.setRating(places.get(i-1).getRating()/2);

        }
        Button saveBlc1 = (Button) findViewById(R.id.saveBtn1);
        Button saveBlc2 = (Button) findViewById(R.id.saveBtn2);
        Button saveBlc3 = (Button) findViewById(R.id.saveBtn3);
        Button saveBlc4 = (Button) findViewById(R.id.saveBtn4);

        saveBlc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = db.savePlaceToUser(MainControlller.user.getID() , places.get(0).getID());
                Log.e(TAG+"getplaces" , "SELECT * FROM PLACE WHERE PLC_ID = " +  places.get(0).getID());
                if(id == -1 ){

                }
                else{
                    Toast.makeText(HomeActivity.this, "Place Saved  ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        saveBlc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = db.savePlaceToUser(MainControlller.user.getID() , places.get(1).getID());
                Log.e(TAG+"getplaces" , "SELECT * FROM PLACE WHERE PLC_ID = " +  places.get(1).getID());
                if(id == -1 ){
                    Toast.makeText(HomeActivity.this, "Place Not Saved  ", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(HomeActivity.this, "Place Saved  ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        saveBlc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG+"getplaces" , "SELECT * FROM PLACE WHERE PLC_ID = " +  places.get(2).getID());
                int id = db.savePlaceToUser(MainControlller.user.getID() , places.get(2).getID());
                if(id == -1 ){
                    Toast.makeText(HomeActivity.this, "Place Already Saved  ", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(HomeActivity.this, "Place Saved  ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        saveBlc4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG+"getplaces" , "SELECT * FROM PLACE WHERE PLC_ID = " +  places.get(3).getID());
                int id = db.savePlaceToUser(MainControlller.user.getID() , places.get(3).getID());
                if(id == -1 ){
                    Toast.makeText(HomeActivity.this, "Place Already Saved  ", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(HomeActivity.this, "Place Saved  ", Toast.LENGTH_SHORT).show();
                }
            }
        });


        TextView plcNameTV1 = (TextView) findViewById(R.id.pNameTV1);
        TextView plcNameTV2 = (TextView) findViewById(R.id.pNameTV2);
        TextView plcNameTV3 = (TextView) findViewById(R.id.pNameTV3);
        TextView plcNameTV4 = (TextView) findViewById(R.id.pNameTV4);

        plcNameTV1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), PlaceActivity.class);
                intent.putExtra("PLACE_ID", places.get(0).getID());
                startActivity(intent);
            }
        });
        plcNameTV2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), PlaceActivity.class);
                intent.putExtra("PLACE_ID", places.get(1).getID());
                startActivity(intent);
            }
        });
        plcNameTV3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), PlaceActivity.class);
                intent.putExtra("PLACE_ID", places.get(2).getID());
                startActivity(intent);
            }
        });
        plcNameTV4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), PlaceActivity.class);
                intent.putExtra("PLACE_ID", places.get(3).getID());
                startActivity(intent);
            }
        });






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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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

        if (id == R.id.nav_Profile) {
            Intent intent = new Intent(HomeActivity.this, MyProfileActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Home) {
            Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_Chat) {
            Intent intent = new Intent(HomeActivity.this, ChatActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Notifications) {
            Intent intent = new Intent(HomeActivity.this, NotificationActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Search) {
            Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Tastes) {
            Intent intent = new Intent(HomeActivity.this, TasteActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Logout) {
            Log.e("LOGOUT" ,"I'm HERE /");
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
