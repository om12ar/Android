package com.simplecalculator.omar.simplecalculator;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.print.PrintHelper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private boolean equalPressed = false ;
    private boolean lastWasOperator = false ;
    String result = "0.0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView tv = (TextView)findViewById(R.id.operationTV);
        tv.setText("");
        tv.setMovementMethod(new ScrollingMovementMethod());

        if (savedInstanceState != null) {

        }


    }
    public void buttonPressed(View view){
        Button b = (Button) view ;
        TextView opTV = (TextView)findViewById(R.id.operationTV);
        TextView resTV = (TextView)findViewById(R.id.resultTV);
        String calculation =b.getTag().toString();
        System.err.println(calculation);
        switch (calculation){
            case "clear":
                opTV.setText("");
                resTV.setText("");
                result="0.0";
                equalPressed = false ;
                lastWasOperator = false ;
                break;
            case "backspace":
                int length = opTV.getText().length();
                if ( length > 0) {
                    opTV.setText(opTV.getText().toString().substring(0, length - 1));
                }
                equalPressed = false ;
                lastWasOperator = false ;
                break;
            case "equal":
                System.err.println(" EQUAL ");
                try{
                    result = Calculator.calculate(opTV.getText().toString());
                    resTV.setText(result);
                }
                catch (Exception e){
                    //resTV.setText(e.toString());
                    resTV.setText("Enter a valid operation");
                }

                equalPressed = true ;
                break;
            case "copy":
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", result);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(this, "Result copied",
                        Toast.LENGTH_SHORT).show();
                break;
            case "operator":
                if(!lastWasOperator){
                    lastWasOperator = true;
                    opTV.append(b.getText());
                }
                if(equalPressed==true){
                    opTV.setText(result);
                    opTV.append(b.getText());
                    equalPressed =false;
                }

                break;
            case "number":
                opTV.append(b.getText());
                if(equalPressed==true){
                    opTV.setText("");
                    opTV.append(b.getText());
                    equalPressed =false;
                }
                equalPressed = false ;
                lastWasOperator = false ;
                break;
            default:

             System.err.println("BREAK DEFULT");
                break;

        }


        System.out.println(b.getTag().toString());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}

