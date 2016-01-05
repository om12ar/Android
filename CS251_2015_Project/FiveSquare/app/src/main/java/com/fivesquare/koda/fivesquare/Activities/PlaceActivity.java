package com.fivesquare.koda.fivesquare.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.fivesquare.koda.fivesquare.Controllers.DBController;
import com.fivesquare.koda.fivesquare.Models.Place;
import com.fivesquare.koda.fivesquare.R;

public class PlaceActivity extends Activity {

    Place place ;
    DBController db = new DBController(this);
    int plcID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             plcID = extras.getInt("PLACE_ID");
        }
        place = new Place(db.getPlace(plcID));

        final TextView placeNameTV = (TextView) findViewById(R.id.placeNameTV);
        final TextView descriptionTV = (TextView) findViewById(R.id.descriptionTV);
        final TextView numCheckinsTV = (TextView) findViewById(R.id.numCheckinsTV);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.placeRatingBar);
        Button plcChechInBtn = (Button) findViewById(R.id.plcChechInBtn);
        final TextView postNameTV1 = (TextView) findViewById(R.id.postNameTV1);
        final TextView postNameTV2 = (TextView) findViewById(R.id.postNameTV2);
        final RatingBar postRatingBar2 = (RatingBar) findViewById(R.id.postRatingBar2);

        placeNameTV.setText(place.getName());
        descriptionTV.setText(place.getDescription());
        numCheckinsTV.setText(""+place.getNumberOfCheckIn());
        ratingBar.setRating(place.getRating() / 2);
        ratingBar.setActivated(false);
        postNameTV1.setText(place.getName());
        postNameTV2.setText(place.getName());
        postRatingBar2.setRating(3);
        plcChechInBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                place.setNumberOfCheckIn(place.getNumberOfCheckIn()+1);
                Intent intent = new Intent(PlaceActivity.this, CheckinActivity.class);
                intent.putExtra("PLACE_ID", place.getID());
                startActivity(intent);

                numCheckinsTV.setText(place.getNumberOfCheckIn()+"");
            }
        });


    }

}

