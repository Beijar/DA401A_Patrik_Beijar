package com.beijar.patrik.myapplication;

import android.content.res.TypedArray;
import android.location.Location;
import com.google.android.gms.location.LocationListener;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener,
        GoogleApiClient.ConnectionCallbacks,
        LocationListener    {

    private static final String TAG = "VERBOSE";
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private int count = 0;
    public int score = 0;
    ArrayList<Marker> mMarker = new ArrayList<>() ;
    ArrayList<Question> mQuestion = new ArrayList<>() ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true); //activate MyLocation Button
        mMap.setOnMarkerClickListener(this);

        TypedArray questionArrays = getResources().obtainTypedArray(R.array.questions);

        for (int i = 0; i < questionArrays.length(); i++) {
            TypedArray questions = getResources().obtainTypedArray(questionArrays.getResourceId(i, 0));
            Question question = new Question(
                    questions.getString(0),
                    questions.getString(1),
                    questions.getString(2),
                    questions.getString(3),
                    questions.getString(4)
            );
            mQuestion.add(question);
            questions.recycle();
        }
        //Recycle of TypedArray recommended by android docs.
        questionArrays.recycle();
        // Add a markers and move the camera
        LatLng start = new LatLng(55.602669, 13.000371);
        LatLng pub_one = new LatLng(55.603681, 13.000514); //real location 55.603681, 13.000514
        LatLng pub_two = new LatLng(55.604899, 12.995285);
        LatLng pub_three = new LatLng(55.605225, 12.997979);
        LatLng pub_four = new LatLng(55.605526, 12.998822);
        LatLng pub_five = new LatLng(55.605754, 13.002739);
        LatLng pub_six = new LatLng(55.604020, 13.006435);
        LatLng pub_final = new LatLng(55.602959, 13.003377);
        //one custom marker
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.beer_marker);

        mMap.addMarker(new MarkerOptions().position(start).title("Start!"));
        mMarker.add(mMap.addMarker(new MarkerOptions().position(pub_one).title("First Pub: Fagans").icon(icon).visible(true)));
        mMarker.add(mMap.addMarker(new MarkerOptions().position(pub_two).title("Second Pub: Fiesta!").icon(icon).visible(false)));
        mMarker.add(mMap.addMarker(new MarkerOptions().position(pub_three).title("Third Pub: Shotluckan").icon(icon).visible(false)));
        mMarker.add(mMap.addMarker(new MarkerOptions().position(pub_four).title("Fourth Pub: Mellow Yellow").icon(icon).visible(false)));
        mMarker.add(mMap.addMarker(new MarkerOptions().position(pub_five).title("Fifth Pub: Paddys").icon(icon).visible(false)));
        mMarker.add(mMap.addMarker(new MarkerOptions().position(pub_six).title("Sixth Pub: Peas & Honey").icon(icon).visible(false)));
        mMarker.add(mMap.addMarker(new MarkerOptions().position(pub_final).title("Final Pub: Pickwick").icon(icon).visible(false)));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(start, 16));

        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }


    @Override
    public void onConnected(Bundle bundle) {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        Vibrator mVibrate = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        MediaPlayer mPlayer = (MediaPlayer.create(this, R.raw.welcome));

        Location mLocation = new Location("mLocation");
        Marker curMarker = mMarker.get(count);
        mLocation.setLongitude(curMarker.getPosition().longitude);
        mLocation.setLatitude(curMarker.getPosition().latitude);

        float distance;
        distance = location.distanceTo(mLocation);

        if(distance < 10) {
            mVibrate.vibrate(100);
            //mPlayer.start();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Bundle args = new Bundle();
            args.putSerializable("questionClass", mQuestion.get(count));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MyDialogFragment mDialog = new MyDialogFragment();
            mDialog.setArguments(args);
            mDialog.show(ft, "Quiz Time!");

            count++;
            mMarker.get(count).setVisible(true);
        }
    }
}
