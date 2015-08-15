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
 * Created by rohitsharma on 2015-08-15.
 */
public class ResultsListAdapter {
    package io.mindbend.android.announcements.reusableFrags;

    import android.content.Context;
    import android.content.Intent;
    import android.os.Bundle;
    import android.support.v4.app.Fragment;
    import android.support.v4.app.FragmentActivity;
    import android.support.v4.app.FragmentTransaction;
    import android.support.v7.widget.RecyclerView;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.TextView;

    import java.io.Serializable;
    import java.util.List;

    import io.mindbend.android.announcements.R;

    /**
     * Created by Akshay Pall on 01/08/2015.
     */
    public class ResultsListAdapter extends RecyclerView.Adapter<ResultsListAdapter.ViewHolder> implements Serializable {

        private static final String TAG = "ResultsListAdapter";

        public class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView mTitle;
            private final TextView mDetail;

            //TODO: create private fields for the elements within a single feed item

            public ViewHolder(View itemView) {
                super(itemView);

                //getting all the elements part of the card, aside from the image
                mTitle = (TextView) itemView.findViewById(R.id.org_title);
                mDetail = (TextView) itemView.findViewById(R.id.org_banner_detail);
            }
        }

        //TODO: create private fields for the list
        private List<Discoveo> mDiscoveos;
        private Context mContext;


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.result_discoveo_feed_item, viewGroup, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            final Discoveo discoveo = mDiscoveos.get(i);

            //TODO: setup view
            viewHolder.mTitle.setText(org.getTitle());

        }

        @Override
        public int getItemCount() {
            return mDiscoveos.size();
        }

        public ResultsListAdapter (Context context, List<Discoveo> discoveos){
            //save the mPosts private field as what is passed in
            mContext = context;
            mDiscoveos = discoveos;
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

}
