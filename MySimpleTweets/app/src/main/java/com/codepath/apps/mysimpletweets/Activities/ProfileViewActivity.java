package com.codepath.apps.mysimpletweets.Activities;


import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.codepath.apps.mysimpletweets.Fragments.UserHeaderFragment;
import com.codepath.apps.mysimpletweets.Fragments.UserTimeLineFragment;
import com.codepath.apps.mysimpletweets.R;


public class ProfileViewActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        String screenName = getIntent().getStringExtra("screen_name");
        boolean owner;

        if(screenName != null){
            owner=false;
            getSupportActionBar().setTitle(screenName);


        }
        else{
            owner = true;
            getSupportActionBar().setTitle("My Profile");

        }
        if(savedInstanceState==null) {
            UserTimeLineFragment userTimeLineFragment = UserTimeLineFragment.newInstance(screenName);
            UserHeaderFragment userHeaderFragment = UserHeaderFragment.newInstance(String.valueOf(owner),screenName);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flContainer, userTimeLineFragment);
            ft.replace(R.id.flUserHeader,userHeaderFragment);
            ft.commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile_view, menu);
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
