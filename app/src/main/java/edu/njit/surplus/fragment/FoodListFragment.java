package edu.njit.surplus.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.njit.surplus.R;
import edu.njit.surplus.activity.DetailsActivity;
import edu.njit.surplus.adapter.EmployeeAdapter;
import edu.njit.surplus.adapter.FoodListAdapter;
import edu.njit.surplus.models.Employee;
import edu.njit.surplus.models.EmployeeList;
import edu.njit.surplus.models.FoodSet;
import edu.njit.surplus.models.Post;
import edu.njit.surplus.network.Apis;
import edu.njit.surplus.network.GPSTracker;
import edu.njit.surplus.network.RetrofitInstance;
import edu.njit.surplus.utilities.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by srini on 11/11/17.
 */

public class FoodListFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;

    public FoodListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_simple_list, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_food_items);
        //GPSTracker gps = new GPSTracker(getActivity());
        //double latitude = gps.getLatitude();
        //double longitude = gps.getLongitude();
        getAvailableFood(40.744668, -74.179996);

        return rootView;
    }

    private void getAvailableFood(double latitude, double longitude) {
        Apis service = RetrofitInstance.getRetrofitInstance().create(Apis.class);
        Call<List<FoodSet>> call = service.getAvailableFood(latitude, longitude);

        call.enqueue(new Callback<List<FoodSet>>() {
            @Override
            public void onResponse(Call<List<FoodSet>> call, Response<List<FoodSet>> response) {
                updateFoodItemsList(response.body());
            }

            @Override
            public void onFailure(Call<List<FoodSet>> call, Throwable t) {
                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateFoodItemsList(List<FoodSet> foodItems) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        FoodListAdapter adapter = new FoodListAdapter(getActivity(), foodItems);
        recyclerView.setAdapter(adapter);
    }

}
