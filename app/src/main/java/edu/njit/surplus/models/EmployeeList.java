package edu.njit.surplus.models;

import android.app.Activity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by srini on 11/11/17.
 */

public class EmployeeList {

    @SerializedName("employeeList")
    @Expose
    private ArrayList<Employee> employeeList = null;

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(ArrayList<Employee> employeeList) {
        this.employeeList = employeeList;
    }

}
