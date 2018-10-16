package edu.njit.surplus.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import edu.njit.surplus.R;
import edu.njit.surplus.adapter.FoodListAdapter;
import edu.njit.surplus.adapter.RequestListAdapter;
import edu.njit.surplus.models.FoodSet;
import edu.njit.surplus.models.Request;
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

public class PeopleListFragment extends Fragment{

    private Context context;
    private RecyclerView recyclerView;

    public PeopleListFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_simple_list, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_food_items);
        GPSTracker gps = new GPSTracker(getActivity());
        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();
        getRequests(latitude, longitude);

        return rootView;
    }

    private void getRequests(double latitude, double longitude) {
        Apis service = RetrofitInstance.getRetrofitInstance().create(Apis.class);
        Call<List<Request>> call = service.getRequests();

        call.enqueue(new Callback<List<Request>>() {
            @Override
            public void onResponse(Call<List<Request>> call, Response<List<Request>> response) {
                updateRequestsList(response.body());
            }

            @Override
            public void onFailure(Call<List<Request>> call, Throwable t) {
                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateRequestsList(List<Request> requests) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        RequestListAdapter adapter = new RequestListAdapter(getActivity(), requests);
        recyclerView.setAdapter(adapter);
    }
}
