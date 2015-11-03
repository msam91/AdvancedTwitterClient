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
public class MentionTimeLineFragment extends TimelineFragment {

    private TwitterClient client;

    @Override
    protected void customLoadMore(int page) {
        getMentionTimeLine(page);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApplication.getRestClient();
        getMentionTimeLine(1);

    }


    private void getMentionTimeLine(int sinceId) {
        client.getMentionTimeLine(new JsonHttpResponseHandler() {
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
