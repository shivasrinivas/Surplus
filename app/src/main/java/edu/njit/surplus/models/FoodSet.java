package edu.njit.surplus.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by srini on 11/12/17.
 */

public class FoodSet {
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("imgUrl")
    @Expose
    private String imgUrl;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("userId")
    @Expose
    private Integer userId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
