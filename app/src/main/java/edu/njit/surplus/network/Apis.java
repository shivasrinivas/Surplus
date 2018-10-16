package edu.njit.surplus.network;

import java.util.List;

import edu.njit.surplus.models.EmployeeList;
import edu.njit.surplus.models.FoodSet;
import edu.njit.surplus.models.Post;
import edu.njit.surplus.models.Request;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by srini on 11/11/17.
 */

public interface Apis {

    @GET("foodservice/foods")
    Call<List<FoodSet>> getAvailableFood(
            @Query("latitude") double latitude,
            @Query("longitude") double longitude);

    @GET("foodservice/fooddetails")
    Call<Post> getFoodDetails(
            @Query("postid") int postId);

    @GET("requester/availablerequester")
    Call<List<Request>> getRequests();

}
