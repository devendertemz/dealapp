package com.deals.dealapp.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.deals.dealapp.R;
import com.deals.dealapp.Service.jsonParser;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/*
public class Nearby_Stroe extends FragmentActivity implements OnMapReadyCallback {


    GoogleMap Map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby__stroe);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);
      mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        Map = googleMap;
        LatLng delhi = new LatLng(19.169257, 73.341601);
        Map.addMarker(new MarkerOptions().position(delhi).title("delhi"));
        Map.moveCamera(CameraUpdateFactory.newLatLng(delhi));


    }
}

*/

/*


public class Nearby_Stroe extends AppCompatActivity {
    SupportMapFragment srf;
    double lat;
    double longt;

    FusedLocationProviderClient client;

    String address_type ="null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby__stroe);
         //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.Flag_F);
        srf = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        client = LocationServices.getFusedLocationProviderClient(this);


        Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        getmylocation();

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();


                    }
                }).check();




    }

    public void getmylocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                srf.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        //Toast.makeText(Currnet_Location.this, "yes", Toast.LENGTH_SHORT).show();
                        if (location!=null)
                        {

                            LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
                            lat=location.getLatitude();
                            longt=location.getLongitude();
                            MarkerOptions markerOptions=new MarkerOptions().position(latLng).title("You are here.....!!");
                            googleMap.addMarker(markerOptions);
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));

                        }else
                        {
                            showAlert();






                        }


                    }
                });

            }
        });



    }







    private void showAlert(){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(false);

        dialog.setTitle("Enable Location")
                .setMessage("Your Location Setting is Set to 'off'.\nPlease Enable Location to" + "Use this App")
                .setPositiveButton("Location Setting", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        dialog.show();

        final AlertDialog.Builder builder =  new AlertDialog.Builder(this);
        final String action = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
        final String message = "Do you want open GPS setting?";

        builder.setMessage(message)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface d, int id) {
                                startActivity(new Intent(action));
                                d.dismiss();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface d, int id) {
                                d.cancel();
                            }
                        });
        builder.create().show();
    }
            }

*/

public class Nearby_Stroe extends AppCompatActivity {

    Spinner spType;
    Button btFind;
    SupportMapFragment supportMapFragment;
    GoogleMap map;

    FusedLocationProviderClient fusedLocationProviderClient;
    double currentLat = 0, currentLong = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby__stroe);

        spType = findViewById(R.id.sp_type);
        btFind = findViewById(R.id.bt_find);

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);

        String[] placetypeList = {"atm", "bank", "hospital", "movie_theater", "restaurant"};

        String[] placeNameList = {"ATM", "Bank", "Hospital", "Movie Theater", "Restaurant"};


        spType.setAdapter(new ArrayAdapter<>(Nearby_Stroe.this, R.layout.support_simple_spinner_dropdown_item, placeNameList));

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        if (ActivityCompat.checkSelfPermission(Nearby_Stroe.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();

        } else {
            //when permission denied;
            //request permission
            ActivityCompat.requestPermissions(Nearby_Stroe.this
                    , new String[]
                            {
                                    Manifest.permission.ACCESS_FINE_LOCATION
                            }, 44);


        }
        btFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get Seleted position of Spinnner

                int i = spType.getSelectedItemPosition();
                String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?" + "location=" + currentLat + "," + currentLong +

                        "&radius=5000" +//near by radius
                        "&types=" + placetypeList[i] +//place type
                        "&sensor=true" +//Sensor
                        "&key=" + getResources().getString(R.string.map_key);



                        //+"AIzaSyDPonGxV9SfVmbK6gPBIaaEhDZw11W05MM" ;


                //maps.googleapis.com/maps/api/place/nearbysearch/json?location=28.6090,76.9855&radius=5000&types=atm&sensor=true&key=AIzaSyDU2i2j6Kk3l4q_kVI-8x5zuJYIjkZXMsE


                //Execute place task to download json data
                new PlaceTask().execute(url);


            }
        });


    }

    private void getCurrentLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location != null) {


                    currentLat = location.getLatitude();
                    currentLong = location.getLongitude();
                    // sync map
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            map = googleMap;
                            LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());

                            MarkerOptions markerOptions=new MarkerOptions().position(latLng).title("You are here.....!!");
                            map.addMarker(markerOptions);
                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));




                           /* //Zoom cuuremt location
                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(currentLat, currentLong), 10
                            ));*/
                        }
                    });
                }
            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //when permission granted
                //call method
                getCurrentLocation();

            }
        }
    }

    private class PlaceTask extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... strings) {
            //Initialize Data
            String data = null;
            try {

                data = downloadUrl(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {

            //Excute parser task
            new ParserTask().execute(s);

        }
    }

    private String downloadUrl(String string) throws IOException {
        //Initialize url

        URL url = new URL(string);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.connect();

        InputStream stream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder builder = new StringBuilder();
        String line = "";

        while ((line = reader.readLine()) != null) {
            //Append line
            builder.append(line);
        }
        //get append data
        String data = builder.toString();
        //close data
        reader.close();

        return data;


    }

    private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String, String>>> {


        @Override
        protected List<HashMap<String, String>> doInBackground(String... strings) {
            //cr+-eate json parser class
            jsonParser jsonParser = new jsonParser();
            List<HashMap<String, String>> mapList = null;
            JSONObject object = null;

            //initialize json object
            try {


                object = new JSONObject(strings[0]);
                // Parse Json object
                mapList = jsonParser.parseResult(object);


            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Return map list

            return mapList;


        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> hashMaps) {

            //Clear map
            map.clear();
            ;
            //use for lop
            for (int i = 0; i < hashMaps.size(); i++) {
                //ini hash map
                HashMap<String,String>hashMapList=hashMaps.get(i);
                //Get Latitude
                double lat= Double.parseDouble(hashMapList.get("lat"));

                //Get Longitude
                double lng= Double.parseDouble(hashMapList.get("lng"));
                // get Name
                String name=hashMapList.get("name");
                //Concat Latitude and Longtiude
                LatLng latLng=new LatLng(lat,lng);
                //Initialize marker option
                MarkerOptions options=new MarkerOptions();
                //Set position

                options.position(latLng);

                //set title
                options.title(name);
                // Add marker on map
                map.addMarker(options);


            }
        }
    }
}

