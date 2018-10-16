package edu.njit.surplus.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by srini on 11/12/17.
 */

public class Request {
    @SerializedName("expectingCost")
    @Expose
    private int expectingCost;
    @SerializedName("needInHours")
    @Expose
    private double needInHours;
    @SerializedName("noOfPeopleWaiting")
    @Expose
    private int noOfPeopleWaiting;
    @SerializedName("postId")
    @Expose
    private int postId;

    public int getExpectingCost() {
        return expectingCost;
    }

    public void setExpectingCost(int expectingCost) {
        this.expectingCost = expectingCost;
    }

    public double getNeedInHours() {
        return needInHours;
    }

    public void setNeedInHours(double needInHours) {
        this.needInHours = needInHours;
    }

    public int getNoOfPeopleWaiting() {
        return noOfPeopleWaiting;
    }

    public void setNoOfPeopleWaiting(int noOfPeopleWaiting) {
        this.noOfPeopleWaiting = noOfPeopleWaiting;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
