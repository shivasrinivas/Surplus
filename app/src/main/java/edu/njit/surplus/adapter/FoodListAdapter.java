package edu.njit.surplus.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.njit.surplus.R;
import edu.njit.surplus.models.FoodSet;
import edu.njit.surplus.utilities.Utilities;

/**
 * Created by srini on 11/12/17.
 */

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.ViewHolder> {

    private List<FoodSet> foodItems = new ArrayList();
    private FoodItemListener listener;

    public FoodListAdapter(Context context, List<FoodSet> foodItems) {
        listener = (FoodItemListener) context;
        this.foodItems = foodItems;
    }

    @Override
    public FoodListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_food_item, parent, false);
        return new FoodListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodListAdapter.ViewHolder holder, final int position) {
        final FoodSet post = foodItems.get(position);
        holder.tvItemName.setText(post.getDescription());
        Date date = Utilities.getDate("");
        holder.tvItemExpiry.setText("Use before" );
        holder.tvItemPrice.setText("$" + post.getPrice());
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFoodItemClicked(post.getId());
            }
        });
        Uri imageUri = Uri.parse(post.getImgUrl());
        holder.draweeView.setImageURI(imageUri);
    }

    @Override
    public int getItemCount() {
        if (foodItems != null) {
            return foodItems.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItemName;
        TextView tvItemExpiry;
        TextView tvItemPrice;

        SimpleDraweeView draweeView;

        View rootView;

        ViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            tvItemName = (TextView) itemView.findViewById(R.id.tv_item_name);
            tvItemExpiry = (TextView) itemView.findViewById(R.id.tv_item_expiry);
            tvItemPrice = (TextView) itemView.findViewById(R.id.tv_item_price);
            draweeView = (SimpleDraweeView) itemView.findViewById(R.id.sdv_image);
        }
    }

    public interface FoodItemListener {
        void onFoodItemClicked(int postId);
    }
}
