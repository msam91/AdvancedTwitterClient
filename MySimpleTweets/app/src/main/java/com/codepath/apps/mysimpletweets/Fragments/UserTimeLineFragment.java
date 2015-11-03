package com.codepath.apps.mysimpletweets.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.codepath.apps.mysimpletweets.TwitterApplication;
import com.codepath.apps.mysimpletweets.clients.TwitterClient;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by msamant on 10/31/15.
 */
public class UserTimeLineFragment extends TimelineFragment {

    private TwitterClient client;

    @Override
    protected void customLoadMore(int page) {
        populateTimeLine(page);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApplication.getRestClient();
        populateTimeLine(1);

    }

    public static UserTimeLineFragment newInstance(String screenName) {
        UserTimeLineFragment userTimeLineFragment = new UserTimeLineFragment();
        Bundle args = new Bundle();
        args.putString("screen_name", screenName);
        userTimeLineFragment.setArguments(args);
        return userTimeLineFragment;
    }


    private void populateTimeLine(int sinceId) {
        String screeName = getArguments().getString("screen_name");
        client.getUserTimeLine(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header header[], JSONArray response) {
                addAll(Tweet.fromJSONArray(response));

            }
            @Override
            public void onFailure(int statusCode, Header header[], Throwable throwable, JSONObject response) {
                //Log.e("ERROR", response.toString());
            }
        }, sinceId,screeName);
    }
}
