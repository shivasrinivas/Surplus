package edu.njit.surplus.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by srini on 11/12/17.
 */

public class Post {

    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("contactNumber")
    @Expose
    private String contactNumber;
    @SerializedName("foodItems")
    @Expose
    private List<FoodItem> foodItems = null;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("imgUrl")
    @Expose
    private String imgUrl;
    @SerializedName("price")
    @Expose
    private double price;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("userId")
    @Expose
    private int userId;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}