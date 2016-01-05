package com.fivesquare.koda.fivesquare.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.fivesquare.koda.fivesquare.Controllers.DBController;
import com.fivesquare.koda.fivesquare.Controllers.MainControlller;
import com.fivesquare.koda.fivesquare.Models.CheckIn;
import com.fivesquare.koda.fivesquare.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CheckinActivity extends Activity {

    DBController db = new DBController(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);
        final EditText reviewET = (EditText) findViewById(R.id.reviewET);
        final RatingBar reviewRT = (RatingBar) findViewById(R.id.reviewRatingBar);
        Button submitCheckin = (Button) findViewById(R.id.submitCheckin);


        int pID = 0;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            pID = extras.getInt("PLACE_ID");
        }
        final int plcID = pID ;
        submitCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 1);
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                String time = cal.getTime().toString() ;
                CheckIn checkIn = new CheckIn();
                checkIn.setUserID(MainControlller.user.getID());
                checkIn.setPlaceID(plcID);
                checkIn.setText(reviewET.getText().toString());
                checkIn.setTime(time);
                checkIn.setRate(reviewRT.getRating() * 2);
                if(db.checkIn(checkIn)){
                        Toast.makeText(CheckinActivity.this, "Checked in successfully  ", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(CheckinActivity.this, "Checkein was not successfull  ", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });


    }
}
