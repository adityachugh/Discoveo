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

import org.w3c.dom.Text;

import java.util.List;


public class DetailActivity extends ActionBarActivity {

    private ReviewAdapter mAdapter;
    private List<Review> mReviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //for the discoveo clicked
        String objectId = getIntent().getStringExtra("objectid");
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
        mAdapter = new ReviewAdapter(this, mReviews);
        RecyclerView reviewList = (RecyclerView) findViewById(R.id.review_list);
        reviewList.setLayoutManager(new LinearLayoutManager(this));
        reviewList.setAdapter(mAdapter);

        //TODO: query parse data here

        Button submitButton = (Button)findViewById(R.id.submit_review_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText review = (EditText)findViewById(R.id.enter_review_edittext);
                String reviewText = review.getText().toString();

                LayoutInflater li = LayoutInflater.from(DetailActivity.this);
                View addCommentView = li.inflate(R.layout.review_dialogue, null);

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
                                        //TODO: add review to parse then add to adapter and review
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
}