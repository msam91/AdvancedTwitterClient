package com.codepath.apps.mysimpletweets.Activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import com.astuetz.PagerSlidingTabStrip;
import com.codepath.apps.mysimpletweets.Fragments.HomeTimeLineFragment;
import com.codepath.apps.mysimpletweets.Fragments.MentionTimeLineFragment;
import com.codepath.apps.mysimpletweets.R;


public class TimelineActivity extends ActionBarActivity {


    private SwipeRefreshLayout swipeContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        ViewPager vpPager = (ViewPager)findViewById(R.id.viewpager);
        vpPager.setAdapter(new TweetPagerAdapter(getSupportFragmentManager()));
        PagerSlidingTabStrip pgSlidingTabStrip = (PagerSlidingTabStrip)findViewById(R.id.tabs);
        pgSlidingTabStrip.setViewPager(vpPager);
    }



    public void onComposeAction(MenuItem mi) {
        // handle click here
        Intent i = new Intent(this,PostTweetActivity.class);
        startActivity(i);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    public void onProfileView(MenuItem mi){
        Intent i = new Intent(this,ProfileViewActivity.class);
        startActivity(i);

    }

    public class TweetPagerAdapter extends FragmentPagerAdapter{
        String tabTitles[] = {"Home","Mentions"};

        public TweetPagerAdapter(FragmentManager fm) {

            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
           if(position==0){
               return new HomeTimeLineFragment();
           }
          else if (position==1){
               return new MentionTimeLineFragment();
           }
          else{
               return null;
           }
        }

        @Override
        public CharSequence getPageTitle(int position){
            return tabTitles[position];
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }
    }
}

