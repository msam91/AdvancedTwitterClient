package com.codepath.apps.mysimpletweets.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.codepath.apps.mysimpletweets.Listeners.EndlessScrollListener;
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
public class HomeTimeLineFragment extends TimelineFragment {

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


    private void populateTimeLine(int sinceId) {
        client.getHomeTimeLine(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header header[], JSONArray response) {
               addAll(Tweet.fromJSONArray(response));

            }
            @Override
            public void onFailure(int statusCode, Header header[], Throwable throwable, JSONObject response) {
                //Log.e("ERROR", response.toString());
            }
        }, sinceId);
    }


}
