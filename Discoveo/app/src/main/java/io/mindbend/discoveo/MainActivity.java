package io.mindbend.discoveo;

import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "MainActivity";

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private List<Discoveo> discoveos;
    private double mLatitude;
    private double mLongitude;
    private ResultsListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "ZPaJOrGD9cjkyJk9BCf28ZIfmqa0dZUkooZ5m70x", "dV2okMiM6Kul0I69zeI57DXPDsTJmCMWPArsbyB3");

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        discoveos = new ArrayList<>();
        discoveos.add(new Discoveo("Test", "detail", 4.0));
        discoveos.add(new Discoveo("Test", "detail", 4.0));
        discoveos.add(new Discoveo("Test", "detail", 4.0));

        mAdapter = new ResultsListAdapter(this, discoveos);

        RecyclerView discoveoListRecyclerView = (RecyclerView) findViewById(R.id.discoveos_list);
        discoveoListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        discoveoListRecyclerView.setAdapter(mAdapter);
        addGoogleAPIClient();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
    }

    @Override
    public void onConnected(Bundle bundle) {
        getCurrentLocation();
        getDiscoveos();
    }

    private void getCurrentLocation() {
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        mLatitude = location.getLatitude();
        mLongitude = location.getLongitude();
    }

    private void addGoogleAPIClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //get user location
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void getDiscoveos() {
        ParseGeoPoint currentLocation = new ParseGeoPoint(mLatitude, mLongitude);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Discoveo");
        query.whereWithinKilometers("location", currentLocation, 1);
        query.setLimit(100);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                for(ParseObject object : parseObjects) {
                    Discoveo newDiscoveo = new Discoveo(object);
                    discoveos.add(newDiscoveo);
                    Log.wtf("test", newDiscoveo.getTitle());

                }
                mAdapter.notifyDataSetChanged();
            }
        });
    }
}
