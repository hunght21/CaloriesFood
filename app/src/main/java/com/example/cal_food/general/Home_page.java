package com.example.cal_food.general;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cal_food.R;
import com.example.cal_food.utils.SharedPreferenceManager;
import com.example.cal_food.utils.TypefaceManager;

import java.util.ArrayList;


public class Home_page extends Fragment {
    private static final String TAG = "Home_page";

    ArrayList<All_Home> all_home = new ArrayList<>();
    //    AppChecker appChecker;
//    String[] arr_color;
    String[] arr_image;
    String[] arr_title;
    boolean doubleBackToExitPressedOnce = false;
    EditText et_sesrch;
    Home_Adapter home_adapter;
    //    ImageView iv_view_more;
 //   private JazzyRecyclerViewScrollListener jazzyScrollListener;
//    LinearLayoutManager linearLayoutManager;
    GridLayoutManager manager;
    RecyclerView recyclerview_homepage;
    //    public RecyclerView recyclerview_recommendedapp;
    SharedPreferenceManager sharedPreferenceManager;

    TypefaceManager typefaceManager;


//    public void attachBaseContext(Context context) {
//        super.attachBaseContext(uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper.wrap(context));
//    }


    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.home_cal, viewGroup, false);






        this.sharedPreferenceManager = new SharedPreferenceManager(getActivity());
        this.typefaceManager = new TypefaceManager(getActivity().getAssets(), getActivity());
//        this.appChecker = new AppChecker(this, this.sharedPreferenceManager.get_Remove_Ad().booleanValue());
        this.et_sesrch = (EditText) inflate.findViewById(R.id.et_sesrch);
//        this.iv_view_more = (ImageView) findViewById(R.id.iv_view_more);
        this.recyclerview_homepage = (RecyclerView) inflate.findViewById(R.id.recyclerview_homepage);



//        this.linearLayoutManager = new LinearLayoutManager(getActivity());
        this.manager = new GridLayoutManager(getActivity(), 2);
        this.typefaceManager = new TypefaceManager(getActivity().getAssets(), getActivity());

        this.recyclerview_homepage.setLayoutManager(this.manager);
        this.home_adapter = new Home_Adapter(getActivity(), this.all_home, getContext());

        this.recyclerview_homepage.setAdapter(this.home_adapter);


        this.arr_title = getResources().getStringArray(R.array.arr_home_title);
        this.arr_image = getResources().getStringArray(R.array.arr_home_image1);
//        this.arr_color = getResources().getStringArray(R.array.arr_home_color1);
        for (int i = 0; i < this.arr_title.length; i++) {
            this.all_home.add(new All_Home(i, this.arr_title[i],  this.arr_image[i]));
        }

        this.et_sesrch.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                try {
                    Home_page.this.home_adapter.getFilter().filter(charSequence);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
//        this.iv_view_more.setOnClickListener(new OnClickListener() {
//            public void onClick(View view) {
//                Home_page.this.displayPopupWindow_main(view);
//            }
//        });


        return inflate;

    }



    public void onBackPressed() {
        if (this.doubleBackToExitPressedOnce) {
            int random = ((int) (Math.random() * 3.0d)) + 1;
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("random_number->");
            sb.append(random);
            Log.d(str, sb.toString());
            if (random == 1 || random == 2) {
//                this.appChecker.show_recommendedApps(true);
            } else {
                getActivity().finishAffinity();
            }
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(getContext(), getResources().getString(R.string.backtext), 0).show();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Home_page.this.doubleBackToExitPressedOnce = false;
            }
        }, 1000);
    }


}
