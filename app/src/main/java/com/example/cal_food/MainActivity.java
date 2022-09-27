package com.example.cal_food;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.cal_food.alcohol.Alcohol_Calculator;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity{

    Toolbar toolbar;
    BottomNavigationView mbottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBAdapter db = new DBAdapter(this);
        db.open();
        int numberRows = db.count("food");

        if(numberRows < 1){
            // Run setup
            Toast.makeText(this, "Loading setup...", Toast.LENGTH_LONG).show();
            DBSetupInsert setupInsert = new DBSetupInsert(this);
            setupInsert.insertAllCategories();
            setupInsert.insertAllFood();
            Toast.makeText(this, "Setup completed!", Toast.LENGTH_LONG).show();

        }

        /* Check if there is user in the user table */
        // Count rows in user table
        numberRows = db.count("users");
        db.close();

        //Toast.makeText(this,"datava", Toast.LENGTH_SHORT).show();

        if(numberRows < 1){
            // Sign up
            // Toast.makeText(this, "You are only few fields away from signing up...", Toast.LENGTH_LONG).show();

            Intent i = new Intent(MainActivity.this, SignUp.class);
            startActivity(i);
        }
//        else{
//            Intent i = new Intent(MainActivity.this, FragmentActivity.class);
//            startActivity(i);
//
//        }
        toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher);

        mbottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        mbottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("ResourceType")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                String str ="";
                switch (item.getItemId()){
                    case R.id.action_cal:
                        MainActivity.this.openFragment(Fragment_Calculate.newInstance(str, str));
                        return true;
                    case R.id.action_food:
                        startActivity(new Intent(MainActivity.this, FragmentActivity.class));
                        return true;



                }

                return false;
            }
        });





    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.home_container, fragment);
        beginTransaction.addToBackStack(null);
        beginTransaction.commit();
    }
}