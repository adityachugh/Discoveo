package io.mindbend.discoveo;

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

    public String getReviewerName() {
        return mReviewerName;
    }

    public String getReviewText(){
        return mReviewText;
    }

    public double getRatingDouble(){
        return mRatingDouble;
    }

}
