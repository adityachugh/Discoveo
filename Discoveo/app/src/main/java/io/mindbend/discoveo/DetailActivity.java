package io.mindbend.discoveo;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class DetailActivity extends ActionBarActivity {

    private ReviewAdapter mAdapter;
    private List<Review> mReviews;
    private String mObjectId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //for the discoveo clicked
        mObjectId = getIntent().getStringExtra("objectid");
        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        String rating = getIntent().getStringExtra("rating");

        //elements
        TextView discoveoName = (TextView)findViewById(R.id.location_name);
        TextView discoveoDescription = (TextView)findViewById(R.id.location_description);
        TextView discoveoRating = (TextView)findViewById(R.id.location_rating);

        //setting data
        discoveoName.setText(title);
        discoveoDescription.setText(description);
        discoveoRating.setText(rating);


        //set the adapter
        mReviews = new ArrayList<>();
        mAdapter = new ReviewAdapter(this, mReviews);
        RecyclerView reviewList = (RecyclerView) findViewById(R.id.review_list);
        reviewList.setLayoutManager(new LinearLayoutManager(this));
        reviewList.setAdapter(mAdapter);

        setupReviews();

        Button submitButton = (Button)findViewById(R.id.submit_review_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText review = (EditText)findViewById(R.id.enter_review_edittext);
                final String reviewText = review.getText().toString();

                LayoutInflater li = LayoutInflater.from(DetailActivity.this);
                final View addCommentView = li.inflate(R.layout.review_dialogue, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailActivity.this);
                // set the dialog's view to alertdialog builder
                alertDialogBuilder.setView(addCommentView);

                TextView submitText = (TextView)addCommentView.findViewById(R.id.review_text_dialogue);
                submitText.setText(reviewText);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Post",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        EditText rating = (EditText)addCommentView.findViewById(R.id.edit_rating);
                                        final int ratingT = Integer.parseInt(rating.getText().toString());
                                        postReview(ratingT, reviewText);
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setupReviews() {
        ParseObject discoveo = ParseObject.createWithoutData("Discoveo", mObjectId);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Review");
        query.include("discoveo");
        query.include("user");
        query.setLimit(100);
        query.whereEqualTo("discoveo", discoveo);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                for (ParseObject object : parseObjects) {
                    Review review = new Review(object);
                    mReviews.add(review);
                }
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    public void postReview(int ratingDouble, String review) {
        ParseObject discoveo = ParseObject.createWithoutData("Discoveo", mObjectId);

        final ParseObject preview = ParseObject.create("Review");
        preview.add("discoveo", discoveo);
        preview.add("rating", ratingDouble);
        preview.add("review", review);
        preview.add("user", ParseUser.getCurrentUser());
        preview.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                mReviews.add(new Review(preview));
                mAdapter.notifyDataSetChanged();

            }
        });

    }
}