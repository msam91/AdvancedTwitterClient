package com.codepath.apps.mysimpletweets.Adapters;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.codepath.apps.mysimpletweets.Activities.ProfileViewActivity;
import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.Utils.FormatDate;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by msamant on 10/24/15.
 */
public class TweetsArrayAdapter extends ArrayAdapter<Tweet> {


    public TweetsArrayAdapter(Context context, List<Tweet> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Tweet tweet = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet, parent, false);
        }
        // Lookup view for data population

        final ImageView ivProfilePic = (ImageView) convertView.findViewById(R.id.ivProfileImage);
        TextView tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
        TextView tvBody = (TextView) convertView.findViewById(R.id.tvBody);
        TextView tvScreenName = (TextView)convertView.findViewById(R.id.tvScreenName);
        TextView tvTime =(TextView)convertView.findViewById(R.id.tvTime);
        ivProfilePic.setTag(R.id.ivProfileImage,tweet.getUser().getScreenName());
        ivProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), ProfileViewActivity.class);
                i.putExtra("screen_name", ivProfilePic.getTag(R.id.ivProfileImage).toString());
                getContext().startActivity(i);

            }
        });


        tvUserName.setText(tweet.getUser().getName());
        tvBody.setText(tweet.getBody());
        tvScreenName.setText("@"+tweet.getUser().getScreenName());
        tvTime.setText(FormatDate.getRelativeTimeAgo(tweet.getCreatedAt()));
        ivProfilePic.setImageResource(android.R.color.transparent);
        Picasso.with(getContext()).load(tweet.getUser().getProfileImgUrl()).fit().placeholder(R.drawable.abc_spinner_mtrl_am_alpha).into(ivProfilePic);

        // Return the completed view to render on screen
        return convertView;
    }

}
