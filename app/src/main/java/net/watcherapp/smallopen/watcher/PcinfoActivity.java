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

public class PcinfoActivity extends AppCompatActivity {

    private SectionsPagerAdapter2 mSectionsPagerAdapter;
    private ViewPager mViewPager;

    public static final String LOG_TAG = "LOGMainActivity";

    public static final int FRAGMENT_SEAT = 0;
    public static final int FRAGMENT_SPEC = 1;
    public static final int FRAGMENT_FOOD = 2;
    public static final int FRAGMENT_REVIEW = 3;
    private String pcName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcinfo);

        // Get the transferred data from source activity.
        Intent intent = getIntent();
        pcName = intent.getStringExtra("pcName");
//        Log.d("pcname", pcName);


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

    @Override
    public void onBackPressed() {
            super.onBackPressed();
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
                    seatFragment.setArguments(args);
                    return seatFragment;
                case FRAGMENT_SPEC:
//                    return new PcMapFragment();

                    /*  case FRAGMENT_QANDA:
                    return new MyFragment();
                case FRAGMENT_SPEC:
                    return new MapFragment();*/

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
