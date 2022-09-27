package com.example.cal_food.alcohol;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cal_food.R;
import com.example.cal_food.utils.GlobalFunction;
import com.example.cal_food.utils.SharedPreferenceManager;
import com.example.cal_food.utils.TypefaceManager;



import java.io.PrintStream;
import java.util.ArrayList;


public class Alcohol_Calculator extends Activity {
    double BACinPer;
    String TAG = getClass().getSimpleName();
    ArrayAdapter<String> adapter_gender;
    ArrayAdapter<String> adapter_time;
    ArrayAdapter<String> adapter_weight;
    double alcohol_level;
    ArrayList<String> arraylist_gender = new ArrayList<>();
    ArrayList<String> arraylist_time = new ArrayList<>();
    ArrayList<String> arraylist_weight = new ArrayList<>();
    EditText et_alcohol_level;
    EditText et_drinkvolume;
    EditText et_timepassed;
    EditText et_weight;
    String gender;
    double gender_ratio;
    GlobalFunction globalFunction;
    ImageView iv_back;
    ListView listViewGender;
    ListView listViewHeight;
    ListView listViewWeight;
    private PopupWindow popupWindowGender;
    private PopupWindow popupWindowTime;
    private PopupWindow popupWindowWeight;
    SharedPreferenceManager sharedPreferenceManager;
    double timePassed;
    String timePassed_unit;
    double total_alcohol;
    TextView tv_alcohol;
    TextView tv_gender;
    TextView tv_genderunit;
    TextView tv_search_bloodalcohol_content;
    TextView tv_timeunit;
    TextView tv_weightunit;
    TypefaceManager typefaceManager;
    double volume;
    double weight;
    String weight_unit;


//    public void attachBaseContext(Context context) {
//        super.attachBaseContext(uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper.wrap(context));
//    }


    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.alcohol_calculator);
        this.globalFunction = new GlobalFunction(this);
        this.sharedPreferenceManager = new SharedPreferenceManager(this);
        this.typefaceManager = new TypefaceManager(getAssets(), this);
        this.globalFunction.set_locale_language();
        this.et_drinkvolume = (EditText) findViewById(R.id.et_drinkvolume);
        this.et_alcohol_level = (EditText) findViewById(R.id.et_alcohol_level);
        this.et_timepassed = (EditText) findViewById(R.id.et_timepassed);
        this.et_weight = (EditText) findViewById(R.id.et_weight);
        this.tv_search_bloodalcohol_content = (TextView) findViewById(R.id.tv_search_bloodalcohol_content);
        this.tv_timeunit = (TextView) findViewById(R.id.tv_timeunit);
        this.tv_weightunit = (TextView) findViewById(R.id.tv_weightunit);
        this.tv_gender = (TextView) findViewById(R.id.tv_gender);
        this.tv_genderunit = (TextView) findViewById(R.id.tv_genderunit);
        this.tv_alcohol = (TextView) findViewById(R.id.tv_alcohol);

        this.iv_back = (ImageView) findViewById(R.id.iv_back);

        if (VERSION.SDK_INT >= 21) {
            getWindow().setFlags(67108864, 67108864);
        }
        this.iv_back.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Alcohol_Calculator.this.onBackPressed();
            }
        });
        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
        this.arraylist_time.clear();
        this.arraylist_time.add(getString(R.string.hour));
        this.arraylist_time.add(getString(R.string.Minute));
        this.arraylist_time.add(getString(R.string.Day));
        this.adapter_time = new ArrayAdapter<>(this, R.layout.spinner_item, R.id.text1, this.arraylist_time);
        this.arraylist_weight.clear();
        this.arraylist_weight.add(getString(R.string.lbs));
        this.arraylist_weight.add(getString(R.string.kg));
        this.adapter_weight = new ArrayAdapter<>(this, R.layout.spinner_item, R.id.text1, this.arraylist_weight);
        this.arraylist_gender.clear();
        this.arraylist_gender.add(getString(R.string.Male));
        this.arraylist_gender.add(getString(R.string.Female));
        this.adapter_gender = new ArrayAdapter<>(this, R.layout.spinner_item, R.id.text1, this.arraylist_gender);
        this.tv_weightunit.setOnClickListener(showPopupWindowWeight());
        this.tv_timeunit.setOnClickListener(showPopupWindowTime());
        this.tv_genderunit.setOnClickListener(showPopupWindowGender());
        this.tv_search_bloodalcohol_content.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (Alcohol_Calculator.this.et_drinkvolume.getText().toString().trim().equals("") || Alcohol_Calculator.this.et_drinkvolume.getText().toString().trim().equals(".")) {
                    Toast.makeText(Alcohol_Calculator.this.getApplicationContext(), Alcohol_Calculator.this.getString(R.string.Enter_Drink_volume), 0).show();
                } else if (Alcohol_Calculator.this.et_alcohol_level.getText().toString().trim().equals("") || Alcohol_Calculator.this.et_alcohol_level.getText().toString().trim().equals(".")) {
                    Toast.makeText(Alcohol_Calculator.this.getApplicationContext(), Alcohol_Calculator.this.getString(R.string.Enter_Alcohol_level), 0).show();
                } else if (Alcohol_Calculator.this.et_timepassed.getText().toString().trim().equals("") || Alcohol_Calculator.this.et_timepassed.getText().toString().trim().equals(".")) {
                    Toast.makeText(Alcohol_Calculator.this.getApplicationContext(), Alcohol_Calculator.this.getString(R.string.Enter_time_passed_sence_drinking), 0).show();
                } else if (Alcohol_Calculator.this.et_weight.getText().toString().trim().equals("") || Alcohol_Calculator.this.et_weight.getText().toString().trim().equals(".")) {
                    Toast.makeText(Alcohol_Calculator.this.getApplicationContext(), Alcohol_Calculator.this.getString(R.string.Enter_weight), 0).show();
                } else {
                    Alcohol_Calculator.this.volume = Double.parseDouble(Alcohol_Calculator.this.et_drinkvolume.getText().toString().trim());
                    Alcohol_Calculator.this.volume *= 0.033814d;
                    Alcohol_Calculator.this.alcohol_level = Double.parseDouble(Alcohol_Calculator.this.et_alcohol_level.getText().toString().trim());
                    Alcohol_Calculator.this.total_alcohol = (Alcohol_Calculator.this.volume * Alcohol_Calculator.this.alcohol_level) / 100.0d;
                    Alcohol_Calculator.this.get_genderratio();
                    Alcohol_Calculator.this.get_weight();
                    Alcohol_Calculator.this.get_time_passed();
                    Alcohol_Calculator.this.BACinPer = (((Alcohol_Calculator.this.total_alcohol * 5.14d) / Alcohol_Calculator.this.weight) * Alcohol_Calculator.this.gender_ratio) - (Alcohol_Calculator.this.timePassed * 0.015d);
                    Intent intent = new Intent(Alcohol_Calculator.this, Alcohol_Result.class);
                    intent.putExtra("BACinPer", Alcohol_Calculator.this.BACinPer);
                    Alcohol_Calculator.this.startActivity(intent);
                }
            }
        });
    }

    private OnClickListener showPopupWindowTime() {
        return new OnClickListener() {
            public void onClick(View view) {
                Alcohol_Calculator.this.popupWindowTime().showAsDropDown(view, 0, 0);
            }
        };
    }

    private OnClickListener showPopupWindowWeight() {
        return new OnClickListener() {
            public void onClick(View view) {
                Alcohol_Calculator.this.popupWindowWeight().showAsDropDown(view, 0, 0);
            }
        };
    }

    private OnClickListener showPopupWindowGender() {
        return new OnClickListener() {
            public void onClick(View view) {
                Alcohol_Calculator.this.popupWindowGender().showAsDropDown(view, 0, 0);
            }
        };
    }


    public PopupWindow popupWindowTime() {
        this.popupWindowTime = new PopupWindow(this);
        this.listViewHeight = new ListView(this);
        this.listViewHeight.setDividerHeight(0);
        this.listViewHeight.setAdapter(this.adapter_time);
        this.listViewHeight.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Alcohol_Calculator.this.tv_timeunit.setText((CharSequence) Alcohol_Calculator.this.arraylist_time.get(i));
                Alcohol_Calculator.this.dismissPopupTime();
            }
        });
        this.popupWindowTime.setFocusable(true);
        this.popupWindowTime.setWidth(this.tv_timeunit.getMeasuredWidth());
        this.popupWindowTime.setHeight(-2);
        this.popupWindowTime.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), 17170443));
        this.popupWindowTime.setContentView(this.listViewHeight);
        return this.popupWindowTime;
    }


    public PopupWindow popupWindowWeight() {
        this.popupWindowWeight = new PopupWindow(this);
        this.listViewWeight = new ListView(this);
        this.listViewWeight.setDividerHeight(0);
        this.listViewWeight.setAdapter(this.adapter_weight);
        this.listViewWeight.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Alcohol_Calculator.this.tv_weightunit.setText((CharSequence) Alcohol_Calculator.this.arraylist_weight.get(i));
                Alcohol_Calculator.this.dismissPopupWeight();
            }
        });
        this.popupWindowWeight.setFocusable(true);
        this.popupWindowWeight.setWidth(this.tv_weightunit.getMeasuredWidth());
        this.popupWindowWeight.setHeight(-2);
        this.popupWindowWeight.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), 17170443));
        this.popupWindowWeight.setContentView(this.listViewWeight);
        return this.popupWindowWeight;
    }


    public PopupWindow popupWindowGender() {
        this.popupWindowGender = new PopupWindow(this);
        this.listViewGender = new ListView(this);
        this.listViewGender.setDividerHeight(0);
        this.listViewGender.setAdapter(this.adapter_gender);
        this.listViewGender.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Alcohol_Calculator.this.tv_gender.setText((CharSequence) Alcohol_Calculator.this.arraylist_gender.get(i));
                Alcohol_Calculator.this.tv_genderunit.setText((CharSequence) Alcohol_Calculator.this.arraylist_gender.get(i));
                Alcohol_Calculator.this.dismissPopupGender();
            }
        });
        this.popupWindowGender.setFocusable(true);
        this.popupWindowGender.setWidth(this.tv_genderunit.getMeasuredWidth());
        this.popupWindowGender.setHeight(-2);
        this.popupWindowGender.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), 17170443));
        this.popupWindowGender.setContentView(this.listViewGender);
        return this.popupWindowGender;
    }


    public void dismissPopupTime() {
        if (this.popupWindowTime != null) {
            this.popupWindowTime.dismiss();
        }
    }


    public void dismissPopupWeight() {
        if (this.popupWindowWeight != null) {
            this.popupWindowWeight.dismiss();
        }
    }


    public void dismissPopupGender() {
        if (this.popupWindowGender != null) {
            this.popupWindowGender.dismiss();
        }
    }

    public void get_genderratio() {
        this.gender = this.tv_gender.getText().toString().trim();
        if (this.gender.equalsIgnoreCase(getString(R.string.Male))) {
            this.gender_ratio = 0.73d;
        } else {
            this.gender_ratio = 0.66d;
        }
    }

    public void get_weight() {
        if (!this.et_weight.getText().toString().trim().equals(".")) {
            this.weight = Double.parseDouble(this.et_weight.getText().toString().trim());
            this.weight_unit = this.tv_weightunit.getText().toString().trim();
            if (this.weight_unit.equalsIgnoreCase(getString(R.string.kg))) {
                this.weight *= 2.204622d;
                return;
            }
            return;
        }
        this.weight = 1.0d;
    }

    public void get_time_passed() {
        if (!this.et_timepassed.getText().toString().equals(".")) {
            this.timePassed = Double.parseDouble(this.et_timepassed.getText().toString().trim());
            this.timePassed_unit = this.tv_timeunit.getText().toString().trim();
            if (this.timePassed_unit.equalsIgnoreCase(getString(R.string.Minute))) {
                this.timePassed /= 60.0d;
            } else if (this.timePassed_unit.equalsIgnoreCase(getString(R.string.day))) {
                this.timePassed *= 24.0d;
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAfterTransition(this);
    }



}
