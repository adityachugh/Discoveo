package io.mindbend.discoveo;

/**
 * Created by rohitsharma on 2015-08-15.
 */
public class Discoveo {

    private String mTitle;
    private String mDetail;
    private double mRatingDouble;

    public Discoveo(String title, String detail, double rating){
        mTitle = title;
        mDetail = detail;
        mRatingDouble = rating;
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
}
