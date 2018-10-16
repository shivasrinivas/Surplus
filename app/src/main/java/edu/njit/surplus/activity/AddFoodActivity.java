package edu.njit.surplus.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.njit.surplus.R;

/**
 * Created by srini on 11/12/17.
 */

public class AddFoodActivity extends AppCompatActivity {

    private TextView tvAddItem;
    private LinearLayout llFoodItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        llFoodItem = (LinearLayout) findViewById(R.id.ll_food_items);
        tvAddItem = (TextView) findViewById(R.id.btn_add);
        tvAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.layout_add_item, null);
                llFoodItem.addView(linearLayout);
            }
        });
    }


}
