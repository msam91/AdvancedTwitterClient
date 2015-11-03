package com.codepath.apps.mysimpletweets.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.TwitterApplication;
import com.codepath.apps.mysimpletweets.clients.TwitterClient;
import com.codepath.apps.mysimpletweets.models.ProfileOwner;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.codepath.apps.mysimpletweets.models.User;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by msamant on 10/31/15.
 */
public class UserHeaderFragment extends Fragment {

    private TwitterClient client;
    private ImageView ivProImage;
    private TextView tvProUserName;
    private TextView tvProScreenName;
    private TextView tvStatus;
    private TextView tvFollowers;
    private TextView tvFollowing;
    private User user;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApplication.getRestClient();
        String owner = getArguments().getString("owner");
        String screenName = getArguments().getString("screen_name");
        if(Boolean.parseBoolean(owner)) {
            getOwnerProfile();
        }
        else{
            getUserProfile(screenName);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_userheader,parent,false);
        setUpViews(v);
        return v;
    }

    public static UserHeaderFragment newInstance(String owner, String screenName) {
        UserHeaderFragment userHeaderFragment = new UserHeaderFragment();
        Bundle args = new Bundle();
        args.putString("owner", owner);
        args.putString("screen_name",screenName);
        userHeaderFragment.setArguments(args);
        return userHeaderFragment;
    }

    private void setUpViews(View v) {
        ivProImage = (ImageView)v.findViewById(R.id.ivProImage);
        tvProUserName = (TextView)v.findViewById(R.id.tvProUserName);
        tvProScreenName = (TextView)v.findViewById(R.id.tvProScreenName);
        tvStatus = (TextView)v.findViewById((R.id.tvProStatus));
        tvFollowers=(TextView)v.findViewById(R.id.tvProFollowers);
        tvFollowing=(TextView)v.findViewById(R.id.tvProFollowing);


    }
    public void getOwnerProfile(){
        client.getOwnProfile(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header header[], JSONObject response) {
              user=  User.fromJson(response);
              Picasso.with(getActivity()).load(user.getProfileImgUrl()).fit().placeholder(R.drawable.abc_spinner_mtrl_am_alpha).into(ivProImage);
              tvProUserName.setText(user.getName().toString());
              tvProScreenName.setText(user.getScreenName().toString());
              tvStatus.setText(user.getStatus().toString());
              tvFollowers.setText(user.getFollowers().toString());
              tvFollowing.setText(user.getFollowing().toString());
            }

            @Override
            public void onFailure(int statusCode, Header header[], Throwable throwable, JSONObject response) {
            }

        });
    }

    public void getUserProfile(final String screenName){
            client.userLookup(new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header header[], JSONArray response) {

                    try {
                        JSONObject jsonObject = response.getJSONObject(0);
                        user = User.fromJson(jsonObject);
                        Picasso.with(getActivity()).load(user.getProfileImgUrl()).fit().placeholder(R.drawable.abc_spinner_mtrl_am_alpha).into(ivProImage);
                        tvProUserName.setText(user.getName().toString());
                        tvProScreenName.setText(user.getScreenName().toString());
                        tvStatus.setText(user.getStatus().toString());
                        tvFollowers.setText(user.getFollowers().toString());
                        tvFollowing.setText(user.getFollowing().toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header header[], Throwable throwable, JSONObject response) {
                    //Log.e("ERROR", response.toString());
                }
            }, screenName);

    }


}
