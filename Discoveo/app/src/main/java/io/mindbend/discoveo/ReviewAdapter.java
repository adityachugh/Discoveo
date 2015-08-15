package io.mindbend.discoveo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Akshay Pall on 01/08/2015.
 */
public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> implements Serializable {

    private static final String TAG = "ResultsListAdapter";

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mUsername;
        private final TextView mReviewText;
        private final TextView mRatingText;

        //TODO: create private fields for the elements within a single feed item

        public ViewHolder(View itemView) {
            super(itemView);

            //getting all the elements part of the card, aside from the image
            mUsername = (TextView) itemView.findViewById(R.id.reviewer_name);
            mReviewText = (TextView) itemView.findViewById(R.id.review_text);
            mRatingText = (TextView) itemView.findViewById(R.id.review_rating);
        }
    }

    //TODO: create private fields for the list
    private List<Review> mReviews;
    private Context mContext;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.review_feed_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Review review = mReviews.get(i);

        //TODO: setup view

    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    public ReviewAdapter(Context context, List<Review> reviews) {
        //save the mPosts private field as what is passed in
        mContext = context;
        mReviews = reviews;
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }
}

