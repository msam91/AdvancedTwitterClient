package com.codepath.apps.mysimpletweets.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by msamant on 10/24/15.
 */
public class User {

   private String name;
   private String screenName;
   private long uid;
   private String profileImgUrl;
   private String status;
   private String followers;
   private String following;

    public String getName() {
        return name;
    }

    public String getScreenName() {
        return screenName;
    }

    public long getUid() {
        return uid;
    }

    public String getProfileImgUrl() {
        return profileImgUrl;
    }

    public String getStatus() {
        return status;
    }

    public String getFollowers() {
        return followers;
    }

    public String getFollowing() {
        return following;
    }

    public static User fromJson(JSONObject jsonObject){

       User user = new User();
       try{

          user.name= jsonObject.getString("name");
          user.screenName= jsonObject.getString("screen_name");
          user.uid= jsonObject.getLong("id");
          user.profileImgUrl= jsonObject.getString("profile_image_url");
          user.status = jsonObject.getString("description");
          user.followers=jsonObject.getString("followers_count");
          user.following=jsonObject.getString("friends_count");
       }
       catch(JSONException e){
           e.printStackTrace();
       }
        return user;
   }
}
