package io.mindbend.discoveo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import javax.xml.transform.Result;

/**
 * Created by akshaypall on 2015-08-15.
 */

/**
 * Created by Akshay Pall on 01/08/2015.
 */
public class ResultsListAdapter extends RecyclerView.Adapter<ResultsListAdapter.ViewHolder> implements Serializable {

    private static final String TAG = "ResultsListAdapter";

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout mItem;
        private final TextView mTitle;
        private final TextView mDetail;
        private final TextView mRatingText;
        private final de.hdodenhof.circleimageview.CircleImageView mImage;

        //TODO: create private fields for the elements within a single feed item

        public ViewHolder(View itemView) {
            super(itemView);

            //getting all the elements part of the card, aside from the image
            mTitle = (TextView) itemView.findViewById(R.id.discoveo_title);
            mDetail = (TextView) itemView.findViewById(R.id.discoveo_description);
            mRatingText = (TextView) itemView.findViewById(R.id.discoveo_rating_string);
            mImage = (de.hdodenhof.circleimageview.CircleImageView) itemView.findViewById(R.id.discoveo_image);
            mItem = (LinearLayout)itemView.findViewById(R.id.result_feed_id);
        }
    }

    //TODO: create private fields for the list
    private List<Discoveo> mDiscoveos;
    private Context mContext;
    private ResultListener mListener;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.result_discoveo_feed_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final Discoveo discoveo = mDiscoveos.get(i);

        //TODO: setup view
        viewHolder.mTitle.setText(discoveo.getTitle());
        viewHolder.mDetail.setText(discoveo.getDetail());
        viewHolder.mRatingText.setText(discoveo.getRatingString());
        viewHolder.mImage.setImageResource(R.drawable.outside_of_student_design_centre);

        viewHolder.mItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.pressedDiscoveo(discoveo);
                mListener.returnImage(viewHolder.mImage);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDiscoveos.size();
    }

    public ResultsListAdapter(Context context, List<Discoveo> discoveos, ResultListener listener) {
        //save the mPosts private field as what is passed in
        mContext = context;
        mDiscoveos = discoveos;
        mListener = listener;
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        mListener = null;
        super.onViewDetachedFromWindow(holder);
    }

    public interface ResultListener {
        void pressedDiscoveo(Discoveo discoveo);
        void returnImage(de.hdodenhof.circleimageview.CircleImageView image);
    }
}

