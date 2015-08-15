package io.mindbend.discoveo;

import android.util.Log;

import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by rohitsharma on 2015-08-15.
 */
public class Review {

    private String mReviewerName;
    private String mReviewText;
    private double mRatingDouble;

    public Review(String reviewerName, String reviewerText, double rating){
        mReviewerName = reviewerName;
        mReviewText = reviewerText;
        mRatingDouble = rating;
    }

    public Review(ParseObject parseObject) {
        //Log.wtf("Review", parseObject.getParseUser("user").toString());
//        if (parseObject.getParseUser("user").getUsername() == null) {
//            mReviewerName = ParseUser.getCurrentUser().getUsername();
//        } else {
//            mReviewerName = parseObject.getParseUser("user").getUsername();
//        }
        mReviewerName = parseObject.getString("user");
        mReviewText = parseObject.getString("review");
        mRatingDouble = parseObject.getDouble("rating");
    }

    public String getReviewerName() {
        return mReviewerName;
    }

    public String getReviewText(){
        return mReviewText;
    }

    public double getRatingDouble() {
        return mRatingDouble;
    }

}
