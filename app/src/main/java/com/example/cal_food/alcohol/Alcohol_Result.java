package com.example.cal_food.alcohol;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cal_food.R;
import com.example.cal_food.utils.GlobalFunction;
import com.example.cal_food.utils.SharedPreferenceManager;
import com.example.cal_food.utils.TypefaceManager;



import java.io.PrintStream;


public class Alcohol_Result extends Activity {
    Double BACinPer;
    String TAG = getClass().getSimpleName();
    Bundle extras;
    GlobalFunction globalFunction;
    ImageView iv_close;
    SharedPreferenceManager sharedPreferenceManager;
    TextView tv_alcohol_result;
    TextView tv_alcohol_result_chart;
    TypefaceManager typefaceManager;


//    public void attachBaseContext(Context context) {
//        super.attachBaseContext(uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper.wrap(context));
//    }


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.popup_alcohol);
        this.globalFunction = new GlobalFunction(this);
        this.sharedPreferenceManager = new SharedPreferenceManager(this);

        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
        this.tv_alcohol_result = (TextView) findViewById(R.id.tv_alcohol_result);
        this.tv_alcohol_result_chart = (TextView) findViewById(R.id.tv_alcohol_result_chart);
        this.iv_close = (ImageView) findViewById(R.id.iv_close);





        this.extras = getIntent().getExtras();
        this.BACinPer = Double.valueOf(this.extras.getDouble("BACinPer"));
        if (this.BACinPer.doubleValue() < 0.0d) {
            TextView textView = this.tv_alcohol_result;
            StringBuilder sb = new StringBuilder();
            sb.append(getString(R.string.BAC_level));
            sb.append(" : 0");
            textView.setText(sb.toString());
        } else {
            TextView textView2 = this.tv_alcohol_result;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(getString(R.string.BAC_level));
            sb2.append(" : %.2f");
            textView2.setText(String.format(sb2.toString(), new Object[]{this.BACinPer}));
        }
        this.tv_alcohol_result_chart.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Alcohol_Result.this.startActivity(new Intent(Alcohol_Result.this, Alcohol_Chart.class));
            }
        });
        this.iv_close.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Alcohol_Result.this.onBackPressed();
            }
        });
    }

}
