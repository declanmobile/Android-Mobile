package com.example.declan.trafficinscotland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/**
 Declan Gibb - S1345890.
 */
public class HomeScreen extends AppCompatActivity {

    public Button button1;
    public Button button2;
   // public Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.currentButton);
        button1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this,CurrentIncidentsActivity.class);
                startActivity(intent);
            }

        });

        button2 = (Button)findViewById(R.id.plannedButton);
        button2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this,PlannedRoadworksActivity.class);
                startActivity(intent);
            }

        });
      //  button2 = (Button) findViewById(R.id.plannedButton);

    }
 /*
        public void test() {
            button1 = (Button) findViewById(R.id.currentButton);
            button1.setOnClickListener(new View.OnClickListener()

            {
                public void onClick(View v) {
                    Intent intent = new Intent(HomeScreen.this,CurrentIncidentsActivity.class);
                    startActivity(intent);
                }

            });
        }
*/
        /*
        public void test2(){
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(HomeScreen.this, PlannedRoadworksActivity.class);
                startActivity(myIntent);
            }
        });


    }

    */
}
