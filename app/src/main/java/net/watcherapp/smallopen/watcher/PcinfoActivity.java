package net.watcherapp.smallopen.watcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PcinfoActivity extends AppCompatActivity {

    private SectionsPagerAdapter2 mSectionsPagerAdapter;
    private ViewPager mViewPager;

    public static final int FRAGMENT_SEAT = 0;
    public static final int FRAGMENT_SPEC = 1;
    public static final int FRAGMENT_EVENTS = 2;
    public static final int FRAGMENT_FOOD = 3;
    public static final int FRAGMENT_REVIEW = 4;

    protected static String pcName;
    protected static String cnt_empty;
    protected static Retrofit mRetrofit;
    protected static RetrofitAPI mRetrofitAPI;
//    private Call<String> mCallMovieList;
    protected static Call<PcInfoOfJson> mPcInfo;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcinfo);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mFirebaseAnalytics.setCurrentScreen(this, "PcInfo Screen", null /* class override */);

        // Get the transferred data from source activity.
        Intent intent = getIntent();
        pcName = intent.getStringExtra("pcName");
        cnt_empty = intent.getStringExtra("cnt_empty");

//        setRetrofitInit();
//        callPcInfo();

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter2(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs_pcinfo);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }

    protected static void setRetrofitInit() {

        mRetrofit = new Retrofit.Builder()

//                .baseUrl("http://nameyeowool.pythonanywhere.com")
                .baseUrl("http://www.watcherapp.net")

                .addConverterFactory(GsonConverterFactory.create())

                .build();

        mRetrofitAPI = mRetrofit.create(RetrofitAPI.class);
    }
    protected  void callPcInfo() {

//        mCallMoviewList = mRetrofitAPI.getMoviewList();
        mPcInfo = mRetrofitAPI.getPcInfo(pcName);
        mPcInfo.enqueue(mRetrofitCallback);

    }

    protected  Callback<PcInfoOfJson> mRetrofitCallback = new Callback<PcInfoOfJson>() {
        @Override
        public void onResponse(Call<PcInfoOfJson> call, Response<PcInfoOfJson> response) {
//            String result = response.body();
            Log.d("call",call.request().toString());
            if(response.isSuccessful()) {
                PcInfoOfJson result = response.body();
                Log.d("get cnt", String.valueOf(result.getCnt_empty()));
                Toast.makeText(getApplicationContext(), "get_ctn" + (result.getCnt_empty()), Toast.LENGTH_SHORT).show();
            }else{
                Log.d("err",response.errorBody().toString());
                Toast.makeText(getApplicationContext(), response.errorBody().toString(), Toast.LENGTH_SHORT).show();
            }

        }
        @Override
        public void onFailure(Call<PcInfoOfJson> call, Throwable t) {
            t.printStackTrace();
        }
    };




    @Override
    public void onBackPressed() {
            super.onBackPressed();
            finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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



    public class SectionsPagerAdapter2 extends FragmentPagerAdapter {

        public SectionsPagerAdapter2(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case FRAGMENT_SEAT:
                    Bundle bundle = new Bundle();
                    bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "Fragment");
                    bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "seat");
                    mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

                    SeatFragment seatFragment = new SeatFragment();
                    // Supply index input as an argument.
//                    Bundle args_seat = new Bundle();
//                    args_seat.putString("pcName", pcName);
//                    seatFragment.setArguments(args_seat);
                    return seatFragment;
                case FRAGMENT_SPEC:
                    Bundle bundle2 = new Bundle();
                    bundle2.putString(FirebaseAnalytics.Param.ITEM_ID, "Fragment");
                    bundle2.putString(FirebaseAnalytics.Param.ITEM_NAME, "spec");
                    mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle2);
                    return new PcSpecFragment();

                case FRAGMENT_EVENTS:
                    return new EventsFragment();
                case FRAGMENT_FOOD:
                    return new FoodListFragment();
                case FRAGMENT_REVIEW:
                    return new ReviewFragment();
                default:
                    return new MyFragment();

            }
        }

        @Override
        public int getCount() {
            //show 3 total pages.
            //return 4;


            return 5;
        }
    }
}
