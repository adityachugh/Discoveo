package io.mindbend.discoveo;

import com.parse.Parse;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

/**
 * Created by rohitsharma on 2015-08-15.
 */
public class Discoveo {

    private String mTitle;
    private String mDetail;
    private double mRatingDouble;
    private ParseGeoPoint mLocation;

    public Discoveo(String title, String detail, double rating){
        mTitle = title;
        mDetail = detail;
        mRatingDouble = rating;
    }
    public Discoveo(ParseObject parseObject) {
        mTitle = parseObject.getString("title");
        mDetail = parseObject.getString("description");
        mRatingDouble = parseObject.getDouble("rating");
        mLocation = parseObject.getParseGeoPoint("location");
    }

    public String getTitle(){
        return mTitle;
    }

    public String getDetail() {
        return mDetail;
    }

    public String getRatingString(){
        return ""+mRatingDouble;
    }

    public ParseGeoPoint getLocation() {
        return mLocation;
    }

    public double getRatingDouble() {
        return mRatingDouble;
    }
}
