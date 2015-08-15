package io.mindbend.discoveo;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Button submitReview = (Button)findViewById(R.id.submit_review_button);
        submitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText review = (EditText)findViewById(R.id.enter_review_edittext);
                String reviewText = review.getText().toString();

                LayoutInflater li = LayoutInflater.from(DetailActivity.this);
                View addCommentView = li.inflate(R.layout.review_dialogue, null);

                //TODO: get the rating entered from the edittext

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailActivity.this);

                // set the dialog's view to alertdialog builder
                alertDialogBuilder.setView(addCommentView);
                alertDialogBuilder
                        .setCancelable(false)
                        .setNegativeButton("Cancel", null)
                        .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //TODO: send to parse then add to recyclerview
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
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