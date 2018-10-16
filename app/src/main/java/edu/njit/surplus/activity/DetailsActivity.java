package edu.njit.surplus.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

import edu.njit.surplus.R;
import edu.njit.surplus.models.Address;
import edu.njit.surplus.models.FoodItem;
import edu.njit.surplus.models.Post;
import edu.njit.surplus.network.Apis;
import edu.njit.surplus.network.RetrofitInstance;
import edu.njit.surplus.utilities.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by srini on 11/11/17.
 */

public class DetailsActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap googleMap;
    private TextView tvDescription;
    private TextView tvPrice;
    private TextView tvName;
    private TextView tvAddress;
    private ImageView ivCall;

    private int postId;
    private SimpleDraweeView draweeView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_details);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        tvDescription = (TextView) findViewById(R.id.tv_description);
        tvPrice = (TextView) findViewById(R.id.tv_price);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvAddress = (TextView) findViewById(R.id.tv_address);
        postId = getIntent().getIntExtra(Constants.INTENT_KEY_POST_ID, -1);
        draweeView = (SimpleDraweeView) findViewById(R.id.sdv_image);
        ivCall = (ImageView) findViewById(R.id.iv_call);


        if (postId != -1) {
            getPost(postId);
        } else {
            Toast.makeText(this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void getPost(int postId) {
        Apis service = RetrofitInstance.getRetrofitInstance().create(Apis.class);
        Call<Post> call = service.getFoodDetails(postId);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                updateDetails(response.body());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void updateDetails(final Post post) {
        tvName.setText(post.getUser().getName());
        tvPrice.setText("$ " + post.getPrice());
        StringBuilder stringBuilder = new StringBuilder();
        for (FoodItem food : post.getFoodItems()) {
            stringBuilder.append(food.getName());
            stringBuilder.append(" available for ");
            stringBuilder.append(food.getQuantity());
            stringBuilder.append(" people. ");
        }
        tvDescription.setText(stringBuilder.toString());
        Address address = post.getAddress();
        StringBuilder stringBuilderAddress = new StringBuilder();
        stringBuilderAddress.append(String.valueOf(address.getHno()));
        stringBuilderAddress.append(",");
        stringBuilderAddress.append(address.getStreetName());
        stringBuilderAddress.append(",");
        stringBuilderAddress.append(address.getCity());
        stringBuilderAddress.append(".");
        stringBuilderAddress.append(address.getZip());
        tvAddress.setText(stringBuilderAddress.toString());

        Uri imageUri = Uri.parse(post.getImgUrl());
        draweeView.setImageURI(imageUri);

        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + post.getContactNumber()));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);
            }
        });

        updateMap(address.getLatitude(), address.getLongitude(), post.getUser().getName());
    }

    private void updateMap(final double latitude, final double longitude, String title) {
        if (googleMap != null) {
            LatLng location = new LatLng(latitude, longitude);
            googleMap.addMarker(new MarkerOptions().position(location)
                    .title(title));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(latitude, longitude))      // Sets the center of the map to location user
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude);
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onClick(View v) {

    }
}
