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

    public static final String LOG_TAG = "LOGMainActivity";

    public static final int FRAGMENT_SEAT = 0;
    public static final int FRAGMENT_SPEC = 1;
    public static final int FRAGMENT_FOOD = 2;
    public static final int FRAGMENT_REVIEW = 3;
    protected static String pcName;
    protected static Retrofit mRetrofit;
    protected static RetrofitAPI mRetrofitAPI;
//    private Call<String> mCallMovieList;
    protected static Call<PcInfoOfJson> mPcInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcinfo);


        // Get the transferred data from source activity.
        Intent intent = getIntent();
        pcName = intent.getStringExtra("pcName");

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

                .baseUrl("http://nameyeowool.pythonanywhere.com")

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
                    SeatFragment seatFragment = new SeatFragment();
                    // Supply index input as an argument.
                    Bundle args = new Bundle();
                    args.putString("pcName", pcName);
                    Log.d("seatFragment:", pcName);
                    seatFragment.setArguments(args);
                    return seatFragment;
                case FRAGMENT_SPEC:
                    return new PcSpecFragment();
//                case FRAGMENT_FOOD:
//                    return new MyFragment();
//                case FRAGMENT_REVIEW:
//                    return new PcReviewFragment();

                default:
                    return new MyFragment();

            }
        }

        @Override
        public int getCount() {
            //show 3 total pages.
            //return 4;


            return 4;
        }
    }
}
