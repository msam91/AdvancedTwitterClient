package com.codepath.apps.mysimpletweets.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.apps.mysimpletweets.Adapters.TweetsArrayAdapter;
import com.codepath.apps.mysimpletweets.Listeners.EndlessScrollListener;
import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.models.Tweet;

import java.util.ArrayList;

/**
 * Created by msamant on 10/31/15.
 */
public abstract class TimelineFragment extends Fragment {

    private ArrayList<Tweet> tweets;
    private TweetsArrayAdapter aTweets;
    private ListView lvTweets;
    private SwipeRefreshLayout swipeContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tweetlist,parent,false);
        setUpViews(v);

        return v;
    }
    private void setUpViews(View v) {
        lvTweets = (ListView) v.findViewById(R.id.lvtweets);
        lvTweets.setAdapter(aTweets);
        lvTweets.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount){

                customLoadMore(page);
                return true;
            }

        });

        swipeContainer = (SwipeRefreshLayout)v.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                aTweets.clear();
                customLoadMore(1);
                swipeContainer.setRefreshing(false);

            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }
    protected abstract void customLoadMore(int page);


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tweets = new ArrayList<>();
        aTweets = new TweetsArrayAdapter(getActivity(), tweets);
    }

    public void addAll(ArrayList<Tweet>tweetList){
        aTweets.addAll(tweetList);

    }

    public void clear (){
       aTweets.clear();
    }


}
